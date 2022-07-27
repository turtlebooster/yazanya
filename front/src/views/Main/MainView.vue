<template>
  <div class="main-view">
    <router-view />
  </div>
</template>

<script>
import { onBeforeMount } from 'vue';

export default {
  setup() {
    onBeforeMount(() => {
      document.documentElement.style.setProperty('--size-h-header', '40px');
      document.documentElement.style.setProperty('--size-w-side', '80px');
    });
  },
};
</script>

<style></style>
