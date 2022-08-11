<template>
  <div class="list" :class="[$root.theme ? 'light' : 'dark']">
    <div>
      프로필
    </div>
    <div>
      <span>화면 모드</span>
      <button @click="modeToggleLight">라이트 모드</button>
      <button @click="modeToggleDark">다크 모드</button>
    </div>
    <div>
      <span>사이드바</span>
      <button @click="sideToggleFix">고정</button>
      <button @click="sideToggleNoFix">고정 ㄴㄴ</button>
    </div>
  </div>
</template>

<script>
import { ref } from 'vue'
import { useStore } from 'vuex'

export default {
  setup() {
    const store = useStore();
    localStorage.setItem('sidebar', 'fix')
    var sideToggle = ref(localStorage.getItem('sidebar'));
    var modeToggle = ref(localStorage.getItem('theme'));
    
    if (sideToggle.value == null) {
      sideToggle.value = 'fix';
      localStorage.setItem('sidebar', true);
    }

    if (modeToggle.value == null) {
      modeToggle.value = true;
      localStorage.setItem('theme', true);
    }

    console.dir('local : ' + localStorage.getItem('theme'));
    // console.dir('root : ' + this.$root.theme);
    function sideToggleFix() {
      sideToggle.value = "fix"
      store.dispatch('setSidebarToggle')
      localStorage.setItem('sidebar',JSON.stringify(sideToggle.value))
      document.documentElement.style.setProperty('--size-w-side-hover', '64px');
    }

    function sideToggleNoFix() {
      sideToggle.value = "nofix"
      store.dispatch('setSidebarToggle')
      localStorage.setItem('sidebar',JSON.stringify(sideToggle.value))
      document.documentElement.style.setProperty('--size-w-side-hover', '200px');
    }

    function modeToggleLight() {
      modeToggle.value = true
      store.dispatch('setModeToggle')
      localStorage.setItem('theme',JSON.stringify(modeToggle.value))
      console.log(modeToggle.value)
      location.reload()
      // console.log(store.state.toggle.modeToggle)
    }

    function modeToggleDark() {
      modeToggle.value = false
      store.dispatch('setModeToggle')
      localStorage.setItem('theme',JSON.stringify(modeToggle.value))
      console.log(modeToggle.value)
      location.reload()
      // console.log(store.state.toggle.modeToggle)
    }
    return {
      sideToggleFix,
      sideToggleNoFix,
      sideToggle,
      modeToggleLight,
      modeToggleDark,
      modeToggle,
    }
  }
};
</script>

<style>

</style>