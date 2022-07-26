<template>
  <nav v-if="defalut_main_nav_flag" class="main-header-nav" :class="[ $root.theme ? 'light' : 'dark' ]">
    <div class="h-100">
      <router-link class="navbar-brand" to="/studyroom">
        <!-- Main Nav -->
        <img src="@/assets/logo/title_logo_009e73.png" class="d-inline-block align-top h-100 pl-3" alt="logo">

        <!-- Study Room Nav -->
        <img v-if="viewname === 'studyroom'" src="@/assets/logo/title_logo_009e73.png" class="d-inline-block align-center h-100" alt="logo">
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
    let defalut_main_nav_flag = ref(true);
    let customed_nav_view_list = [
      'studyroom',
    ]

    console.log(props.viewname);

    customed_nav_view_list.forEach((viewname_item) => {
      if(viewname_item == props.viewname) {
        defalut_main_nav_flag.value = false;
      }
    })

    console.log(defalut_main_nav_flag.value);

    return { defalut_main_nav_flag }
  }
}
</script>

<style scoped>
  .main-header-nav {
    border-bottom: 4px solid #009e73;
    height: 32px
  }
</style>