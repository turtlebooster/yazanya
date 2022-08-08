<template>
  <div class="main-lobby">
    <div class="d-flex flex-column align-items-center" style="padding: 8px">
      <div class="spacer"></div>

      <!-- search -->
      <div class="search outer d-flex align-items-center">
        <input class="search_input" type="text" placeholder="방 검색" />
        <a href="#" class="search_icon"><i class="bi bi-search"></i></a>
      </div>
      <div class="spacer"></div>
      <div class="spacer"></div>

      <!-- room history -->
      <div style="width: 90%; font-size: 24px; font-weight: bold">
        나의 공부방
      </div>
      <div class="spacer"></div>

      <div class="container-fluid" style="border-radius: 24px; width: 90%">
        <div class="row">
          <div
            v-for="room in roomHistory"
            :key="room.roomNum"
            class="room col-12 col-sm-6 col-md-4 col-lg-3 col-xl-2"
          >
            <!-- room -->
            <div class="room-template outer">
              <router-link
                :to="`/studyroom/${room.roomNum}`"
                class="room-component d-flex flex-column"
              >
                <!-- thumbnail -->
                <div class="room-thumbnail flex-grow-1 flex-shrink-1">
                  <div
                    class="room-thumbnail-image text-align-center"
                    style="
                      color: white;
                      font-size: 40px;
                      margin-top: 8px;
                      text-align: center;
                    "
                  >
                    {{ room.roomNum }}
                  </div>
                </div>

                <div
                  class="d-flex flex-grow-0 flex-shrink-0 align-items-center"
                  style="margin-top: 8px"
                >
                  <!-- profile -->
                  <b-avatar
                    :src="require(`@/assets/avatar/0.jpg`)"
                    size="2em"
                  ></b-avatar>

                  <div
                    class="d-flex flex-column flex-grow-1"
                    style="margin-left: 8px"
                  >
                    <!-- title -->
                    <div style="font-size: 16pt; font-weight: bold">
                      {{ room.roomName }}
                    </div>

                    <!-- detail -->
                    <div style="font-size: 10pt">
                      {{ room.roomDescription }}
                    </div>
                  </div>
                </div>
              </router-link>
            </div>
          </div>

          <!-- new room -->

          <div class="room col-12 col-sm-6 col-md-4 col-lg-3 col-xl-2">
            <div class="room-template outer" @click="openModal">
              <div class="room-component d-flex flex-column">
                <i
                  class="bi bi-plus-circle-fill"
                  style="
                    font-size: 2em;
                    position: absolute;
                    left: 50%;
                    top: 50%;
                    transform: translate(-50%, -50%);
                  "
                ></i>
              </div>
            </div>

            <!-- modal -->
            <b-modal
              v-model="isShow"
              id="modal-newroom"
              title="방 만들기"
              centered
              ok-disabled
              hide-header
              hide-footer
              no-close-on-backdrop
              @close="resetModal"
              @hidden="resetModal"
            >
              <form ref="form" @submit.prevent="handleSubmit">
                <div class="form-row">
                  <div class="col-md-6 mb-3">
                    <b-form-group
                      label="방 이름"
                      label-for="input-room-name"
                      valid-feedback=""
                      invalid-feedback="방의 이름을 적어주세요"
                      :state="validation.name"
                    >
                      <b-form-input
                        id="input-room-name"
                        v-model="newRoom.roomName"
                        :state="validation.name"
                      ></b-form-input>
                    </b-form-group>
                  </div>

                  <div class="col-md-6 mb-3">
                    <b-form-group
                      label="방 설명"
                      label-for="input-room-desc"
                      valid-feedback=""
                      invalid-feedback="설명을 적어주세요"
                      :state="validation.desc"
                    >
                      <b-form-input
                        id="input-room-desc"
                        v-model="newRoom.roomDescription"
                        :state="validation.desc"
                      ></b-form-input>
                    </b-form-group>
                  </div>

                  <div class="col-md-6 mb-3">
                    <b-form-group
                      label="방 비밀번호"
                      label-for="input-room-pw"
                      valid-feedback=""
                      invalid-feedback="4글자 이상이어야 합니다."
                      :state="validation.pw"
                    >
                      <b-form-input
                        id="input-room-pw"
                        v-model="newRoom.roomPw"
                        :state="validation.pw"
                      ></b-form-input>
                    </b-form-group>
                  </div>
                </div>
                <div class="d-flex">
                  <b-button @click="closeModal">닫기</b-button>
                  <div class="flex-grow-1"></div>
                  <b-button @click="makeRoom">방 생성하기</b-button>
                </div>
              </form>
            </b-modal>
          </div>
        </div>
      </div>

      <div class="spacer"></div>
      <div class="liner"></div>
      <div class="spacer"></div>
      <div class="spacer"></div>

      <!-- room recommend -->
      <div style="width: 90%; font-size: 24px; font-weight: bold">
        추천하는 공부방
      </div>
      <div class="spacer"></div>

      <div class="container-fluid" style="border-radius: 24px; width: 90%">
        <div class="row">
          <div
            v-for="room in roomRecommend"
            :key="room.roomNum"
            class="room col-12 col-sm-6 col-md-4 col-lg-3 col-xl-2"
          >
            <!-- room -->
            <div class="room-template outer">
              <router-link
                :to="`/studyroom/${room.roomNum}`"
                class="room-component d-flex flex-column"
              >
                <!-- thumbnail -->
                <div class="room-thumbnail flex-grow-1 flex-shrink-1">
                  <div
                    class="room-thumbnail-image text-align-center"
                    style="
                      color: white;
                      font-size: 40px;
                      margin-top: 8px;
                      text-align: center;
                    "
                  >
                    {{ room.roomNum }}
                  </div>
                </div>

                <div
                  class="d-flex flex-grow-0 flex-shrink-0 align-items-center"
                  style="margin-top: 8px"
                >
                  <!-- profile -->
                  <b-avatar
                    :src="require(`@/assets/avatar/0.jpg`)"
                    size="2em"
                  ></b-avatar>

                  <div
                    class="d-flex flex-column flex-grow-1"
                    style="margin-left: 8px"
                  >
                    <!-- title -->
                    <div style="font-size: 16pt; font-weight: bold">
                      {{ room.roomName }}
                    </div>

                    <!-- detail -->
                    <div style="font-size: 10pt">
                      {{ room.roomDescription }}
                    </div>
                  </div>
                </div>
              </router-link>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { ref, computed, nextTick, onBeforeMount } from 'vue';
import rest_room from '@/rest/room';

export default {
  setup() {
    const form = ref(null);

    const validation = ref({
      name: computed(() => newRoom.value.roomName.length > 0),
      desc: computed(() => newRoom.value.roomDescription.length > 0),
      capa: true,
      sound: true,
      video: true,
      pw: computed(() => newRoom.value.roomPw.length >= 4),
    });

    const newRoom = ref({
      roomName: '',
      roomDescription: '',
      roomCapacity: 25,
      roomSound: true,
      roomVideo: true,
      roomStudyTime: 0,
      roomRestTime: 0,
      roomPw: '',
      roomActive: true,
    });

    const isShow = ref(false);
    const roomHistory = ref();
    const roomRecommend = ref();

    async function init() {
      roomHistory.value = await rest_room.getRoomHistoryList('');
      roomRecommend.value = await rest_room.getRoomRecommendList('');
    }

    function openModal() {
      isShow.value = true;
    }

    function closeModal() {
      isShow.value = false;
    }

    function resetModal() {
      newRoom.value.roomName = '';
      newRoom.value.roomDescription = '';
      newRoom.value.roomPw = '';
    }

    async function makeRoom() {
      // Exit when the form isn't valid
      if (
        !validation.value.name ||
        !validation.value.desc ||
        !validation.value.pw
      ) {
        return;
      }
      await rest_room.creatRoom(newRoom.value);
      await nextTick();
      init();
      closeModal();
    }

    onBeforeMount(() => {
      init();
    });

    return {
      form,
      validation,
      newRoom,
      isShow,
      roomHistory,
      roomRecommend,

      // function
      openModal,
      closeModal,
      resetModal,
      makeRoom,
    };
  },
};
</script>

<style scoped>
.main-lobby {
  background-color: var(--light-main-color);
  height: 100%;
}

.outer {
  box-shadow: 4px 4px 10px -1px rgba(0, 0, 0, 0.25),
    -4px -4px 10px -1px rgba(255, 255, 255, 0.25);
}

.inner {
  box-shadow: inset 4px 4px 10px -1px rgba(0, 0, 0, 0.25),
    inset -4px -4px 10px -1px rgba(255, 255, 255, 0.25);
}

.liner {
  height: 2px;
  width: 80%;
  border-radius: 1px;
  background: var(--light-sub-color);
}

.spacer {
  height: 24px;
}

.search {
  height: 40px;
  width: 80%;
  flex-wrap: nowrap;

  background-color: var(--light-main-color);
  border-radius: 24px;
  padding: 10px;
}

.search_input {
  width: 100px;
  height: 32px;
  color: black;
  /* color: white; */
  border: 0;
  outline: 0;
  background: none;

  padding: 0 10px;
  flex-grow: 1;
  flex-shrink: 1;
  caret-color: var(--theme-color);

  overflow: hidden;
}

.search:hover > .search_icon {
  background: var(--theme-color);
  color: white;
}

.search_icon {
  min-height: 28px;
  min-width: 28px;
  float: right;
  display: flex;
  justify-content: center;
  align-items: center;
  border-radius: 50%;
  color: white;
  background-color: black;
}

a:link {
  text-decoration: none;
}

.room-template * {
  color: #000000;

  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}

.room-template {
  background: white;
  border-radius: 16px;
  overflow: hidden;
  position: relative;
  margin-bottom: 32px;
  margin-left: auto;
  margin-right: auto;

  transition-duration: 0.2s;
}

.room-template:after {
  content: '';
  display: block;
  padding-bottom: 100%;
}

.room-template:hover {
  z-index: 10;
  transform: scale(1.1); /*  default */
  -webkit-transform: scale(1.1); /*  크롬 */
  -moz-transform: scale(1.1); /* FireFox */
  -o-transform: scale(1.1); /* Opera */
}

.room-component {
  padding: 8px;
  position: absolute;
  width: 100%;
  height: 100%;
}

.room-thumbnail {
  width: 100%;
  object-fit: cover;
  border-radius: 8px;
  background: black;
}

.room-thumbnail-image {
  width: 100%;
  height: 100%;
  object-fit: cover;
}
</style>
