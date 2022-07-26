// 현재 mainview 에서 임포트하여 쓰고 있는 네비게이션바
// Merge 시, 디자인 간격 붕괴를 회피하기 위해서 분리해둠 마지막에 머지 후, 정리할 것

<template>
  <div class="header-nav">
    <!-- Image and text -->
    <b-navbar variant="faded" type="light">
      <b-navbar-brand href="#">
        <img src="@/assets/logo/title_logo_009e73.png" height="32" id="logo" class="d-inline-block align-top" alt="Kitten">
      </b-navbar-brand>
    </b-navbar>
  </div>
</template>

<script>
export default {

}
</script>

<style scoped>
  * {
    position: relative;
  }
  #logo {
    margin-top: 4px;
    margin-left: 10px;
  }
  button {
    border:none;
    background-color:transparent;
  }

  .header-nav {
    width: 100vw;
    position: fixed;
    padding-left: 24px;
    border-bottom: 3px solid #009e73;
    background-color:white;
  }
</style>