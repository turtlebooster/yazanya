<template>
  <div
    class="account-sign d-flex flex-column align-items-center justify-content-center"
  >
    <div class="account-sign-background"></div>

    <div class="panel outer main">
      <div class="form sign-in">
        <h2>로그인</h2>
        <label>
          <span>ID</span>
          <input type="id" name="id" v-model="id" />
        </label>
        <label>
          <span>Password</span>
          <input type="password" name="pw" v-model="pw" />
        </label>
        <button class="submit" type="button" @click="login">로그인</button>
        <p class="forgot-pass">비밀번호를 잊으셨나요 ?</p>

        <!-- 나중에 생각해볼 기능 -->
        <!-- <div class="social-media">
        <ul>
          <li><img src="images/facebook.png" /></li>
          <li><img src="images/twitter.png" /></li>
          <li><img src="images/linkedin.png" /></li>
          <li><img src="images/instagram.png" /></li>
        </ul>
      </div> -->
      </div>

      <div class="sub-panel main">
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

        <div class="form sign-up">
          <h2>회원가입</h2>
          <label>
            <span>ID</span>
            <input type="id" name="id" v-model="id" />
          </label>
          <label>
            <span>Password</span>
            <input type="password" name="pw" v-model="pw" />
          </label>
          <label>
            <span>E-mail</span>
            <input type="email" name="email" v-model="email" />
          </label>
          <label>
            <span>Name</span>
            <input type="name" name="name" v-model="name" />
          </label>
          <label>
            <span>Nickname</span>
            <input type="nickname" name="nickname" v-model="nickname" />
          </label>

          <button type="button" class="submit" @click="signup">회원가입</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { ref, onBeforeMount } from 'vue';
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
    let name = ref('');
    let nickname = ref('');

    async function login() {
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

    function change() {
      document.querySelector('.account-sign').classList.toggle('s-signup');
    }

    onBeforeMount(() => {
      document.documentElement.style.setProperty('--size-h-header', '40px');
    });

    return {
      id,
      pw,
      email,
      name,
      nickname,
      login,
      signup,
      change,
    };
  },
};
</script>

<style scoped>
.account-sign {
  position: relative;
  z-index: 1;
  height: 100vh;
  width: 100vw;
  background: linear-gradient(-45deg, #8d42ff, #01e973);
}

.account-sign-background {
  position: fixed;
  height: 100vh;
  width: 100vw;
  background: linear-gradient(-45deg, #26bbac, #bcfb69);
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
  overflow: hidden;
  position: relative;
  width: 900px;
  height: 550px;
  border-radius: 40px;
}

.form {
  position: relative;
  width: 640px;
  height: 100%;
  padding: 50px 30px;
  -webkit-transition: -webkit-transform 1.2s ease-in-out;
  transition: -webkit-transform 1.2s ease-in-out;
  transition: transform 1.2s ease-in-out;
  transition: transform 1.2s ease-in-out, -webkit-transform 1.2s ease-in-out;
}

h2 {
  width: 100%;
  font-size: 30px;
  text-align: center;
}

label {
  display: block;
  width: 260px;
  margin: 25px auto 0;
  text-align: center;
}

label span {
  font-size: 14px;
  font-weight: 600;
  color: #505f75;
  text-transform: uppercase;
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

button {
  display: block;
  margin: 0 auto;
  width: 260px;
  height: 36px;
  border-radius: 30px;
  color: #fff;
  font-size: 15px;
  cursor: pointer;
}

.submit {
  margin-top: 40px;
  margin-bottom: 30px;
  text-transform: uppercase;
  font-weight: 600;
  font-family: 'Nunito', sans-serif;
  background: -webkit-linear-gradient(left, #7579ff, #b224ef);
}

.forgot-pass {
  margin-top: 15px;
  text-align: center;
  font-size: 14px;
  font-weight: 600;
  color: #0c0101;
  cursor: pointer;
}

.forgot-pass:hover {
  color: red;
}

.social-media {
  width: 100%;
  text-align: center;
  margin-top: 20px;
}

.social-media ul {
  list-style: none;
}

.social-media ul li {
  display: inline-block;
  cursor: pointer;
  margin: 25px 15px;
}

.social-media img {
  width: 40px;
  height: 40px;
}

.sub-panel {
  overflow: hidden;
  position: absolute;
  left: 640px;
  top: 0;
  width: 900px;
  height: 100%;
  padding-left: 260px;
  -webkit-transition: -webkit-transform 1.2s ease-in-out;
  transition: -webkit-transform 1.2s ease-in-out;
  transition: transform 1.2s ease-in-out;
}

.account-sign.s-signup .panel .sub-panel {
  -webkit-transform: translate3d(-640px, 0, 0);
  transform: translate3d(-640px, 0, 0);
}

.img {
  overflow: hidden;
  z-index: 2;
  position: absolute;
  left: 0;
  top: 0;
  width: 260px;
  height: 100%;
  padding-top: 360px;
}

.img:before {
  content: '';
  position: absolute;
  right: 0;
  top: 0;
  width: 900px;
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
  -webkit-transform: translate3d(640px, 0, 0);
  transform: translate3d(640px, 0, 0);
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
  -webkit-transform: translate3d(640px, 0, 0);
  transform: translate3d(640px, 0, 0);
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
  padding: 0;
  -webkit-transform: translate3d(-900px, 0, 0);
  transform: translate3d(-900px, 0, 0);
}

.account-sign.s-signup .panel .sign-up {
  -webkit-transform: translate3d(0, 0, 0);
  transform: translate3d(0, 0, 0);
}
</style>
