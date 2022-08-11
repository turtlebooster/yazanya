<template>
  <div class="main-planner">
    <div class="d-flex flex-column align-items-center" style="padding: 8px">
      <div class="spacer"></div>

      <!-- search -->
      <div class="search outer d-flex align-items-center">
        <input
          class="search_input"
          type="text"
          placeholder="플래너 템플릿 검색"
        />
        <a href="#" class="search_icon"><i class="bi bi-search"></i></a>
      </div>
      <div class="spacer"></div>
      <div class="spacer"></div>

      <!-- widget -->
      <div class="container-fluid" style="border-radius: 24px; width: 90%">
        <div class="row">
          <div
            v-for="(widget, index) in widgetList"
            :key="index"
            class="widget col-12 col-md-6 col-xl-4"
          >
            <component
              :is="componentList[widget]"
              class="widget-template outer"
              v-b-modal="`modal-${widget}`"
            ></component>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { ref } from 'vue';
import PlannerCalender from './components/PlannerCalendar.vue';
import PlannerProgress from './components/PlannerProgress.vue';
import PlannerTodo from './components/PlannerTodo.vue';
import PlannerTotal from './components/PlannerTotal.vue';
import PlannerRank from './components/PlannerRank.vue';
import PlannerStatus from './components/PlannerStatus.vue';

export default {
  setup() {
    const componentList = [
      PlannerCalender, // 0
      PlannerProgress,
      PlannerTodo,
      PlannerTotal,
      PlannerRank,
      PlannerStatus, // 5
    ];

    const componentNameList = [
      'planner-calender',
      'planner-progress',
      'planner-todo',
      'planner-total',
      'planner-rank',
      'planner-status',
    ];

    const widgetList = ref([4, 3, 5, 2, 1, 0]);

    return { componentList, componentNameList, widgetList };
  },
};
</script>

<style scoped>
.main-lobby {
  background-color: var(--light-main-color);
  height: 100%;
}

.outer {
  box-shadow: 4px 4px 10px -1px rgba(0, 0, 0, 0.25),
    -4px -4px 10px -1px rgba(255, 255, 255, 0.25);
}

.inner {
  box-shadow: inset 4px 4px 10px -1px rgba(0, 0, 0, 0.25),
    inset -4px -4px 10px -1px rgba(255, 255, 255, 0.25);
}

.liner {
  height: 2px;
  width: 80%;
  border-radius: 1px;
  background: var(--light-sub-color);
}

.spacer {
  height: 24px;
}

.search {
  height: 40px;
  width: 80%;
  flex-wrap: nowrap;

  background-color: var(--light-main-color);
  border-radius: 24px;
  padding: 10px;
}

.search_input {
  width: 100px;
  height: 32px;
  color: black;
  /* color: white; */
  border: 0;
  outline: 0;
  background: none;

  padding: 0 10px;
  flex-grow: 1;
  flex-shrink: 1;
  caret-color: var(--theme-color);

  overflow: hidden;
}

.search:hover > .search_icon {
  background: var(--theme-color);
  color: white;
}

.search_icon {
  min-height: 28px;
  min-width: 28px;
  float: right;
  display: flex;
  justify-content: center;
  align-items: center;
  border-radius: 50%;
  color: white;
  background-color: black;
}

a:link {
  text-decoration: none;
}

.widget-template * {
  /* text-decoration: none; */
  color: #000000;

  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}

.widget-template {
  background: white;
  border-radius: 16px;
  overflow: hidden;
  position: relative;
  margin-bottom: 32px;
  margin-left: auto;
  margin-right: auto;
  height: 240px;

  transition-duration: 0.2s;
}

.widget-template:hover {
  /* padding-bottom: 10%; */
  z-index: 10;
  transform: scale(1.1); /*  default */
  -webkit-transform: scale(1.1); /*  크롬 */
  -moz-transform: scale(1.1); /* FireFox */
  -o-transform: scale(1.1); /* Opera */
}
</style>
