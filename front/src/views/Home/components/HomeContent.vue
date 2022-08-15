<template>
  <div class="home-content d-flex flex-column">
    <div class="column-1">
      <div
        id="carouselExampleCaptions"
        class="carousel carousel-dark slide"
        data-bs-ride="false"
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
        <div class="carousel-inner">
          <div class="carousel-item active">
            <div
              class="carousel-item-1 d-flex flex-column align-items-center justify-content-center"
              style="
                background: linear-gradient(-45deg, #8ec5fc, #e0c3fc);
                height: 90vh;
              "
            >
              <img
                src="@/assets/logo/icon_009e73.png"
                style="height: 100px; object-fit: cover"
                alt="logo"
              />
              <div>야자냐에 오신 것을 환영합니다.</div>
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

          <div class="carousel-item">
            <div
              class="carousel-item-2 d-flex flex-column align-items-center justify-content-center"
              style="height: 90vh"
            >
              <img
                src="@/assets/logo/icon_009e73.png"
                style="height: 100px; object-fit: cover"
                alt="logo"
              />
              <div>야자냐에 오신 것을 환영합니다.</div>
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

    <div class="column-2 d-flex">
      <div class="flex-grow-1" style="height: 400px">
        { 대충 모두와 함께 공부하는 야자냐 너무 좋다는 내용 + 캐치프레이즈 }
      </div>
      <div style="height: 400px; width: 400px; margin-top: 32px">
        <img
          src="@/assets/connection_img.png"
          style="object-fit: cover"
          alt="logo"
        />
      </div>
    </div>

    <div class="column-3 d-flex" style="background: var(--light-sub-color)">
      <div style="height: 400px; width: 400px; background: gray">sd</div>
      <div class="flex-grow-1" style="height: 400px">
        { 야자냐 잠깨워주는 기능 너무 좋다는 내용 }
      </div>
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style>
.carousel-item-2 {
  background-image: url('@/assets/thumbnail/1.jpg');
  background-size: cover;
}
</style>
