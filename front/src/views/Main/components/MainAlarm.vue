<template>
  <div class="main-alarm">
    <div>테스트 용</div>
    <div class="container d-flex flex-column">
      <div v-for="alarm in state.alarmList" :key="alarm.alarmNum">
        <div class="alarm-form">
          <div>
            {{ alarm.alarmDetail }}
          </div>
          <div>알람1</div>
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
      alarmList: [
        {
          alarmCode: '1',
          alarmNum: 1022,
          alarmDetail: '{A}님이 당신을 팔로우 합니다.',
        },
        {
          alarmCode: '1',
          alarmNum: 1023,
          alarmDetail: '{B}님이 당신을 팔로우 합니다.',
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

.alarm-form {
  margin: 10px;
  background: red;
  height: 100px;
  width: 100px;
  border-radius: 10%;
  box-shadow: 1px 1px 8px gray;
}
</style>
