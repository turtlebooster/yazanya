<template>
  <div class="account-view d-flex flex-column">
    <!-- Navbar -->
    <div class="account-header">
      <account-navbar />
    </div>

    <div class="flex-grow-1">
      <router-view />
    </div>
  </div>
</template>

<script>
import AccountNavbar from '@/views/Account/components/AccountNavbar.vue';
import { onBeforeMount } from 'vue';

export default {
  setup() {
    onBeforeMount(() => {
      // setting
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
.account-header {
  /* background: var(--sub-color-r); */
  min-height: var(--size-h-header);
}

.account-view {
  height: 100vh;
  width: 100vw;
}
</style>
