<template>
  <div class="widget calendar d-flex flex-column">
    <div class="widget-component">
      <!-- <button
      type="button"
      class="btn-close align-self-end"
      aria-label="Close"
    ></button> -->

      <!-- <div v-if="theme.value"> -->
      <div v-show="checkTheme()">
        <!-- <v-calendar is-expanded/> -->
        <v-date-picker is-expanded mode="date" v-model="date" />
      </div>
      <div v-show="!checkTheme()">
        <v-date-picker is-expanded is-dark mode="date" v-model="date" />
      </div>
    </div>

    <!-- modal -->
    <b-modal id="modal-planner-calendar" centered title="달력" ok-title="추가" hide-footer>
      <p class="my-4">{{ dateFormat(date) }}</p>
      <!-- <v-calendar is-expanded /> -->
      <!-- <v-date-picker v-model="date" />  -->
      <!-- <b-button class="mt-3">추가</b-button> -->
      <!-- <template #modal-footer="{ ok, cancel }"> -->
        <b-button size="sm" variant="secondary">
          취소
        </b-button>
        <b-button v-b-modal.modal-prevent-closing size="sm" variant="primary">
          추가
        </b-button>

        </b-modal>
        <b-modal
          id="modal-prevent-closing"
          ref="modal"
          title="투두리스트를 입력하세요"
          no-stacking
          @show="resetModal"
          @hidden="resetModal"
          @ok="handleOk"
        >
          <form ref="form" @submit.stop.prevent="handleSubmit">
            <b-form-group
              :label="dateFormat(date)"
              label-for="name-input"
              invalid-feedback="Schedule is required"
              :state="nameState"
            >
              <b-form-input
                id="name-input"
                v-model="name"
                :state="nameState"
                required
              ></b-form-input>
            </b-form-group>
          </form>
        </b-modal>
      <!-- </template> -->
    <!-- </b-modal> -->
  </div>
</template>

<script>
import { ref } from 'vue';
// import rest_todo from '@/rest/todo'
// import todoList from '@/views/Planner/PlannerTodo.vue'
export default {
  setup() {
    let date = ref(new Date());

    function checkTheme() {
      return ref(JSON.parse(localStorage.getItem('theme'))).value
    }

    function dateFormat(date) {
        let month = date.getMonth() + 1;
        let day = date.getDate();

        month = month >= 10 ? month : '0' + month;
        day = day >= 10 ? day : '0' + day;

        return date.getFullYear() + '-' + month + '-' + day;
}
    
    let name = ''
    let nameState = ref(null)
    let scheduleList = ref([])

    function checkFormValidity() {
        const valid = this.$refs.form.checkValidity()
        this.nameState.value = valid
        return valid
    }

    function resetModal() {
      this.name.value = ''
      this.nameState.value = null
    }

    function handleOk(bvModalEvent) {
      // Prevent modal from closing
      bvModalEvent.preventDefault()
      // Trigger submit handler
      this.handleSubmit()
    }
    
    function handleSubmit() {
      // Exit when the form isn't valid
      if (!this.checkFormValidity()) {
        return
      }
      // Push the name to submitted names
      this.scheduleList.push(this.name.value)
      // Hide the modal manually
      this.$nextTick(() => {
        this.$bvModal.hide('modal-prevent-closing')
      })
    }
    function showTodo() {
      
    }

    function addTodo() {
      
    }

    function deleteTodo() {

    }
    function cancel() {
    }
    return { date, checkTheme, dateFormat, showTodo, addTodo, deleteTodo, cancel, checkFormValidity, resetModal, handleOk, handleSubmit, name, nameState, scheduleList};
  },
};
</script>

<style></style>
