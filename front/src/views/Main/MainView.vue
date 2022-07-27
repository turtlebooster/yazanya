<template>
  <div class="main-view">
    <div class="main-container">
      <side-bar />
      <router-view class="router-view" />
    </div>
  </div>
</template>

<script>
import SideBar from '@/views/common/components/SideBar.vue';

export default {
  components: {
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
