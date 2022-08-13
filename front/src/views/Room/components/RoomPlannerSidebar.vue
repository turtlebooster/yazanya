<template>
    <div class="d-flex flex-column">
        <b-card class="m-3">
            <room-pomodoro v-if="isUsingPomodoro"/>
        </b-card>
    </div>
</template>

<script>
import { computed } from 'vue'
import { useStore } from 'vuex';

import RoomPomodoro from './RoomPomodoro.vue';


export default {
    components: {
        RoomPomodoro,
    },

    setup() {
        return {
            isUsingPomodoro : computed(()=> useStore().getters.isUsingPomodoro)
        }
    }
}
</script>
