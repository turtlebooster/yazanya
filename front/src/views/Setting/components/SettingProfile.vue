<template>
  <div class="d-flex justify-content-center" :class="[$root.theme ? 'light' : 'dark']">
    <b-card class="mt-4 w-50">
      <div class="d-flex flex-column">
        <h4 class="m-2">내 프로필 관리</h4>
        <hr class="my-3 me-4">
        <div class="d-flex flex-fill align-items-end mb-3 overflow-hidden">
          <b-avatar :src="`${server_link}/showImg/profile/number/${user.userNum}?t=${new Date().getTime()}`" size="6em">
          </b-avatar>
          <b-button class="border border-2 rounded-circle px-2"
            variant="light"
            style="position:absolute; left:5.2em;"
            @click="uploadProfileImg(user.userId)">
            <h6 class="m-0"><i class="bi bi-camera-fill"></i></h6>
          </b-button>
          <div class="d-flex flex-column flex-fill">
            <div class="d-flex justify-content-between" style="font-size:0.8em; vertical-align: middle;"> 
              <label for="userId" class="ms-4">닉네임</label>
              <b-button class="me-4" pill size="sm"
                style="font-size:1em; position:absolute; right:1.4em; top:11em;"
                @click="nicknameDuplCheck(user.userNickname)"
                :disabled="isDuplChecked">중복체크</b-button>
            </div>
            <b-form-input
              class="flex-grow-1 border border-3 border-top-0 border-end-0 border-start-0 mb-2 mx-4"
              v-model="user.userNickname"
              @keyup.enter="nicknameDuplCheck(user.userNickname)"
              style="font-size:1.3em; width:auto"
            >
            </b-form-input>
          </div>
        </div>

        <div class="d-flex flex-column flex-fill mt-4">
          <label for="userId" class="ms-4" style="font-size:0.8em;">아이디</label>
          <input id="userId" class="flex-grow-1 border border-3 border-top-0 border-end-0 border-start-0 mb-2 mx-4"
            v-model="user.userId"
            style="font-size:1.3em;" disabled/>
        </div>
        <div class="d-flex flex-column flex-fill mt-4">
          <label for="userId" class="ms-4" style="font-size:0.8em;">비밀번호</label>
          <b-form-input id="userId" class="flex-grow-1 border border-3 border-top-0 border-end-0 border-start-0 mb-2 mx-4"
            v-model="password"
            placeholder="변경할 비밀번호"
            :state="validation.userPw"
            style="font-size:1.3em; width:auto"/>
          <b-form-invalid-feedback class="ms-4" :state="validation.userPw">
            비밀번호는 4-20글자 사이 영어, 숫자여야 해요
          </b-form-invalid-feedback>
        </div>
        

        <div class="d-flex flex-column flex-fill mt-3">
          <label for="userEmail" class="ms-4" style="font-size:0.8em;">이메일</label>
          <input id="userEmail" class="flex-grow-1 border border-3 border-top-0 border-end-0 border-start-0 mb-2 mx-4"
            v-model="user.userEmail"
            style="font-size:1.3em;" disabled/>
        </div>

        <div class="d-flex flex-column flex-fill mt-3">
          <label for="userPw" class="ms-4" style="font-size:0.8em;">내 관심 태그</label>
          <b-form-tags
              input-id="userPw"
              class="flex-grow-1 border border-3 border-top-0 border-end-0 border-start-0 mx-4"
              separator=" ,;"
              v-model="hashtags"
              :state="validation.hashTag"
              placeholder="관심 태그"
              duplicateTagText="중복된 태그가 있어요 "
              tagVariant="success"
              remove-on-delete
              style="font-size:1.3em; width:auto;"
            >
          </b-form-tags>
          <b-form-invalid-feedback class="ms-4" :state="validation.hashTag">
            방 태그는 최대 3개까지 지정할 수 있어요
          </b-form-invalid-feedback>
        </div>

        <div class="d-flex flex-column flex-fill mt-3">
          <label for="userIntroduce" class="ms-4" style="font-size:0.8em;">내 소개</label>
          <textarea id="userIntrodution" class="flex-grow-1 border border-3 border-top-0 border-end-0 border-start-0 mb-2 mx-4"
            v-model="user.profileSelfIntroduce"
            style="font-size:1.3em; resize: none;" maxlength="50"/>
        </div>

        <div class="d-flex justify-content-between mt-3 mb-2">
          <b-button class="ms-4" variant="success" :disabled="!validation.isAllOk" @click="saveChange()">저장</b-button>
          <b-button class="me-4" variant="danger" @click="quit(user.userId)">회원 탈퇴</b-button>
        </div>
      </div>
    </b-card>
  </div>  
</template>

<script>
import {  onBeforeMount, ref, computed, reactive } from 'vue';
import { useStore } from 'vuex';
import { useRouter } from 'vue-router'
import Swal from 'sweetalert2';
import rest_user from '@/rest/user';
import rest_img from '@/rest/image';

export default {
  setup() {
    const store = useStore();
    const router = useRouter();

    // ----------------------- input validation -------------------------- //
    let hashtags = ref([]);
    let password = ref("");

    // for nickname change
    let prev_userNickname = ref('');
    let isDuplChecked = computed({
      get(){
        return prev_userNickname.value === user.value.userNickname;
      },
      set(val) {
        isDuplChecked.value = val;
      }
    });

    async function nicknameDuplCheck(user_nickname) {
      user_nickname = user_nickname.trim();
      try {
        if(user_nickname.length < 1 || user_nickname.length > 20) {
          throw '닉네임이 형식에 맞지 않습니다\n(1 - 20글자)';
        }

        // request to server
        await rest_user.checkNicknameDuplicated(user_nickname);
        prev_userNickname.value = user.value.userNickname;

        Swal.fire({
          icon: 'success',
          title: '사용가능한 닉네임입니다',
          timer: 2200,
        });
      } catch(error) {
        Swal.fire({
          icon: 'warning',
          title: error,
          timer: 2200,
        });
      }
    }

    const validation = reactive({
      userPw: computed(() => {
        return password.value == ""
            ? null
            : new RegExp(/^[a-zA-Z0-9]{4,20}$/).test(password.value);
      }), 

      hashTag: computed(() => {
        return hashtags.value.length <= 3 ? null : false;
      }),

      isAllOk : computed(()=> {
        if(validation.userPw == false || validation.hashTag == false || !isDuplChecked.value) return false;
        return true;
      })
    });

    let user = ref({ userNum : -1 });
    onBeforeMount(async ()=> {
      try {
        // get profile from server
        user.value = await rest_user.getProfile(store.getters.getUserID);
        prev_userNickname.value = user.value.userNickname;

        // get hashtag list from server
        hashtags.value = await rest_user.getHashTags(user.value.userId);
      } catch(error) {
        Swal.fire({
          icon: 'warning',
          title: error,
          timer: 2200,
        });
      }
    })

    // --------------------- request modify to server ------------------------ //
    async function saveChange() {
      try {
        await rest_user.addUserHashTags(user.value.userId, hashtags.value);
        
        if(password.value) user.value.userPw = password.value;
        await rest_user.modifyProfile(user.value);

        Swal.fire({
          icon: 'success',
          title: '회원정보 수정 완료',
          timer: 2200,
        });
      } catch(error) {
        Swal.fire({
          icon: 'warning',
          title: error,
          timer: 2200,
        });
      }
    }

    async function quit(user_id) {
      try {
        await rest_user.quitAccount(user_id);

        await Swal.fire({
          icon: 'success',
          title: '회원탈퇴 완료',
          timer: 2200,
        });

        await store.dispatch('logout');
        router.replace('/home');
      } catch(error) {
        Swal.fire({
          icon: 'warning',
          title: error,
          timer: 2200,
        });
      }
    }

    // ---------------------- for profile image ---------------------- //
    const server_link = ref(process.env.VUE_APP_SERVER);

    function uploadProfileImg(user_id) {
      let imageInput = document.createElement('input');
      imageInput.type = 'file';
      imageInput.accept = 'image/png, image/jpeg';

      // emit event
      imageInput.click();
      imageInput.onchange = async (event) => {
        const target = event.target;
        if (target.files && target.files[0]) {
          // Max file size is 5MB
          const maxAllowedSize = 5 * 1024 * 1024;
          if (target.files[0].size > maxAllowedSize) {
            Swal.fire({
              icon: 'warning',
              title: '파일의 최대 크기는 5MB 입니다',
            });
            target.value = '';
          }

          // check file type
          let fileName = target.files[0].name;
          let fileDot = fileName.lastIndexOf('.');
          let fileType = fileName
            .substring(fileDot + 1, fileName.length)
            .toLowerCase();

          if (fileType !== 'png' && fileType !== 'jpg') {
            Swal.fire({
              icon: 'warning',
              title: '지원하지 않는 확장자입니다',
            });
            target.value = '';
            return;
          }

          // request server
          const formData = new FormData();
          formData.append('pic', target.files[0]);
          try {
            await rest_img.addProfileImage(user_id, formData);
          } catch(error) {
            Swal.fire({
              icon: 'warning',
              title: error,
            });
          }
        }
      }
    }

    return {
      server_link,
      user,

      hashtags,
      password,
      validation,
      isDuplChecked,
      prev_userNickname,

      saveChange,
      quit,
      uploadProfileImg,
      nicknameDuplCheck,
    }
  }
};
</script>

<style>
input, textarea {
  font-family: 'Nanum Gothic', sans-serif !important;
}

input:focus {
  outline: none;
}

input:disabled {
  background: none;
}

/* disable tags add button */
.b-form-tags-button {
  visibility: hidden !important;
}

</style>