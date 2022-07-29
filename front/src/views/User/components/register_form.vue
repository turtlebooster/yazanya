<template>
  <div class="register">
      <div class="container">
        <div style="text-align:left; font-weight: bold; color: white;">Sign Up</div>
        <div class="input">
          <div class="form-floating">
            <input type="text" class="form-control" id="floatingID" placeholder="ID">
            <label for="floatingID">ID</label>
          </div>
          <div class="form-floating">
            <input type="password" class="form-control" id="floatingPassword" placeholder="Password">
            <label for="floatingPassword">Password</label>
          </div>
          <div class="form-floating">
            <input type="email" class="form-control" id="floatingEmail" placeholder="E-mail">
            <label for="floatingEmail">E-mail</label>
          </div>
          <div class="form-floating">
            <input type="text" class="form-control" id="floatingName" placeholder="이름">
            <label for="floatingName">Name</label>
          </div>
          <div class="form-floating">
            <input type="text" class="form-control" id="floatingNickname" placeholder="닉네임">
            <label for="floatingNickname">Nickname</label>
          </div>
        </div>
        <div style="display:flex; justify-content:space-between;">
          <button @click="$router.back()">Cancel</button>
          <button>Submit</button>
        </div>
      </div>
  </div>
</template>

<script>
export default {
  setup() {
    
  }
}
</script>

<style scoped>
  
  .container {
    width: 400px;
    background-color: rgb(0,0,0,0.3);
    padding: 16px;
  }
  .input {
    margin: 16px 16px;
  }
  .form-floating{
    width: 250px;
    margin: 16px auto;
  }
  .form-control {
    background-color: rgb(0,0,0,0);
    border-top: transparent;
    border-left: transparent;
    border-right: transparent;
    border-radius: 0;
  }
  label {
    color: white;
  }
  button {
    border: transparent;
    border-radius: 5px;
    /* background-color: rgb(131,220,183); */
    background-color: var(--main-color);
    width: 100px;
    height: 40px;
    margin-top: 50px;
    font-weight: bold;
    color: white;
  }
</style>