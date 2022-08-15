<template>
  <div class="account-view">
    <!-- Navbar -->
    <div class="main-header">
      <account-navbar />
    </div>

    <router-view />
  </div>
</template>

<script>
import AccountNavbar from '@/views/Account/components/AccountNavbar.vue';
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
    AccountNavbar,
  },
};
</script>

<style scoped>
* {
  font-family: NanumSquareRoundB;
}
.main-header {
  /* background: var(--sub-color-r); */
  min-height: var(--size-h-header);
}

.account-view {
  height: 100vh;
  width: 100vw;
}
</style>
