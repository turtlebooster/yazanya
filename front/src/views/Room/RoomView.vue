<template>
<div class="d-flex flex-column" style="max-height: 100%; height:100%">
  <div
    class="room-navbar"
    :class="[$root.theme ? 'light-back-only' : 'dark-back-only']"
    style="height: 52px"
  >
    <room-nav @toggle-planner="togglePlanner()" @toggle-chat="toggleChat()" />
  </div>

  <div
    class="room-main d-flex justify-content-center flex-grow-1 w-100"
    :class="[$root.theme ? 'light' : 'dark']"
  >
    <!-- Planner Side bar -->
    <div
      class="planner-sidebar me-auto shadow"
      :style="{
        width: planner_width + '%',
        minWidth: planner_width != 0? 200 + 'px': 0,
        overflow: 'hidden',
        height: '98%',
      }"
    >
      <!-- TODO planner components -->
      <p>TODO planner components</p>
      <button @click="test()" class="m-2">increase member</button>
      <button @click="test2()" class="m-2"> send message </button>

      {{ Object.keys(participants).length }}

      <div v-for="(value, name) in participants" :key="name">
        {{ name }}   
      </div>
    </div>

    <!-- Video Content -->
    <div
      class="video-plane d-flex flex-column w-100 m-2 rounded-3 shadow"
      :class="[$root.theme ? 'light' : 'dark']"
      style="overflow: hidden; height = 95%"
    >
      <div
        class="video-room-info-topbar d-flex justify-content-center  mb-auto p-1 pt-2"
        style="height: 8%; min-height: 52px"
      >
        <i
          class="ms-2 mt-1 me-auto"
          :class="[isPrivateRoom ? 'bi bi-lock-fill' : '']"
          style="font-size: 1.5em; font-style: normal;"
          >&nbsp;&nbsp;{{ roomName }}
        </i>

        <div class="drowdown ms-auto me-3">
          <a
            class="dropdown-toggle"
            :class="[$root.theme ? 'light' : 'dark']"
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
        class="video-components-plane d-flex justify-content-center align-content-center flex-grow-1 flex-wrap"
        style="overflow: hidden"
        ref="video_plane"
      >
        <div v-for="(value, name) in participants" :key="name" :ref="(el) => { members[name] = el }"
          :style="{ position: 'relative', width:  each_video_width + 'px', height:  + each_video_height + 'px'}"
          >
          <p class="text-center align-middle rounded m-0 px-1" :class="[$root.theme ? 'light' : 'dark']" style="position: absolute; top: 10px; left: 10px; z-index: 2">{{name}}</p>
          <b-dropdown
            style="position: absolute; bottom: 10px; right: 10px; z-index: 2; border-radius: 4px;"
            :class="[$root.theme ? 'light-back-only' : 'dark-back-only']"
            size="sm" variant="link" toggle-class="text-decoration-none" no-caret>
            <template #button-content><i class="bi bi-three-dots-vertical" :class="[$root.theme ? 'light-color-only' : 'dark-color-only']"></i></template>
            <b-dropdown-item href="#">Action</b-dropdown-item>
            <b-dropdown-item href="#">Another action</b-dropdown-item>
            <b-dropdown-item href="#">Something else here...</b-dropdown-item>
          </b-dropdown>
        </div>
      </div>

      <!-- Videoroom bottombar for control -->
      <hr class="border-top border-dark border-3 rounded-3 mx-4 my-2 p-0" />
      <div
        class="video-setting-bottombar d-flex justify-content-center align-items-center w-100 mt-auto p-3 pt-2"
        style="height: 12%; min-height: 56px"
      >
        <b-button
          class="rounded-circle mx-2"
          :class="[$root.theme ? 'dark' : 'light']"
          @click="toggleAudio()"
          :disabled = !isRoomAudioOn
        >
          <i v-if="isAudioOn" class="bi bi-mic-fill" style="font-size: 1.3em"></i>
          <i v-if="!isAudioOn" class="bi bi-mic-mute-fill" style="font-size: 1.3em"></i>
        </b-button>
        <b-button
          class="rounded-circle mx-2"
          :class="[$root.theme ? 'dark' : 'light']"
          @click="toggleVideo()"
          :disabled = !isRoomVideoOn
        >
          <i v-if="isVideoOn" class="bi bi-camera-video-fill" style="font-size: 1.3em"></i>
          <i v-if="!isVideoOn" class="bi bi-camera-video-off-fill" style="font-size: 1.3em"></i>
        </b-button>
        <b-button
          class="rounded-circle mx-2"
          :class="[$root.theme ? 'dark' : 'light']"
          @click="toggleFilter()"
        >
          <i v-if="isFilterOn" class="bi bi-emoji-smile-fill" style="font-size: 1.3em;"></i>
          <i v-if="!isFilterOn" class="bi bi-emoji-sunglasses-fill" style="font-size: 1.3em"></i>
        </b-button>

        <b-button pill
          class="mx-4 m-1 px-sm-4"
          variant="danger"
          @click="leaveRoom()"
        >
          <i class="bi bi-power" style="font-size: 1.3em"></i>
        </b-button>
      </div>
    </div>

    <!-- Chat Sidebar -->
    <div
      class="chat-sidebar ms-auto my-auto rounded-3 shadow"
      :style="{
        width: chat_width + '%',
        minWidth: chat_width != 0?
        200 + 'px': 0,
        height: '98%',
      }"
    >
      <chat-sidebar :isSidebarOn="chat_width"/>
    </div>
  </div>
  </div>
</template>

<script>
import { ref, computed, onBeforeMount, onUpdated } from 'vue';
import { useRouter } from 'vue-router';
import { useStore } from 'vuex'

import RoomNav from './components/RoomNavbar.vue';
import ChatSidebar from './components/RoomChatSidebar.vue';

import rest_room from '@/rest/room';
import rest_user from '@/rest/user';

export default {
  components: {
    RoomNav,
    ChatSidebar,
  },

  setup() {
    const router = useRouter();
    const store = useStore();

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

    // --------------------- room information ----------------------- //
    let room_number;
    onBeforeMount(() => {
      // ----------- for room name ↓ ------------ //
      let url = document.URL;
      let idx = url.indexOf('studyroom/') + 10;
      if (idx === -1) {
        alert('올바르지 않은 방주소입니다');
        router.replace('/main');
        return;
      }

      // check room number NaN
      room_number = url.slice(idx);
      if (isNaN(room_number)) {
        alert('링크가 형식에 맞지 안습니다');
        router.replace('/main');
        return;
      }

      enterRoom(room_number);
      // console.log(enterRoom);
    });

    async function enterRoom(room_number) {
      try {
        // TODO : add check room has password
        await rest_room.joinRoom(room_number, prompt("방의 비밀번호를 입력해주세요"))

        // gain room info
        let room = await rest_room.getRoomInfo(room_number)
        await store.dispatch('saveRoomInfo', room)

        // gain user info
        let user = await rest_user.getProfile(store.getters.getUserID)
        await store.dispatch('saveUserInfo', user);

        // set UI with Room Info
        isVideoOn.value = store.getters.isVideoEnabled;
        isAudioOn.value = store.getters.isAudioEnabled;
        
        // connect kurento server
        store.dispatch('joinRoom');
      } catch(error) {
        alert(error);
        await rest_room.leaveRoom(room_number);
        router.replace('/main');
      }
    }

    async function leaveRoom() {
      // APP Server Socket disconnect
      //store.dispatch("leaveRoom");
      // REST request
      await rest_room.leaveRoom(store.state.Room.room.roomNum);
      location.replace(window.location.origin + '/main');
    }

    // ---------- dynamic video grid for participants ↓ ------------ //
    var nMember = computed(()=> store.getters.getParticipantsCount);
    var nCols = computed(()=> nMember.value < 3 ? nMember.value : Math.ceil(Math.sqrt(nMember.value + 1)));
    var nRows = computed(()=> Math.ceil(nMember.value / nCols.value));

    // ------------------- calculate width and height ------------------------ //
    let video_plane = ref(null);
    const video_container_width = computed(()=> video_plane.value != null? video_plane.value.clientWidth - 5: 0);
    const video_container_height = computed(()=> video_plane.value != null? video_plane.value.clientHeight - 5: 0);

    function cal_video_wh(returnWidth) {
      // axis 1 : 1.7     
      let width = video_container_width.value / nCols.value;
      let height = width * 0.59;

      let over_height = height * nRows.value - video_container_height.value;
      if(over_height > 0) {
        width -= (over_height / nRows.value) * 1.7;
        height = width * 0.59
      }
      return returnWidth? width : height;
    }
    var each_video_width = computed(()=> cal_video_wh(true));
    var each_video_height = computed(()=> cal_video_wh(false));
    

    // --------------------- for debugging ------------------------ //
    async function test() {
      let temp_id = prompt("유저 이름", "제임스");
      await store.dispatch('saveRoomInfo', { roomNum : 11})
      await store.dispatch('saveUserInfo', { userID : temp_id, userNickname : temp_id, profilePictureLink : "https://placekitten.com/300/300" });
      store.dispatch('joinRoom');
    }
    function test2() {
     store.dispatch('sendChat', {sender: store.state.Account.userID, message:prompt('채팅 내용')});
    }
    function not_impl() {
      alert('아직 미구현입니다');
    }

    // --------------------- add video on updated participants ----------------------- //
    var members = ref({});
    onUpdated(() => {
      // add video of newly participants
      Object.keys(members.value).forEach((key) => {
        if(!members.value[key]) {
          // check null
          return;
        }
        if(members.value[key].lastChild.tagName === "VIDEO") {
          // already has video
          return;
        }
          
        let video = store.state.Room.participants[key].getVideoElement();
        video.style.margin = 'auto';
        video.style.objectFit = 'cover';
        video.setAttribute('width', each_video_width.value - 5);
        video.setAttribute('height', each_video_height.value - 5);
        members.value[key].appendChild(video);
      })

      // -------------------- match width height dynamically ---------------------- //
      Object.keys(members.value).forEach((key) => {
        if(!members.value[key]) {
          // check null
          return;
        }
        // already has video
        if(members.value[key].lastChild.tagName !== "VIDEO") {
          return;
        }
  
        members.value[key].lastChild.setAttribute('width', each_video_width.value - 5);
        members.value[key].lastChild.setAttribute('height', each_video_height.value - 5);
      })
    })

    // ------------------------- handle audio and video -------------------------- //
    let isVideoOn = ref(true);
    let isAudioOn = ref(true);

    function toggleAudio() {
      isAudioOn.value = !isAudioOn.value;
      if(store.state.Room.user) {
        store.getters.getParticipants[store.state.Room.user.userNickname].handleAudio(isAudioOn.value);
      }
    }

    function toggleVideo() {
      isVideoOn.value = !isVideoOn.value;
      if(store.state.Room.user) {
        store.state.Room.participants[store.state.Room.user.userNickname].handleVideo(isVideoOn.value);
      }
    }

    // ------------------------- About filter ------------------------- //
    let isFilterOn = ref(false)
    function toggleFilter() {
      isFilterOn.value = !isFilterOn.value
      store.dispatch('toggleFilter', isFilterOn.value);
    }


    return {
      togglePlanner,
      toggleChat,
      planner_width,
      chat_width,

      leaveRoom,
      members,

      // for test
      test,
      test2,
      not_impl,

      video_plane,

      nMember,
      each_video_width,
      each_video_height,

      roomName : computed(()=>store.getters.getRoomName),
      isPrivateRoom : computed(()=>store.getters.isPrivateRoom),

      participants: computed(() => store.state.Room.participants),
      isConnected: computed(() => store.state.Room.isSocketConnected),

      isVideoOn,
      isAudioOn,

      isRoomVideoOn : computed(()=> store.getters.isVideoEnabled),
      isRoomAudioOn : computed(()=> store.getters.isAudioEnabled),

      toggleAudio,
      toggleVideo,

      isFilterOn,
      toggleFilter
    };
  },
};
</script>

<style scoped>
/* for theme */
.light {
  background-color: #f3f3f3;
  color: #545664;
}
.dark {
  background-color: #545664;
  color: #f3f3f3;
}

.light-back-only {
  background-color: #fafafa;
}
.dark-back-only {
  background-color: #404040;
}

.light-color-only {
  color: #545664;
}
.dark-color-only {
  color: #f3f3f3;
}


/* remove dropdwon triangle */
.dropdown-toggle::after {
  display: none;
}
</style>
