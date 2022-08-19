<template>
  <div class="main-alarm" :class="[$root.theme ? 'light' : 'dark']">
    <div style="width: 300px; height: 1200px">
      <planner-view />
    </div>
    <div class="container d-flex flex-column align-items-center">
      <div v-for="alarm in state.alarmList" :key="alarm.alarmNum">
        <div class="alarm-form outer">
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
// import PlannerView from '@/views/Room/components/RoomPlanner.vue';
import PlannerView from '@/views/Planner/PlannerView.vue';
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
        {
          alarmCode: '1',
          alarmNum: 1023,
          alarmDetail: '{B}님이 당신을 팔로우 합니다.',
        },
        {
          alarmCode: '1',
          alarmNum: 1023,
          alarmDetail: '{B}님이 당신을 팔로우 합니다.',
        },
        {
          alarmCode: '1',
          alarmNum: 1023,
          alarmDetail: '{B}님이 당신을 팔로우 합니다.',
        },
        {
          alarmCode: '1',
          alarmNum: 1023,
          alarmDetail: '{B}님이 당신을 팔로우 합니다.',
        },
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
        {
          alarmCode: '1',
          alarmNum: 1023,
          alarmDetail: '{B}님이 당신을 팔로우 합니다.',
        },
        {
          alarmCode: '1',
          alarmNum: 1023,
          alarmDetail: '{B}님이 당신을 팔로우 합니다.',
        },
        {
          alarmCode: '1',
          alarmNum: 1023,
          alarmDetail: '{B}님이 당신을 팔로우 합니다.',
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

  components: {
    PlannerView,
  },
};
</script>

<style>
.main-friends {
  margin: 10px;
}

.alarm-form {
  margin: 10px;
  height: 100px;
  width: 80vw;
  border-radius: 16px;
}
</style>
