<template>
    <div class="d-flex flex-column">
        <b-card class="m-3">
            <room-pomodoro v-if="isUsingPomodoro"/>
        </b-card>
    </div>
    <study-planner/>
</template>

<script>
import { computed } from 'vue'
import { useStore } from 'vuex';

import RoomPomodoro from './RoomPomodoro.vue';
import StudyPlanner from '../../Planner/PlannerView.vue';


export default {
    components: {
        RoomPomodoro,
        StudyPlanner,
    },

    setup() {
        return {
            isUsingPomodoro : computed(()=> useStore().getters.isUsingPomodoro)
        }
    }
}
</script>
