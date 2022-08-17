<template>
  <div class="planner-view d-flex flex-column align-items-center main">
    <div class="spacer"></div>

    <!-- search -->
    <!-- <div class="search outer d-flex align-items-center">
      <input
        class="search_input"
        type="text"
        placeholder="플래너 템플릿 검색"
      />
      <a href="#" class="search_icon sub"><i class="bi bi-search"></i></a>
    </div> -->

    <!-- <div>받아오는 값</div>
    <div>{{ profile }}</div>

    <div>현재 리스트 순서</div>
    <div>{{ widgetList.toString() }}</div> -->

    <!-- <div class="spacer"></div> -->
    <div class="spacer"></div>

    <!-- planner masonry & draggable layout -->
    <div
      class="masonry-container drag-container"
      style="width: 90%; display: grid; column-gap: 10px; grid-auto-rows: 10px"
    >
      <div
        class="masonry-item drag-item"
        v-for="(widgetNum, index) in widgetList"
        :id="`dragItem-${index}-widget-${widgetNum}`"
        :key="index"
      >
        <component
          :is="componentList[widgetNum]"
          class="widget outer main"
          v-b-modal="`modal-${componentNameList[widgetNum]}`"
        ></component>
      </div>
    </div>
  </div>
</template>

<script>
/* still in progress */
import PlannerCalender from './components/PlannerCalendar.vue';
import PlannerProgress from './components/PlannerProgress.vue';
import PlannerTodo from './components/PlannerTodo.vue';
import PlannerTotal from './components/PlannerTotal.vue';
import PlannerRank from './components/PlannerRank.vue';
import PlannerStatus from './components/PlannerStatus.vue';
import PlannerTimer from './components/PlannerTimer.vue';

import rest_user from '@/rest/user';
// import rest_todo from '@/rest/todo';
import { ref, nextTick, onMounted } from 'vue';
import { useStore } from 'vuex';

export default {
  setup() {
    const store = useStore();

    // 테스트 용
    const widgetList = ref([0, 1, 4, 3, 5, 2]);

    const componentList = [
      PlannerTotal,
      PlannerRank,
      PlannerStatus, // 5
      PlannerCalender, // 0
      PlannerProgress,
      PlannerTodo,
      PlannerTimer,
    ];

    const componentNameList = [
      'planner-total',
      'planner-rank',
      'planner-status',
      'planner-calendar',
      'planner-progress',
      'planner-todo',
      'planner-timer',
    ];

    const profile = ref({
      profileTotalStudyTime: null,
      profileRank: null,
      profilePlannerSet: null,
    });

    async function init() {
      profile.value = await rest_user.getProfile(store.getters.getUserID);
      // 주석 처리 풀어야 서버로 부터 받아옴
      widgetList.value = await profile.value.profilePlannerSet.split(',');

      nextTick(() => {
        masonryLayout();
        window.addEventListener('resize', masonryLayout);
        dragLayout(document.querySelector('.drag-container'));
      });
    }

    function masonryLayout() {
      const masonryContainer = document.querySelector('.masonry-container');
      if (!masonryContainer) {
        return;
      }

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

    function dragLayout(list) {
      let currNum = null;
      let currIndex = null;

      let nextNum = null;
      let nextIndex = null;

      [...list.children].map((item) => {
        item.draggable = true;
      });

      function _onDragOver(e) {
        e.preventDefault();
        if (e.target.closest('.widget')) {
          const nextData = e.target.closest('.drag-item').id.split('-');
          nextNum = nextData[3];
          nextIndex = nextData[1];
        }
      }

      function _onDragEnd(e) {
        e.preventDefault();
        e.target.style.visibility = 'visible';
        if (nextNum != null) {
          widgetList.value.splice(currIndex, 1);
          widgetList.value.splice(nextIndex, 0, currNum);

          nextTick(() => {
            masonryLayout();
          });
        }
      }

      list.addEventListener('dragstart', function (e) {
        const currData = e.target.closest('.drag-item').id.split('-');
        currIndex = currData[1];
        currNum = currData[3];

        e.target.style.visibility = 'hidden';
        list.addEventListener('dragover', _onDragOver, false);
        list.addEventListener('dragend', _onDragEnd, false);
      });
    }

    onMounted(() => {
      init();
    });

    return {
      componentList,
      componentNameList,
      widgetList,
      profile,
      masonryLayout,
    };
  },
};
</script>

<style scoped>
.search {
  height: 40px;
  width: 80%;
  flex-wrap: nowrap;
  border-radius: 24px;
  padding: 10px;
}

.search_input {
  width: 100px;
  height: 32px;
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
}

.drag-container {
  -webkit-user-select: none;
  -moz-user-select: none;
  user-select: none;
  transition-duration: 0.2s;
}

.blank {
  border: 1px dashed #000;
  border-radius: 16px;
}

.widget {
  z-index: 1;
  position: relative;
  border-radius: 16px;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;

  transition-duration: 0.2s;
}

.widget-component {
  width: 100%;
  padding: 16px;
  display: block;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}

.widget-component * {
  /* text-decoration: none; */
  display: block;
  color: #000000;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}
</style>
