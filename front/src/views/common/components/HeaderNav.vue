<!--
  현재 mainview 만드는 중 쓰고 있는 네비게이션바 
  서로 충돌날까봐 분리해둠 마지막에 머지 후, 정리할 것
-->

<template>
  <nav
    v-if="defalut_main_nav_flag"
    class="header-nav"
    :class="[$root.theme ? 'dark' : 'light']"
  >
    <div class="h-100">
      <router-link to="/main">
        <!-- Main Nav -->
        <img
          src="@/assets/logo/title_logo_009e73.png"
          class="align-bottom h-100"
          style="margin-left: 24px"
          alt="logo"
        />
      </router-link>
    </div>
  </nav>
</template>

<script>
import { ref } from 'vue';

export default {
  props: {
    viewname: {
      default: '',
    },
  },

  setup(props) {
    let defalut_main_nav_flag = ref(true);
    let customed_nav_view_list = ['studyroom'];

    // console.log(props.viewname);

    customed_nav_view_list.forEach((viewname_item) => {
      if (viewname_item == props.viewname) {
        defalut_main_nav_flag.value = false;
      }
    });

    // console.log(defalut_main_nav_flag.value);

    return { defalut_main_nav_flag };
  },
};
</script>

<style>
.header-nav {
  border-bottom: 4px solid #009e73;
  height: var(--size-h-header);
}
</style>
