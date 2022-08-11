import { createApp } from 'vue';
import App from './App.vue';
import router from './router';
import store from './store';

// for bootstrap
import { BootstrapVue3, BToastPlugin } from 'bootstrap-vue-3';
// import CSS
import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap-vue-3/dist/bootstrap-vue-3.css';
import 'bootstrap-icons/font/bootstrap-icons.css';

import 'v-calendar/dist/style.css';
import VCalendar from 'v-calendar';

createApp(App)
  .use(router)
  .use(store)
  .use(BootstrapVue3)
  .use(BToastPlugin)
  .use(VCalendar)
  .mount('#app');
