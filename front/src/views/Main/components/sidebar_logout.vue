<template>
    <div class="sidebar">
        <div class="info">
        <a href="/user/login" >로그인</a>
        <span>이 필요합니다.</span>
        </div>
        <hr>

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
        padding: 1rem;
        margin:0;
        position: absolute;
        left:0;top:0;
        z-index:9;
    }
    
    a, span {
        text-decoration: none;
        color: gray;
        font-weight:bold;
    }
    
    .info { 
        padding-top: 2rem;
    }
</style>