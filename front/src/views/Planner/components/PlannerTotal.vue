<template>
  <div class="widget-template">
    <div class="widget-component">
      <div class="widget-component-icon">
        <span><i class="bi bi-trophy-fill"></i></span>
      </div>
      <div class="widget-component-detail">
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
  width: 100%;
  height: 100%;
  padding: 16px;
  background: linear-gradient(-30deg, #b576f6, #9a4fe9);
}

.widget-component * {
  font-size: 20pt;
  color: white;
}

.widget-component::before,
.widget-component::after {
  content: '';
  position: absolute;
  top: -5%;
  right: -15%;
  height: 150px;
  width: 150px;
  background-color: rgba(255, 255, 255, 0.2);
  border-radius: 100%;
}

.widget-component::after {
  top: auto;
  bottom: -5%;
  right: -5%;
}

.widget-component-icon {
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
