<template>
  <div class="main-lobby" :class="[$root.theme ? 'light' : 'dark']">
    <div class="d-flex flex-column align-items-center" style="padding: 8px">
      <div class="spacer"></div>

      <!-- search -->
      <div class="search main-color outer d-flex align-items-center">
        <input class="search_input" type="text" placeholder="방 검색" />
        <a href="#" class="search_icon"><i class="bi bi-search"></i></a>
      </div>
      <div class="spacer"></div>
      <div class="spacer"></div>

      <!-- room history -->
      <div style="width: 90%; font-size: 24px; font-weight: bold">
        이전 참여방
      </div>
      <div class="spacer"></div>

      <div class="container-fluid" style="border-radius: 24px; width: 90%">
        <div class="row">
          <div
            v-for="room in roomHistory"
            :key="room.room.roomNum"
            class="room col-12 col-sm-6 col-md-4 col-lg-3"
          >
            <MainRoomComp :room="room" />
          </div>

          <!-- new room -->
          <div class="room col-12 col-sm-6 col-md-4 col-lg-3">
            <div class="room-template outer main-color" @click="openModal">
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
              centered
              ok-disabled
              hide-header
              hide-footer
              no-close-on-backdrop
              @close="resetModal()"
              @hidden="resetModal()"
            >
              <div style="font-family: 'Noto Sans KR', sans-serif">
                <b-card>
                  <!-- <h4>나의 공부방 만들기</h4>
                  <hr class="border-top border-dark border-2 my-2 p-0 mb-4" /> -->
                  <b-form-input
                    class="mb-3"
                    v-model="newRoom.roomName"
                    type="text"
                    placeholder="방 이름"
                    required
                    :state="validation.roomName"
                  ></b-form-input>
                  <b-form-invalid-feedback :state="validation.roomName">
                    방 이름은 1-20글자여야 해요
                  </b-form-invalid-feedback>

                  <b-form-checkbox v-model="newRoom.roomHasPw">
                    <small><i class="bi bi-lock-fill"></i>&nbsp;비밀방</small>
                  </b-form-checkbox>

                  <b-form-input
                    v-if="newRoom.roomHasPw"
                    class="mt-2"
                    placeholder="방 비밀번호"
                    v-model="newRoom.roomPw"
                    :disabled="!newRoom.roomHasPw"
                    :state="validation.roomPw"
                  >
                  </b-form-input>
                  <b-form-invalid-feedback :state="validation.roomPw">
                    비밀번호는 최소 4글자의 영문/숫자여야 해요
                  </b-form-invalid-feedback>

                  <b-form-textarea
                    class="mt-3"
                    v-model="newRoom.roomDescription"
                    placeholder="방에 대한 설명이에요"
                    rows="2"
                    max-rows="2"
                    no-resize
                    maxlength="60"
                  >
                  </b-form-textarea>

                  <div class="d-flex mt-3 align-items-center">
                    <div class="flex-grow-1">
                      <!-- <label class="mt-3 ms-1" for="roomTags"><small>방 추천시 사용될 태그를 추가할 수 있어요</small></label> -->
                      <b-form-tags
                        input-id="roomTags"
                        v-model="newRoom.roomHashTags"
                        separator=" ,;"
                        :state="validation.roomHashTags"
                        placeholder="방 태그"
                        duplicateTagText="중복된 태그가 있어요 "
                        tagVariant="success"
                        remove-on-delete
                      >
                      </b-form-tags>
                      <b-form-invalid-feedback :state="validation.roomHashTags">
                        방 태그는 최대 3개까지 지정할 수 있어요
                      </b-form-invalid-feedback>
                    </div>

                    <Popper arrow>
                      <i
                        class="bi bi-question-circle ms-2"
                        style="vertical-align: middle"
                      ></i>
                      <template #content>
                        방 추천 및 검색 시 사용돼요
                      </template>
                    </Popper>
                  </div>

                  <div class="d-flex mt-3" style="space-between">
                    <b-form-checkbox v-model="newRoom.roomSound">
                      <small class="me-4"
                        ><i class="bi bi-mic-mute-fill"></i>&nbsp;마이크
                        끄기</small
                      >
                    </b-form-checkbox>
                    <b-form-checkbox v-model="newRoom.roomVideo">
                      <small
                        ><i class="bi bi-camera-video-off-fill"></i
                        >&nbsp;&nbsp;캠화면 끄기</small
                      >
                    </b-form-checkbox>
                  </div>

                  <b-button v-b-toggle.collapse class="my-1 mt-3"
                    >방 세부 설정</b-button
                  >

                  <b-collapse id="collapse" class="mt-2">
                    <b-card>
                      <label for="range-1"
                        >방 최대 인원 : {{ newRoom.roomCapacity }} 명</label
                      >
                      <b-form-input
                        id="range-1"
                        v-model="newRoom.roomCapacity"
                        type="range"
                        min="1"
                        max="15"
                      ></b-form-input>

                      <div class="d-flex">
                        <b-form-checkbox v-model="newRoom.useAlarm">
                          <small
                            ><i class="bi bi-alarm-fill"></i>&nbsp;공부 알람
                            사용하기</small
                          >
                        </b-form-checkbox>
                        <Popper arrow>
                          <i
                            class="bi bi-question-circle ms-2"
                            style="vertical-align: middle"
                          ></i>
                          <template #content>
                            지정된 시간 마다 알람을 틀어줘요
                          </template>
                        </Popper>
                      </div>

                      <div v-if="newRoom.useAlarm">
                        <label class="mt-2" for="range-1"
                          >공부 시간 : {{ newRoom.roomStudyTime }} 분</label
                        >
                        <b-form-input
                          id="range-1"
                          v-model="newRoom.roomStudyTime"
                          type="range"
                          min="10"
                          max="300"
                          step="10"
                        ></b-form-input>

                        <label class="mt-2" for="range-1"
                          >쉬는 시간 : {{ newRoom.roomRestTime }} 분</label
                        >
                        <b-form-input
                          id="range-1"
                          v-model="newRoom.roomRestTime"
                          type="range"
                          min="5"
                          max="60"
                          step="5"
                        ></b-form-input>
                      </div>

                      <label class="mt-3" for="formFile">방 썸네일 사진</label>
                      <input
                        class="form-control"
                        type="file"
                        id="roomThumbnail"
                        accept="image/png, image/jpeg"
                      />
                    </b-card>
                  </b-collapse>
                </b-card>
                <div class="d-flex mt-2">
                  <b-button
                    variant="success"
                    :disabled="!isAllValid()"
                    @click="makeRoom"
                    >완료</b-button
                  >
                  <div class="flex-grow-1"></div>
                  <b-button @click="closeModal">닫기</b-button>
                </div>
              </div>
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
            :key="room.room.roomNum"
            class="room col-12 col-sm-6 col-md-4 col-lg-3"
          >
            <MainRoomComp :room="room" />
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import {
  ref,
  computed,
  nextTick,
  onBeforeMount,
  onMounted,
  reactive,
} from 'vue';
import Swal from 'sweetalert2';
import rest_room from '@/rest/room';
import rest_thumbnail from '@/rest/thumbnail';
import MainRoomComp from './MainRoomComp.vue';
import { useRouter } from 'vue-router';

export default {
  components: {
    MainRoomComp,
  },

  setup() {
    // --------------------- load Room list ------------------------ //
    const roomHistory = ref();
    const roomRecommend = ref();

    onBeforeMount(() => {
      getRoomList();
    });

    async function getRoomList() {
      roomHistory.value = await rest_room.getRoomHistoryList('');
      roomRecommend.value = await rest_room.getRoomRecommendList('');
    }

    // ------------------------ modal control ---------------------------- //
    const isShow = ref(false);
    function openModal() {
      isShow.value = true;
    }

    function closeModal() {
      isShow.value = false;
    }

    function resetModal() {
      newRoom.roomName = null;
      newRoom.roomDescription = '';
      newRoom.roomHasPw = false;
      newRoom.roomPw = null;
      newRoom.roomHashTags = [];
      newRoom.roomCapacity = 15;
      newRoom.roomSound = false;
      newRoom.roomVideo = false;
      newRoom.roomStudyTime = 0;
      newRoom.roomRestTime = 0;
      newRoom.useAlarm = false;
    }
    // ---------------------- validation -------------------------- //
    const validation = reactive({
      roomName: computed(() => {
        return newRoom.roomName == null
          ? null
          : new RegExp(/^.{1,20}$/).test(newRoom.roomName);
      }),
      roomPw: computed(() => {
        return newRoom.roomHasPw
          ? newRoom.roomPw == null
            ? null
            : new RegExp(/^[a-zA-Z0-9]{4,10}$/).test(newRoom.roomPw)
          : null;
      }),
      roomHashTags: computed(() => {
        return newRoom.roomHashTags.length <= 3 ? null : false;
      }),
    });
    function isAllValid() {
      // check null
      let roomNameVali = validation.roomName || false;
      let roomPwVali = validation.roomPw || true;
      let roomHashTagsVali = validation.roomHashTags || true;

      return roomNameVali && roomPwVali && roomHashTagsVali;
    }

    // ---------------------- make room ---------------------------- //
    const newRoom = reactive({
      roomName: null,
      roomDescription: null,
      roomHasPw: false,
      roomPw: null,

      roomCapacity: 15,
      roomSound: false,
      roomVideo: false,
      roomStudyTime: 0,
      roomRestTime: 0,
      roomHashTags: [],

      useAlarm: false,
    });

    const router = useRouter();
    async function makeRoom() {
      // Exit when the form isn't valid
      if (!isAllValid()) {
        return;
      }

      try {
        // data prehandle
        newRoom.roomSound = !newRoom.roomSound;
        newRoom.roomVideo = !newRoom.roomVideo;

        // create room
        let room_num = await rest_room.creatRoom(newRoom);

        // upload thumbnail
        let input = document.getElementById('roomThumbnail');
        if (input.files.length > 0) {
          const formData = new FormData();
          formData.append('thumbnail', input.files[0]);
          await rest_thumbnail.addThumbnail(room_num, formData);
        }

        // add hashtag
        console.log('added tags', newRoom.roomHashTags);
        await rest_room.addHashTags(room_num, newRoom.roomHashTags);

        await nextTick();
        getRoomList();
        closeModal();

        router.replace({ path: '/studyroom/' + room_num });
      } catch (error) {
        Swal.fire({
          icon: 'warning',
          title: error,
        });
      }
    }

    // ---------------- thumbnail ----------------------- //
    onMounted(() => {
      // file validate
      const imageInput = document.getElementById('roomThumbnail');
      imageInput.addEventListener('change', (event) => {
        const target = event.target;
        if (target.files && target.files[0]) {
          // Max file size is 5MB
          const maxAllowedSize = 5 * 1024 * 1024;
          if (target.files[0].size > maxAllowedSize) {
            Swal.fire({
              icon: 'warning',
              title: '파일의 최대 크기는 5MB 입니다',
            });
            target.value = '';
          }

          // check file type
          let fileName = target.files[0].name;
          let fileDot = fileName.lastIndexOf('.');
          let fileType = fileName
            .substring(fileDot + 1, fileName.length)
            .toLowerCase();

          if (fileType !== 'png' && fileType !== 'jpg') {
            Swal.fire({
              icon: 'warning',
              title: '지원하지 않는 확장자입니다',
            });
            target.value = '';
          }
        }
      });
    });

    return {
      isShow,
      roomHistory,
      roomRecommend,
      validation,
      newRoom,
      // functions
      openModal,
      closeModal,
      resetModal,
      makeRoom,
      isAllValid,
    };
  },
};
</script>

<style>
.main-lobby {
  /* background-color: var(--light-main-color); */
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

/* disable tags add button */
.b-form-tags-button {
  visibility: hidden;
}

/* check box color adjust */
.form-check-input:checked {
  background-color: #404040;
  border-color: #404040;
}

/* font for modal */
@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap');

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

/* range slider color setting */
input[type='range']::-webkit-slider-thumb,
input[type='range']::-webkit-slider-thumb:active,
input[type='range']::-webkit-slider-thumb:focus {
  background: #333333;
}
input[type='range']::-moz-range-thumb,
input[type='range']::-moz-range-thumb:active,
input[type='range']::-moz-range-thumb:focus {
  background: #333333;
}
input[type='range']::-ms-thumb,
input[type='range']::-ms-thumb:active,
input[type='range']::-ms-thumb:focus {
  background: #333333;
}
</style>
