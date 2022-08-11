<template>
  <div class="calendar d-flex flex-column">
    <!-- <button
      type="button"
      class="btn-close align-self-end"
      aria-label="Close"
    ></button> -->

    <div>
      <v-calendar is-expanded/>
      <!-- <v-date-picker v-model="date" />  -->
    </div>

    <!-- modal -->
    <b-modal id="modal-0" centered title="달력">
      <p class="my-4">달력 세부 사항</p>
    </b-modal>
  </div>
</template>

<script>

export default {
 
}

</script>

<style></style>
