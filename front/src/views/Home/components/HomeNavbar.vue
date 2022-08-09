<template>
  <nav class="home-nav blur">
    <div>
      <router-link to="/main">
        <!-- Main Nav -->
        <img
          src="@/assets/logo/title_logo_009e73.png"
          class="align-bottom"
          style="margin-left: 16px; height: var(--size-h-header)"
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
    // for erase HeaderNav for certain views
    let hide_nav_view_list = [
      // excluded paths
      'studyroom',
    ];

    var main_nav_on = ref(true);
    hide_nav_view_list.forEach((item) => {
      if (document.URL.includes(item) || props.viewname.includes(item)) {
        return (main_nav_on.value = false);
      }
    });

    return { main_nav_on };
  },
};
</script>

<style>
.home-nav {
  z-index: 20;
  position: fixed;
  width: 100vw;
  height: var(--size-h-header);
}

.blur {
  -webkit-backdrop-filter: blur(50px);
  backdrop-filter: blur(50px);
  background-blend-mode: overlay;
  box-shadow: 2px 2px 5px 4px black;
}
</style>
