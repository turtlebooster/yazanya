<template>
  <div class="main-view">
    <!-- Header -->
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
import HeaderNav from './components/MainNavbar.vue';
import SideBar from './components/MainSidebar.vue';

export default {
  components: {
    HeaderNav,
    SideBar,
  },
};
</script>

<style></style>
