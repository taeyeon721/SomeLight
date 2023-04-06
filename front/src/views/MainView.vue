<template>
  <div id="minaWrapper">
    <div class="section" ref="section1">
      <div id="contentsWrapper">
        <div id="imgWrapper">
          <!-- <img src="@/assets/img/main/item1.jpg" alt="" /> -->
          <video muted autoplay loop src="@/assets/img/main/main.mp4" type="video/mp4"></video>
        </div>
        <div id="textWrapper">
          <h1 class="font">AI가 판별해주는 <br />나의 썸 이야기</h1>
          <h2 class="font fixMargin">
            요즘따라 내꺼 인듯 내꺼 아닌 너 <br />썸인지 아닌지 AI가 구분해준다.
          </h2>
        </div>
      </div>
    </div>
    <div class="section" ref="section2">
      <div class="article">
        <div class="imgWrapper">
          <img src="@/assets/img/main/item5.png" alt="" />
        </div>
        <div class="textWrapper">
          <h1 class="font">나만의 프라이빗한<br />연애상담소</h1>
          <h2 class="font">
            그 사람의 마음이 뭘까? <br />허심탄회하게 말하거나 <br />
            글로 써보세요.
          </h2>
        </div>
      </div>
    </div>
    <div class="section" ref="section3">
      <div class="article">
        <div class="imgWrapper addImg">
          <img src="@/assets/img/main/item3.png" alt="" />
        </div>
        <div class="textWrapper">
          <h1 class="font">RED?<br />GREEN?</h1>
          <h2 class="font">
            총 3가지 결과로 <br />당신의 썸을 판별해 드립니다.
          </h2>
        </div>
      </div>
    </div>
    <div class="section" ref="section4">
      <div class="article">
        <div class="imgWrapper">
          <img src="@/assets/img/main/item6.png" alt="" />
        </div>
        <div class="textWrapper">
          <h1 class="font">다른 사람이 봐도 <br />썸일까?</h1>
          <h2 class="font">
            커뮤니티 투표로 <br />다른 사람의 생각을 들어보세요.
          </h2>
        </div>
      </div>
    </div>

    <div class="footer font">
      <h1>Team<span>RED</span>Light</h1>
      <h2>Copyright © 2023 BRIX Templates | All Rights Reserved</h2>
    </div>
  </div>
</template>

<script>
export default {
  setup() {},
  mounted() {
    // 스크롤 section마다 이동
    const elm = this.$refs;
    const elmCount = Object.keys(elm).length;
    Object.keys(elm).forEach((key, index) => {
      const item = elm[key];
      item.addEventListener("wheel", function (event) {
        event.preventDefault();
        let delta = 0;

        if (!event) event = window.event;
        if (event.wheelDelta) {
          delta = event.wheelDelta / 120;
          if (window.opera) delta = -delta;
        } else if (event.detail) delta = -event.detail / 3;

        let moveTop = window.scrollY;
        let elmSelector = elm[key];

        // wheel down : move to next section
        if (delta < 0) {
          if (elmSelector !== elmCount - 1) {
            try {
              moveTop =
                window.pageYOffset +
                elmSelector.nextElementSibling.getBoundingClientRect().top;
            } catch (e) {
              console.log(e);
            }
          }
        }
        // wheel up : move to previous section
        else {
          if (elmSelector !== 0) {
            try {
              moveTop =
                window.pageYOffset +
                elmSelector.previousElementSibling.getBoundingClientRect().top;
            } catch (e) {
              console.log(e);
            }
          }
        }

        const body = document.querySelector("html");
        window.scrollTo({ top: moveTop, left: 0, behavior: "smooth" });
      });
    });
  },
};
</script>

<style scoped>
#minaWrapper {
  /* width: 100vw;
  height: 100vh; */
  /* 마우스 휠때문에 가로 휠이 생긴다. */
  width: calc(100vw - (100vw - 100%));
  height: calc(100vh - (100vh - 100%));
}
.font {
  font-family: "Dovemayo_gothic";
  color: rgba(77, 69, 93, 100%);
}
h1 {
  font-size: 3rem;
  line-height: 5rem;
  font-weight: bold;
  margin-bottom: 5vh;
}
h2 {
  font-size: 1.5rem;
  line-height: 3.5rem;
}
.section {
  width: 100%;
  height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
}

#contentsWrapper {
  width: 100%;
  height: 70%;
  display: flex;
}
#imgWrapper {
  width: 60%;
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
}
#imgWrapper > img {
  width: 100%;
  height: 100%;
}
#textWrapper {
  width: 40%;
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

.article {
  width: 70%;
  height: 70%;
  display: flex;
  align-items: center;
}
.imgWrapper {
  width: 60%;
  height: 70%;
  display: flex;
  align-items: center;
  justify-content: center;
}
.imgWrapper > img {
  width: 100%;
  height: auto;
}
#imgWrapper > video{
  width: 100%;
  height: 120%;
}
.textWrapper {
  width: 40%;
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  /* align-items: center; */
}
.textWrapper > h1,
h2 {
  margin-left: 15%;
}
.fixMargin {
  margin-left: 0%;
}
.textWrapper:nth-child(0) > h2 {
  margin-left: 0%;
}
.addImg {
  height: 100%;
}
.addImg > img {
  height: 100%;
  width: auto;
}

.footer {
  width: 100%;
  height: 10vh;
  background-color: rgba(245, 233, 207);
  display: flex;
  justify-content: space-around;
  align-items: center;
  text-align: center;
}
.footer > h1 {
  line-height: 10vh;
  margin-bottom: 0px;
}
.footer > h1 > span {
  color: rgba(243, 153, 138, 100%);
}
.footer > h2 {
  font-size: 1rem;
  margin-left: 0px;
}
/* -------- */
</style>
