<template>
  <div class="main-header-nav">
    <!-- Image and text -->
    <b-navbar variant="faded" type="light">
      <button><i class="bi bi-list" style="font-size: 20pt;"></i></button>
      <b-navbar-brand href="#">
        <img src="@/assets/title_logo_green.png" height="32" id="logo" class="d-inline-block align-top" alt="Kitten">
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

  .main-header-nav {
    padding-left: 24px;
    border-bottom: 3px solid rgb(131,220,183);
    background-color:white;
  }
</style>