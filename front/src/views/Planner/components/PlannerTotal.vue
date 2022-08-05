<template>
  <div>
    <div class="block">
      <div class="block-icon">
        <span><i class="bi bi-mortarboard-fill"></i></span>
      </div>
      <div class="block-detail">
        <div>총 공부 시간</div>
        <div style="font-size: 16pt">15시간 45분 02초</div>
      </div>
    </div>

    <!-- modal -->
    <b-modal id="modal-3" centered title="총 공부 시간">
      <p class="my-4">대충 공부한 거 내용</p>
    </b-modal>
  </div>
</template>

<script>
export default {};
</script>

<style scoped>
.widget-component {
  background: linear-gradient(-30deg, #b576f6, #9a4fe9);
  /* background: linear-gradient(to left, #feb683, #ff8993); */
}

.block {
  padding: 16px;
}

.block * {
  font-size: 20pt;
  color: white;
}

.block::before,
.block::after {
  content: '';
  position: absolute;
  top: -5%;
  right: -15%;
  height: 150px;
  width: 150px;
  background-color: rgba(255, 255, 255, 0.2);
  border-radius: 100%;
}

.block::after {
  top: auto;
  bottom: -5%;
  right: -5%;
}

.block-icon {
  margin-bottom: 15px;
  height: 60px;
  width: 60px;
  background-color: rgba(255, 255, 255, 0.4);
  border-radius: 50%;

  display: flex;
  justify-content: center;
  align-items: center;
  box-shadow: 0 0 8px white;
}
</style>
