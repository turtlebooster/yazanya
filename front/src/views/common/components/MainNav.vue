<template>
  <!-- Main Nav -->
  <nav v-if="main_nav_on" class="main-header-nav" :class="[ $root.theme ? 'light' : 'dark' ]">
    <div class="h-100">
      <router-link class="navbar-brand" to="/">
        <img src="@/assets/logo/title_logo_009e73.png" class="d-inline-block align-top h-100 ps-3" alt="logo">
      </router-link>
    </div>
  </nav>
</template>

<script>
import { ref } from 'vue';

export default {
  props: {
    viewname: {
      default: "",
    }
  },

  setup(props) {
    let hide_nav_view_list = [
      // excluded paths
      'studyroom',
    ]

    var main_nav_on = ref(true);
    hide_nav_view_list.forEach((item) => {
        if(document.URL.includes(item) || props.viewname.includes(item)) {
          return main_nav_on.value = false;
        }
      });
    
    return { main_nav_on }
  }
}
</script>

<style scoped>
  .main-header-nav {
    position: fixed;
    border-bottom: 4px solid #009e73;
    height: 32px;
    width: 100vw;
  }
</style>