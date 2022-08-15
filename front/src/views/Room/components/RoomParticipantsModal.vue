<template>
  <div class="d-flex flex-column">
    <b-card class="w-100">
      <h3>참여자 목록</h3>
      <hr class="my-2">
      <div v-for="(member, index) in participants" :key="index" class="d-flex align-items-center my-2">
        <b-avatar variant="info" :src="`${server_link}/showImg/profile/number/${member.userNum}`"></b-avatar>
        <span class="mx-2 fw-bolder align-middle"><h4>{{member.userNickname}}</h4></span>

        <Popper arrow>
          <img :src="`@/assets/img/rank/${member.profileRank}.png`" style="height:24px; width:20px;"/>
          <template #content>
            지금까지 한 공부 시간에 따른 랭크에요
          </template>
        </Popper>

        <b-badge v-if="isHost" class="mx-2" variant="warning">Host</b-badge>
      </div>
    </b-card>
  </div>
  
</template>

<script>
import { ref, computed } from 'vue';
import { useStore } from 'vuex';

export default {
    props: {
      participants: Array,
      HostNum : Number,
  },

  setup() {
    // for profile link 
    const server_link = ref(process.env.VUE_APP_SERVER);
    return {
      server_link,
      isHost : computed(() => useStore().getters.isRoomHost),
    }
  }
}
</script>
