<template>
	<div class="main-view">
		<main-header-nav />
		<div class="main-view-container">
			<main-side-bar />
			<div style="min-width: 104px;"></div>
			<router-view class="router-view" />
		</div>
	</div>
</template>

<script>
import MainHeaderNav from "@/views/Main/components/common/MainHeaderNav.vue";
import MainSideBar from "@/views/Main/components/common/MainSideBar.vue";

export default {
	components: {
		MainHeaderNav,
		MainSideBar,
	}

}
</script>

<style>
.main-view {
	width: 100vw;
	height: 100vh;

	display: flex;
	flex-direction: column;
	height: 100vh;
}
.main-view-container {
	flex-grow: 1;
	display: flex;
}
.main-header-nav {
	z-index: 25;
}
.main-side-bar {
	z-index: 20;
}
.router-view {
	flex-grow: 1;
}
</style>