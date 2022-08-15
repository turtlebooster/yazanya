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
      <div
        style="
          margin-right: 16px;
          background: var(--theme-color);
          height: calc(var(--size-h-header) - 12px);
          width: 100px;
          border-radius: var(--size-h-header);
          color: white;
          text-align: center;
          line-height: calc(var(--size-h-header) - 12px);
          font-size: 14pt;
        "
      >
        시작하기
      </div>
    </router-link>
  </nav>
</template>

<script>
export default {};
</script>

<style>
.home-navbar {
  z-index: 20;
  position: fixed;
  width: 100vw;
  border-bottom: 4px solid var(--theme-color);
  height: var(--size-h-header);
}
</style>
