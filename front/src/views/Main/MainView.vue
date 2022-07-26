<template>
  <div class="main-view">
    <header-nav />
    <div style="main-header-blank"></div>
    <div class="main-container">
      <side-bar />
      <div class="main-side-blank" style="min-width: 104px"></div>
      <router-view class="router-view" />
    </div>
  </div>
</template>

<script>
import HeaderNav from '@/views/common/components/HeaderNav.vue';
import SideBar from '@/views/common/components/SideBar.vue';

export default {
  components: {
    HeaderNav,
    SideBar,
  },
};
</script>

<style>
.main-view {
  width: 100vw;
  height: 100vh;

  display: flex;
  flex-direction: column;
  height: 100vh;
}

.main-container {
  flex-grow: 1;
  display: flex;
}

.main-header-blank {
  background: red;
}

.router-view {
  flex-grow: 1;
}
</style>
