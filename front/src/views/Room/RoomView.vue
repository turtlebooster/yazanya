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
            <li><a class="dropdown-item" href="#">방 정보</a></li>
            <li><a class="dropdown-item" href="#">튜토리얼</a></li>
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
            v-if="!(name === userNickname)"
            style="position: absolute; bottom: 10px; right: 10px; z-index: 2; border-radius: 4px;"
            :class="[$root.theme ? 'light-back-only' : 'dark-back-only']"
            size="sm" variant="link" toggle-class="text-decoration-none" no-caret>
            <template #button-content><i class="bi bi-three-dots-vertical" :class="[$root.theme ? 'light-color-only' : 'dark-color-only']"></i></template>
            <b-dropdown-item href="#" @click.prevent="ya_zanya(name)"><i class="bi bi-alarm-fill" style="color: #8e2b80; font-style: normal; font-size:1.2em;">&nbsp;야! 자냐?</i></b-dropdown-item>
            <b-dropdown-divider></b-dropdown-divider>
            <b-dropdown-item href="#" @click.prevent="kickUser(name)" :disabled="!isHost"><i class="bi bi-indent" style="color: #d15253; font-style: normal;">&nbsp;강제 퇴장</i></b-dropdown-item>
            <b-dropdown-item href="#" @click.prevent="reportUser(name)"><i class="bi bi-exclamation-diamond" style="color: #b2b56f; font-style: normal;">&nbsp;신고하기</i></b-dropdown-item>
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
          @click="leaveRoom(0)"
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
import { useStore } from 'vuex';
import { useToast } from 'bootstrap-vue-3';
import Swal from 'sweetalert2'

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
    
    // ---------------------- Toast ------------------------- //
    const toast = useToast();
    function showWarnToast(title, message) {
      toast.show(
        { title: title, body: message},
        { pos: 'bottom-right', variant:'danger'}
      )
    }
    function showInfoToast(title, message) {
      toast.show(
        { title: title, body: message},
        { pos: 'bottom-right'}
      )
    }

    // ---------------- kick and report --------------------- //
    async function kickUser(userNickname) {
      const result = await Swal.fire({
              icon: 'warning',
              title: userNickname + '님을 강퇴하시겠습니까?',
              showCancelButton: true,
              confirmButtonText: '나가',
              cancelButtonText: '아차, 실수'
            })

      if(result.isConfirmed && store.getters.isRoomHost) {      
        store.commit('kickUser', userNickname);
      }
    }

    async function reportUser(userNickname) {
      const { value : text } = await Swal.fire({
        icon : 'warning',
        input: 'textarea',
        title: userNickname + '님을 신고합니다',
        inputLabel: '신고 당시 상황을 적어주세요',
        inputAttributes: {
          resize: 'none',
          maxlength: '150',
        },
        showCancelButton: true,
      })

      console.debug(text);
      Swal.fire({
        icon: 'error',
        title: '죄송합니다',
        text: '현재 고객센터는 운영 중이 아닙니다',
      })
    }

    store.watch((state, getters) => getters.getLeaveTriggerFlag, (flag) => {
      // watch kicked or closed room
      leaveRoom(flag)
    });

    // -------------------- alarm Ya-ZaNya ---------------------------- //
    let ya_zanya_cooltime = ref(false);
    async function ya_zanya(userNickname) {
      if(ya_zanya_cooltime.value) {
        // limit cool time
        Swal.fire({ title: '야! 자냐? 는 2분마다 사용할 수 있습니다', timer: 2000 })
        return;
      }
      const result = await Swal.fire({
              icon: 'warning',
              title: userNickname + '님에게 야! 자냐? 를 \n시전하시겠습니까?',
              text: '공부를 방해할 수도 있습니다...',
              showCancelButton: true,
              confirmButtonText: '야! 자냐?',
              cancelButtonText: '아니오'
            })

      if(result.isConfirmed) {
        ya_zanya_cooltime.value = true;
        store.commit('sendYaZanya', { target: userNickname, flag: 1});
        setTimeout(()=> {
          // reset cooltime
          ya_zanya_cooltime.value = false;
        }, 120000);
      }
    }

    store.watch((state, getters) => getters.getYaZanyaTriggerCode, (code) => {
      console.log("yazanya watched", code);

      // watch ya zanya
      if(code == 1) {
        play_ya_zanya();
        Swal.fire({
          icon:'question',
          title: '야! 자냐?',
          showConfirmButton: false,
          showCancelButton: true, 
          cancelButtonColor: '#d33',
          cancelButtonText: '안잔다',
          timer: 10000,
          timerProgressBar: true,
        }).then((result) => {
          if(result.dismiss === Swal.DismissReason.timer) {
            // time over
            store.commit('sendYaZanya', { target: store.state.Room.yaZanyaTrigger.sender, flag: 3});
          } else {
            // send not sleep
            store.commit('sendYaZanya', { target: store.state.Room.yaZanyaTrigger.sender, flag: 2});
          }
          store.commit('setYaZanya', { code: 0 });
        })
      } else if (code == 2) {
        // recevied not sleep
        Swal.fire({
          icon: 'success',
          title: '안잔다고 합니다~',
          showConfirmButton: false,
          timer: 2000
        });
        store.commit('setYaZanya', { code: 0 });
      } else if(code == 3) {
        // recevied not sleep
        Swal.fire({
          icon: 'error',
          title: '자는 중인 것 같습니다',
          showConfirmButton: false,
          timer: 2000
        });
        store.commit('setYaZanya', { code: 0 });
      }
    });

    function play_ya_zanya() {
      let audio = new Audio(require('@/assets/audio/ya.mp3'));
      audio.play();
    }

    // --------------- sidebar sizing event handling  ↓ --------------- //
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
        showWarnToast('Failed', '올바르지 않은 방주소입니다');
        router.replace('/main');
        return;
      }

      // check room number NaN
      room_number = url.slice(idx);
      if (isNaN(room_number)) {
        showWarnToast('Failed', '올바르지 않은 링크입니다');
        router.replace('/main');
        return;
      }

      enterRoom(room_number);
      // console.log(enterRoom);
    });

    async function enterRoom(room_number) {
      let isEntered = false;
      try {
        // check password
        const hasPw = await rest_room.hasRoomPw(room_number);
        if(hasPw) {
            const { value : pw } = await Swal.fire( {
            title: '비밀번호를 입력해주세요',
            icon: 'question',
            input: 'password',
            inputPlaceholder: '비밀번호를 입력해주세요',
            inputAttributes: {
              maxlength: 10,
              autocapitalize: 'off',
              autocorrect: 'off'
            }
          })

          isEntered = await rest_room.joinRoom(room_number, pw)
        }
        
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
        if(isEntered) {
          await rest_room.leaveRoom(room_number);
        }
        Swal.fire({
            icon: 'warning',
            title: error,
            timer: 3000,
          })
        router.replace('/main');
      }
    }

    /**
     * 0: usual, 1: room closed, 2: kicked
     */
    async function leaveRoom(leaveCase) {
      // REST request
      await rest_room.leaveRoom(room_number);

      // show alert
      switch(leaveCase) {
        case 0:
          if(store.getters.isRoomHost) {
            const result = await Swal.fire({
              icon: 'warning',
              title: '방을 유지시킨 채로 \n나가시겠습니까?',
              showCancelButton: true,
              confirmButtonText: '네, 유지시키겠습니다',
              cancelButtonText: '아니요'
            })
            // delete room
            if(!result.isConfirmed && result.dismiss !== Swal.DismissReason.backdrop) {
              store.commit('sendClosed');
              try {
                await rest_room.removeRoom(room_number);
              } catch (error) {
                Swal.fire({
                  icon: 'warning',
                  title: 'error',
                })
              }
              // wait data channel
              await Swal.fire({
                icon: 'success',
                title: '방을 닫는 중입니다',
                showConfirmButton: false,
                timer: 2000,
              })
            }
          }
          break;
        case 1:
          await Swal.fire({
            icon: 'warning',
            title: '방장에 의해 방이 닫혔습니다',
            timer: 3000,
            backdrop: 'rgba(124, 185, 79, 0.28)',
          })
          break;
        case 2:
          await Swal.fire({
            icon: 'error',
            title:'방에서 강제 퇴장당했습니다',
            timer: 2500,
            backdrop: 'rgba(214, 46, 54, 0.28)',
          })
          break;
      }

      // APP Server Socket disconnect
      // await store.dispatch("leaveRoom");
      // router.replace('/main');
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
      toggleFilter,

      showWarnToast,
      showInfoToast,

      kickUser,
      reportUser,

      isHost : computed(() => store.getters.isRoomHost),
      userNickname : computed(()=> store.getters.getNickname),

      ya_zanya,
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
