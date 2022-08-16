<template>
  <div
    class="home-content main-color d-flex flex-column align-items-center justify-content-center"
  >
    <div class="column-1" style="width: 100%">
      <div
        id="carouselExampleCaptions"
        class="carousel carousel-dark slide"
        data-bs-ride="false"
        style="width: 100vw; min-width: 100vw"
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
        </div>

        <!-- carousel contents -->
        <div class="carousel-inner">
          <div class="carousel-item active">
            <div class="carousel-item-1 d-flex" style="height: 50vh">
              <div class="carousel-item-1-img flex-grow-1"></div>
              <div
                class="d-flex flex-column align-items-center justify-content-center"
                style="height: 40vh; width: 40%"
              >
                <h3>나를 위한 스터디 카페</h3>
                <img
                  src="@/assets/logo/icon_009e73.png"
                  style="height: 100px; object-fit: cover"
                  alt="logo"
                />
                <router-link to="/account/login" style="text-decoration: none">
                  <div
                    style="
                      margin-right: 16px;
                      background: var(--theme-color);
                      height: calc(var(--size-h-header) - 12px);
                      width: 100px;
                      border-radius: var(--size-h-header);
                      color: white;
                      text-align: center;
                      line-height: calc(var(--size-h-header) - 12px);
                      font-size: 14pt;
                    "
                  >
                    시작하기
                  </div>
                </router-link>
              </div>
            </div>
          </div>

          <div class="carousel-item">
            <div class="carousel-item-2 d-flex" style="height: 50vh">
              <div class="carousel-item-2-img flex-grow-1 flex-shrink-1"></div>
              <div
                class="d-flex flex-column align-items-center justify-content-center"
                style="height: 40vh; width: 40%"
              >
                <h3>혼자 공부하기 힘들다면</h3>
                <img
                  src="@/assets/logo/icon_009e73.png"
                  style="height: 100px; object-fit: cover"
                  alt="logo"
                />
                <router-link to="/account/login" style="text-decoration: none">
                  <div
                    style="
                      margin-right: 16px;
                      background: var(--theme-color);
                      height: calc(var(--size-h-header) - 12px);
                      width: 100px;
                      border-radius: var(--size-h-header);
                      color: white;
                      text-align: center;
                      line-height: calc(var(--size-h-header) - 12px);
                      font-size: 14pt;
                    "
                  >
                    시작하기
                  </div>
                </router-link>
              </div>
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

    <div class="spacer"></div>
    <div class="liner"></div>
    <div class="spacer"></div>

    <div class="column-2 d-flex" style="width: 90%">
      <h2
        class="flex-grow-1 flex-shrink-1"
        style="color: var(--theme-color); text-align: center"
      >
        야자냐로 모두와 함께 공부하세요
      </h2>

      <img
        src="@/assets/connection_img.png"
        style="width: 25%; object-fit: contain"
        alt="logo"
      />
    </div>

    <div class="spacer"></div>
    <div class="liner"></div>
    <div class="spacer"></div>

    <div class="column-3 d-flex" style="width: 90%">
      <img
        src="@/assets/homeImg/0.png"
        style="width: 25%; object-fit: contain"
        alt="logo"
      />
      <img
        src="@/assets/homeImg/1.png"
        style="width: 8%; object-fit: contain"
        alt="logo"
      />
      <h2
        class="flex-grow-1 flex-shrink-1 text-align-center"
        style="color: var(--theme-color); text-align: center"
      >
        어떤 기기로든 접속하세요
      </h2>
    </div>

    <div class="spacer"></div>
    <div class="liner"></div>
    <div class="spacer"></div>

    <div class="column-4 d-flex" style="width: 90%">
      <h2
        class="flex-grow-1 flex-shrink-1 text-align-center"
        style="color: var(--theme-color); text-align: center"
      >
        온라인 스터디에 플래너가 함께합니다.
      </h2>
      <img
        src="@/assets/homeImg/2.png"
        style="width: 30%; object-fit: contain"
        alt="logo"
      />
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style>
.dark .carousel-item-1-img {
  background: linear-gradient(
      to left,
      rgba(0, 0, 0, 1) 0%,
      rgba(0, 0, 0, 0) 100%
    ),
    url('@/assets/thumbnail/1.jpg');
  background-repeat: no-repeat;
  background-size: cover;
}

.light .carousel-item-1-img {
  background: linear-gradient(
      to left,
      rgba(255, 255, 255, 1) 0%,
      rgba(255, 255, 255, 0) 100%
    ),
    url('@/assets/thumbnail/1.jpg');
  background-repeat: no-repeat;
  background-size: cover;
}

.dark .carousel-item-2-img {
  background: linear-gradient(
      to left,
      rgba(0, 0, 0, 1) 0%,
      rgba(0, 0, 0, 0) 100%
    ),
    url('@/assets/thumbnail/2.jpg');
  background-repeat: no-repeat;
  background-size: cover;
}

.light .carousel-item-2-img {
  background: linear-gradient(
      to left,
      rgba(255, 255, 255, 1) 0%,
      rgba(255, 255, 255, 0) 100%
    ),
    url('@/assets/thumbnail/2.jpg');
  background-repeat: no-repeat;
  background-size: cover;
}
</style>
