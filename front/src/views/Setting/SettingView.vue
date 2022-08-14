<template>
  <div class="setting-view main">
    <div class="app-header">
      <!-- <header-nav style="display: none" /> -->
      <header-nav />
    </div>

    <!-- Container -->
    <div class="app-container d-flex flex-grow-1 flex-shrink-1">
      <!-- Side -->
      <div class="app-side">
        <!-- <side-bar style="display: none" /> -->
        <side-bar />
      </div>

      <!-- Contents -->
      <div class="app-contents flex-grow-1">
        <router-view />
      </div>
    </div>
  </div>
</template>

<script>
import HeaderNav from '../Main/components/MainNavbar.vue';
import SideBar from '../Main/components/MainSidebar.vue';
import { onBeforeMount } from 'vue';
export default {
  components: {
    HeaderNav,
    SideBar,
  },
  setup() {
    onBeforeMount(() => {
      // setting
      document.documentElement.style.setProperty('--size-h-header', '40px');
      document.documentElement.style.setProperty('--size-w-side', '64px');
    });
  },
};
</script>

<style>
.setting-view {
  height: 100vh;
}
</style>
