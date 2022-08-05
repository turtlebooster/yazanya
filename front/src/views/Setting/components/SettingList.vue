<template>
  <div class="list">
    <div>
      프로필
    </div>
    <div>
      <span>화면 모드</span>
      <button>라이트 모드</button>
      <button>다크 모드</button>
    </div>
    <div>
      <span>사이드바</span>
      <button @click="toggleOn">고정</button>
      <button @click="toggleOff">고정 ㄴㄴ</button>
    </div>
  </div>
</template>

<script>
import { ref } from 'vue'
export default {
  setup() {
    var toggle = ref(false);
    function toggleOn() {
      if (!toggle.value) {
        toggle.value = !toggle.value;
        localStorage.setItem('toggle',JSON.stringify(toggle.value))
      }
      console.log(toggle.value)
    }
    function toggleOff() {
      if (toggle.value) {
        toggle.value = !toggle.value;
        localStorage.setItem('toggle',JSON.stringify(toggle.value))
      }
      console.log(toggle.value)
    }
    return {
      toggleOn,
      toggleOff,
    }
  }
};
</script>

<style>

</style>