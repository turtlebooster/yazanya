<template>
  <div
    class="room-navbar"
    :class="[$root.theme ? 'light' : 'dark']"
    style="height: 48px"
  >
    <room-nav @toggle-planner="togglePlanner()" @toggle-chat="toggleChat()" />
  </div>

  <div class="room-main d-flex justify-content-center w-100 h-100">
    <div
      class="planner-sidebar h-100 me-auto"
      :style="{
        width: planner_width + '%',
        transition: '750ms',
        overflow: 'hidden',
      }"
    >
      <!-- TODO planner components -->
      <p>TODO planner components</p>
      <button @click="test()" class="m-2">increase member</button>
    </div>

    <div
      class="video-plane d-flex flex-column w-100 h-100"
      :class="[$root.theme ? 'light-content' : 'dark-content']"
    >
      <div
        class="video-room-info-topbar d-flex justify-content-center w-100 mb-auto p-1 pt-2"
        style="height: 8%; min-height: 36px"
      >
        <i
          class="ms-2 mt-1 me-auto"
          :class="[isRoomPrivate ? 'bi bi-lock-fill' : '']"
          style="font-size: 1.2em"
          >&nbsp;&nbsp;{{ roomname }}
        </i>

        <div class="drowdown ms-auto me-3">
          <a
            class="dropdown-toggle"
            :class="[$root.theme ? 'light-content' : 'dark-content']"
            href="#"
            role="button"
            id="dropdownMenuLink"
            data-bs-toggle="dropdown"
            aria-expanded="false"
          >
            <i class="bi bi-list text-center" style="font-size: 1.5em"></i>
          </a>
          <ul class="dropdown-menu" aria-labelledby="dropdownMenuLink">
            <li><a class="dropdown-item" href="#">Action</a></li>
            <li><a class="dropdown-item" href="#">Another action</a></li>
            <li><a class="dropdown-item" href="#">Something else here</a></li>
          </ul>
        </div>
      </div>

      <div
        class="video-components-plane justify-content-center d-flex flex-column w-100 h-100 p-5"
      >
        <div
          v-for="i in rows_cnt"
          :key="i"
          class="row d-flex justify-content-center flex-grow-1"
        >
          <div
            v-for="j in cols_cnt"
            :key="j"
            class="col justify-content-center py-1"
          >
            <img
              class="w-100 h-100"
              src="@/assets/logo/title_logo_009e73.png"
              style="background-color: #fafafa"
            />
          </div>
        </div>
      </div>

      <div
        class="video-setting-bottombar w-100 mt-auto"
        style="height: 12%; min-height: 56px"
      ></div>
    </div>

    <div
      class="chat-sidebar h-100 ms-auto"
      :style="{ width: chat_width + '%', transition: '750ms' }"
    >
      <!-- TODO chat components -->
    </div>
  </div>
</template>

<script>
import RoomNav from './components/RoomNavbar.vue';
// import VideoComp from  './components/RoomVideo.vue';
import { ref, computed, onBeforeMount } from 'vue';
import { useRouter } from 'vue-router';

export default {
  components: {
    RoomNav,
    // VideoComp
  },

  setup() {
    // --------- sidebar sizing event handling  ↓ ----------- //
    const SIDEBAR_WIDTH = 28;
    const planner_width = ref(0);
    const chat_width = ref(SIDEBAR_WIDTH);

    function togglePlanner() {
      planner_width.value = planner_width.value === 0 ? SIDEBAR_WIDTH : 0;
    }
    function toggleChat() {
      chat_width.value = chat_width.value === 0 ? SIDEBAR_WIDTH : 0;
    }

    let roomname = ref(null);
    let router = useRouter();
    onBeforeMount(() => {
      // ----------- for room name ↓ ------------ //
      let url = document.URL;
      let idx = url.indexOf('studyroom/') + 10;
      if (idx === -1) {
        alert('올바르지 않은 방주소입니다');
        router.replace('/');
        return;
      }

      // check room number NaN
      let room_number = url.slice(idx);
      if (Number.isInteger(room_number)) {
        alert('방 번호가 형식에 맞지 않습니다');
        router.replace('/');
        return;
      }

      // TODO : get roomname from server
      roomname.value = room_number;

      // ---------------- for hide header nav and side bar ------------------ //
      document.documentElement.style.setProperty('--size-h-header', '0');
      document.documentElement.style.setProperty('--size-w-side', '0');
    });

    // ---------- dynamic vedio grid for participants ↓ ------------ //
    var member_cnt = ref(1);
    let cols_cnt = computed(() => {
      return member_cnt.value < 4
        ? member_cnt.value
        : Math.ceil(Math.sqrt(member_cnt.value));
    });
    let rows_cnt = computed(() => {
      return Math.ceil(member_cnt.value / cols_cnt.value);
    });

    function test() {
      console.log(member_cnt);
      member_cnt.value++;
    }

    let isRoomPrivate = ref(true);

    return {
      togglePlanner,
      toggleChat,
      planner_width,
      chat_width,
      test,
      cols_cnt,
      rows_cnt,
      roomname,
      isRoomPrivate,
    };
  },
};
</script>

<style scoped>
/* for theme */
.light {
  background-color: #fafafa;
}
.dark {
  background-color: #404040;
}

.light-content {
  background-color: #f3f3f3;
  color: #545664;
}
.dark-content {
  background-color: #545664;
  color: #f3f3f3;
}

.light-content {
  color: #545664;
}
.dark-content {
  color: #f3f3f3;
}

/* remove dropdwon triangle */
.dropdown-toggle::after {
  display: none;
}

.planner-sidebar {
  background-color: blueviolet;
}

.chat-sidebar {
  background-color: azure;
}

.video-setting-bottombar {
  background-color: brown;
}
</style>
