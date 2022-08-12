<template>
  <!-- room -->
    <div class="room-template outer">
        <router-link
        :to="`/studyroom/${room.room.roomNum}`"
        class="room-component d-flex flex-column"
        >
            <!-- room private icon -->
            <b-button v-if="room.room.roomHasPw" style="position: absolute; top:1em; left:1em;" class="rounded-circle p-0 px-1" variant="light" disabled>
                <i class="bi bi-lock-fill"></i>
            </b-button>

            <b-button pill variant="light" disabled
            class="p-0 pe-1 ps-2"
            style="position: absolute; right:1em; top:1em;">
                <i class="bi bi-people-fill me-1"
                style="font-style: normal;">
                &nbsp;{{room.room.roomParticipationCount}} / {{room.room.roomCapacity}}</i>
            </b-button>

            <!-- thumbnail -->
            <div class="room-thumbnail flex-grow-1 flex-shrink-1">
                <!-- rooom setting -->
                <div class="w-100 h-100" style="position: relative">
                    <div style="position: absolute; right:0.5em; bottom:0.5em;">
                        <!-- audio -->
                        <b-button v-if="!room.room.roomSound" pill variant="light" disabled
                            class="rounded-circle p-0 px-1 me-2">
                            <i class="bi bi-mic-mute-fill"></i>
                        </b-button>
                        <b-button v-if="!room.room.roomVideo" pill variant="light" disabled
                            class="rounded-circle p-0 px-1">
                            <i class="bi bi-camera-video-off-fill"></i>
                        </b-button>
                    </div>
                </div>
                <v-img
                    :src="`${server_link}/image/thumbnail/${room.room.roomThumbnail}`">
                </v-img>

            </div>

            <div
                class="d-flex flex-grow-0 flex-shrink-0 align-items-center"
                style="margin-top: 8px"
            >
                <!-- profile -->
                <b-avatar
                :src="require(`@/assets/avatar/0.jpg`)"
                size="2em"
                ></b-avatar>

                <div
                class="d-flex flex-column flex-grow-1"
                style="margin-left: 8px"
                >
                <!-- title -->
                <div style="font-size: 16pt; font-weight: bold">
                    {{ room.room.roomName }}
                </div>
                </div>
            </div>
            <div class="d-flex mt-1"
                style="min-height:20px;">
                <b-badge v-for="(hash, index) in room.roomHash" :key="index" pill
                class="mx-1"
                style="background: #cdeaf0 !important;">#{{hash}}</b-badge>
            </div>
        </router-link>
    </div>
</template>

<script>
import { ref } from 'vue';
export default {
    props: {
        room: {
            type : Object,
            required : true,
        }
    },

    setup() {
        const server_link = ref(process.env.VUE_APP_SERVER);
        return {
            server_link
        }
    }
}
</script>
