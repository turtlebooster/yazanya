import { createApp, nextTick } from 'vue';
import App from './App.vue';
import router from './router';
import store from './store';
import Popper from 'vue3-popper';

// for bootstrap
import { BootstrapVue3, BToastPlugin } from 'bootstrap-vue-3';
// import CSS
import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap-vue-3/dist/bootstrap-vue-3.css';
import 'bootstrap-icons/font/bootstrap-icons.css';

import 'v-calendar/dist/style.css';
import VCalendar from 'v-calendar';

// for setting site title
router.afterEach((to, from) => {
  const title = to.meta.title === undefined ? 'YaZanya' : to.meta.title;
  nextTick(() => {
    console.debug(from);
    document.title = title;
  });
});

createApp(App)
  .use(router)
  .use(store)
  .use(BootstrapVue3)
  .use(BToastPlugin)
  .use(VCalendar)
  .component('Popper', Popper)
  .mount('#app');
