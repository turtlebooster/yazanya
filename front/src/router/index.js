import { createWebHistory, createRouter } from 'vue-router';
import store from '../store';

// true : 로그인을 해야 이동 가능
// false : 로그인을 하면 이동 불가능
const beforeAuth = (needAuth) => (from, to, next) => {
  const isLogined = store.getters['isLogined']; // is logined
  if (needAuth && isLogined) {
    next('/user');
  } else if (!needAuth && !isLogined) {
    next('/');
  }

  next();
};

const routes = [
  {
    path: '/',
    name: 'home',
    component: () => import('../views/Home/HomeView.vue'),
    beforeEnter: beforeAuth(true),
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
        path: 'Planner',
        name: 'main.Planner',
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
    component: () => import('../views/User/UserView.vue'),
    // beforeEnter: beforeAuth(false),
    children: [
      {
        path: 'login',
        name: 'user.login',
        component: () => import('../views/User/components/UserLogin.vue'),
      },
      {
        path: 'signup',
        name: 'user.signup',
        component: () => import('../views/User/components/UserSignup.vue'),
      },
      {
        path: 'findid',
        name: 'user.findid',
        component: () => import('../views/User/components/UserFindid.vue'),
      },
      {
        path: 'findpw',
        name: 'user.findpw',
        component: () => import('../views/User/components/UserFindpw.vue'),
      },
    ],
  },
  {
    path: '/studyroom',
    name: 'studyroom',
    // beforeEnter: beforeAuth(true),
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
