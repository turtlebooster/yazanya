<template>
  <div class="main-sidebar">
    <router-link to="/main/setting/profile">
      <img
        :src="require(`@/assets/avatar/${userNum}.jpg`)"
        alt="profile"
        class="image icon"
        style="padding: 8px; object-fit: cover; border-radius: 50%"
      />
      <span class="title">{{ userName }}</span>
    </router-link>

    <router-link to="/main">
      <span class="icon"><i class="bi bi-house"></i></span>
      <span class="title">로비</span>
    </router-link>

    <router-link to="/main/planner">
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

    <a href="#" @click="logout()">
      <span class="icon"><i class="bi bi-box-arrow-right"></i></span>
      <span class="title">로그아웃</span>
    </a>
  </div>
</template>

<script>
import { ref, onBeforeMount } from 'vue';
import { useStore } from 'vuex';
import { useRouter } from 'vue-router';

import rest_user from '@/rest/user';

export default {
  setup() {
    let userNum = ref(0);
    let userName = ref(0);

    const store = useStore();
    const router = useRouter();

    function logout() {
      store.dispatch('logout').then(() => {
        router.replace('/');
      });
    }

    async function init() {
      let userData = await rest_user.getProfile(store.getters.getUserID);
      userNum.value = userData.userNum;
      userName.value = userData.userName;
    }

    onBeforeMount(() => {
      init();
    });

    return { userNum, userName, logout };
  },
};
</script>

<style scoped>
.main-sidebar {
  z-index: 20;
  position: fixed;

  display: flex;
  flex-direction: column;
  margin: 8px;

  top: var(--size-h-header);
  left: 0px;
  bottom: 0px;

  border-radius: calc((var(--size-w-side) - 16px) / 2);

  width: calc(var(--size-w-side) - 16px);

  background-color: var(--theme-color);

  transition: 0.2s;

  overflow: hidden;
  white-space: nowrap;
}

.main-sidebar:hover {
  width: calc(var(--size-w-side) * 2.5);
  box-shadow: 8px 0px 8px white;
}

.main-sidebar a {
  position: relative;
  display: flex;
  width: 100%;

  text-decoration: none;
  color: var(--light-main-color);
  font-size: 12pt;

  border-bottom-left-radius: calc(var(--size-w-side) / 2);
  border-top-left-radius: calc(var(--size-w-side) / 2);
}

.main-sidebar a.router-link-exact-active {
  background: var(--light-main-color);
  color: var(--theme-color);
}

.main-sidebar a.router-link-exact-active::before {
  content: '';
  position: absolute;
  top: -30px;
  right: 0;
  width: 30px;
  height: 30px;
  background: var(--theme-color);
  border-radius: 50%;
  box-shadow: 15px 15px 0 var(--light-main-color);
  z-index: -1;
}

.main-sidebar a.router-link-exact-active::after {
  content: '';
  position: absolute;
  bottom: -30px;
  right: 0;
  width: 30px;
  height: 30px;
  background: var(--theme-color);
  border-radius: 50%;
  box-shadow: 15px -15px 0 var(--light-main-color);
  z-index: -1;
}

.main-sidebar a .icon {
  position: relative;

  width: calc(var(--size-w-side) - 16px);
  min-width: calc(var(--size-w-side) - 16px);
  height: calc(var(--size-w-side) - 16px);
  line-height: calc(var(--size-w-side) - 16px);
  text-align: center;
}

.main-sidebar a .icon i {
  font-size: 1.5em;
}

.main-sidebar a .title {
  position: relative;
  display: block;

  height: calc(var(--size-w-side) - 16px);
  line-height: calc(var(--size-w-side) - 16px);
  font-size: 1.2em;
}
</style>
