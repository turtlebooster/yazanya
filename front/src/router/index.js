import { createWebHistory, createRouter } from "vue-router";
import store from "../store";

// true : 로그인을 해야 이동 가능
// false : 로그인을 하면 이동 불가능
const beforeAuth = (needAuth) => (from, to, next) => {
  const isLogined = store.getters["isLogined"]; // is logined
  if (needAuth && !isLogined) {
    next("/user");
  } else if (!needAuth && isLogined) {
    next("/");
  }

  next();
};

const routes = [
  {
    path: "/",
    name: "home",
    component: () => import("../views/MainView.vue"),
    beforeEnter: beforeAuth(true),
  },
  {
    path: "/user",
    name: "user",
    redirect: "/user/login",
    component: () => import("../views/UserView.vue"),
    beforeEnter: beforeAuth(false),
    children: [
      {
        path: "login",
        name: "user.login",
        component: () => import("../components/user/login_form.vue"),
      },
      {
        path: "register",
        name: "user.register",
        component: () => import("../components/user/register_form.vue"),
      },
      {
        path: "findid",
        name: "user.findid",
        component: () => import("../components/user/findId_form.vue"),
      },
      {
        path: "findpw",
        name: "user.findpw",
        component: () => import("../components/user/findPw_form.vue"),
      },
    ],
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
