<template>
  <div class="findid">
    <div class="container">
      <div
        style="
          text-align: left;
          font-weight: bold;
          margin-bottom: 32px;
          color: white;
        "
      >
        Find ID
      </div>
      <div class="d-flex flex-column m-3 input">
        <div class="form-floating mb-3">
          <input
            type="email"
            class="form-control"
            id="floatingEmail"
            placeholder="E-mail"
          />
          <label for="floatingEmail">E-mail</label>
        </div>
        <div>
          <button>Submit</button>
        </div>
        <!-- <div style="display:flex;justify-content: space-between;" id="link"> -->
        <div class="d-flex justify-content-between link">
          <span><router-link to="login">Login</router-link></span>
          <span><router-link to="findpw">Find Password</router-link></span>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style scoped>
.container {
  width: 400px;
  background-color: rgb(0, 0, 0, 0.3);
  padding: 16px;
}
.form-control {
  background-color: rgb(0, 0, 0, 0);
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
  background-color: var(--theme-color);
  width: 100%;
  height: 40px;
  margin-top: 48px;
  margin-bottom: 32px;
  font-weight: bold;
  color: white;
}
.link * {
  text-decoration: none;
  color: white;
}
</style>
