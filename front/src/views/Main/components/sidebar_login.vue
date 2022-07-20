<template>
    <div class="sidebar">
        <div id="info">
            <img src="../../../assets/icon_profile_basic.png"> <!-- DB 프로필 사진으로 -->
            <a href="#" id="name">  ???</a>
        </div>
        <hr>
        <ul>
            <a href="#"><li>빠른 시작</li></a>
            <a href="#"><li>플래너</li></a>
            <a href="#"><li>공유</li></a>
            <a href="#"><li>친구 목록</li></a>
        </ul>
        <div id="bottom">
            <i class="bi bi-gear"></i>
            <i class="bi bi-box-arrow-right"></i>
        </div>
    </div>
</template>

<script>

</script>

<style scoped>
    .sidebar {
        background-color:rgb(242,242,242);
        align-items: flex-start;
        width: 280px;
        height:100vh;
        margin:0;
        position: absolute;
        left:0;top:0;
        z-index:9;
    }
    #info {
        padding: 1rem;
        padding-top: 3rem;
    }
    #name {
        color: gray;
        font-weight:bold;
    }
    #bottom {
        display: flex;
        /* height:100vh; */
        justify-content: space-between;
        vertical-align: bottom;
        margin: 0 5%;
    }
    a {
        text-decoration: none;
        color: black;
    }
    ul {
        list-style: none;
        font-weight: bold;
        line-height:2rem;
        padding-left: 0;
        padding-right: 2rem;
    }
    ul li {
        padding-left: 1rem;
    }
    ul > :hover li{
        color: white;
        background-color: rgb(76,205,151);
        border-top-right-radius: 20px;
        border-bottom-right-radius: 20px;
    }
</style>