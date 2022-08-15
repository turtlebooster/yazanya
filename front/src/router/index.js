import { createWebHistory, createRouter } from 'vue-router';
import store from '../store';

// true : 로그인을 해야 이동 가능
// false : 로그인을 하면 이동 불가능
const beforeAuth = (needAuth) => (from, to, next) => {
  const isLogined = store.getters['isAuthenticated']; // is logined
  if (needAuth && !isLogined) {
    if (from.path.includes('studyroom')) {
      // save last room info for using after login
      store.commit('SET_NEXT_ROOM', from.path);
    }

    // 로그인 필요
    alert('로그인이 필요한 서비스 입니다');
    next('/account/login');
    // 로그인 필요 없음
  } else if (!needAuth && isLogined) {
    next('/main');
  } else {
    next();
  }
};

const routes = [
  {
    path: '/',
    name: 'home',
    redirect: '/home',
    beforeEnter: beforeAuth(false),
    component: () => import('../views/Home/HomeView.vue'),
    children: [
      {
        path: '/home',
        name: 'home.content', // default page
        component: () => import('../views/Home/components/HomeContent.vue'),
      },
    ],
  },
  {
    path: '/main',
    component: () => import('../views/Main/MainView.vue'),
    beforeEnter: beforeAuth(true),
    children: [
      {
        path: '',
        name: 'main.lobby', // default page
        component: () => import('../views/Main/components/MainLobby.vue'),
      },
      {
        path: 'setting/profile',
        name: 'setting',
        component: () =>
          import('../views/Setting/components/SettingProfile.vue'),
      },
      {
        path: 'planner',
        name: 'main.planner',
        component: () => import('../views/Planner/PlannerView.vue'),
      },
      {
        path: 'friends',
        name: 'main.share',
        component: () => import('../views/Main/components/MainFriends.vue'),
      },
      {
        path: 'alarm',
        name: 'main.alarm',
        component: () => import('../views/Main/components/MainAlarm.vue'),
      },
    ],
  },
  {
    path: '/account',
    name: 'account',
    redirect: '/account/login',
    component: () => import('../views/Account/AccountView.vue'),
    beforeEnter: beforeAuth(false),
    children: [
      {
        path: 'login',
        name: 'account.login',
        component: () => import('../views/Account/components/AccountLogin.vue'),
      },
      {
        path: 'find',
        name: 'account.find',
        component: () => import('../views/Account/components/AccountFind.vue'),
      },
    ],
  },
  {
    path: '/studyroom',
    name: 'studyroom',
    beforeEnter: beforeAuth(true),
    component: () => import('../views/Room/RoomView.vue'),
    children: [
      {
        path: ':roomnum',
        name: 'studyroom.inroom',
        component: () => import('../views/Room/RoomView.vue'),
      },
    ],
  },
  {
    path: '/setting',
    // beforeEnter: beforeAuth(true),
    component: () => import('@/views/Setting/SettingView.vue'),
    children: [
      {
        path: '',
        name: 'setting.list',
        component: () => import('@/views/Setting/components/SettingList.vue'),
      },
      {
        path: 'profile',
        name: 'setting.profile',
        component: () =>
          import('@/views/Setting/components/SettingProfile.vue'),
      },
    ],
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
