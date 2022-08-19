<template>
<div>
  <b-card class="p-1">
    <div class="d-flex align-items-center m-2" style="overflow: wrap;">
      <p class="m-0 flex-grow-1" style="font-size:1.5em">오늘의 할 일</p>
      <b-button size="sm" @click="showModal">만들기</b-button>
    </div>
    <hr class="my-1 mb-3">
    <p v-if="TODO_list.length == 0" class="ms-2">오늘의 할일이 없습니다. 추가해주세요.</p>

    <div v-for="(item, index) in TODO_list" :key="index"  class="d-flex flex-column mb-3">
      <div class="d-flex align-items-center" :class="item.todoTime > 0?'mb-2':''" style="overflow: hidden;">
        <h5 class="m-0 me-2 flex-grow-1 ">{{item.todoName}}</h5>
        <b-button v-if="!item.flag && item.todoTime > 0" variant="success" class="m-0" size="sm" @click="startTodo(item)">시작</b-button>
        <b-button v-if="item.flag" class="m-0" size="sm" variant="primary" @click="completeTodo(item)">완료</b-button>
        <b-button v-if="item.todoTime < 0" class="m-0" variant="danger" size="sm" @click="eraseTodo(item)">삭제</b-button>
      </div>
      <!-- <b-badge v-if="item.todoProgress > 0" class="m-2 d-flex align-items-center"
        variant="success"
        style="vertical-align: middle;">
          {{item.todoProgress}}분 <b-spinner class="ms-1" variant="light" small></b-spinner>
      </b-badge> -->
      <b-progress :animated="item.flag" v-if="item.todoTime > 0" striped height="1.2em"
         :max="item.todoProgress" show-value >
        <b-progress-bar :value="item.todoTime" variant="success">
          <span ><strong style="color: #FFFFFF">{{ Math.floor(item.todoTime / 3600) > 0 ? Math.floor(item.todoTime / 3600) + '시 ': ''}}
          {{Math.floor((item.todoTime % 3600) / 60) > 0 ? Math.floor((item.todoTime % 3600) / 60) + '분 ': ''}}
          {{Math.floor(item.todoTime % 60) > 0 ? Math.floor(item.todoTime % 60) + '초': ''}}</strong></span>
        </b-progress-bar>
      </b-progress>
    </div> 
  </b-card>

  <b-modal
      v-model="isModalShown"
      id="modal-newroom"
      centered
      ok-disabled
      hide-header
      hide-footer>
      <b-card class="mb-3">
        <h5 class="mb-3 p-1">오늘의 할 일은?</h5>
        <b-form-input class="mb-3" v-model="todo_name" placeholder="제목" size="lg"></b-form-input>
        <label for="todo_time">할당 시간 : {{ todo_time }}분</label>
        <b-form-input id="todo_time" v-model="todo_time" type="range" min="0" max="180" step="10"></b-form-input>
      </b-card>
      <b-button variant="success" @click="add_todo">추가</b-button>
  </b-modal>
</div>
  
<!-- modal -->
<!-- <b-modal id="modal-1" centered title="달성도">
  <p class="my-4">달성도 상세사항</p>
</b-modal> -->
</template>

<script>
import { onBeforeMount, ref } from 'vue';
import { useStore } from 'vuex';
import rest_todo from '@/rest/todo';
import Swal from 'sweetalert2';

export default {

  setup() {
    const store = useStore();

    // get users todoList
    let TODO_list = ref([]);
    async function getTodoList() {
      try {
        TODO_list.value = await rest_todo.getTodo(store.getters.getUserID)
        TODO_list.value.forEach(todo => {
          if(!todo['todoTime']) todo['todoTime'] = todo['todoProgress'];
          if(!todo['flag']) todo['flag'] = todo['todoTime'] == 0? true : false;
        });
      } catch(error) {
        warning(error);
      }
    }

    onBeforeMount(()=> {
      getTodoList();
    })

    // Modal control
    let isModalShown = ref(false);
    function showModal() {
      isModalShown.value = true;
    }

    // update TODO to server
    function saveTodo(todo) {
      try {
        rest_todo.updateTodo(todo)
      } catch(error) {
        warning(error);
      }
    }

    // alert
    function warning(message) {
      Swal.fire({
          icon : 'warning',
          title : message,
          timer : 2200,
        })
    }


    // input and add todo
    let todo_name = ref('');
    let todo_time = ref(0);

    async function add_todo() {
      let todo = {
        todoName : todo_name.value == ''? '제목 미지정': todo_name.value,
        todoProgress : todo_time.value * 60,
        todoContent : '',
        todoEndTime : '',
        todoStartTime : '',
      };
      try {
        let todo_num = await rest_todo.createTodo(todo);
        todo['todoNum'] = todo_num;
        todo['todoTime'] = todo['todoProgress'];
        todo['flag'] = todo['todoTime'] == 0? true : false;

        TODO_list.value.push(todo);

        // reset modal
        todo_name.value = '';
        todo_time.value = 0;
        isModalShown.value = false;  
      } catch(error) {
        warning(error);
      }
    }

    // setTimer to todo
    function startTodo(todo) {
      todo.flag = true;

      console.log(todo);
      
      let timer = setInterval(() => {
        todo.todoTime -= 1;

        if(todo.todoTime <= 0) {
          clearInterval(timer);
          Swal.fire({
            icon:'success',
            title: todo.todoName + ' 완료!'
          })
        }
      }, 1000);
    }

    function completeTodo(todo) {
      todo.flag = false;
      todo.todoTime = -1;
    }

    async function eraseTodo(todo) {
      try {
        await rest_todo.deleteTodo(todo.todoNum);
        for(let i = 0; i < TODO_list.value.length; i++) {
          if(TODO_list.value[i].todoNum === todo.todoNum) {
            TODO_list.value.splice(i, 1);
          }
        }
      } catch(error) {
        warning(error);
      }
    }

    return {
      showModal,
      isModalShown,

      saveTodo,
      TODO_list,

      todo_name,
      todo_time,

      add_todo,

      startTodo,
      completeTodo,
      eraseTodo,
    }
  }
};
</script>

<style scoped>
.container {
  display: flex;
  justify-content: center;
}

.progress-container {
  width: 140px;
  height: 140px;
}

.progressbar-track {
  fill: transparent;
  stroke: #eeeeee;
  stroke-width: 3px;
}

.progressbar-line {
  fill: transparent;
  stroke: #f0e442;
  stroke-width: 3px;
  stroke-dasharray: 0 calc(30 * 2 * 3.141593);
  stroke-linecap: round;
  transform: rotate(-90deg);
  transform-origin: 50% 50%;

  animation: progress 1.2s forwards;
}

@keyframes progress {
  to {
    stroke-dasharray: 110;
  }
}
</style>
