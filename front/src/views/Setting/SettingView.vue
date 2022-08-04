<template>
  <div class="setting-view">
    <div class="app-header">
      <!-- <header-nav style="display: none" /> -->
      <header-nav />
    </div>

    <!-- Container -->
    <div class="app-container d-flex flex-grow-1 flex-shrink-1">
      <!-- Side -->
      <div class="app-side">
        <!-- <side-bar style="display: none" /> -->
        <side-bar v-if="toggle"/>
      </div>

      <!-- Contents -->
      <div class="app-contents flex-grow-1">
        <router-view />
      </div>
    </div>
  </div>
</template>

<script>
import HeaderNav from '../Main/components/MainNavbar.vue';
import SideBar from '../Main/components/MainSidebar.vue';

export default {
  components: {
    HeaderNav,
    SideBar,
  },  
    setup() {
      var toggle = localStorage.getItem('toggle')
      function fix(toggle) {
        if (toggle) {
          window.getComputedStyle(
            document.querySelector('side-bar'), ':hover'
          ).setProperty('width', '40px');
        }
        else {
          window.getComputedStyle(
            document.querySelector('side-bar'), ':hover'
          ).setProperty('width', '160px');
        }
        console.log(toggle)
      }
      return {
        fix,
        toggle,
      }
    }
};
</script>

<style></style>
