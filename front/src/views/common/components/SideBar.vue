<template>
  <div v-if="sidebar_on" class="side-bar">
    <!-- <div style="height: 10px"></div> -->

    <router-link to="/setting/profile">
      <img
        src="@/assets/avatar/3.jpg"
        alt="profile"
        class="image icon"
        style="padding: 8px; object-fit: cover; border-radius: 50%"
      />
      <span class="title">홍 길동</span>
    </router-link>

    <router-link to="/main">
      <span class="icon"><i class="bi bi-house"></i></span>
      <span class="title">로비</span>
    </router-link>

    <router-link to="/planner">
      <span class="icon"
        ><i class="bi bi-layout-text-sidebar-reverse"></i
      ></span>
      <span class="title">플래너</span>
    </router-link>

    <router-link to="/main/friends">
      <span class="icon"><i class="bi bi-people"></i></span>
      <span class="title">친구 목록</span>
    </router-link>

    <router-link to="/main/alarm">
      <span class="icon"><i class="bi bi-bell"></i></span>
      <span class="title">알림</span>
    </router-link>

    <div style="flex-grow: 1"></div>

    <router-link to="/setting">
      <span class="icon"><i class="bi bi-gear"></i></span>
      <span class="title">설정</span>
    </router-link>

    <router-link to="/대충로그아웃하는주소">
      <span class="icon"><i class="bi bi-box-arrow-right"></i></span>
      <span class="title">로그아웃</span>
    </router-link>
  </div>
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
    let hide_sidebar_view_list = [
      // excluded paths
      'studyroom',
    ];

    var sidebar_on = ref(true);
    hide_sidebar_view_list.forEach((item) => {
      if (document.URL.includes(item) || props.viewname.includes(item)) {
        return (sidebar_on.value = false);
      }
    });

    return { sidebar_on };
  },
};
</script>

<style scoped>
.side-bar {
  display: flex;
  flex-direction: column;

  position: fixed;
  /* top: var(--size-h-header); */
  top: 56px;
  left: 10px;
  bottom: 10px;

  border-radius: calc(var(--size-w-side) / 2);

  width: var(--size-w-side);

  background-color: var(--main-color);

  transition: 0.2s;

  overflow: hidden;
  white-space: nowrap;
}

.side-bar:hover {
  width: calc(var(--size-w-side) * 4);
  box-shadow: 8px 0px 8px white;
}

.side-bar a {
  position: relative;
  display: flex;
  width: 100%;

  font-family: 'Ubuntu', sans-serif;
  text-decoration: none;
  color: var(--light-main-color);
  font-size: 12pt;

  border-bottom-left-radius: calc(var(--size-w-side) / 2);
  border-top-left-radius: calc(var(--size-w-side) / 2);
}

.side-bar a.router-link-exact-active {
  background: var(--light-main-color);
  color: var(--main-color);
}

.side-bar a.router-link-exact-active::before {
  content: '';
  position: absolute;
  top: -30px;
  right: 0;
  width: 30px;
  height: 30px;
  background: var(--main-color);
  border-radius: 50%;
  box-shadow: 15px 15px 0 var(--light-main-color);
}

.side-bar a.router-link-exact-active::after {
  content: '';
  position: absolute;
  bottom: -30px;
  right: 0;
  width: 30px;
  height: 30px;
  background: var(--main-color);
  border-radius: 50%;
  box-shadow: 15px -15px 0 var(--light-main-color);
}

.side-bar a .icon {
  position: relative;

  width: var(--size-w-side);
  min-width: var(--size-w-side);
  height: var(--size-w-side);
  line-height: var(--size-w-side);
  text-align: center;

  z-index: 1;
}

.side-bar a .icon i {
  font-size: 1.5em;
}

.side-bar a .title {
  position: relative;
  display: block;

  padding-left: 16px;
  height: var(--size-w-side);
  line-height: var(--size-w-side);
  /* font-weight: bold; */
  font-size: 1.2em;

  z-index: 1;
}
</style>
