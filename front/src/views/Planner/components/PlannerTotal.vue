<template>
  <div class="widget planner-total">
    <div class="widget-component" id="1">
      <div class="widget-component-icon">
        <span><i class="bi bi-book-half"></i></span>
      </div>
      <div class="widget-component-detail">
        <div>총 공부 시간</div>
        <div style="font-size: 0.8em">{{user_studytime}}</div>
      </div>
    </div>

    <!-- modal -->
    <!-- <b-modal id="modal-planner-total" centered title="총 공부 시간">
      <p class="my-4">user 에서 가져오면 됨 ㅇㅇ</p>
    </b-modal> -->
  </div>
</template>

<script>
import { onBeforeMount, ref } from '@vue/runtime-core';
import { useStore } from 'vuex';
import rest_user from '@/rest/user';
export default {
  setup() {
    onBeforeMount(async ()=> {
      let profile = await rest_user.getProfile(useStore().getters.getUserID);

      let totalTime = profile.profileTotalStudyTime;
      if(totalTime || totalTime == 0)  {
        user_studytime.value = '';
        if(totalTime >= 60) {
          user_studytime.value += Math.floor(totalTime / 60) + '시간 '
          user_studytime.value += totalTime % 60 == 0? '' : totalTime % 60 + '분'
        } else {
          user_studytime.value += totalTime + '분';
        }
      } else {
        user_studytime.value = '공부 시간 정보 불러오기 실패';
      }
    })

    let user_studytime = ref("시간 불러오는 중");
    return {
      user_studytime,
    }
  }
};
</script>

<style scoped>
.widget-component {
  position: relative;
  width: 100%;
  height: 100%;
  padding: 16px;
  background: linear-gradient(-30deg, #b576f6, #9a4fe9);
}

.widget-component * {
  font-size: 20pt;
  color: white;
}

.widget-component::before,
.widget-component::after {
  content: '';
  position: absolute;
  top: -5%;
  right: -15%;
  height: 150px;
  width: 150px;
  background-color: rgba(255, 255, 255, 0.2);
  border-radius: 100%;
}

.widget-component::after {
  top: auto;
  bottom: -5%;
  right: -5%;
}

.widget-component-icon {
  margin-bottom: 15px;
  height: 60px;
  width: 60px;
  background-color: rgba(255, 255, 255, 0.4);
  border-radius: 50%;

  display: flex;
  justify-content: center;
  align-items: center;
  box-shadow: 0 0 8px white;
}
</style>
