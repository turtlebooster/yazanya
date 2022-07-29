<template>
    <div class="mainLogout">
        <img src="../../../assets/book.jpg" id="book">
        <div class="home">
            <div><img src="../../../assets/connection_img.png" id="logo"></div>
            <div id="text" style="font-weight:bold; text-align:left;">
                <span style="color:rgb(76,205,151); font-weight:bold; font-size:1.5rem;">야자냐가 처음이세요?</span><br>
                <span>지금도 0명의 유저가<br>
                야자냐와 함께 공부중입니다.<br>
                </span>
                <span>
                    
                    <button id="btnLogin" @click="pageLogin">로그인</button>
                    
                    <button id="btnRegister" @click="pageRegister">회원가입</button>
                </span>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        name: "HomeLogout",
        setup() {
            function pageLogin() {
                this.$router.push({path : '/user/login'})
            }

            function pageRegister() {
                this.$router.push({path : '/user/register'})
            }

            return {
                pageLogin,
                pageRegister,
            }
        }
    }
</script>

<style scoped>
    .home {
        text-align:center;
        /* display: block; */
        /* font-size:0; */
        position:relative;
        top: -100px;
    }
    .home div {
        display: inline-block;
        width: 400px;
        padding: 0 5px;
        
        vertical-align: bottom;
    }
    .home span {
        /* font-size:20px; */
        text-align:left;
    }
    #book {
        width:100vw;
        height:280px;
    }
    #logo{
        width:300px;
        height:300px;
    }
    button {
        border-radius: 5px;
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
