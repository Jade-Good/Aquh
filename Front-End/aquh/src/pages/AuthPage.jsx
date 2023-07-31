import React, { useState, setState } from "react";
import "./AuthPage.css";

function AuthPage() {
  return (
    <div className='authPage'>
      <h3 className='authPageText'>개인정보(수정 전) 모달 창 입니다.</h3>
      <div className='userCharIdLvSym'>
        <div className='userIcon'>
          <img className='userChar' src='' alt='User캐릭터' />
          <div>
            <h4>"유저 아이디"</h4>
            <h6>"유저 레벨"</h6>
          </div>
        </div>
        <div className='userSymbol'>
          <h4>나의 심볼</h4>
          <div>[심볼 목록]</div>
        </div>
      </div>
      <div className='userInfo'>
        <div className='authPageUsername'>
          <h4>닉네임 : </h4>
          <p>"username"</p>
        </div>
        <div className='authPageEmail'>
          <h4>이메일 : </h4>
          <p>asdf@naver.com</p>
        </div>
        <div className='authPagePassword'>
          <button>비밀번호 변경</button>
        </div>
        <div>
          <button>회원탈퇴</button>
        </div>
      </div>
      <button>수정하기</button>
    </div>
  );
}

export default AuthPage;