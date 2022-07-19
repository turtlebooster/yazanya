<template>
    <div class="mainLogout">
        <img src="../../../assets/book.jpg" id="book">
        <img src="../../../assets/icon_logo_blk.png" id="logo">
        <div id="circle1" style="margin-left:20%;"></div>
        <div id="text" style="font-weight:bold;">
            <h4 style="color:rgb(76,205,151); font-weight:bold;">야자냐가 처음이세요?</h4>
            지금도 0명의 유저가<br>
            야자냐와 함께 공부중입니다.
            <div>
                <button id="btnLogin">로그인</button>
                <button id="btnRegister">회원가입</button>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        name: "HomeLogout",
        setup() {

        }
    }
    // const targetTop = document.getElementById('logo').offSetTop;
    // document.getElementById('text').style.top = targetTop;
</script>

<style scoped>
    #book {
        width: 100vw;
        height: 40vh;
    }
    #logo{
        width:200px;
        height:200px;
        margin-left:20%;
        background-color:rgb(242,242,242);
        border-radius: 100%;
        position:relative; left:100px; bottom:100px;
    }
    #circle1 {
        width:280px;
        height:280px;
        border-radius: 100%;
        border: 0.5em solid rgb(131,220,183);
        position:relative; left:60px; bottom:340px;
    }
    #text {
        position:absolute; top:45vh; left: 50vw;
    }
    button {
        border-radius: 10%;
        color: white;
        border:transparent;
        width: 100px;
        margin:30px;
    }
    #btnLogin {
        background-color: rgb(76,205,151);
    }
    #btnRegister {
        background-color: gainsboro;
    }
</style>
