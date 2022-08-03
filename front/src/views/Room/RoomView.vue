<template>
<div class="d-flex flex-column" style="max-height: 100%; height:100%">
  <div
    class="room-navbar"
    :class="[$root.theme ? 'light' : 'dark']"
    style="height: 52px"
  >
    <room-nav @toggle-planner="togglePlanner()" @toggle-chat="toggleChat()" />
  </div>

  <div
    class="room-main d-flex justify-content-center flex-grow-1 w-100"
    :class="[$root.theme ? 'light-content' : 'dark-content']"
  >
    <!-- Planner Side bar -->
    <div
      class="planner-sidebar me-auto"
      :style="{
        width: planner_width + '%',
        transition: '750ms',
        overflow: 'hidden',
      }"
    >
      <!-- TODO planner components -->
      <p>TODO planner components</p>
      <button @click="test()" class="m-2">increase member</button>
      <button @click="register()" class="m-2">add member </button>
      <button @click="leaveRoom()" class="m-2">leave member </button>
    </div>

    <!-- Video Content -->
    <div
      class="video-plane d-flex flex-column w-100 m-3 rounded-3 shadow"
      :class="[$root.theme ? 'light-content' : 'dark-content']"
    >
      <div
        class="video-room-info-topbar d-flex justify-content-center  mb-auto p-1 pt-2"
        style="height: 8%; min-height: 52px"
      >
        <i
          class="ms-2 mt-1 me-auto"
          :class="[isRoomPrivate ? 'bi bi-lock-fill' : '']"
          style="font-size: 1.5em"
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
            <i class="bi bi-list text-center" style="font-size: 2em"></i>
          </a>
          <ul class="dropdown-menu" aria-labelledby="dropdownMenuLink">
            <li><a class="dropdown-item" href="#">Action</a></li>
            <li><a class="dropdown-item" href="#">Another action</a></li>
            <li><a class="dropdown-item" href="#">Something else here</a></li>
          </ul>
        </div>
      </div>

      <div
        class="video-components-plane d-flex justify-content-center flex-grow-1 flex-wrap"
        style="overflow: hidden"
        ref="video_plane"
      >
        <div v-for="(value, name) in participants" :key="name" :ref="(el) => { members[name] = el }"
          class="d-flex justify-content-center m-0 p-0"
          style="height: 100vh; flex-grow: 1;"
          >
        </div>


        <!-- <div
          v-for="i in rows_cnt"
          :key="i"
          class="row d-flex justify-content-center flex-grow-1"
        >
          <div
            v-for="j in cols_cnt"
            :key="j"
            class="col justify-content-center py-1 w-100"
          >
          </div>
        </div> -->
      </div>

      <!-- Videoroom bottombar for control -->
      <hr class="border-top border-dark border-3 rounded-3 mx-4 my-2 p-0" />
      <div
        class="video-setting-bottombar d-flex justify-content-center align-items-center w-100 mt-auto p-3 pt-2"
        style="height: 12%; min-height: 56px"
      >
        <b-button
          class="rounded-circle mx-2"
          :class="[$root.theme ? 'dark-content' : 'light-content']"
        >
          <i class="bi bi-mic-fill" style="font-size: 1.3em"></i>
          <!-- <i class="bi bi-mic-mute-fill"></i> -->
        </b-button>
        <b-button
          class="rounded-circle mx-2"
          :class="[$root.theme ? 'dark-content' : 'light-content']"
        >
          <i class="bi bi-camera-video-fill" style="font-size: 1.3em"></i>
          <!-- <i class="bi bi-camera-video-off-fill"></i> -->
        </b-button>
        <b-button
          class="rounded-circle mx-2"
          :class="[$root.theme ? 'dark-content' : 'light-content']"
        >
          <i class="bi bi-display" style="font-size: 1.3em"></i>
          <!-- <i class="bi bi-camera-video-off-fill"></i> -->
        </b-button>

        <b-button pill
          class="mx-4 m-1 px-sm-4"
          variant="danger"
        >
          <i class="bi bi-power" style="font-size: 1.3em"></i>
          <!-- <i class="bi bi-camera-video-off-fill"></i> -->
        </b-button>
      </div>
    </div>

    <!-- Chat Sidebar -->
    <div
      class="chat-sidebar ms-auto my-auto rounded-3"
      :style="{ width: chat_width + '%', height: '95%', transition: '750ms' }"
    >
      <!-- TODO chat components -->
    </div>
  </div>
  </div>
</template>

<script>
import RoomNav from './components/RoomNavbar.vue';
// import VideoComp from  './components/RoomVideo.vue';
import { ref, computed, onBeforeMount, onUpdated } from 'vue';
import { useRouter } from 'vue-router';
import { useStore } from 'vuex'

export default {
  components: {
    RoomNav,
    // VideoComp
  },

  setup() {
    // --------- sidebar sizing event handling  ↓ ----------- //
    const SIDEBAR_WIDTH = 28;
    const planner_width = ref(0);
    const chat_width = ref(0);

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
      if (isNaN(room_number)) {
        alert('방 번호가 형식에 맞지 않습니다');
        router.replace('/');
        return;
      }

      // TODO : get roomname from server
      roomname.value = room_number;

      // ---------------- for hide header nav and side bar ------------------ //
      // document.documentElement.style.setProperty('--size-h-header', '0');
      // document.documentElement.style.setProperty('--size-w-side', '0');

      // start socket connection
      store.commit("initSocket");
    });


    // for debugging
    function test() {
      console.log();
    }

    // -------------------- video utility -------------------- //
    var store = useStore();

    function register() {
      store.dispatch("register", { username: prompt("이름은?", "그래") || 'asdf', roomname: '310' });
    }
    function leaveRoom() {
      store.dispatch("leaveRoom");
    }

    // add video on updated participants
    var members = ref({});
    var video_plane = ref("");
    

    onUpdated(() => {
      // add video of newly participants 
      Object.keys(members.value).forEach((key) => {
        console.log(key + " : " + members.value[key]);
        if(members.value[key].children.length > 0) {
          return;
        }
          
        let video = store.state.Room.participants[key].getVideoElement();
        members.value[key].appendChild(video);
      })

      // calculate width and height
      let width = video_plane.value.clientWidth;
      let height = video_plane.value.clientHeight;
      let nMembers = Object.keys(members.value).length;

      // ---------- dynamic video grid for participants ↓ ------------ //
      let nCols = nMembers < 4
          ? nMembers
          : Math.ceil(Math.sqrt(nMembers));
      let nRows =  Math.ceil(nMembers / nCols);

      console.log(nCols);
      console.log(nRows);

      let video_width = width / nCols;
      let video_height = height / nRows;

      // resize width and height
      Object.keys(members.value).forEach((key) => {
        console.log(key, video_width);
        console.log(key, video_height);

        // members.value[key].firstChild.setAttribute('width', video_width);
        // members.value[key].firstChild.setAttribute('height', video_height);
      })
    })

    // --------------------- room information ----------------------- //
    let isRoomPrivate = ref(true);

    return {
      togglePlanner,
      toggleChat,
      planner_width,
      chat_width,
      roomname,
      isRoomPrivate,

      test,
      register,
      leaveRoom,
      members,

      video_plane,

      participants: computed(() => store.state.Room.participants),
      isConnected: computed(() => store.state.Room.isSocketConnected)
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

.light-content-color {
  color: #545664;
}
.dark-content-color {
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


/* for video */
video {
  max-height: 100%;
  min-width: 100%;
  object-fit: contain;
}
</style>
