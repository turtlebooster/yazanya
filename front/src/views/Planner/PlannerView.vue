<template>
  <div class="main-mypage">
    <div class="achievement">
      <div class="container">
        <div class="logo">
          <h1>achievement</h1>
          <h2>progress</h2>
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
</template>

<script>
export default {};
</script>

<style>
/* .main-mypage {
  background-color: red;
} */

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
