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

        <h2>비밀번호 찾기</h2>

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
            placeholder="아이디를 입력해주세요"
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
            type="text"
            name="email"
            v-model="email"
            placeholder="이메일을 입력해주세요"
          />
        </div>

        <div class="spacer"></div>

        <button class="submit" type="button" @click="findPw">
          임시 비밀번호 발급
        </button>

        <router-link to="login">
          <p class="forgot-pass">로그인하러 가기</p>
        </router-link>
      </div>

      <div class="sub-panel main-color">
        <div class="img" @click="change">
          <div class="img-text m-up" style="color: red">
            <h2>아이디를 잊으셨나요?</h2>
            <p>이 곳을 클릭하면 아이디를 조회할 수 있습니다.</p>
          </div>
          <div class="img-text m-in">
            <h2>비밀번호를 잊으셨나요?</h2>
            <p>가입하신 이메일로 임시 비밀번호를 발급해 드립니다.</p>
          </div>
          <div class="img-btn">
            <span class="m-up">아이디 찾기</span>
            <span class="m-in">비밀번호 찾기</span>
          </div>
        </div>

        <div class="form sign-up d-flex flex-column align-items-center">
          <div class="spacer"></div>
          <div class="spacer"></div>
          <div class="spacer"></div>
          <div class="spacer"></div>
          <h2>아이디 찾기</h2>

          <div class="spacer"></div>

          <!-- E-mail -->
          <div class="search outer main-color d-flex align-items-center">
            <div class="search_icon">
              <i class="bi bi-envelope-fill"></i>
            </div>
            <input
              class="search_input"
              type="text"
              name="email"
              v-model="email"
              placeholder="이메일을 입력해주세요"
            />
          </div>

          <div class="spacer"></div>

          <div class="spacer"></div>
          <div v-if="hasId">당신의 ID는 {{ id }} 입니다.</div>
          <div v-if="hasId" class="spacer"></div>

          <button type="button" class="submit" @click="findId">
            아이디 조회
          </button>
        </div>
      </div>
    </div>

    <div class="spacer"></div>
  </div>
</template>

<script>
import { ref } from 'vue';
import Swal from 'sweetalert2';
import rest_user from '@/rest/user';

export default {
  setup() {
    let id = ref('');
    let email = ref('');

    let hasId = ref(false);
    let isMove = ref(false);

    // for alert
    function warn(message) {
      Swal.fire({
          icon: 'warning',
          title: message,
          timer: 2200,
      });
    }

    async function findId() {
      if (isMove.value) return;

      rest_user
        .findId(email.value)
        .then((response) => {
          if (response == 'fail') {
            hasId.value = false;
            warn('회원이 아닙니다');
            return;
          }
          id.value = response;
          hasId.value = true;
        })
        .catch((error) => {
          console.log(error);
          warn('다시 시도해 주세요.');
        });
    }

    async function findPw() {
      if (isMove.value) return;

      rest_user
        .findPw({ id: id.value, email: email.value })
        .then((response) => {
          warn(response.data);
          return;

          // id.value = response;
          // hasId.value = true;
        })
        .catch((error) => {
          console.log(error);
          warn('다시 시도해 주세요.');
        });
    }

    async function change() {
      isMove.value = true;
      document.querySelector('.account-sign').classList.toggle('s-signup');
      setTimeout(() => {
        isMove.value = false;
      }, 1200);
    }

    return {
      id,
      email,
      hasId,
      findId,
      findPw,
      change,
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

  background-image: linear-gradient(
    -45deg,
    #35c3f3 0%,
    #8b9fe8 20%,
    #e681d8 39%,
    #ffa9a4 76%,
    #fed2ce 100%
  );
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
  background-image: linear-gradient(135deg, #f984f7, #03fae5);
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
}

.search_icon * {
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

  -webkit-transition: -webkit-transform 1.2s ease-in-out;
  transition: -webkit-transform 1.2s ease-in-out;
  transition: transform 1.2s ease-in-out, -webkit-transform 1.2s ease-in-out;
}

.img * {
  color: #fff !important;
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
