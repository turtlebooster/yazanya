<template>
  <div class="home-view main d-flex flex-column vh-100">
    <div class="home-header">
      <home-navbar />
    </div>

    <div class="home-container d-flex flex-grow-1 flex-shrink-1">
      <!-- Contents -->
      <div class="home-contents flex-grow-1">
        <router-view />
      </div>
    </div>
  </div>
</template>

<script>
import HomeNavbar from './components/HomeNavbar.vue';
import { useRouter } from 'vue-router';
import { onBeforeMount } from 'vue';

export default {
  name: 'HomeView',
  components: {
    HomeNavbar,
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

<style scoped>
* {
  font-family: Koverwatch !important;
}

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
  min-height: var(--size-h-header);
}

.home-contents {
  /* background: var(--sub-color-o); */
  /* visibility: hidden; */
}
</style>
