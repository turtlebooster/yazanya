<template>
  <div class="login">
    <input v-model="id">
    <input v-model="pw">
    <button v-on:click="login">로그인</button>
  </div>
</template>


<script>
import { ref } from 'vue';
import { useStore } from 'vuex';
import { useRouter } from 'vue-router'

export default {
  setup() {
    const id = ref("test")
    const pw = ref("test")
    const store = useStore();
    const router = useRouter();

    store.dispatch('login', {
          id : id.value,
          password : pw.value,
        })
          .then((rs) => {
            console.log(rs);
            router.replace();
            })
          .catch((err) => { alert(err);});
  }
};
</script>