<template>
  <div class="room-plannersidebar d-flex flex-column">
    <b-card class="m-3">
      <room-pomodoro v-if="isUsingPomodoro" />
    </b-card>
  </div>
  <div class="room-plannersidebar">
    <study-planner ref="studyPlanner" />
  </div>
</template>

<script>
import { ref, computed } from 'vue';
import { useStore } from 'vuex';

import RoomPomodoro from './RoomPomodoro.vue';
import StudyPlanner from '../../Planner/PlannerView.vue';

export default {
  components: {
    RoomPomodoro,
    StudyPlanner,
  },

  setup() {
    const studyPlanner = ref(null);
    async function plannerResize() {
      await studyPlanner.value.masonryLayout();
    }
    return {
      studyPlanner,
      plannerResize,
      isUsingPomodoro: computed(() => useStore().getters.isUsingPomodoro),
    };
  },
};
</script>

<style scoped>
.room-plannersidebar {
  overflow: hidden;
}
</style>
