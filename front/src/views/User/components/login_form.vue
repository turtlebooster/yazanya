<template>
  <div class="login">
    <div class="container">
      <div
        style="
          text-align: left;
          font-weight: bold;
          margin-bottom: 32px;
          color: white;
        "
      >
        Login
      </div>
      <div class="d-flex flex-column m-3 input">
        <div class="form-floating mb-3">
          <input
            type="text"
            class="form-control"
            id="floatingID"
            placeholder="ID"
          />
          <label for="floatingID">ID</label>
        </div>
        <div class="form-floating mb-3">
          <input
            type="password"
            class="form-control"
            id="floatingPassword"
            placeholder="Password"
          />
          <label for="floatingPassword">Password</label>
        </div>
        <div>
          <button>Submit</button>
        </div>
        <!-- <div
          style="display: flex; justify-content: space-between; color: white"
          id="link"
        > -->
        <div class="d-flex justify-content-between link">
          <span><router-link to="signup">Sign Up</router-link></span>
          <span>
            <span><router-link to="findid">Find ID</router-link></span> /
            <span><router-link to="findpw">Password</router-link></span>
          </span>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { ref, onBeforeMount } from 'vue';
import { useStore } from 'vuex';
import { useRouter } from 'vue-router';

export default {
  setup() {
    const id = ref('test');
    const pw = ref('test');
    const store = useStore();
    const router = useRouter();

    store
      .dispatch('login', {
        id: id.value,
        password: pw.value,
      })
      .then((rs) => {
        console.log(rs);
        router.replace();
      })
      .catch((err) => {
        alert(err);
      });

    onBeforeMount(() => {
      document.documentElement.style.setProperty('--size-h-header', '0');
      document.documentElement.style.setProperty('--size-w-side', '0');
    });
  },
};
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
  background-color: var(--main-color);
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
