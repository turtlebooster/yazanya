<template>
  <div class="d-flex flex-column" style="height: 100vh; width: 100vw">
    <div
      class="room-navbar"
      :class="[$root.theme ? 'light-back-only' : 'dark-back-only']"
      style="height: 52px"
    >
      <room-nav @toggle-planner="togglePlanner()" @toggle-chat="toggleChat()" />
    </div>

    <div
      class="room-main d-flex justify-content-center"
      :class="[$root.theme ? 'light' : 'dark']"
      style="height: 92%"
    >
      <!-- Planner Side bar -->
      <div
        class="planner-sidebar me-auto shadow"
        :style="{
          width: planner_width + '%',
          minWidth: planner_width != 0 ? 200 + 'px' : 0,
          overflow: 'auto',
          height: '95%',
          maxHeight: '95%',
        }"
      >
        <planner-sidebar ref="plannerSidebar" />
      </div>

      <!-- Video Content -->
      <div
        class="video-plane d-flex flex-column w-100 m-2 rounded-3 shadow"
        :class="[$root.theme ? 'light' : 'dark']"
        style="overflow: hidden; height: 95%"
      >
        <div
          class="video-room-info-topbar d-flex justify-content-center mb-auto p-1 pt-2"
          style="height: 8%; min-height: 52px"
        >
          <i
            class="ms-2 mt-1 me-auto"
            :class="[isPrivateRoom ? 'bi bi-lock-fill' : '']"
            style="font-size: 1.5em; font-style: normal; font-weight: bold"
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
            <ul
              class="dropdown-menu dropdown-menu-end"
              aria-labelledby="dropdownMenuLink"
            >
              <li>
                <a
                  class="dropdown-item"
                  href="#"
                  @click="showRoomInfo"
                  style="font-size: 1.1em"
                >
                  <i class="bi bi-info-circle me-3" style="font-size: 1.2em"></i
                  >방 정보</a
                >
              </li>
              <li>
                <a
                  class="dropdown-item"
                  href="#"
                  @click="copyRoomURL"
                  style="font-size: 1.2em"
                >
                  <i class="bi bi-share-fill me-3"></i>방 참여 링크</a
                >
              </li>
              <li>
                <a
                  class="dropdown-item"
                  href="#"
                  @click="showMemberInfo"
                  style="font-size: 1.2em"
                >
                  <i class="bi bi-people-fill me-3"></i>참가자 정보</a
                >
              </li>
            </ul>
          </div>
        </div>

        <div
          class="video-components-plane d-flex justify-content-center align-content-center flex-grow-1 flex-wrap"
          style="overflow: hidden"
          ref="video_plane"
        >
          <div
            v-for="(value, name) in participants"
            :key="name"
            :ref="
              (el) => {
                members[name] = el;
              }
            "
            :style="{
              position: 'relative',
              width: each_video_width + 'px',
              height: +each_video_height + 'px',
            }"
          >
            <p
              class="text-center align-middle rounded m-0 px-1"
              :class="[$root.theme ? 'light' : 'dark']"
              style="position: absolute; top: 10px; left: 10px; z-index: 2"
            >
              {{ name }}
            </p>
            <b-dropdown
              v-if="!(name === userNickname)"
              style="
                position: absolute;
                bottom: 10px;
                right: 10px;
                z-index: 2;
                border-radius: 4px;
              "
              :class="[$root.theme ? 'light-back-only' : 'dark-back-only']"
              size="sm"
              variant="link"
              toggle-class="text-decoration-none"
              no-caret
            >
              <template #button-content
                ><i
                  class="bi bi-three-dots-vertical"
                  :class="[
                    $root.theme ? 'light-color-only' : 'dark-color-only',
                  ]"
                ></i
              ></template>
              <b-dropdown-item href="#" @click.prevent="ya_zanya(name)"
                ><i
                  class="bi bi-alarm-fill"
                  style="color: #8e2b80; font-style: normal; font-size: 1.2em"
                  >&nbsp;야! 자냐?</i
                ></b-dropdown-item
              >
              <b-dropdown-divider></b-dropdown-divider>
              <b-dropdown-item
                v-if="isHost"
                href="#"
                @click.prevent="kickUser(name)"
                ><i
                  class="bi bi-indent"
                  style="color: #d15253; font-style: normal"
                  >&nbsp;강제 퇴장</i
                ></b-dropdown-item
              >
              <b-dropdown-item href="#" @click.prevent="reportUser(name)"
                ><i
                  class="bi bi-exclamation-diamond"
                  style="color: #b2b56f; font-style: normal"
                  >&nbsp;신고하기</i
                ></b-dropdown-item
              >
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
            :disabled="!isRoomAudioOn"
          >
            <i
              v-if="isAudioOn"
              class="bi bi-mic-fill"
              style="font-size: 1.3em"
            ></i>
            <i
              v-if="!isAudioOn"
              class="bi bi-mic-mute-fill"
              style="font-size: 1.3em"
            ></i>
          </b-button>
          <b-button
            class="rounded-circle mx-2"
            :class="[$root.theme ? 'dark' : 'light']"
            @click="toggleVideo()"
            :disabled="!isRoomVideoOn"
          >
            <i
              v-if="isVideoOn"
              class="bi bi-camera-video-fill"
              style="font-size: 1.3em"
            ></i>
            <i
              v-if="!isVideoOn"
              class="bi bi-camera-video-off-fill"
              style="font-size: 1.3em"
            ></i>
          </b-button>
          <b-button
            class="rounded-circle mx-2"
            :class="[$root.theme ? 'dark' : 'light']"
            @click="toggleFilter()"
          >
            <i
              v-if="isFilterOn"
              class="bi bi-emoji-smile-fill"
              style="font-size: 1.3em"
            ></i>
            <i
              v-if="!isFilterOn"
              class="bi bi-emoji-sunglasses-fill"
              style="font-size: 1.3em"
            ></i>
          </b-button>

          <b-button
            pill
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
          minWidth: chat_width != 0 ? 200 + 'px' : 0,
          height: '98%',
        }"
      >
        <chat-sidebar :isSidebarOn="chat_width" />
      </div>
    </div>
  </div>

  <b-modal
    v-model="isRoomInfoShow"
    centered
    ok-disabled
    hide-header
    hide-footer
  >
    <room-info-modal />
  </b-modal>

  <b-modal
    v-model="isMemberInfoShow"
    centered
    ok-disabled
    hide-header
    hide-footer
  >
    <room-participants-modal :participants="participantsInfo" />
  </b-modal>
</template>

<script>
import {
  ref,
  computed,
  onBeforeMount,
  onUpdated,
  onMounted,
  onBeforeUnmount,
  nextTick,
} from 'vue';
import { useRouter } from 'vue-router';
import { useStore } from 'vuex';
import { useToast } from 'bootstrap-vue-3';
import Swal from 'sweetalert2';

import RoomNav from './components/RoomNavbar.vue';
import ChatSidebar from './components/RoomChatSidebar.vue';
import PlannerSidebar from './components/RoomPlannerSidebar.vue';

import RoomInfoModal from './components/RoomInfoModal.vue';
import RoomParticipantsModal from './components/RoomParticipantsModal.vue';

import rest_room from '@/rest/room';
import rest_user from '@/rest/user';

export default {
  components: {
    RoomNav,
    ChatSidebar,
    PlannerSidebar,
    RoomInfoModal,
    RoomParticipantsModal,
  },

  setup() {
    const router = useRouter();
    const store = useStore();

    const plannerSidebar = ref(null);

    // --------------------- Modal control -------------------- //
    let isRoomInfoShow = ref(false);
    let isMemberInfoShow = ref(false);

    function showRoomInfo() {
      isMemberInfoShow.value = false;
      isRoomInfoShow.value = true;
    }

    let participantsInfo = ref([]);
    async function showMemberInfo() {
      // get info from server
      participantsInfo.value = await rest_room.getParticipants(room_number);

      isRoomInfoShow.value = false;
      isMemberInfoShow.value = true;
    }

    // ---------------------- Room Share Link ----------------- //
    async function copyRoomURL() {
      try {
        await navigator.clipboard.writeText(document.URL);
        showInfoToast('Info', '링크를 클립보드에 복사하였습니다');
      } catch (error) {
        showWarnToast('Warn', '클립보드 복사 중 문제가 발생하였습니다');
      }
    }

    // ---------------------- Toast ------------------------- //
    const toast = useToast();
    function showWarnToast(title, message) {
      toast.show(
        { title: title, body: message },
        { pos: 'top-center', variant: 'danger' }
      );
    }
    function showInfoToast(title, message) {
      toast.show(
        { title: title, body: message },
        { pos: 'top-center', variant: 'info' }
      );
    }

    // ---------------- kick and report --------------------- //
    async function kickUser(userNickname) {
      const result = await Swal.fire({
        icon: 'warning',
        title: userNickname + '님을 강퇴하시겠습니까?',
        showCancelButton: true,
        confirmButtonText: '나가',
        cancelButtonText: '아차, 실수',
      });

      if (result.isConfirmed && store.getters.isRoomHost) {
        store.commit('kickUser', userNickname);
      }

      try {
        await rest_room.kickUser(userNickname, store.state.Room.room.roomNum);
      } catch (error) {
        Swal.fire({
          icon: 'warning',
          title: error,
          showConfirmButton: false,
          timer: 2000,
        });
      }
    }

    async function reportUser(userNickname) {
      const { value: text } = await Swal.fire({
        icon: 'warning',
        input: 'textarea',
        title: userNickname + '님을 신고합니다',
        inputLabel: '신고 당시 상황을 적어주세요',
        inputAttributes: {
          resize: 'none',
          maxlength: '150',
        },
        showCancelButton: true,
      });

      console.debug(text);
      Swal.fire({
        icon: 'error',
        title: '죄송합니다',
        text: '현재 고객센터는 운영 중이 아닙니다',
      });
    }

    store.watch(
      (state, getters) => getters.getLeaveTriggerFlag,
      (flag) => {
        // watch kicked or closed room
        leaveRoom(flag);
      }
    );

    // -------------------- alarm Ya-ZaNya ---------------------------- //
    let ya_zanya_cooltime = ref(false);
    async function ya_zanya(userNickname) {
      if (ya_zanya_cooltime.value) {
        // limit cool time
        Swal.fire({
          title: '야! 자냐? 는 2분마다 사용할 수 있습니다',
          timer: 2000,
        });
        return;
      }
      const result = await Swal.fire({
        icon: 'warning',
        title: userNickname + '님에게 야! 자냐? 를 \n시전하시겠습니까?',
        text: '공부를 방해할 수도 있습니다...',
        showCancelButton: true,
        confirmButtonText: '야! 자냐?',
        cancelButtonText: '아니오',
      });

      if (result.isConfirmed) {
        ya_zanya_cooltime.value = true;
        store.commit('sendYaZanya', { target: userNickname, flag: 1 });
        setTimeout(() => {
          // reset cooltime
          ya_zanya_cooltime.value = false;
        }, 120000);
      }
    }

    store.watch(
      (state, getters) => getters.getYaZanyaTriggerCode,
      (code) => {
        console.log('yazanya watched', code);

        // watch ya zanya
        if (code == 1) {
          play_ya_zanya();
          Swal.fire({
            icon: 'question',
            title: '야! 자냐?',
            showConfirmButton: false,
            showCancelButton: true,
            cancelButtonColor: '#d33',
            cancelButtonText: '안잔다',
            timer: 10000,
            timerProgressBar: true,
          }).then((result) => {
            if (result.dismiss === Swal.DismissReason.timer) {
              // time over
              store.commit('sendYaZanya', {
                target: store.state.Room.yaZanyaTrigger.sender,
                flag: 3,
              });
            } else {
              // send not sleep
              store.commit('sendYaZanya', {
                target: store.state.Room.yaZanyaTrigger.sender,
                flag: 2,
              });
            }
            store.commit('setYaZanya', { code: 0 });
          });
        } else if (code == 2) {
          // recevied not sleep
          Swal.fire({
            icon: 'success',
            title: '안잔다고 합니다~',
            showConfirmButton: false,
            timer: 2000,
          });
          store.commit('setYaZanya', { code: 0 });
        } else if (code == 3) {
          // recevied not sleep
          Swal.fire({
            icon: 'error',
            title: '자는 중인 것 같습니다',
            showConfirmButton: false,
            timer: 2000,
          });
          store.commit('setYaZanya', { code: 0 });
        }
      }
    );

    function play_ya_zanya() {
      let audio = new Audio(require('@/assets/audio/ya.mp3'));
      audio.play();
    }

    // --------------- sidebar sizing event handling  ↓ --------------- //
    const SIDEBAR_WIDTH = 28;
    const planner_width = ref(0);
    const chat_width = ref(SIDEBAR_WIDTH);

    async function togglePlanner() {
      planner_width.value = planner_width.value === 0 ? SIDEBAR_WIDTH : 0;
      nextTick(() => {
        plannerSidebar.value.plannerResize();
      });
    }
    function toggleChat() {
      chat_width.value = chat_width.value === 0 ? SIDEBAR_WIDTH : 0;
    }

    // --------------------- room enter and exit ----------------------- //
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
        if (hasPw) {
          const { value: pw } = await Swal.fire({
            title: '비밀번호를 입력해주세요',
            icon: 'question',
            input: 'password',
            inputPlaceholder: '비밀번호를 입력해주세요',
            inputAttributes: {
              maxlength: 10,
              autocapitalize: 'off',
              autocorrect: 'off',
            },
          });
          isEntered = await rest_room.joinRoom(room_number, pw);
        } else {
          isEntered = await rest_room.joinRoom(room_number, '');
        }

        // gain room info
        let room = await rest_room.getRoomInfo(room_number);
        await store.dispatch('saveRoomInfo', room);

        // gain user info
        let user = await rest_user.getProfile(store.getters.getUserID);
        await store.dispatch('saveUserInfo', user);

        // set UI with Room Info
        isVideoOn.value = store.getters.isVideoEnabled;
        isAudioOn.value = store.getters.isAudioEnabled;

        // connect kurento server
        store.dispatch('joinRoom');
      } catch (error) {
        if (isEntered) {
          await rest_room.leaveRoom(room_number, store.getters.getUserID);
        }
        Swal.fire({
          icon: 'warning',
          title: error,
          timer: 3000,
        });
        router.replace('/main');
      }
    }

    /**
     * 0: usual, 1: room closed, 2: kicked
     */
    async function leaveRoom(leaveCase) {
      // show alert
      switch (leaveCase) {
        case 0:
          // REST request
          await rest_room.leaveRoom(room_number, store.getters.getUserID);
          if (store.getters.isRoomHost) {
            const result = await Swal.fire({
              icon: 'warning',
              title: '방을 유지시킨 채로 \n나가시겠습니까?',
              showCancelButton: true,
              confirmButtonText: '네, 유지시키겠습니다',
              cancelButtonText: '아니요',
              backdrop: false,
            });
            // delete room
            if (!result.isConfirmed) {
              store.commit('sendClosed');
              // wait data channel
              try {
                await rest_room.removeRoom(room_number);
              } catch (error) {
                Swal.fire({
                  icon: 'warning',
                  title: 'error',
                });
              }

              await Swal.fire({
                icon: 'success',
                title: '방을 닫는 중입니다',
                showConfirmButton: false,
                timer: 2000,
              });
            }
          }
          break;
        case 1:
          await Swal.fire({
            icon: 'warning',
            title: '방장에 의해 방이 닫혔습니다',
            timer: 3000,
            backdrop: 'rgba(124, 185, 79, 0.28)',
          });
          break;
        case 2:
          // REST request
          await rest_room.leaveRoom(room_number, store.getters.getUserID);
          await Swal.fire({
            icon: 'error',
            title: '방에서 강제 퇴장당했습니다',
            timer: 2500,
            backdrop: 'rgba(214, 46, 54, 0.28)',
          });
          break;
      }

      // APP Server Socket disconnect (not used cause error)
      // await store.dispatch("leaveRoom");
      // router.replace('/main');
      location.replace(window.location.origin + '/main');
    }

    // -------------------- quit event with rest -------------------- //
    function leaveRoomWithBeacon(event) {
      if (store.getters.getLeaveTriggerFlag != 0) {
        return;
      }
      // send exit room request for participation sync
      if (
        navigator.sendBeacon(
          `${process.env.VUE_APP_SERVER}/room/exit/${room_number}/${store.getters.getUserID}`,
          new FormData()
        )
      ) {
        return;
      }
      event.preventDefault();
      event.returnValue = '';
    }

    onMounted(() => {
      window.addEventListener('beforeunload', leaveRoomWithBeacon);
    });

    onBeforeUnmount(() => {
      window.removeEventListener('beforeunload', leaveRoomWithBeacon);
    });

    // ---------- dynamic video grid for participants ↓ ------------ //
    var nMember = computed(() => store.getters.getParticipantsCount);
    var nCols = computed(() =>
      nMember.value < 3
        ? nMember.value
        : Math.ceil(Math.sqrt(nMember.value + 1))
    );
    var nRows = computed(() => Math.ceil(nMember.value / nCols.value));

    // ------------------- calculate width and height ------------------------ //
    let video_plane = ref(null);
    const video_container_width = computed(() =>
      video_plane.value != null ? video_plane.value.clientWidth - 5 : 0
    );
    const video_container_height = computed(() =>
      video_plane.value != null ? video_plane.value.clientHeight - 5 : 0
    );

    function cal_video_wh(returnWidth) {
      // axis 1 : 1.7
      let width = video_container_width.value / nCols.value;
      let height = width * 0.59;

      let over_height = height * nRows.value - video_container_height.value;
      if (over_height > 0) {
        width -= (over_height / nRows.value) * 1.7;
        height = width * 0.59;
      }
      return returnWidth ? width : height;
    }
    var each_video_width = computed(() => cal_video_wh(true));
    var each_video_height = computed(() => cal_video_wh(false));

    // --------------------- add video on updated participants ----------------------- //
    var members = ref({});
    onUpdated(() => {
      // add video of newly participants
      Object.keys(members.value).forEach((key) => {
        if (!members.value[key]) {
          // check null
          return;
        }
        if (members.value[key].lastChild.tagName === 'VIDEO') {
          // already has video
          return;
        }

        let video = store.state.Room.participants[key].getVideoElement();
        video.style.margin = 'auto';
        video.style.objectFit = 'cover';
        video.setAttribute('width', each_video_width.value - 5);
        video.setAttribute('height', each_video_height.value - 5);
        members.value[key].appendChild(video);
      });

      // -------------------- match width height dynamically ---------------------- //
      Object.keys(members.value).forEach((key) => {
        if (!members.value[key]) {
          // check null
          return;
        }
        // already has video
        if (members.value[key].lastChild.tagName !== 'VIDEO') {
          return;
        }

        members.value[key].lastChild.setAttribute(
          'width',
          each_video_width.value - 5
        );
        members.value[key].lastChild.setAttribute(
          'height',
          each_video_height.value - 5
        );
      });
    });

    // ------------------------- handle audio and video -------------------------- //
    let isVideoOn = ref(true);
    let isAudioOn = ref(true);

    function toggleAudio() {
      isAudioOn.value = !isAudioOn.value;
      if (store.state.Room.user) {
        store.getters.getParticipants[
          store.state.Room.user.userNickname
        ].handleAudio(isAudioOn.value);
      }
    }

    function toggleVideo() {
      isVideoOn.value = !isVideoOn.value;
      if (store.state.Room.user) {
        store.state.Room.participants[
          store.state.Room.user.userNickname
        ].handleVideo(isVideoOn.value);
      }
    }

    // ------------------------- About filter ------------------------- //
    let isFilterOn = ref(false);
    function toggleFilter() {
      isFilterOn.value = !isFilterOn.value;
      store.dispatch('toggleFilter', isFilterOn.value);
    }

    return {
      plannerSidebar,
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

      roomName: computed(() => store.getters.getRoomName),
      isPrivateRoom: computed(() => store.getters.isPrivateRoom),

      participants: computed(() => store.state.Room.participants),
      isConnected: computed(() => store.state.Room.isSocketConnected),

      isVideoOn,
      isAudioOn,

      isRoomVideoOn: computed(() => store.getters.isVideoEnabled),
      isRoomAudioOn: computed(() => store.getters.isAudioEnabled),

      toggleAudio,
      toggleVideo,

      isFilterOn,
      toggleFilter,

      showWarnToast,
      showInfoToast,

      kickUser,
      reportUser,

      isHost: computed(() => store.getters.isRoomHost),
      userNickname: computed(() => store.getters.getNickname),

      ya_zanya,

      showRoomInfo,
      showMemberInfo,
      isRoomInfoShow,
      isMemberInfoShow,

      copyRoomURL,
      participantsInfo,
    };
  },
};
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap');

/* popper style */
:root {
  --popper-theme-background-color: #ffffff;
  --popper-theme-background-color-hover: #ffffff;
  --popper-theme-text-color: #333333;
  --popper-theme-border-width: 0px;
  --popper-theme-border-style: solid;
  --popper-theme-border-radius: 6px;
  --popper-theme-padding: 12px 20px 12px 20px;
  --popper-theme-box-shadow: 0 6px 30px -6px rgba(0, 0, 0, 0.4);
}

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
