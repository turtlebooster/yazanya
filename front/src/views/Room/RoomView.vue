<template>
  <div class="room-navbar" :class="[$root.theme ? 'light' : 'dark']">
    <room-nav @toggle-planner="test()" @toggle-chat="test()"></room-nav>
  </div>
</template>

<script>
import RoomNav from './components/RoomNavbar.vue';

export default {
  components: {
    RoomNav,
  },

  setup() {
    function test() {
      console.log('test');
    }

    return { test };
  },

  //emits: ['toggle-plannerSidebar', 'toggle-chatSidebar'],
};
</script>

<style scoped>
.room-navbar {
  height: 80px;
}
</style>
