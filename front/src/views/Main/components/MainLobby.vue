<template>
  <div class="main-lobby">
    <div class="container d-flex flex-column">
      <div class="main-lobby-search d-flex justify-content-center">
        <div class="search d-flex align-items-center">
          <input
            class="search_input"
            type="text"
            placeholder="Search here..."
          />
          <a href="#" class="search_icon"><i class="bi bi-search"></i></a>
        </div>
      </div>

      <div
        class="main-lobby-history"
        style="background: var(--sub-color-o); height: 100px"
      ></div>

      <div
        class="main-lobby-recommend"
        style="background: var(--sub-color-b); height: 100px"
      ></div>
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style scoped>
.main-lobby {
  background-color: var(--dark-main-color);
  padding: 10px;
  height: 100%;
}

.search {
  height: 48px;
  background-color: var(--dark-main-color);
  border-radius: 24px;
  padding: 10px;
  box-shadow: 6px 6px 10px -1px rgba(0, 0, 0, 0.2),
    -6px -6px 10px -1px rgba(255, 255, 255, 0.2);
}

.search_input {
  height: 32px;
  color: black;
  border: 0;
  outline: 0;
  background: none;
  margin-top: 5px;
  caret-color: transparent;
  line-height: 40px;

  padding: 0 10px;
  flex-grow: 1;
  caret-color: var(--main-color);
}

.search:hover > .search_icon {
  background: var(--main-color);
  color: white;
}

.search_icon {
  height: 32px;
  width: 32px;
  float: right;
  display: flex;
  justify-content: center;
  align-items: center;
  border-radius: 50%;
  color: white;
  background-color: black;
}

a:link {
  text-decoration: none;
}
</style>
