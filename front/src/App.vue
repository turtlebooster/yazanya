<!-- Comment -->

<template>
  <div id="app">
    <navi :viewname="$route" />
    <router-view />
  </div>
</template>

<script>
import Navi from '@/views/common/components/MainNav.vue';

export default {
  setup() {
    // get theme flag from local
    let theme = JSON.parse(localStorage.getItem('yaza_theme')) || true;
    return { theme };
  },

  components: {
    Navi,
  },
};
</script>

<style>
:root {
  /* 메인 테마색 */
  --main-color: #009e73;

  --sub-color-r: #d55e00;
  --sub-color-o: #e69f00;
  --sub-color-y: #f0e442;
  --sub-color-b: #0072b2;
  --sub-color-p: #cc79a7;

  /* 각 모드 배경색 */
  --dark-bg-color: #404040;
  --light-bg-color: #ffffff;
}

* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

/* ---- for light and dark mode ---- */
.light {
  background: #ffffff;
}

.dark {
  background: #404040;
}
/* --------------------------------- */

#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  /* text-align: center; */
  color: #2c3e50;
  /* margin-top: 60px; */
}
</style>
