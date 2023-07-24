package com.ssafy.team8alette.member.model.service;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.team8alette.member.exception.DuplicatedMemberException;
import com.ssafy.team8alette.member.exception.InvalidMemberPasswordException;
import com.ssafy.team8alette.member.exception.RegexException;
import com.ssafy.team8alette.member.model.dao.MemberRepository;
import com.ssafy.team8alette.member.model.dto.Member;
import com.ssafy.team8alette.member.util.NullValueChecker;
import com.ssafy.team8alette.member.util.PasswordEncryptor;
import com.ssafy.team8alette.member.util.RegexChecker;

@Service
public class MemberService {

	private final MemberRepository memberRepository;
	private final RegexChecker regexChecker;
	private final PasswordEncryptor passwordEncryptor;
	private final NullValueChecker nullValueChecker;

	@Autowired
	public MemberService(
		MemberRepository memberRepository,
		RegexChecker regexChecker,
		PasswordEncryptor passwordEncryptor,
		NullValueChecker nullValueChecker) {

		this.memberRepository = memberRepository;
		this.regexChecker = regexChecker;
		this.passwordEncryptor = passwordEncryptor;
		this.nullValueChecker = nullValueChecker;
	}

	public Member getMemberInfo(Long memberNumber) {
		return memberRepository.findMemberByMemberNumber(memberNumber);
	}

	public void register(Map<String, String> registerMemberInfo) throws NoSuchAlgorithmException {

		Long memberNumber = Long.parseLong(registerMemberInfo.get("member_number"));
		String memberPassword = registerMemberInfo.get("member_password");
		String memberPasswordRepeat = registerMemberInfo.get("member_password_repeat");
		String memberName = registerMemberInfo.get("member_name");
		String memberNickname = registerMemberInfo.get("member_nickname");

		nullValueChecker.check(
			memberName,
			memberPassword,
			memberPasswordRepeat,
			memberName,
			memberNickname);

		if (memberRepository.findMemberByMemberNickname(registerMemberInfo.get("member_nickname")) != null)
			throw new DuplicatedMemberException();

		if (regexChecker.checkValidationRegisterPassword(memberPassword) != true)
			throw new RegexException("비밀번호 형식이 맞지 않습니다.");

		if (memberPassword.equals(memberPasswordRepeat) != true)
			throw new InvalidMemberPasswordException("비밀번호를 다시 확인해주세요");

		String memberPasswordEncoded = passwordEncryptor.encodePassword(memberPassword);

		Member member = memberRepository.findMemberByMemberNumber(memberNumber);
		member.setMemberName(memberName);
		member.setMemberNickname(memberNickname);
		member.setMemberPassword(memberPasswordEncoded);
		memberRepository.save(member);
	}

	public void deactivate(Long memberNumber) throws SQLException {
		Member member = memberRepository.findMemberByMemberNumber(memberNumber);
		member.setMemberState(2);
		memberRepository.save(member);
	}

	public void changePassword(Long memberNumber, String curPassword, String newPassword,
		String newPasswordRepeat) throws
		NoSuchAlgorithmException {

		Member member = memberRepository.findMemberByMemberNumber(memberNumber);

		nullValueChecker.check(curPassword, newPassword, newPasswordRepeat);

		if (passwordEncryptor.match(curPassword, member.getMemberPassword()) != true)
			throw new InvalidMemberPasswordException("현재 비밀번호가 일치하지 않습니다.");

		if (regexChecker.checkValidationRegisterPassword(newPassword) != true)
			throw new RegexException("비밀번호 형식이 맞지 않습니다.");

		if (newPassword.equals(newPasswordRepeat) != true)
			throw new InvalidMemberPasswordException("변경할 비밀번호가 일치 하지 않습니다.");

		String newPasswordEncoded = passwordEncryptor.encodePassword(newPassword);

		member.setMemberPassword(newPasswordEncoded);
		memberRepository.save(member);
	}

}
