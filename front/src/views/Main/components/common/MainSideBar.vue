<template>
  <div class="main-side-bar">
		<router-link to="/main">
			<span class="icon"><i class="bi bi-person-circle"></i> </span>
			<span class="title">&nbsp; 마이페이지(프로필 사진)</span>
		</router-link>

		<router-link to="/main/quickstart">
			<span class="icon"><i class="bi bi-skip-end-circle-fill"></i> </span>
			<span class="title">&nbsp; 빠른시작</span>
		</router-link>

		<router-link to="/main/planner">
			<span class="icon"><i class="bi bi-journal-bookmark-fill"></i></span>
			<span class="title">&nbsp; 플래너</span>
		</router-link>

		<router-link to="/main/share">
			<span class="icon"><i class="bi bi-share"></i> </span>
			<span class="title">&nbsp; 공유</span>
		</router-link>

		<router-link to="/main/friends">
			<span class="icon"><i class="bi bi-person-circle"></i> </span>
			<span class="title">&nbsp; 친구 목록</span>
		</router-link>

		<div style="flex-grow: 1;"></div>

		<router-link to="/main/alarm">
			<span class="icon"><i class="bi bi-person-circle"></i> </span>
			<span class="title">&nbsp; 알림</span>
		</router-link>

		<router-link to="/main/setting">
			<span class="icon"><i class="bi bi-person-circle"></i> </span>
			<span class="title">&nbsp; 마이페이지(프로필 사진)</span>
		</router-link>

		<router-link to="/대충로그오프하는주소">
			<span class="icon"><i class="bi bi-person-circle"></i> </span>
			<span class="title">&nbsp; 마이페이지(프로필 사진)</span>
		</router-link>


    <!-- <ul>
			<li>
        <router-link to="/main">
          <span class="icon"><i class="bi bi-person-circle"></i> </span>
					<span class="title">&nbsp; 마이페이지(프로필 사진)</span>
        </router-link>
			</li>

			<hr />
      <li>
        <router-link to="/main/quickstart">
          <span class="icon"><i class="bi bi-person-circle"></i> </span>
					<span class="title">&nbsp; 빠른시작</span>
        </router-link>
			</li>
      <li>
        <router-link to="/main/planner">
          <span class="icon"><i class="bi bi-person-circle"></i> </span>
					<span class="title">&nbsp; 플래너</span>
        </router-link>
			</li>
      <li>
        <router-link to="/main/share">
          <span class="icon"><i class="bi bi-person-circle"></i> </span>
					<span class="title">&nbsp; 공유</span>
        </router-link>
			</li>
      <li>
        <router-link to="/main/friends">
          <span class="icon"><i class="bi bi-person-circle"></i> </span>
					<span class="title">&nbsp; 친구 목록</span>
        </router-link>
			</li>
      <li>
        <router-link to="/main/alarm">
          <span class="icon"><i class="bi bi-person-circle"></i> </span>
					<span class="title">&nbsp; 알림</span>
        </router-link>
			</li>
    </ul> -->
  </div>
</template>

<script>
export default {

}
</script>

<style scoped>
* {
	margin: 0;
  padding: 0;
  box-sizing: border-box;
	z-index: 1;
}
.main-side-bar {
  position: fixed;
	inset: 20px;
	background: rgb(131,220,183);
	width: 50px;
	border-left: 12px solid rgb(131,220,183);
	border-right: 12px solid rgb(131,220,183);
	margin-top: -16px;
	padding-top: 40px;
	padding-bottom: 8px;
	border-radius: 24px;
	/* box-shadow: 15px 15px 25px rgba(0,0,0,0.1); */

	display: flex;
	flex-direction: column;

	font-size: 20pt;

	overflow: hidden;
	text-overflow: ellipsis;
  white-space: nowrap; 	
	
	transition: 0.2s;
}
.main-side-bar:hover {
	width: 200px;
}
.main-side-bar ul li{
	position: relative;
	list-style: none;
	width: 100%;
}
.main-side-bar * {
	font-family: 'Ubuntu', sans-serif;
	text-decoration: none;
	color: black;
}
</style>