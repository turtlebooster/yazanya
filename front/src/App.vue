<template>
  <div id="app">
    <navi />
    <router-view />
  </div>
</template>

<script>
// import Navi from "@/views/common/components/MainNav.vue";

export default {
  components: {
    // Navi,
  },
};
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  /* text-align: center; */
  color: #2c3e50;
  /* margin-top: 60px; */
}
</style>
