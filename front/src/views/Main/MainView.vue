<template>
  <div>
    <MainBar @toggle="toggleOnOff"/>
    <section>
    <SideBar v-if="isOn"/>
    <HomeLogout/>
    </section>
  </div>
</template>


<script>
  import MainBar from "./components/mainbar.vue"
  import SideBar from "./components/sidebar_logout.vue"
  import HomeLogout from "./components/home_logout.vue"
  export default {
    name: "MainView",
    components: {
      MainBar,
      SideBar,
      HomeLogout,
    },
    data() {
      return {
        isOn : false
      }
    },
    methods: {
      toggleOnOff: function() {
        this.isOn = !this.isOn
      }
    }
  }
</script>

<style>
  section {
    display: flex;
  }
</style>