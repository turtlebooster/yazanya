<template>
  <div class="widget planner-todo">
    <div class="widget-component">
      <div class="">대충 투두</div>
      <div v-for="todo in todoList" :key="todo.todoNum">
        <div class="d-flex">
          <div>{{ todo.todoProgress }}</div>
          |
          <div>{{ todo.todoName }}</div>
          |
          <div>{{ todo.todoContent }}</div>
          |
          <div>{{ todo.todoStartTime.split('T')[0] }}</div>
          |
          <div>{{ todo.todoEndTime.split('T')[0] }}</div>
        </div>
      </div>
    </div>

    <!-- modal -->
    <b-modal id="modal-2" centered title="프로필">
      <p class="my-4">대충 투두 디테일</p>
      <div v-for="todo in todoList" :key="todo.todoNum">
        <div>{{ todo.todoName }}</div>
      </div>
      <!-- <div>{{ todoList }}</div> -->
    </b-modal>
  </div>
</template>

<script>
//import rest_todo from '@/rest/todo';
import { onBeforeMount, ref } from 'vue';

export default {
  setup() {
    const todoList = ref();

    async function init() {
      //todoList.value = await rest_todo.getTodo();
    }

    onBeforeMount(() => {
      init();
    });

    return {
      todoList,
    };
  },
};
</script>

<style scoped></style>
