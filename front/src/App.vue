<template>
  <div id="app" class="d-flex flex-column flex-nowrap">
    <router-view />
  </div>
</template>

<script>
export default {};
</script>

<style>
@font-face {
  /* 여기어때 잘난체 */
  font-family: 'Jalnan';
  src: url('@/assets/font/Jalnan.ttf') format('truetype');
  font-weight: normal;

  /* 한버워치체 */
  font-family: 'Koverwatch';
  src: url('@/assets/font/Koverwatch.ttf') format('truetype');
  font-weight: normal;

  /* 나눔고딕 */
  font-family: 'NanumGothic';
  src: url('@/assets/font/NanumGothic.ttf') format('truetype');
  font-weight: normal;
}

:root {
  /* 비율 */
  /* TODO remove */
  --size-h-header: 40px;
  --size-w-side: 40px;

  /* 메인 테마색 */
  --main-color: #009e73;

  --sub-color-r: #d55e00;
  --sub-color-o: #e69f00;
  --sub-color-y: #f0e442;
  --sub-color-b: #0072b2;
  --sub-color-p: #cc79a7;

  /* 다크모드 */
  --dark-bg-color: #404040;

  --dark-main-color: #404040;
  --dark-sub-color: #000000;

  /* 라이트모드 */
  --light-bg-color: #ffffff;

  --light-main-color: #ffffff;
  --light-sub-color: #d9d9d9;
}

* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;

  font-family: NanumGothic !important;
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
  /* background: var(--sub-color-p); */
  height: 100vh;
  width: 100vw;
  max-height: 100vh;

  /* font-family: Jalnan, Avenir, Helvetica, Arial, sans-serif; */
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
