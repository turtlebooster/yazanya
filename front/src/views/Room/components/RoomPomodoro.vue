<template>
  <div class="d-flex flex-column justify-content-center align-items-center"
    style="font-family: 'Gamja Flower', cursive;">
        <p class="mb-1 text-center" v-if="isStudying" style="font-size: 1.2em"><i class="bi bi-fire"></i>공부 시간</p>
        <p class="mb-1 text-center" v-if="!isStudying" style="font-size: 1.2em"><i class="bi bi-plug-fill"></i>쉬는 시간</p>
        
        <CircleProgress
            :percent="percent"
            :viewport="true"
            :size="size"
            :border-width="border"
            :border-bg-width="border_bg"
            linecap=""
            :fill-color="isStudying?'#52bbbd':'#64b355'"/>
        <b-badge class="px-2" pill variant="light"
            style="position: absolute; top:150px; font-size: 0.9em">
            {{hours}} : {{minutes}} : {{seceonds}}
        </b-badge>
    </div>
</template>

<script>
import { computed, ref, watch } from 'vue';
import { useStore } from 'vuex';

// for circular process
import "vue3-circle-progress/dist/circle-progress.css";
import CircleProgress from "vue3-circle-progress";
import Swal from 'sweetalert2';


export default {
    components: {
        CircleProgress,
    },

    setup() {
        const store = useStore();

        //UI size
        let size = ref(160);
        let border = ref(80);
        let border_bg = ref(80);

        let roomTime = computed(()=> store.getters.getRoomStartTime);
        let nowTime = ref(new Date().getTime());
        let passedTime = computed(()=> {
            return roomTime.value? ((nowTime.value - roomTime.value.getTime()) / 1000) : 0;
        });

        let roomStudyTime = computed(()=> store.getters.getRoomStudyTime);
        let roomRestTime = computed(()=> store.getters.getRoomRestTime);

        let currentTime = computed(()=> passedTime.value % (roomStudyTime.value + roomRestTime.value));
        let isStudying = computed(()=> (currentTime.value <= roomStudyTime.value));

        // apply on UI
        let percent = computed(()=> {
            if(isStudying.value) {
                return currentTime.value / roomStudyTime.value * 100;
            } else {
                return (currentTime.value - roomStudyTime.value) / roomRestTime.value * 100;
            }
        })

        // used on center timer
        let seceonds = computed(()=> {
            return Math.floor(isStudying.value?
                (roomStudyTime.value - currentTime.value) % 60 : (roomRestTime.value - (currentTime.value - roomStudyTime.value)) % 60)
                    .toString().padStart(2, '0');
        });
        let minutes = computed(()=> { 
            return Math.floor(isStudying.value?
                (roomStudyTime.value - currentTime.value) / 60 : (roomRestTime.value - (currentTime.value - roomStudyTime.value)) / 60)
                    .toString().padStart(2, '0');
        });
        let hours = computed(()=>  {
            return Math.floor(isStudying.value?
                (roomStudyTime.value - currentTime.value) / 3600: (roomRestTime.value - (currentTime.value - roomStudyTime.value)) / 3600)
                    .toString().padStart(2, '0');
        });

        // time flow
        setInterval(()=> {
            nowTime.value += 1000;
        }, 1000);

        // ----------------- when ------------------- //
        function playBell() {
            let audio = new Audio(require('@/assets/audio/interval_alarm.mp3'));
            audio.play();
        }

        watch(isStudying,()=> {
          playBell();
          if(!isStudying.value) {
            const imagePath = require("@/assets/img/nyan-cat-nyan.gif");
            // inform rest time
            Swal.fire({
                title: '이야~ 쉬는 시간이에요',
                confirmButtonText: '네',
                width: 600,
                padding: '3em',
                imageUrl: imagePath,
                timer: roomRestTime.value * 1000,
                timerProgressBar: true,
                color: '#716add',
                backdrop: `
                    rgba(0,0,123,0.4)
                `
            })
          }
        })

        return {
            currentTime,
            percent,
            isStudying,
            hours,
            minutes,
            seceonds,
            size,
            border,
            border_bg
        }
    }
}
</script>

<style>
@import url('https://fonts.googleapis.com/css2?family=Gamja+Flower&display=swap');
</style>