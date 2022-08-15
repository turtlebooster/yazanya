<template>
  <div
    class="account-sign d-flex flex-column align-items-center justify-content-center"
  >
    <div class="account-sign-background"></div>

    <div class="spacer"></div>

    <div class="panel outer main-color">
      <div class="form sign-in d-flex flex-column align-items-center">
        <div class="spacer"></div>
        <div class="spacer"></div>

        <h2>로그인</h2>

        <div class="spacer"></div>

        <!-- ID -->
        <div class="search outer main-color d-flex align-items-center">
          <div class="search_icon">
            <i class="bi bi-person-fill"></i>
          </div>
          <input
            class="search_input"
            type="text"
            name="id"
            v-model="id"
            placeholder="아이디"
          />
        </div>

        <div class="spacer"></div>

        <!-- Password -->
        <div class="search outer main-color d-flex align-items-center">
          <div class="search_icon">
            <i class="bi bi-lock-fill"></i>
          </div>
          <input
            class="search_input"
            type="password"
            name="pw"
            v-model="pw"
            placeholder="비밀번호"
          />
        </div>

        <div class="spacer"></div>

        <button class="submit" type="button" @click="login">로그인</button>

        <p class="forgot-pass">비밀번호를 잊으셨나요?</p>
      </div>

      <div class="sub-panel main-color">
        <div class="img">
          <div class="img-text m-up">
            <h2>처음 오셨나요?</h2>
            <p>회원이 되시면 야자냐의 기능들을 사용하실 수 있습니다.</p>
          </div>
          <div class="img-text m-in">
            <h2>이미 회원이신가요?</h2>
            <p>로그인해주세요</p>
          </div>
          <div class="img-btn" @click="change">
            <span class="m-up">회원가입</span>
            <span class="m-in">로그인</span>
          </div>
        </div>

        <div class="form sign-up d-flex flex-column align-items-center">
          <h2>회원가입</h2>

          <div class="spacer"></div>

          <!-- ID -->
          <div class="search outer main-color d-flex align-items-center">
            <div class="search_icon">
              <i class="bi bi-person-fill"></i>
            </div>
            <input
              class="search_input"
              type="text"
              name="id"
              v-model="id"
              placeholder="아이디"
            />
          </div>

          <div class="spacer"></div>

          <!-- Password -->
          <div class="search outer main-color d-flex align-items-center">
            <div class="search_icon">
              <i class="bi bi-lock-fill"></i>
            </div>
            <input
              class="search_input"
              type="password"
              name="pw"
              v-model="pw"
              placeholder="비밀번호"
            />
          </div>

          <div class="spacer"></div>

          <!-- E-mail -->
          <div class="search outer main-color d-flex align-items-center">
            <div class="search_icon">
              <i class="bi bi-envelope-fill"></i>
            </div>
            <input
              class="search_input"
              type="email"
              name="email"
              v-model="email"
              placeholder="이메일"
            />
            <div
              style="
                width: 100px;
                background: -webkit-linear-gradient(left, #7579ff, #b224ef);
                text-align: center;
                border-radius: 50px;
              "
              @click="emailCheck"
            >
              인증
            </div>
          </div>

          <div class="spacer"></div>

          <!-- E-mail Code -->
          <div
            v-if="hasCode"
            class="search outer main-color d-flex align-items-center"
          >
            <div class="search_icon">
              <i class="bi bi-key-fill"></i>
            </div>
            <input
              class="search_input"
              type="code"
              name="code"
              v-model="code"
              placeholder="이메일 코드"
            />
            <div
              style="
                width: 100px;
                background: -webkit-linear-gradient(left, #7579ff, #b224ef);
                text-align: center;
                border-radius: 50px;
              "
              @click="codeCheck"
            >
              확인
            </div>
          </div>

          <div class="spacer"></div>

          <!-- Name -->
          <div class="search outer main-color d-flex align-items-center">
            <div class="search_icon">
              <i class="bi bi-emoji-smile-fill"></i>
            </div>
            <input
              class="search_input"
              type="name"
              name="name"
              v-model="name"
              placeholder="이름"
            />
          </div>

          <div class="spacer"></div>

          <!-- NickName -->
          <div class="search outer main-color d-flex align-items-center">
            <div class="search_icon">
              <i class="bi bi-chat-dots-fill"></i>
            </div>
            <input
              class="search_input"
              type="nickname"
              name="nickname"
              v-model="nickname"
              placeholder="닉네임"
            />
          </div>

          <button type="button" class="submit" @click="signup">회원가입</button>
        </div>
      </div>
    </div>

    <div class="spacer"></div>
  </div>
</template>

<script>
import { ref } from 'vue';
import { useStore } from 'vuex';
import { useRouter } from 'vue-router';

import rest_user from '@/rest/user';

export default {
  setup() {
    const store = useStore();
    const router = useRouter();

    let id = ref('');
    let pw = ref('');
    let email = ref('');
    let code = ref('');

    let name = ref('');
    let nickname = ref('');

    let hasCode = ref(false);
    let isConfirm = ref(false);
    let isMove = ref(false);

    async function login() {
      if (isMove.value) return;
      try {
        let tokens = await rest_user.login({ id: id.value, pw: pw.value });
        await store.dispatch('login', {
          'access-token': tokens.access_TOKEN,
          'refresh-token': tokens.refresh_TOKEN,
          'id': id.value,
        });

        console.log(store.state.Account.nextRoom);
        if (store.state.Account.nextRoom != '') {
          // move to room
          router.replace(store.state.Account.nextRoom);
        } else {
          router.replace('/main');
        }
      } catch (error) {
        console.error(error);
        alert(error);
      }
    }

    function signup() {
      // TODO : 빈칸 체크, 입력값 검증, ID, EMAIL, PW, 중복 검사

      if (isMove.value) return;

      if (id.value == '') return alert('아이디를 기입해주세요.');
      if (pw.value == '') return alert('비밀번호를 기입해주세요.');
      if (email.value == '') return alert('이메일을 기입해주세요.');
      if (!isConfirm.value) return alert('이메일 인증을 진행해주세요.');
      if (name.value == '') return alert('이름을 기입해주세요');
      if (nickname.value == '') return alert('닉네임을 기입해주세요');

      rest_user
        .signUp({
          id: id.value,
          pw: pw.value,
          email: email.value,
          name: name.value,
          nickname: nickname.value,
        })
        .then((response) => {
          console.log(response);

          if (response.data === 'success') {
            change();
          } else {
            alert(
              '회원가입 중 문제가 발생하였습니다. 나중에 다시 시도해주세요'
            );
          }
        })
        .catch((error) => {
          console.log(error);
          alert('회원가입 중 문제가 발생하였습니다. 나중에 다시 시도해주세요');
        });
    }

    async function change() {
      isMove.value = true;
      document.querySelector('.account-sign').classList.toggle('s-signup');
      setTimeout(() => {
        isMove.value = false;
      }, 1200);
    }

    function emailCheck() {
      // TODO : 빈칸 체크, 입력값 검증, ID, EMAIL, PW, 중복 검사
      hasCode.value = true;
      rest_user
        .confirmEmail(email.value)
        .then((response) => {
          if (response === 'alreadyRegistEmail') {
            alert(
              '이메일 인증번호가 이미 발송되었습니다. 이메일을 확인해주세요.'
            );
          } else {
            alert('이메일 인증번호를 발송하였습니다.');
          }
        })
        .catch((error) => {
          console.log(error);
          alert(
            '이메일 인증 중 문제가 발생하였습니다. 나중에 다시 시도해주세요'
          );
        });
    }

    function codeCheck() {
      // TODO : 빈칸 체크, 입력값 검증, ID, EMAIL, PW, 중복 검사
      hasCode.value = true;
      rest_user
        .confirmCode({ email: email.value, code: code.value })
        .then((response) => {
          if (response === 'success') {
            alert('이메일이 인증되었습니다.');
            isConfirm.value = true;
          } else {
            alert('코드나, 이메일이 잘못되었습니다.');
          }
        })
        .catch((error) => {
          console.log(error);
          alert('회원가입 중 문제가 발생하였습니다. 나중에 다시 시도해주세요');
        });
    }

    return {
      hasCode,
      code,
      id,
      pw,
      email,
      name,
      nickname,
      login,
      signup,
      change,
      emailCheck,
      codeCheck,
    };
  },
};
</script>

<style scoped>
.account-sign {
  position: relative;
  z-index: 1;
  height: 100%;
  width: 100%;
  background: linear-gradient(-45deg, #8d42ff, #01e973);
  background-size: cover;
}

.account-sign-background {
  position: fixed;
  top: 0;
  left: 0;
  bottom: 0;
  right: 0;
  height: 100%;
  width: 100%;
  background: linear-gradient(135deg, #26bbac, #bcfb69);
  opacity: 1;
  -webkit-transition: opacity 1.2s ease-in-out;
  -ms-transition: opacity 1.2s ease-in-out;
  transition: opacity 1.2s ease-in-out;
}

.account-sign.s-signup .account-sign-background {
  opacity: 0;
}

input,
button {
  border: none;
  outline: none;
  background: none;
}

.panel {
  overflow-x: hidden;
  position: relative;
  width: 90vw;
  max-width: 900px;
  min-height: 600px;
  border-radius: 40px;
}

.form {
  position: relative;
  width: 70%;

  padding: 50px 30px;

  -webkit-transition: -webkit-transform 1.2s ease-in-out;
  transition: -webkit-transform 1.2s ease-in-out;
  transition: transform 1.2s ease-in-out;
  transition: transform 1.2s ease-in-out, -webkit-transform 1.2s ease-in-out;
}

.search {
  height: 40px;
  width: 100%;
  max-width: 400px;
  flex-wrap: nowrap;
  border-radius: 24px;
  padding: 10px;
}

.search_input {
  width: 100%;
  height: 32px;
  border: 0;
  outline: 0;
  background: none;

  padding-left: 8px;
  text-align: left;

  flex-grow: 1;
  flex-shrink: 1;
  caret-color: var(--theme-color);
  overflow: hidden;
}

.search:hover > .search_icon {
  background: var(--theme-color);
  color: white;
}

.search:hover > .search_icon {
  background: var(--theme-color);
  color: white;
}

.search_icon {
  min-height: 28px;
  min-width: 28px;
  float: right;
  display: flex;
  justify-content: center;
  align-items: center;
  border-radius: 50%;
  background: black;
  color: white;
}

input {
  display: block;
  width: 100%;
  margin-top: 5px;
  font-size: 16px;
  padding-bottom: 5px;
  border-bottom: 1px solid rgba(109, 93, 93, 0.4);
  text-align: center;
  font-family: 'Nunito', sans-serif;
}

.submit {
  display: block;
  margin: 0 auto;
  width: 100%;
  max-width: 400px;
  height: 36px;
  border-radius: 30px;
  color: #fff;
  font-size: 15px;
  cursor: pointer;
  margin-top: 40px;
  margin-bottom: 30px;
  text-transform: uppercase;
  font-weight: 600;
  font-family: 'Nunito', sans-serif;
  background: -webkit-linear-gradient(left, #7579ff, #b224ef);
}

.forgot-pass:hover {
  color: red;
}

.sub-panel {
  overflow: hidden;
  position: absolute;
  left: 70%;
  top: 0;
  width: 90vw;
  max-width: 900px;
  height: 100%;
  padding-left: 30%;
  -webkit-transition: -webkit-transform 1.2s ease-in-out;
  transition: -webkit-transform 1.2s ease-in-out;
  transition: transform 1.2s ease-in-out;
}

.account-sign.s-signup .panel .sub-panel {
  -webkit-transform: translate3d(-70%, 0, 0);
  transform: translate3d(-70%, 0, 0);
}

.img {
  overflow: hidden;
  z-index: 2;
  position: absolute;
  left: 0;
  top: 0;
  width: 30%;
  height: 100%;
  padding-top: 360px;
}

.img:before {
  content: '';
  position: absolute;
  right: 0;
  top: 0;
  width: 90vw;
  max-width: 900px;
  height: 100%;
  background-image: url(@/assets/thumbnail/0.jpg);
  background-size: cover;
  transition: -webkit-transform 1.2s ease-in-out;
  transition: transform 1.2s ease-in-out, -webkit-transform 1.2s ease-in-out;
}

.img:after {
  content: '';
  position: absolute;
  left: 0;
  top: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.3);
}

.account-sign.s-signup .panel .img:before {
  -webkit-transform: translate3d(70%, 0, 0);
  transform: translate3d(70%, 0, 0);
}

.img-text {
  z-index: 2;
  position: absolute;
  left: 0;
  top: 50px;
  width: 100%;
  padding: 0 20px;
  text-align: center;
  color: #fff;
  -webkit-transition: -webkit-transform 1.2s ease-in-out;
  transition: -webkit-transform 1.2s ease-in-out;
  transition: transform 1.2s ease-in-out, -webkit-transform 1.2s ease-in-out;
}

.img-text h2 {
  margin-bottom: 10px;
  font-weight: normal;
}

.img-text p {
  font-size: 14px;
  line-height: 1.5;
}

.account-sign.s-signup .panel .img-text.m-up {
  -webkit-transform: translateX(520px);
  transform: translateX(520px);
}

.img-text.m-in {
  -webkit-transform: translateX(-520px);
  transform: translateX(-520px);
}

.account-sign.s-signup .panel .img-text.m-in {
  -webkit-transform: translateX(0);
  transform: translateX(0);
}

.sign-in {
  padding-top: 65px;
  -webkit-transition-timing-function: ease-out;
  transition-timing-function: ease-out;
}

.account-sign.s-signup .panel .sign-in {
  -webkit-transition-timing-function: ease-in-out;
  transition-timing-function: ease-in-out;
  -webkit-transition-duration: 1.2s;
  transition-duration: 1.2s;
  -webkit-transform: translate3d(70%, 0, 0);
  transform: translate3d(70%, 0, 0);
}

.img-btn {
  overflow: hidden;
  z-index: 2;
  position: relative;
  width: 100px;
  height: 36px;
  margin: 0 auto;
  background: transparent;
  color: #fff;
  text-transform: uppercase;
  font-size: 15px;
  cursor: pointer;
}

.img-btn:after {
  content: '';
  z-index: 2;
  position: absolute;
  left: 0;
  top: 0;
  width: 100%;
  height: 100%;
  border: 2px solid #fff;
  border-radius: 30px;
}

.img-btn span {
  position: absolute;
  left: 0;
  top: 0;
  display: -webkit-box;
  display: flex;
  -webkit-box-pack: center;
  justify-content: center;
  align-items: center;
  width: 100%;
  height: 100%;
  -webkit-transition: -webkit-transform 1.2s;
  transition: -webkit-transform 1.2s;
  transition: transform 1.2s;
  transition: transform 1.2s, -webkit-transform 1.2s;
}

.img-btn span.m-in {
  -webkit-transform: translateY(-72px);
  transform: translateY(-72px);
}

.account-sign.s-signup .panel .img-btn span.m-in {
  -webkit-transform: translateY(0);
  transform: translateY(0);
}

.account-sign.s-signup .panel .img-btn span.m-up {
  -webkit-transform: translateY(72px);
  transform: translateY(72px);
}

.sign-up {
  width: 100%;

  padding: 50px 30px;
  -webkit-transform: translate3d(-70%, 0, 0);
  transform: translate3d(-70%, 0, 0);
}

.account-sign.s-signup .panel .sign-up {
  -webkit-transform: translate3d(0, 0, 0);
  transform: translate3d(0, 0, 0);
}
</style>
