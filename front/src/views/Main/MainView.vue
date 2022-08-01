<template>
  <div class="main-view d-flex flex-column">
    <!-- Navbar -->
    <div class="main-header">
      <main-navbar />
    </div>

    <!-- Container -->
    <div class="main-container d-flex flex-grow-1 flex-shrink-1">
      <!-- Side -->
      <div class="main-side">
        <main-sidebar />
      </div>

      <!-- Contents -->
      <div class="main-contents flex-grow-1">
        <router-view />
      </div>
    </div>
  </div>
</template>

<script>
import MainNavbar from './components/MainNavbar.vue';
import MainSidebar from './components/MainSidebar.vue';
import { onBeforeMount } from 'vue';

export default {
  setup() {
    onBeforeMount(() => {
      // setting
      document.documentElement.style.setProperty('--size-h-header', '48px');
      document.documentElement.style.setProperty('--size-w-side', '80px');
    });
  },

  components: {
    MainNavbar,
    MainSidebar,
  },
};
</script>

<style>
.main-view {
  height: 100vh;
  width: 100vw;

  /* background: var(--sub-color-b); */
  overflow-y: auto;
  overflow-x: auto;

  -ms-overflow-style: none; /* IE, Edge 스크롤 지우는 코드 */
  scrollbar-width: none; /* Firefox 스크롤 지우는 코드 */
}

.main-view::-webkit-scrollbar {
  display: none; /* Chrome, Safari, Opera 스크롤 지우는 코드 */
}

.main-header {
  /* background: var(--sub-color-r); */
  min-height: var(--size-h-header);
}

.main-side {
  /* background: var(--sub-color-o); */
  min-width: var(--size-w-side);
}

.main-contents {
  background: var(--sub-color-o);
}
</style>
