<template>
  <div class="main-friends">
    <div class="d-flex flex-column align-items-center" style="padding: 8px">
      <div class="container-fluid" style="border-radius: 24px; width: 90%">
        <div class="row">
          <div
            v-for="user in userList"
            :key="user.userNum"
            class="user outer col-12 col-sm-6 col-md-4 col-lg-3"
          >
            <b-avatar
              :src="`${server_link}/showImg/profile/number/${user.userNum}`"
              size="5em"
            ></b-avatar>
            <div>
              {{ user }}
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { ref, onBeforeMount } from 'vue';
import rest_user from '@/rest/user';

export default {
  setup() {
    const server_link = ref(process.env.VUE_APP_SERVER);
    const userList = ref();

    async function init() {
      userList.value = await rest_user.getUserList();
    }

    onBeforeMount(() => {
      init();
    });

    return { userList, server_link };
  },
};
</script>

<style scoped>
.main-friends {
  margin: 10px;
}

.friend-form {
  margin: 10px;
  height: 100px;
  width: 50vw;
  border-radius: 16px;
}
</style>
