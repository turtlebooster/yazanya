import { createStore } from 'vuex';

import { Account } from '@/store/modules/account';
import { Room } from '@/store/modules/kurento_room';
import { Setting } from '@/store/modules/setting';

export default createStore({
  modules: {
    Account,
    Room,
    Setting,
  },
});
