<template>
  <div>
    <div class="widget-component">
      <div class="widget-component-icon">
        <img :src="require(`@/assets/img/rank/${user_rank}.png`)" style="height:1em; width:0.8em;"/>
      </div>
      <div class="widget-component-detail">
        <div>공부 티어</div>
        <div style="font-size: 0.8em">{{user_rank}}</div>
      </div>
    </div>

    <!-- modal -->
    <!-- <b-modal id="modal-planner-rank" centered title="미구현">
      <p class="my-4">미구현</p>
    </b-modal> -->
  </div>
</template>

<script>
import { onBeforeMount, ref, computed } from '@vue/runtime-core';
import { useStore } from 'vuex';
import rest_user from '@/rest/user';
export default {
  setup() {
    onBeforeMount(async ()=> {
      let profile = await rest_user.getProfile(useStore().getters.getUserID);

      if(profile.profileRank)  {
        user_rank.value = profile.profileRank;
      } else {
        user_rank.value = '브론즈';
      }
    })

    let user_rank = ref("브론즈");
    return {
      user_rank,
      user_rank_color : computed(()=> {
        // currently not used
        if(user_rank.value === '마스터') return '#ff0062';
        if(user_rank.value === '다이아') return '#00b4fc';
        if(user_rank.value === '플레') return '#27e2a4';
        if(user_rank.value === '골드') return '#ec9a00';
        if(user_rank.value === '실버') return '#435f7a';
        if(user_rank.value === '브론즈') return '#ad5600';
        else return;
      })
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
  background: linear-gradient(-30deg, #6aa5e3, #6866e9);
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
