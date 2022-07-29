<template>
  <div class="user">
    <router-view/>
  </div>
</template>


<script>
export default {
  setup() {
    
  },
}
</script>

<style scoped>
  .user {
    display:flex;
    justify-content:center;
    align-items: center;
    width: 100vw;
    height: 100vh;
    background-image: url("../../assets/book_user.jpg");
  }
</style>