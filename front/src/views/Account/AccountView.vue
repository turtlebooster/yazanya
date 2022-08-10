<template>
  <div class="account-view">
    <!-- Navbar -->
    <div class="main-header">
      <main-navbar />
    </div>

    <router-view />
  </div>
</template>

<script>
import MainNavbar from '@/views/Main/components/MainNavbar.vue';
import { onBeforeMount } from 'vue';

export default {
  setup() {
    onBeforeMount(() => {
      // setting
      console.log('작동함');
      document.documentElement.style.setProperty('--size-h-header', '40px');
      document.documentElement.style.setProperty('--size-w-side', '64px');
    });
  },

  components: {
    MainNavbar,
  },
};
</script>

<style scoped>
.main-header {
  /* background: var(--sub-color-r); */
  min-height: var(--size-h-header);
}

.account-view {
  height: 100vh;
  width: 100vw;
}
</style>
