<template>
  <div class="list d-flex flex-column" :class="[$root.theme ? 'light' : 'dark']">
    <div class="d-flex justify-content-center" style="font-size:2rem">
      설정
    </div>
    <div class="row">
      <div class="col-3">
        <span>화면 모드</span> 
      </div>
      <div class="col-6">
        <button id="btn" :class="[ $root.theme ? 'on' : 'off']" @click="modeToggleLight">라이트 모드</button>
        <button id="btn" :class="[ $root.theme ? 'off' : 'on']" @click="modeToggleDark">다크 모드</button>
      </div>
    </div>
    <div class="row">
      <div class="col-3">
        <span>사이드바</span>
      </div>
      <div class="col-6">
        <button id="btn" :class="[ $root.sidebar ? 'on' : 'off' ]" @click="sideToggleFix">고정</button>
        <button id="btn" :class="[ $root.sidebar ? 'off' : 'on' ]" @click="sideToggleNoFix">확장</button>
      </div>
    </div>
  </div>
</template>

<script>
import { ref } from 'vue'
import { useStore } from 'vuex'

export default {
  setup() {
    const store = useStore();
    var modeToggle = ref(localStorage.getItem('theme'));
    var sideToggle = ref(localStorage.getItem('sidebar'));
    
    function modeToggleLight() {
      modeToggle.value = true
      store.dispatch('setModeToggle')
      localStorage.setItem('theme',JSON.stringify(modeToggle.value))
      location.reload()
    }

    function modeToggleDark() {
      modeToggle.value = false
      store.dispatch('setModeToggle')
      localStorage.setItem('theme',JSON.stringify(modeToggle.value))
      location.reload()
    }

    function sideToggleFix() {
      sideToggle.value = true
      store.dispatch('setSidebarToggle')
      localStorage.setItem('sidebar',JSON.stringify(sideToggle.value))
      location.reload()
    }

    function sideToggleNoFix() {
      sideToggle.value = false
      store.dispatch('setSidebarToggle')
      localStorage.setItem('sidebar',JSON.stringify(sideToggle.value))
      location.reload()
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

<style scoped>
  * {
    font-family: Koverwatch !important;
    font-size: 1.5rem;
  }
  .row {
    margin-bottom: 8px;
  }
  .col-3 {
    display: flex;
    justify-content: center;
    align-items: center;
  }
  .col-6 {
    display: flex;
    align-items: center;
  }
  #btn {
    border: none;
    border-radius: 40px;
    width: 200px;
    height: 40px;
    margin: 8px;
  }
  #btn:hover {
    background-color: var(--theme-color);
  }
  .on {
    background-color: var(--theme-color);
    color: white;
  }

  .off {
    background-color: gray;
    color: white;
  }
</style>