<template>
  <div class="widget planner-total">
    <div class="widget-component" id="1">
      <div class="widget-component-icon">
        <span><i class="bi bi-trophy-fill"></i></span>
      </div>
      <div class="widget-component-detail">
        <div>총 공부 시간</div>
        <div style="font-size: 16pt">15시간 45분 02초</div>
      </div>
    </div>

    <!-- modal -->
    <b-modal id="modal-planner-total" centered title="총 공부 시간">
      <p class="my-4">user 에서 가져오면 됨 ㅇㅇ</p>
    </b-modal>
  </div>
</template>

<script>
export default {};
</script>

<style scoped>
.widget-component {
  position: relative;
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
