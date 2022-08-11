<template>
  <div class="calendar d-flex flex-column">
    <!-- <button
      type="button"
      class="btn-close align-self-end"
      aria-label="Close"
    ></button> -->

    <div>
      <!-- <v-calendar is-expanded/> -->
      <v-date-picker is-expanded mode="date" v-model="date" /> 
    </div>

    <!-- modal -->
    <b-modal id="modal-0" centered title="달력">
      <p class="my-4">{{date}}</p>
      <!-- <v-calendar is-expanded /> -->
      <!-- <v-date-picker v-model="date" />  -->
    </b-modal>
  </div>
</template>

<script>
import { ref } from 'vue';
export default {
  setup() {
    let date = ref();
    
    return { date }
  }
}
</script>

<style></style>
