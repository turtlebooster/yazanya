<template>
  <div class="planner-view d-flex flex-column align-items-center">
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

    <div>{{ profile }}</div>
    <div class="spacer"></div>
    <div class="spacer"></div>

    <!-- planner masonry layout -->
    <div
      class="masonry-container"
      style="width: 80%; display: grid; column-gap: 10px; grid-auto-rows: 10px"
    >
      <div
        class="masonry-item"
        v-for="(widget, index) in widgetList"
        :key="index"
      >
        <component
          :is="componentList[widget]"
          class="widget outer"
          v-b-modal="`modal-${componentNameList[widget]}`"
        ></component>
      </div>
    </div>
  </div>
</template>

<script>
import PlannerCalender from './components/PlannerCalendar.vue';
import PlannerProgress from './components/PlannerProgress.vue';
import PlannerTodo from './components/PlannerTodo.vue';
import PlannerTotal from './components/PlannerTotal.vue';
import PlannerRank from './components/PlannerRank.vue';
import PlannerStatus from './components/PlannerStatus.vue';
import PlannerTimer from './components/PlannerTimer.vue';

import rest_user from '@/rest/user';
// import rest_todo from '@/rest/todo';
import { ref, onMounted } from 'vue';
import { useStore } from 'vuex';

export default {
  setup() {
    const store = useStore();

    const componentList = [
      PlannerCalender, // 0
      PlannerProgress,
      PlannerTodo,
      PlannerTotal,
      PlannerRank,
      PlannerStatus, // 5
      PlannerTimer,
    ];

    const componentNameList = [
      'planner-calendar',
      'planner-progress',
      'planner-todo',
      'planner-total',
      'planner-rank',
      'planner-status',
      'planner-timer',
    ];

    const profile = ref({
      profileTotalStudyTime: null,
      profileRank: null,
      profilePlannerSet: null,
    });

    async function init() {
      profile.value = await rest_user.getProfile(store.getters.getUserID);
    }

    const widgetList = ref([0, 6, 4, 3, 5, 2]);

    function masonryResize() {
      const masonryContainer = document.querySelector('.masonry-container');
      if (!masonryContainer) return;

      const masonryContainerStyle = getComputedStyle(masonryContainer);

      const containerWidth = parseInt(
        masonryContainerStyle.getPropertyValue('width')
      );

      if (containerWidth > 1440) {
        masonryContainer.style.gridTemplateColumns = `repeat(4, calc(${containerWidth}px / 4)`;
      } else if (containerWidth > 960) {
        masonryContainer.style.gridTemplateColumns = `repeat(3, calc(${containerWidth}px / 3)`;
      } else if (containerWidth > 560) {
        masonryContainer.style.gridTemplateColumns = `repeat(2, calc(${containerWidth}px / 2)`;
      } else {
        masonryContainer.style.gridTemplateColumns = `repeat(1, ${containerWidth}px)`;
      }

      const columnGap = parseInt(
        masonryContainerStyle.getPropertyValue('column-gap')
      );
      const autoRows = parseInt(
        masonryContainerStyle.getPropertyValue('grid-auto-rows')
      );

      document.querySelectorAll('.masonry-item').forEach((el) => {
        el.style.gridRowEnd = `span ${Math.ceil(
          el.querySelector('.widget').scrollHeight / autoRows +
            columnGap / autoRows
        )}`;
      });
    }

    onMounted(() => {
      init();
      masonryResize();
      window.addEventListener('resize', masonryResize);
    });

    return { componentList, componentNameList, widgetList, profile };
  },
};
</script>

<style>
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

.widget-component * {
  /* text-decoration: none; */
  display: block;
  color: #000000;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}

.widget {
  z-index: 1;
  position: relative;
  background: white;
  border-radius: 16px;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;

  transition-duration: 0.2s;
}

.widget:hover {
  z-index: 2;
  transform: scale(1.1); /*  default */
  -webkit-transform: scale(1.1); /*  크롬 */
  -moz-transform: scale(1.1); /* FireFox */
  -o-transform: scale(1.1); /* Opera */
}

.widget-component {
  width: 100%;
  padding: 16px;
  display: block;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  background: var(--main-color);
}

.dark .widget-component {
  background: var(--dark-main-color);
}
</style>
