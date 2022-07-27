<template>
  <nav
    class="navbar justify-content-center w-100 h-100 border-bottom shadow-sm py-1"
  >
    <i
      id="planner-sidebar-toggle-icon"
      class="toggle-btn bi bi-calendar-week align-items-center me-auto p-2 pe-4"
      :class="[
        $root.theme ? 'dark' : 'light',
        planner_play_anim ? 'planner-anim-active' : '',
      ]"
      style="
        font-size: 2rem;
        border-top-right-radius: 2rem;
        border-bottom-right-radius: 2rem;
      "
      ref="planner_sidebar_toggle_icon"
      @click="openPlanner()"
    ></i>
    <button @click="planner_play_anim = true" class="mx-auto">
      alram Planner
    </button>
    <button @click="chat_play_anim = true" class="mx-auto">alram Chat</button>
    <p id="room-name-text" class="mx-auto">{{ roomname }}</p>

    <i
      id="chat-sidebar-toggle-icon"
      class="toggle-btn bi bi-chat-dots ms-auto p-2 ps-4"
      :class="[
        $root.theme ? 'dark' : 'light',
        chat_play_anim ? 'chat-anim-active' : '',
      ]"
      style="
        font-size: 2rem;
        border-top-left-radius: 2rem;
        border-bottom-left-radius: 2rem;
      "
      ref="chat_sidebar_toggle_icon"
      @click="openChat()"
    ></i>
  </nav>
</template>

<script>
import { ref, onBeforeMount } from 'vue';
import { useRouter } from 'vue-router';

export default {
  setup(props, { emit }) {
    // get Room name
    let roomname = ref(null);
    let router = useRouter();
    onBeforeMount(() => {
      let url = document.URL;
      let idx = url.indexOf('studyroom/') + 10;
      if (idx === -1) {
        alert('올바르지 않은 방주소입니다');
        router.replace('/');
        return;
      }

      // check room number is number
      let room_number = url.slice(idx);
      if (Number.isInteger(room_number)) {
        alert('방 번호가 형식에 맞지 않습니다');
        router.replace('/');
        return;
      }

      // TODO : get roomname from server
      roomname.value = room_number;
    });

    // flags for setting class
    var planner_play_anim = ref(false);
    var chat_play_anim = ref(false);

    //
    function openPlanner() {
      planner_play_anim.value = false;
      emit('togglePlanner');
    }
    function openChat() {
      chat_play_anim.value = false;
      emit('toggleChat');
    }

    return {
      roomname,
      openPlanner,
      openChat,
      planner_play_anim,
      chat_play_anim,
    };
  },
};
</script>

<style scoped>
/* for alram animation*/
@keyframes alram {
  0% {
    background-color: var(--dark-bg-color);
  }
  25% {
    background-color: var(--main-color);
  }
  75% {
    background-color: var(--dark-bg-color);
  }
  100% {
    background-color: var(--main-color);
  }
}

.planner-anim-active,
.chat-anim-active {
  animation-duration: 750ms;
  animation-name: alram;
  background-color: var(--main-color);
}

/* .toggle-btn:hover {
  background-color: var(--main-color);
  transition-duration: 250ms;
} */
</style>
