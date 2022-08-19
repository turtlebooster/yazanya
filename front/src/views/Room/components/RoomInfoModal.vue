<template>
  <b-card> 
    <div class="d-flex flex-column" style="font-family: 'Nanum Gothic', sans-serif;">
      <h3>{{room.roomName}}</h3>
      <h5 class="mt-2">{{room.roomDescription}}</h5>
      <hr class="my-2">

      <div class="d-flex mt-2">
        <h6 style="width:190px">방 최대 인원</h6> <h6>&nbsp;:&nbsp;&nbsp;</h6> <h6>{{room.roomCapacity}}</h6>
      </div>
      <div class="d-flex mt-1">
        <h6 style="width:190px">방 생성 시간</h6> <h6>&nbsp;:&nbsp;&nbsp;</h6> <h6>{{new Date(room.roomStartTime).toLocaleString()}}</h6>
      </div>
      <div class="d-flex mt-1">
        <h6 style="width:190px">방 비밀번호 사용 여부</h6> <h6>&nbsp;:&nbsp;&nbsp;</h6> <h6>{{room.roomHasPw?'예':'아니오'}}</h6>
      </div>
      <div class="d-flex mt-1">
        <h6 style="width:190px">오디오 사용 여부</h6> <h6>&nbsp;:&nbsp;&nbsp;</h6> <h6>{{room.roomSound?'예':'아니오'}}</h6>
      </div>
      <div class="d-flex mt-1">
        <h6 style="width:190px">캠화면 사용 여부</h6> <h6>&nbsp;:&nbsp;&nbsp;</h6> <h6>{{room.roomVideo?'예':'아니오'}}</h6>
      </div>
      <div v-if="room.roomRestTime > 0" class="d-flex mt-1">
        <h6 style="width:190px">공부 시간</h6> <h6>&nbsp;:&nbsp;&nbsp;</h6> <h6>{{room.roomStudyTime + '분'}}</h6>
      </div>
      <div v-if="room.roomRestTime > 0" class="d-flex mt-1">
        <h6 style="width:190px">쉬는 시간</h6> <h6>&nbsp;:&nbsp;&nbsp;</h6> <h6>{{room.roomRestTime + '분'}}</h6>
      </div>
    </div>
  </b-card>
</template>

<script>
import { computed } from 'vue';
import { useStore } from 'vuex';

export default {
  setup() {
    return {
      room : computed(()=> useStore().getters.getRoom)
    }
  }
}
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap');
</style>