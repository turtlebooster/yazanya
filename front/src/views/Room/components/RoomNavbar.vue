<template>
  <nav
    class="navbar justify-content-center w-100 h-100 border-bottom shadow-sm py-1"
  >
    <b-button
      class="align-items-center me-auto py-1 pe-4 ps-3"
      :class="[
        $root.theme ? 'light-btn' : 'dark-btn',
        planner_play_anim ? 'planner-anim-active' : '',
      ]"
      style="
        font-size: 1.3em;
        border-top-right-radius: 2rem;
        border-bottom-right-radius: 2rem;
      "
      @click="openPlanner()"
    ><i class="bi bi-calendar-week"> </i></b-button>
    
    <img src="@/assets/logo/title_logo_009e73.png" class="d-inline-block align-top h-100" alt="logo">

    <b-button
      class="align-items-center ms-auto py-1 pe-3 ps-4"
      :class="[
        $root.theme ? 'light-btn' : 'dark-btn',
        chat_play_anim ? 'chat-anim-active' : '',
      ]"
      style="
        font-size: 1.3em;
        border-top-left-radius: 2rem;
        border-bottom-left-radius: 2rem;
      "
      @click="openChat()"
    ><i class="bi bi-chat-dots"></i></b-button>
  </nav>
</template>

<script>
import { ref } from 'vue';

export default {
  setup(props, { emit }) {
    // flags for setting class
    var planner_play_anim = ref(false);
    var chat_play_anim = ref(false);

    function openPlanner() {
      planner_play_anim.value = false;
      emit('togglePlanner');
    }
    function openChat() {
      chat_play_anim.value = false;
      emit('toggleChat');
    }

    return {
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

.light-btn {
  background-color :#545664;
  color: #EEEEEE;
}
.dark-btn {
  background-color: #EEEEEE;
  color: #545664;
}

.planner-anim-active,
.chat-anim-active {
  animation-duration: 750ms;
  animation-name: alram;
  background-color: var(--main-color);
}
</style>
