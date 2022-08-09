<template>
  <div class="home-sidebar">
    <div></div>
  </div>
</template>

<script>
export default {};
</script>

<style scoped>
.home-sidebar {
  z-index: 20;
  position: fixed;

  display: flex;
  flex-direction: column;
  margin: -2px 8px 8px 8px;

  top: var(--size-h-header);
  left: 0px;
  bottom: 0px;

  border-radius: calc((var(--size-w-side) - 16px) / 2);

  width: calc(var(--size-w-side) - 16px);

  background-color: var(--theme-color);

  transition: 0.2s;

  overflow: hidden;
  white-space: nowrap;
}

.main-sidebar a {
  position: relative;
  display: flex;
  width: 100%;

  text-decoration: none;
  color: var(--light-main-color);
  font-size: 12pt;

  margin-bottom: 8px;

  border-bottom-left-radius: calc(var(--size-w-side) / 2);
  border-top-left-radius: calc(var(--size-w-side) / 2);
}

.main-sidebar a.router-link-exact-active {
  background: var(--light-main-color);
  color: var(--theme-color);
}

.main-sidebar a.router-link-exact-active::before {
  content: '';
  position: absolute;
  top: -30px;
  right: 0;
  width: 30px;
  height: 30px;
  background: var(--theme-color);
  border-radius: 50%;
  box-shadow: 15px 15px 0 var(--light-main-color);
  z-index: -1;
}

.main-sidebar a.router-link-exact-active::after {
  content: '';
  position: absolute;
  bottom: -30px;
  right: 0;
  width: 30px;
  height: 30px;
  background: var(--theme-color);
  border-radius: 50%;
  box-shadow: 15px -15px 0 var(--light-main-color);
  z-index: -1;
}

.main-sidebar a .icon {
  position: relative;

  width: calc(var(--size-w-side) - 16px);
  min-width: calc(var(--size-w-side) - 16px);
  height: calc(var(--size-w-side) - 16px);
  line-height: calc(var(--size-w-side) - 16px);
  text-align: center;
}

.main-sidebar a .icon i {
  font-size: 1.5em;
}

.main-sidebar a .title {
  position: relative;
  display: block;

  margin-left: 8px;

  height: calc(var(--size-w-side) - 16px);
  line-height: calc(var(--size-w-side) - 16px);
  font-size: 1.5em;
}
</style>
