<template>
  <nav class="home-navbar">
    <!-- Main Nav -->

    <router-link to="/main">
      <img
        src="@/assets/logo/title_logo_ffffff.png"
        class="align-bottom"
        style="margin-left: 16px; height: var(--size-h-header)"
        alt="logo"
      />
    </router-link>

    <ul>
      <li><router-link to="/account/sign"> 로그인 </router-link></li>
      <li><router-link to="/account/sign"> 회원가입 </router-link></li>
      <li><router-link to="/account/sign"> 로그인 </router-link></li>
      <li><router-link to="/account/sign"> Contact </router-link></li>
    </ul>

    <!-- <div class="flex-grow-1"></div>

      <router-link to="/account/login">
        <b-button pill variant="success" class="button">로그인</b-button>
      </router-link>
      <router-link to="/account/signup">
        <b-button pill variant="outline-danger" class="button">Button</b-button>
      </router-link> -->
  </nav>
</template>

<script>
export default {};
</script>

<style scoped>
* {
  margin: 0;
}

.home-navbar {
  height: var(--size-h-header);
  width: 100vw;
  position: fixed;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.home-navbar ul {
  display: flex;
  justify-content: center;
  align-items: center;
}

.home-navbar ul li {
  list-style: none;
  margin-right: 16px;
}

.home-navbar ul li a {
  padding: 6px 15px;
  text-decoration: none;
  color: white;
  border-radius: 20px;
}

.home-navbar ul li a:hover {
  background: #ffffff;
  color: #2b1055;
}
</style>
