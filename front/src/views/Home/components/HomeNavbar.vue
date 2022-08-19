<template>
  <nav class="home-navbar main-color d-flex align-items-center">
    <router-link to="/main">
      <!-- Main Nav -->
      <img
        src="@/assets/logo/title_logo_009e73.png"
        class="align-bottom"
        style="margin-left: 16px; height: var(--size-h-header)"
        alt="logo"
      />
    </router-link>
    <div class="flex-grow-1"></div>
    <router-link to="/account/login" style="text-decoration: none">
      <div class="btn-login">시작하기</div>
    </router-link>
  </nav>
</template>

<script>
export default {};
</script>

<style>
.btn-login {
  margin-right: 16px;
  height: calc(var(--size-h-header) - 12px);
  width: 120px;
  border-radius: var(--size-h-header);
  color: white;
  text-align: center;
  line-height: calc(var(--size-h-header) - 12px);
  font-size: 12pt;
  font-weight: bold;
  font-family: 'Nunito', sans-serif;
  background: var(--theme-color);
}

.home-navbar {
  z-index: 20;
  position: fixed;
  width: 100vw;
  border-bottom: 4px solid var(--theme-color);
  height: var(--size-h-header);
}
</style>
