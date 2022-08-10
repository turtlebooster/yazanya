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
            v-model="id"
          />
          <label for="floatingID">ID</label>
        </div>
        <div class="form-floating mb-3">
          <input
            type="password"
            class="form-control"
            id="floatingPassword"
            placeholder="Password"
            v-model="pw"
          />
          <label for="floatingPassword">Password</label>
        </div>
        <div>
          <button @click="login()">Submit</button>
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

import rest_user from '@/rest/user';

export default {
  setup() {
    const store = useStore();
    const router = useRouter();

    let id = ref('');
    let pw = ref('');

    async function login() {
      try {
        let tokens = await rest_user.login({ id: id.value, pw: pw.value })
        await store.dispatch('login', {'access-token': tokens.access_TOKEN, 'refresh-token': tokens.refresh_TOKEN, id: id.value });
        router.replace('/main');
      }
      catch(error) {
        alert(error);
      }  
    }

    onBeforeMount(() => {
      document.documentElement.style.setProperty('--size-h-header', '0');
      document.documentElement.style.setProperty('--size-w-side', '0');
    });

    return { id, pw, login };
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
