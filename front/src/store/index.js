import { createStore } from 'vuex';

import { User } from '@/store/modules/user';
import { Room } from '@/store/modules/room';

export default createStore({
  modules: {
    User,
    Room,
  },
});
