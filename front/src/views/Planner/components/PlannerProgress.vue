<template>
  <div>
    <div class="planner-progress d-flex flex-column">
      <button
        type="button"
        class="btn-close align-self-end"
        aria-label="Close"
      ></button>

      <div>달성도</div>
      <div class="achievement">
        <div class="container">
          <div class="logo">
            <h1>achievement</h1>
            <h2>75%</h2>
          </div>

          <div class="progress-container">
            <svg class="progressbar" viewBox="0 0 64 64">
              <circle
                class="progressbar-track"
                cx="50%"
                cy="50%"
                r="30px"
              ></circle>
              <circle
                class="progressbar-line"
                cx="50%"
                cy="50%"
                r="30px"
              ></circle>
            </svg>
          </div>
        </div>
      </div>
    </div>

    <!-- modal -->
    <b-modal id="modal-1" centered title="달성도">
      <p class="my-4">달성도 상세사항</p>
    </b-modal>
  </div>
</template>

<script>
export default {};
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
