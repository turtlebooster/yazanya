<template>
  <div class="header">
    <button @click="sendToggle"><i class="bi bi-list" ></i></button>
    <img src="../../../assets/title_logo_green.png" height="16" id="logo">

  </div>
</template>

<script>
  export default {
    name: "MainBar",
    methods: {
      sendToggle() {
        this.$emit('toggle')
      }
    }
  }
</script>

<style scoped>
  #logo {
    margin-left:0.5rem;
  }
  .header {
    border-bottom: 3px solid rgb(131,220,183);
    background-color:white;
  }
  button {
    border:none;
    background-color:transparent;
  }
</style>