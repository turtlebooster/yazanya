<template>
  <div class="main-friends">
    <div class="container d-flex flex-column">
      <div v-for="friend in state.friendList" :key="friend.userNum">
        <div class="friend-form">
          <div>
            {{ friend.userNum }}
          </div>
          <div>
            <img
              :src="require(`@/assets/avatar/${friend.userNum}.jpg`)"
              alt="profile"
              class="image icon"
              style="
                height: 40px;
                width: 40px;
                object-fit: cover;
                border-radius: 50%;
              "
            />
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { reactive } from 'vue';

export default {
  setup() {
    const state = reactive({
      testNum: 1,
      friendList: [
        {
          userNum: 1,
        },
        {
          userNum: 2,
        },
        {
          userNum: 3,
        },
        {
          userNum: 4,
        },
        {
          userNum: 5,
        },
        {
          userNum: 6,
        },
      ],

      list: ['소갈비', '감자'],
    });

    return { state };
  },
};
</script>

<style>
.main-friends {
  margin: 10px;
}

.friend-form {
  margin: 10px;
  background: white;
  height: 100px;
  width: 100px;
  border-radius: 10%;
  box-shadow: 1px 1px 8px gray;
}

.container {
  display: flex;
  justify-content: center;
}
</style>
