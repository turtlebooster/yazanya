<template>
  <div class="d-flex flex-column vh-100 home-view">
    <img class="vw-100 h-50" src="@/assets/book.jpg"/>
    <div class="d-flex justify-content-center">

      <div class="d-flex fw-bold fs-6 home">
        <img class="mx-5" style="width:20vw;" src="@/assets/connection_img.png"/>
        <div class="d-flex flex-column justify-content-end">
        <span
          style="color: rgb(76, 205, 151);"
          >야자냐가 처음이세요?</span
        >
        <span>지금도 0명의 유저가</span>
        <span>야자냐와 함께 공부중입니다.</span>
        <span>
          <button style="background-color:var(--main-color);" @click="pageLogin">로그인</button>
          <button style="background-color:var(--light-sub-color);" @click="pageSignup">회원가입</button>
        </span>
        </div>
      </div>

    </div>
  </div>
</template>

<script>
import { useRouter } from 'vue-router';

export default {
  name: 'HomeView',
  setup() {
    let router = useRouter();
    function pageLogin() {
      router.push({ path: '/user/login' });
    }
    function pageSignup() {
      router.push({ path: '/user/signup' });
    }

    return {
      pageLogin,
      pageSignup,
    };
  },
};
</script>

<style scoped>
  .home {
    margin-top: -10%;
  }
  button {
    border-radius: 5px;
    color: white;
    border: transparent;
    width: 100px;
    margin: 30px;
  }
</style>
