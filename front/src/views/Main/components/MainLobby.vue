<template>
  <div class="main-lobby">
    <div class="container d-flex flex-column">
      <div class="main-lobby-search d-flex justify-content-center">
        <div class="search outer d-flex align-items-center">
          <input
            class="search_input"
            type="text"
            placeholder="Search here..."
          />
          <a href="#" class="search_icon"><i class="bi bi-search"></i></a>
        </div>
      </div>

      <div
        class="search outer d-flex flex-column"
        style="height: 400px; width: 400px; padding: 20px"
      >
        <div class="search outer d-flex"></div>

        <div></div>

        <img
          :src="require(`@/assets/avatar/3.jpg`)"
          alt="profile"
          class="image icon"
          style="
            height: 40px;
            width: 40px;
            object-fit: cover;
            border-radius: 50%;
          "
        />
      </div>

      <div
        class="main-lobby-history"
        style="background: var(--sub-color-o); height: 100px"
      ></div>

      <div class="line"></div>

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
  background-color: var(--light-main-color);
  padding: 16px;
  height: 100%;
}

.outer {
  box-shadow: 4px 4px 10px -1px rgba(0, 0, 0, 0.25),
    -4px -4px 10px -1px rgba(255, 255, 255, 0.25);
}

.inner {
  box-shadow: inset 4px 4px 10px -1px rgba(0, 0, 0, 0.25),
    inset -4px -4px 10px -1px rgba(255, 255, 255, 0.25);
}

.line {
  height: 4px;
  background: var(--main-color);
}

.search {
  height: 40px;
  width: 60vw;
  flex-wrap: nowrap;

  background-color: var(--light-main-color);
  border-radius: 24px;
  margin-bottom: 50px;
  padding: 10px;
}

.search_input {
  width: 100px;
  height: 32px;
  color: black;
  /* color: white; */
  border: 0;
  outline: 0;
  background: none;

  padding: 0 10px;
  flex-grow: 1;
  flex-shrink: 1;
  caret-color: var(--main-color);

  overflow: hidden;
}

.search:hover > .search_icon {
  background: var(--main-color);
  color: white;
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
  background-color: black;
}

a:link {
  text-decoration: none;
}
</style>
