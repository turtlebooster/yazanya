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

  import { ref } from 'vue'

  export default {
    components: {
      MainBar,
      SideBar,
      HomeLogout,
    },

    setup() {
      var isOn = ref(false)
      function toggleOnOff() {
        isOn.value = !isOn.value;
      }
      
      return {
        isOn,
        toggleOnOff
      }
    }
  }
</script>

<style>
  section {
    display: flex;
  }
</style>
