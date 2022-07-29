<template>
  <div class="profile">
    <div class="row">
      <div class="col-3">
        <div class="summary">
          <div class="title">Profile</div>
          <div>
            <img
              src="@/assets/avatar/3.jpg"
              alt="profile"
              class="image icon"
              style="padding: 8px; width:60px; height: 60px; border-radius: 50%"
            />
          </div>
          <div>이름</div>
          <div>
            <span>팔로잉 </span>
            <span>팔로워 </span>
            <span>맞팔수 </span>
          </div>
        </div>
      </div>
      <div class="col-1">
        <!-- 세로줄용 -->
      </div>
      <div class="col-7">
        <div class="info">
          <div class="title">Edit Info</div>
          <div id="id">
            <div>ID</div>
            <input type="text"/>
          </div>
          <div class="row">
            <div class="col" id="password">
              <div>비밀번호</div>
              <form action="">
                <input type="password" id="inputPw"/>
                <i class="bi bi-eye-fill" id="iconPw" @click="togglePw"></i>
              </form>
            </div>
            <div class="col" id="confirmpassword">
              <div>비밀번호 확인</div>
              <form action="">
                <input type="password" id="inputConfirm"/>
                <i class="bi bi-eye-fill" id="iconConfirm" @click="toggleConfirm"></i>
              </form>
            </div>
          </div>
          <div id="email">
            <div>E-mail</div>
            <input type="email"/>
          </div>
          <div id="name">
            <div>이름</div>
            <input type="text"/>
          </div>
          <div id="nickname">
            <div>닉네임</div>
            <input type="text"/>
          </div>
          <div id="dept">
            <div>소속</div>
            <input type="text"/>
          </div>
          <div id="hashtag">
            <div>관심사</div>
            <input type="text"/>
          </div>
          <div class="d-flex justify-content-end">
            <button id="save">수정</button>
            <button id="cancel">취소</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  setup() {
    var togglePw = function () {
      var iconPw = document.getElementById("iconPw")
      var inputPw = document.getElementById("inputPw")
      if (iconPw.className === "bi bi-eye-fill") {
        iconPw.className = "bi bi-eye-slash-fill"
        inputPw.type = "text"
      }
      else {
        iconPw.className = "bi bi-eye-fill"
        inputPw.type = "password"
      }
    }
    
    var toggleConfirm = function () {
      var iconConfirm = document.getElementById("iconConfirm")
      var inputConfirm = document.getElementById("inputConfirm")
      if (iconConfirm.className === "bi bi-eye-fill") {
        iconConfirm.className = "bi bi-eye-slash-fill"
        inputConfirm.type = "text'"
      }
      else {
        iconConfirm.className = "bi bi-eye-fill"
        inputConfirm.type = "password"
      }
    }
    return {
      togglePw,
      toggleConfirm,
    }
  }
};
</script>

<style scoped>
@font-face {
    font-family: 'MabinogiClassicR';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2207-01@1.0/MabinogiClassicR.woff2') format('woff2');
    font-weight: normal;
    font-style: normal;
}
  .profile {
    text-align: center;
    margin: 32px;
    font-family: 'MabinogiClassicR';
  }
  .col-1 {
    border-left: 1px solid rgb(232,232,232);
    margin: 0;
  }
  .title {
    font-weight: bold;
  }
  .info {
    text-align: left;
  }
  .info > div {
    margin-bottom: 16px;
  }
  input {
    width: 100%;
  }
  button {
    width: 100px;
    height: 40px;
    border: 1px solid var(--main-color);
    border-radius: 10px;
  }
  #save {
    background-color: var(--main-color);
    margin-right: 16px;
    color: white;
  }
  #cancel {
    background-color: white;
    color: var(--main-color);
  }
  #iconPw, #iconConfirm{
    font-weight : 900;
    background-color : transparent;
    border : none;
    cursor : pointer;
    position : absolute;
    right : 5px;
    top : 50%;
    transform : translatey(-50%);
  }
  form {
    position: relative;
  }
</style>
