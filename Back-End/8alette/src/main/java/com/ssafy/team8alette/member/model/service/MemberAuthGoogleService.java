package com.ssafy.team8alette.member.model.service;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import com.ssafy.team8alette.member.exception.MemberDuplicatedException;
import com.ssafy.team8alette.member.exception.UnAuthorizedException;
import com.ssafy.team8alette.member.model.dao.MemberLoginInfoRepository;
import com.ssafy.team8alette.member.model.dao.MemberRepository;
import com.ssafy.team8alette.member.model.dto.Member;
import com.ssafy.team8alette.member.model.dto.MemberType;
import com.ssafy.team8alette.member.util.NullValueChecker;
import com.ssafy.team8alette.member.util.PasswordUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberAuthGoogleService {

	private final MemberRepository memberRepository;
	private final PasswordUtil passwordUtil;
	private final MemberLoginInfoRepository memberLoginInfoRepository;
	private final NullValueChecker nullValueChecker;

	public void login(Long memberNumber, String refreshToken) throws SQLException {

		if (memberNumber == -1) {
			throw new MemberDuplicatedException("회원이 존재하지 않습니다");
		}
		memberLoginInfoRepository.insertMemberLoginInfo(memberNumber, refreshToken, true);
	}

	public Long register(JSONObject naverMemberInfo) throws NoSuchAlgorithmException {

		String memberId = naverMemberInfo.get("sub").toString();
		String memberEmail = naverMemberInfo.get("email").toString();
		String memberNickname = naverMemberInfo.get("name").toString();
		String memberName = naverMemberInfo.get("name").toString();

		Member member = memberRepository.findMemberByMemberId(memberId);

		if (member != null) {

			if (member.getMemberState() == 2) {
				throw new UnAuthorizedException("이미 탈퇴한 회원입니다");
			} else {
				return member.getMemberNumber();
			}
		}

		nullValueChecker.check(
			memberEmail,
			memberId,
			memberName,
			memberNickname);

		Member newMember = new Member();

		newMember.setMemberId(memberId);
		newMember.setMemberEmail(memberEmail);
		newMember.setMemberPassword(passwordUtil.encodePassword(passwordUtil.getRandomPassword()));
		newMember.setMemberNickname("G_" + memberNickname);
		newMember.setMemberName(memberName);
		newMember.setMemberState(1);
		newMember.setMemberType(MemberType.GO);
		newMember.setEmailVerified(true);
		newMember.setEmailReceive(true);
		memberRepository.save(newMember);

		return memberRepository.findMemberByMemberId(memberId).getMemberNumber();
	}
}
