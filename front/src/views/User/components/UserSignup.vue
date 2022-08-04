<template>
  <div class="signup">
      <div class="container">
        <div style="text-align:left; font-weight: bold; color: white;">Sign Up</div>
        <div class="d-flex flex-column m-3 input">
          <div class="form-floating m-auto mb-3 w-75">
            <input type="text" class="form-control" id="floatingID" placeholder="ID" v-model="id">
            <label for="floatingID">ID</label>
          </div>
          <div class="form-floating m-auto mb-3 w-75">
            <input type="password" class="form-control" id="floatingPassword" placeholder="Password" v-model="pw">
            <label for="floatingPassword">Password</label>
          </div>
          <div class="form-floating m-auto mb-3 w-75">
            <input type="email" class="form-control" id="floatingEmail" placeholder="E-mail" v-model="email">
            <label for="floatingEmail">E-mail</label>
          </div>
          <div class="form-floating m-auto mb-3 w-75">
            <input type="text" class="form-control" id="floatingName" placeholder="이름" v-model="name">
            <label for="floatingName">Name</label>
          </div>
          <div class="form-floating m-auto mb-3 w-75">
            <input type="text" class="form-control" id="floatingNickname" placeholder="닉네임" v-model="nickname">
            <label for="floatingNickname">Nickname</label>
          </div>
        </div>
        <div class="d-flex justify-content-between link">
          <button @click="$router.back()">Cancel</button>
          <button @click="signup">Submit</button>
        </div>
      </div>
  </div>
</template>

<script>
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import rest_user from '@/rest/user';


export default {
  setup() {
    const router = useRouter();

    let id = ref("");
    let pw = ref("");
    let email = ref("");
    let name = ref("");
    let nickname = ref("");

    function signup() {
      // TODO : 빈칸 체크, 입력값 검증, ID, EMAIL, PW, 중복 검사
      rest_user.signUp({
        id : id.value, pw : pw.value, email : email.value, 
        name : name.value, nickname : nickname.value
      })
        .then((response) => {
          console.log(response);

          if(response.data === "success") {
            router.replace('/account/login');
          } else {
            alert("회원가입 중 문제가 발생하였습니다. 나중에 다시 시도해주세요");  
          }
        })
        .catch((error)=> {
          console.log(error);
          alert("회원가입 중 문제가 발생하였습니다. 나중에 다시 시도해주세요");
        })
    }
   
    return { id, pw, email, name, nickname, signup }
  }
}
</script>

<style scoped>
  
  .container {
    width: 400px;
    background-color: rgb(0,0,0,0.3);
    padding: 16px;
  }
  .form-control {
    background-color: rgb(0,0,0,0);
    border-top: transparent;
    border-left: transparent;
    border-right: transparent;
    border-radius: 0;
  }
  label {
    color: white;
  }
  button {
    border: transparent;
    border-radius: 5px;
    /* background-color: rgb(131,220,183); */
    background-color: var(--main-color);
    width: 100px;
    height: 40px;
    margin-top: 50px;
    font-weight: bold;
    color: white;
  }
</style>