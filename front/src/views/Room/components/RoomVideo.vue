<template>
    <div class="video_plane" style="position: relative;" ref="video_plane">
        <!-- <img src="@/assets/logo/title_logo_009e73.png" class="d-inline-block align-top h-100" alt="logo"> -->

        <!-- <b-dropdown
            style="position: absolute; bottom: 5px; right: 5px;"
            size="sm" variant="link" toggle-class="text-decoration-none" no-caret>
            <template #button-content><i class="bi bi-three-dots-vertical"></i></template>
            <b-dropdown-item href="#">Action</b-dropdown-item>
            <b-dropdown-item href="#">Another action</b-dropdown-item>
            <b-dropdown-item href="#">Something else here...</b-dropdown-item>
        </b-dropdown> -->
    </div>  
</template>

<script>
import { ref, onMounted } from 'vue';

export default {
    props : {
        username : String,
        participant : Object,
    },
        
    setup(props) {
        let video_plane = ref(null);
    
        onMounted(()=> {
            if(video_plane.value == null) {
                console.log("not rendered");
                return;
            }

            video_plane.value.addchild(props.participant.getVideoElement());
        })

        return { video_plane }
    }
}
</script>