<template>
  <div
    id="app"
    class="d-flex flex-column flex-nowrap"
    :class="[$root.theme ? 'light' : 'dark']"
  >
    <router-view />
  </div>
  <b-container
    :toast="{ root: true }"
    fluid="sm"
    position="position-fixed"
    style="top: 50px; left: -200px"
  >
  </b-container>
</template>

<script>
export default {
  setup() {
    // get theme flag from local
    let theme =
      JSON.parse(localStorage.getItem('theme')) == undefined
        ? true
        : JSON.parse(localStorage.getItem('theme'));
    let sidebar = JSON.parse(
      localStorage.getItem('sidebar') == undefined
        ? false
        : JSON.parse(localStorage.getItem('sidebar'))
    );
    sidebar
      ? document.documentElement.style.setProperty(
          '--size-w-side-hover',
          '64px'
        )
      : document.documentElement.style.setProperty(
          '--size-w-side-hover',
          '200px'
        );
    return { theme, sidebar };
  },
};
</script>

<style>
@import url('https://hangeul.pstatic.net/hangeul_static/css/nanum-square-round.css');
@font-face {
  /* 여기어때 잘난체 */
  font-family: 'Jalnan';
  src: url('./assets/font/Jalnan.ttf') format('truetype');
  font-weight: normal;
}

@font-face {
  /* 한버워치체 */
  font-family: 'Koverwatch';
  src: url('./assets/font/Koverwatch.ttf') format('truetype');
  font-weight: normal;
}

@font-face {
  /* 나눔고딕 */
  font-family: 'NanumGothic';
  src: url('./assets/font/NanumGothic.ttf') format('truetype');
  font-weight: normal;
}

:root {
  /* 비율 */
  --size-h-header: 48px;
  --size-w-side: 80px;
  --size-w-side-hover: 64px;

  /* 메인 테마색 */
  --theme-color: #009e73;

  --sub-color-r: #d55e00;
  --sub-color-o: #e69f00;
  --sub-color-y: #f0e442;
  --sub-color-b: #0072b2;
  --sub-color-p: #cc79a7;

  /* 다크모드 */
  --dark-main-color: #404040;
  --dark-sub-color: #000000;
  --dark-reverser-color: #f4f4f4;

  /* 라이트모드 */
  --light-main-color: #ffffff;
  --light-sub-color: #f4f4f4;
  --light-reverse-color: #404040;
}

* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;

  font-family: NanumSquareRoundEB;
}

/* ---- for light and dark mode ---- */
.light * {
  color: #404040;
}

.dark * {
  color: #f4f4f4;
}

.light .main {
  background: #ffffff;
}

.light .sub {
  background: #f4f4f4;
}

.light .reverse {
  background: #404040;
}

.dark .main {
  background: #404040;
}

.dark .sub {
  background: #000000;
}

.dark .reverse {
  background: #f4f4f4;
}

.light .main-color {
  background: #ffffff;
}

.light .sub-color {
  background: #f4f4f4;
}

.light .reverse-color {
  background: #404040;
}

.dark .main-color {
  background: #404040;
}

.dark .sub-color {
  background: #000000;
}

.dark .reverse-color {
  background: #f4f4f4;
}

/* --------------------------------- */

.outer {
  box-shadow: 4px 4px 10px -1px rgba(0, 0, 0, 0.25),
    -4px -4px 10px -1px rgba(255, 255, 255, 0.25);
}

.inner {
  box-shadow: inset 4px 4px 10px -1px rgba(0, 0, 0, 0.25),
    inset -4px -4px 10px -1px rgba(255, 255, 255, 0.25);
}

.liner {
  height: 2px;
  width: 80%;
  border-radius: 1px;
  background: var(--light-sub-color);
}

.spacer {
  height: 24px;
}

#app {
  height: 100vh;
  width: 100vw;
  max-height: 100vh;

  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
}

.app-header {
  /* background: var(--sub-color-r); */
  min-height: var(--size-h-header);
}

.app-container {
  overflow-y: hidden;
}

.app-side {
  /* background: var(--sub-color-o); */
  min-width: var(--size-w-side);
}

.app-contents {
  /* background: var(--sub-color-b); */
  margin-left: 12px;
  overflow-y: auto;
  overflow-x: auto;

  -ms-overflow-style: none; /* IE, Edge 스크롤 지우는 코드 */
  scrollbar-width: none; /* Firefox 스크롤 지우는 코드 */
}

.app-contents::-webkit-scrollbar {
  display: none; /* Chrome, Safari, Opera 스크롤 지우는 코드 */
}
</style>
