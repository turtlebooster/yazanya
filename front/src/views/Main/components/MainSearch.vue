<template>
  <div class="main-lobby">
    <div class="container">
      <input
        class="form-control mr-sm-2"
        type="search"
        placeholder="Search"
        aria-label="Search"
      />
      <div class="room-history"></div>
      <div class="room-recommend"></div>
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style scoped>
/* .main-lobby {
  background-color: green;
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
