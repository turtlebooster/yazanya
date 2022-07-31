<template>
  <div class="main-lobby">
    <div class="d-flex flex-column align-items-center" style="padding: 8px">
      <div class="spacer"></div>

      <!-- search -->
      <div class="search outer d-flex align-items-center">
        <input class="search_input" type="text" placeholder="Search here..." />
        <a href="#" class="search_icon"><i class="bi bi-search"></i></a>
      </div>
      <div class="spacer"></div>
      <div class="spacer"></div>

      <!-- room history -->
      <div style="width: 80%">이전에 참여했던 방</div>
      <div class="spacer"></div>

      <div
        class="main-lobby-history container-fluid"
        style="border-radius: 24px; width: 80%"
      >
        <div class="row">
          <div
            v-for="room in state.roomHistory"
            :key="room.userNum"
            class="col-12 col-sm-6 col-md-4 col-lg-3 col-xl-2"
          >
            <div
              class="main-room outer d-flex flex-column"
              style="margin: auto; margin-top: 16px"
            >
              <router-link
                :to="room.codeNum"
                class="main-room-component"
                style="text-decoration: none"
              >
                <!-- thumbnail -->
                <img
                  :src="require(`@/assets/thumbnail/${room.thumbnailNum}.jpg`)"
                  alt="main-room-thumbnail"
                  style="
                    padding: 8px;
                    height: 60%;
                    width: 100%;
                    object-fit: cover;
                    border-radius: 24px;
                  "
                />

                <div class="d-flex">
                  <!-- profile -->
                  <img
                    :src="require(`@/assets/avatar/${room.userNum}.jpg`)"
                    alt="main-room-profile"
                    style="
                      height: 40px;
                      width: 40px;
                      object-fit: cover;
                      border-radius: 50%;
                      border: 4px solid white;
                    "
                  />

                  <div class="d-flex flex-column">
                    <!-- title -->
                    <div>{{ room.title }}</div>
                    <div style="font-size: 2pt">{{ room.detail }}</div>
                  </div>
                </div>
              </router-link>
            </div>
          </div>

          <!-- new room -->
          <div class="col-12 col-sm-6 col-md-4 col-lg-3 col-xl-2">
            <div
              class="main-room inner d-flex flex-column"
              style="margin: auto; margin-top: 16px"
            >
              <router-link to="newroom" class="main-room-component text-center">
                <div
                  style="padding-top: 40%; font-size: 30px; font-weight: bold"
                >
                  +
                </div>
              </router-link>
            </div>
          </div>
        </div>
      </div>

      <div class="spacer"></div>
      <div class="liner"></div>
      <div class="spacer"></div>
      <div class="spacer"></div>

      <!-- room recommend -->
      <div style="width: 80%">추천하는 공부방</div>
      <div class="spacer"></div>
    </div>
  </div>
</template>

<script>
import { reactive } from 'vue';

export default {
  setup() {
    const state = reactive({
      testNum: 1,
      roomHistory: [
        {
          thumbnailNum: 1,
          userNum: 3,
          codeNum: '/studyroom/b310',
          title: '공부 1대1 고수만',
          detail: '쉬는 시간 세팅 0으로 해놨습니다. 쫄?',
          hashTag: ['#1', '#2', '#3'],
        },
        {
          thumbnailNum: 0,
          userNum: 2,
          codeNum: '/studyroom/b311',
          title: 'ASMR',
          detail: '카페 분위기에서 공부하실 분?',
          hashTag: ['#1', '#2', '#3'],
        },
      ],
    });

    return { state };
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
  caret-color: var(--main-color);

  overflow: hidden;
}

.search:hover > .search_icon {
  background: var(--main-color);
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

.main-room * {
  text-decoration: none;
  color: #000000;

  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}

.main-room {
  background: white;
  border-radius: 16px;
  overflow: hidden;
  position: relative;
}

.main-room:after {
  content: '';
  display: block;
  padding-bottom: 100%;
}

.main-room-component {
  position: absolute;
  z-index: 10;
  width: 100%;
  height: 100%;
}
</style>
