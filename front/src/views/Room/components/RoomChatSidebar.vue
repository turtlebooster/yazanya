<template>
    <div class="d-flex flex-column w-100 h-100 overflow-hidden">
        <div class="d-flex align-items-center p-2 px-4" style="height: 8%; min-height: 54px">
            <i class="bi bi-people-fill" style="font-size: 1.8em; font-style: normal">&nbsp;{{nMember}}</i> 
        </div>
        <hr class="border-top border-dark border-3 rounded-3 mx-4 my-2 p-0" />
        <div class="d-flex flex-column flex-grow-1 overflow-auto p-4"
            style="height: 1px"
            ref="chatting_plane"
            @scroll="calScrollPosition">
            <b-card 
                v-for="(chat, index) in chatList"
                :key="index"
                class="my-2 rounded border-0"
                :class="[$root.theme ? 'light' : 'dark']"
                style="width: 90%"
                :style="(chat.senderId === myId)? 'margin-start: auto':''">
                <template v-if="!(chat.senderId === myId)" #header>
                    <b-avatar variant="info" :src="`${server_link}/showImg/profile/nickname/${chat.senderName}`"></b-avatar>
                    <span class="ms-3 fw-bolder align-middle">{{chat.senderName}}</span>
                </template>
                <b-card-text>{{chat.message}}</b-card-text>
            </b-card>

            <b-button
                v-if = "(isActive && isScrolled)"
                class="border-0 align-middle"
                size="sm"
                style="position:absolute; right: 20px;"
                @click="setScrollBottom()">
                <i class="bi bi-caret-down-fill"></i>
            </b-button>
        </div>
        <div class="d-flex p-4" style="height: 16%; min-height: 80px">
            <b-form-textarea
                v-model="message"
                :class="[$root.theme ? 'light' : 'dark']"
                placeholder="채팅을 입력해"
                rows="3"
                max-rows="3"
                maxlength="100"
                no-resize
                @keydown.enter.exact.prevent="sendMessage"
                @keydown.enter.shift.exact.prevent="text += '\n'"
                >
            </b-form-textarea>
            <b-button
                class="ms-2 fw-bold"
                style="font-size:0.8em"
                @click="sendMessage()"
                >Submit</b-button>
        </div>
    </div>
</template>

<script>
import { useStore } from 'vuex';
import { ref, computed } from 'vue';

export default {
    props: {
        isSidebarOn: Number,
    },

    setup(props) {
        const store = useStore();

        // ------------------------  Scrollbar event and to bot button ---------------------------- //
        let chatting_plane = ref(null);
        function setScrollBottom() {
            chatting_plane.value.scrollTop = chatting_plane.value.scrollHeight - chatting_plane.value.clientHeight;
        }
        let isScrolled = ref(false);
        function calScrollPosition() {
            if((chatting_plane.value.scrollHeight - chatting_plane.value.clientHeight) - chatting_plane.value.scrollTop <  80) {
                isScrolled.value = false;
            } else {
                isScrolled.value = true;
            }
        } 

        // ----------------------- send chat button ------------------------------- //
        let message = ref('');
        function sendMessage() {
            if(message.value != null) {
                store.dispatch('sendChat', {senderId: store.state.Room.user.userId, senderName: store.state.Room.user.userNickname, 
                    profile: store.state.Room.user.profilePictureLink, message: message.value});
                message.value = '';
                setScrollBottom();
            }
        }

        // for profile link
        const server_link = ref(process.env.VUE_APP_SERVER);

        return {
            chatting_plane,
            isScrolled,
            setScrollBottom,
            calScrollPosition,
            message,
            sendMessage,
            chatList : computed(()=> store.getters.getChatList),
            myId : computed(()=> store.getters.getUserID),
            isActive : computed(()=> props.isSidebarOn === 0 ? false: true),
            nMember : computed(()=> store.getters.getParticipantsCount),
            
            server_link,
        }
    }
};
</script>

<style scoped>
.top {
    background-color: aqua;
}
/* for theme */
.light {
  background-color: #ffffff;
  color: #545664;
}
.dark {
  background-color: #545664;
  color: #f3f3f3;
}

.light-back-only {
  background-color: #fafafa;
}
.dark-back-only {
  background-color: #404040;
}

.light-color-only {
  color: #545664;
}
.dark-color-only {
  color: #f3f3f3;
}



/* width */
::-webkit-scrollbar {
  width: 6px;
}
/* Track */
::-webkit-scrollbar-track {
  background: #f1f1f1; 
}
/* Handle */
::-webkit-scrollbar-thumb {
  background: #888; 
}
/* Handle on hover */
::-webkit-scrollbar-thumb:hover {
  background: #555; 
}
</style>