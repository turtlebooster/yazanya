<template>
  <div
    class="home-footer main-color d-flex align-items-center justify-content-center"
  >
    <div @click="modeToggleLight">라이트모드</div>
    <div @click="modeToggleDark">다크모드</div>
  </div>
</template>

<script>
import { ref } from 'vue';
import { useStore } from 'vuex';

export default {
  setup() {
    const store = useStore();
    var modeToggle = ref(localStorage.getItem('theme'));

    function modeToggleLight() {
      modeToggle.value = true;
      store.dispatch('setModeToggle');
      localStorage.setItem('theme', JSON.stringify(modeToggle.value));
      location.reload();
    }

    function modeToggleDark() {
      modeToggle.value = false;
      store.dispatch('setModeToggle');
      localStorage.setItem('theme', JSON.stringify(modeToggle.value));
      location.reload();
    }

    return {
      modeToggleLight,
      modeToggleDark,
    };
  },
};
</script>

<style scoped>
.home-footer * {
  padding-left: 8px;
  padding-right: 8px;
}
</style>
