<template>
    <b-button @click="test()">
        방 정보 출력
    </b-button>
    
</template>

<script>
import { useStore } from 'vuex';

export default {
    setup() {
        const store = useStore();
        
        function test() {
            const room_start_time = new Date(store.state.Room.room);
            console.log(room_start_time);
            console.log(new Date());
        }

        return {
            test,
        }
    }
}
</script>
