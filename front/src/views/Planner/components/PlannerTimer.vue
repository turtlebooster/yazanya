<template>
  <div class="widget planner-timer">
    <div class="widget-component">
      <div class="timer-container">
        <div id="timer" class="timer">
          <div id="lines"></div>
        </div>
      </div>
    </div>

    <!-- modal -->
    <b-modal
      class="widget-component-modal"
      id="modal-planner-timer"
      centered
      title="타이머 세팅"
    >
      <p class="my-4">타이머</p>
    </b-modal>
  </div>
</template>

<script>
import { onMounted } from 'vue';
export default {
  setup() {
    onMounted(() => {
      const lines = document.getElementById('lines');

      for (let i = 0; i < 30; i++) {
        const line = document.createElement('div');
        line.classList.add('line');
        line.style.transform = `rotate(${i * 6}deg)`;

        if (i % 5 == 0) {
          line.classList.add('thick');
        }
        // console.log(lines);
        lines.append(line);
      }
    });
  },
};
</script>

<style scoped>
.timer-container {
  color: rgb(50, 50, 50);
  width: 100%;
  height: 200px;
  border: 15px solid rgb(55, 55, 55);
  border-radius: 20%;
}

.timer {
  position: relative;
  font-size: 20px;
}

.timer .line {
  width: 100%;
  height: 1px;
  top: calc(50% - 1px / 2);

  background: #000;
  position: absolute;
}

.timer .line.thick {
  width: calc(100% + 0.4em);
  left: calc(-0.4em / 2);

  height: 3px;
  top: calc(50% - 3px / 2);
}
</style>
