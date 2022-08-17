<template>
  <div class="home-content main-color d-flex flex-column">
    <div
      id="carouselExampleCaptions"
      class="carousel slide"
      :class="[$root.theme ? 'carousel-dark' : '']"
      data-bs-ride="false"
      style="width: 100vw; min-width: 100vw; height: 100%"
    >
      <!-- carousel bottom button -->
      <div class="carousel-indicators">
        <button
          type="button"
          data-bs-target="#carouselExampleCaptions"
          data-bs-slide-to="0"
          class="active"
          aria-current="true"
          aria-label="Slide 1"
        ></button>
        <button
          type="button"
          data-bs-target="#carouselExampleCaptions"
          data-bs-slide-to="1"
          aria-label="Slide 2"
        ></button>
        <button
          type="button"
          data-bs-target="#carouselExampleCaptions"
          data-bs-slide-to="2"
          aria-label="Slide 3"
        ></button>
      </div>

      <!-- carousel contents -->
      <div class="carousel-inner" style="height: 100%">
        <div class="carousel-item active" style="height: 100%">
          <div
            class="carousel-item-1 itemBox outer d-flex"
            style="height: 100%"
          >
            <div
              class="d-flex flex-column align-items-center justify-content-center"
              style="height: 100%; width: 400px"
            >
              <div class="textBox">
                <h2>
                  <span>홀로</span> 하는 <span>공부</span>는<br />
                  너무나도 괴롭다
                </h2>
                <div class="spacer"></div>
                <p><span>야자냐</span>가 여러분의 공부를 도와드릴게요</p>
              </div>
              <img
                src="@/assets/logo/icon_009e73.png"
                style="height: 100px; object-fit: cover"
                alt="logo"
              />
              <router-link to="/account/login" style="text-decoration: none; z-index: 2;">
                <div class="btn-login">시작하기</div>
              </router-link>
            </div>
            <div class="carousel-item-1-img flex-grow-1"></div>
          </div>
        </div>

        <div class="carousel-item" style="height: 100%">
          <div
            class="carousel-item-2 outer itemBox d-flex"
            style="height: 100%"
          >
            <div class="container-fluid" style="height: 100%; width: 100%">
              <div class="row" style="height: 100%; width: 100%">
                <div
                  class="textBox col-12 col-sm-4"
                  style="padding-top: 24vh; padding-left: 8%"
                >
                  <h2>
                    <span>화상 공부방 서비스</span>
                  </h2>
                  <div class="spacer"></div>
                  <p>
                    실시간으로 <span>모두</span>와 함께 <span>공부</span>할 수
                    있어요
                  </p>
                  <p>
                    오른쪽의 플래너를 <span>드래그 & 드롭</span>하여
                    <br />여러분의 학습계획을 커스터마이징해보세요
                  </p>
                </div>

                <div
                  class="carousel-item-2-img col-12 col-sm-8"
                  :style="{
                    'min-height': '50%',
                    'background-image':
                      'url(' + require('@/assets/homeImg/1.png') + ')',
                    'background-size': 'contain',
                    'background-repeat': 'no-repeat',
                    'background-position': 'center',
                  }"
                ></div>
              </div>
            </div>
          </div>
        </div>

        <div class="carousel-item" style="height: 100%">
          <div
            class="carousel-item-3 outer itemBox d-flex"
            style="height: 100%"
          >
            <div class="container-fluid" style="height: 100%; width: 100%">
              <div class="row" style="height: 100%; width: 100%">
                <div
                  class="textBox col-12 col-sm-4"
                  style="padding-top: 24vh; padding-left: 8%"
                >
                  <h2>
                    <span>반응형 UI / UX</span>
                  </h2>
                  <div class="spacer"></div>
                  <p>
                    실시간으로 <span>모두</span>와 함께 <span>공부</span>할 수
                    있어요
                  </p>
                  <p>
                    오른쪽의 플래너를 <span>드래그 & 드롭</span>하여
                    <br />여러분의 학습계획을 커스터마이징해보세요
                  </p>
                </div>

                <div
                  class="carousel-item-3-img col-12 col-sm-8"
                  :style="{
                    'min-height': '50%',
                    'margin-top': '10vh',
                    'margin-bottom': '10vh',
                    'background-image':
                      'url(' + require('@/assets/homeImg/3.png') + ')',
                    'background-size': 'contain',
                    'background-repeat': 'no-repeat',
                    'background-position': 'center',
                  }"
                ></div>
              </div>
            </div>

            <!-- <div
              class="d-flex flex-column align-items-center justify-content-center"
              style="height: 100%; width: 320px"
            >
              <div class="textBox">
                <h2>
                  <span>반응형 UI / UX</span>
                </h2>
                <div class="spacer"></div>
                <p>
                  실시간으로 <span>모두</span>와 함께 <span>공부</span>할 수
                  있어요
                </p>
                <p>
                  오른쪽의 플래너를 <span>드래그 & 드롭</span>하여
                  <br />여러분의 학습계획을 커스터마이징해보세요
                </p>
              </div>
            </div> -->
          </div>
        </div>
      </div>

      <!-- carousel side button -->
      <button
        class="carousel-control-prev"
        type="button"
        data-bs-target="#carouselExampleCaptions"
        data-bs-slide="prev"
      >
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Previous</span>
      </button>
      <button
        class="carousel-control-next"
        type="button"
        data-bs-target="#carouselExampleCaptions"
        data-bs-slide="next"
      >
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Next</span>
      </button>
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style scoped>
.btn-login {
  margin-right: 16px;
  height: calc(var(--size-h-header) - 12px);
  width: 120px;
  border-radius: var(--size-h-header);
  color: white;
  text-align: center;
  line-height: calc(var(--size-h-header) - 12px);
  font-size: 12pt;
  font-weight: bold;
  font-family: 'Nunito', sans-serif;
  background: var(--theme-color);
}

.textBox span {
  color: var(--theme-color);
  font-size: 1.2em;
}

.carousel-item {
  padding: 16px;
}

.itemBox {
  border-radius: 32px;
  overflow: hidden;
}

.dark .carousel-item-1-img {
  background: linear-gradient(
      to right,
      rgba(64, 64, 64, 1) 0%,
      rgba(64, 64, 64, 0) 50%
    ),
    url('@/assets/homeImg/0.jpg');
  background-repeat: no-repeat;
  background-size: cover;
}

.light .carousel-item-1-img {
  background: linear-gradient(
      to right,
      rgba(255, 255, 255, 1) 0%,
      rgba(255, 255, 255, 0) 50%
    ),
    url('@/assets/homeImg/0.jpg');
  background-repeat: no-repeat;
  background-size: cover;
}
</style>
