<template>
  <div class="home-view main d-flex flex-column vh-100">
    <div class="home-header">
      <home-navbar />
    </div>

    <div class="home-container d-flex flex-grow-1 flex-shrink-1">
      <!-- Contents -->
      <router-view />
    </div>

    <div class="home-footer">
      <home-footer />
    </div>
  </div>
</template>

<script>
import HomeNavbar from './components/HomeNavbar.vue';
import HomeFooter from './components/HomeFooter.vue';
import { useRouter } from 'vue-router';
import { onBeforeMount } from 'vue';

export default {
  name: 'HomeView',
  components: {
    HomeNavbar,
    HomeFooter,
  },
  setup() {
    let router = useRouter();
    function pageLogin() {
      router.push({ path: '/account/login' });
    }
    function pageSignup() {
      router.push({ path: '/account/signup' });
    }

    onBeforeMount(() => {
      // setting
      document.documentElement.style.setProperty('--size-h-header', '40px');
      document.documentElement.style.setProperty('--size-w-side', '64px');
    });

    return {
      pageLogin,
      pageSignup,
    };
  },
};
</script>

<style>
.home-view {
  height: 100vh;
  width: 100vw;

  /* background: var(--sub-color-b); */
  overflow-y: auto;
  overflow-x: auto;

  -ms-overflow-style: none; /* IE, Edge 스크롤 지우는 코드 */
  scrollbar-width: none; /* Firefox 스크롤 지우는 코드 */
}

.home-view::-webkit-scrollbar {
  display: none; /* Chrome, Safari, Opera 스크롤 지우는 코드 */
}

.home-header {
  /* background: var(--sub-color-r); */
  /* visibility: hidden; */
  min-height: 52px;
}

.home-container {
  visibility: auto;
}

.home-footer {
  /* background: var(--sub-color-r); */
  /* visibility: hidden; */
  min-height: 52px;
}
</style>
