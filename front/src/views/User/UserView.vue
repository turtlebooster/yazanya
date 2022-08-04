<template>
  <div class="d-flex justify-content-center align-items-center vw-100 vh-100 user-view">
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
  .user-view {
    background-image: url("../../assets/book_user.jpg");
  }
</style>