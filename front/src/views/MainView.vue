<template>
  <div id="mainWrapper">
    <div id="topWrapper">
      <div id="titleWrapper">
        <div id="logoWrapper">
          <img id="logo" src="@/assets/img/main/log_only.png" alt="" />
        </div>
        <div id="title" class="fontEnglish">
          <span>SOME</span><span>LIGHT</span>
        </div>
      </div>
    </div>

    <div id="bodyWrapper">
      <div id="contentWrapper">
        <div id="loginWrapper">
          <div id="circleLogo">
            <img id="greenBulb" src="@/assets/img/main/greenBulb.png" alt="" />
          </div>
          <div id="nonMember" class="button">
            <div class="iconImg">
              <img src="@/assets/img/main/nonImg.png" alt="" />
            </div>
            <div class="text">비회원 이용</div>
          </div>
          <div id="kakaoMember" class="button">
            <div class="iconImg">
              <img src="@/assets/img/main/kakaoImg.png" alt="" />
            </div>
            <div class="text">카카오톡 로그인</div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
export default {
  setup() {},
};
</script>

<style scoped>
/* 너비 체크용 */
/* div {
  box-shadow: 0 0 0 1px #f00 inset;
} */

#mainWrapper {
  /* background-color: blue; */
  background-color: rgba(255, 251, 251, 100%);
}
#topWrapper {
  width: 100vw;
  height: 20vh;
  display: flex;
  justify-content: center;
  align-items: flex-end;
}
#titleWrapper {
  width: 27%;
  height: 60%;
  display: flex;
}
#logoWrapper {
  height: 100%;
  width: 26%;
}
#logo {
  height: 100%;
  width: 100%;
}
#title {
  width: 74%;
  font-size: 5.5rem;
  text-shadow: -2px 0 black, 0 2px black, 2px 0 black, 0 -2px black;
  color: red;
  line-height: 5.5rem;
  display: flex;
  align-items: flex-end;
}
@media (max-width: 768px) {
  #title {
    font-size: 3rem;
  }
}

#title > :nth-child(1) {
  color: rgba(254, 170, 183, 100%);
}
#title > :nth-child(2) {
  color: rgba(93, 90, 136, 100%);
}
#bodyWrapper {
  width: 100vw;
  height: 80vh;
  display: flex;
  justify-content: center;
  align-items: flex-start;
}
#contentWrapper {
  width: 45%;
  height: 88%;
  display: flex;
  align-items: flex-end;
}
#loginWrapper {
  width: 100%;
  height: 87%;
  border: 1px solid #cfcaca;
  box-shadow: 5px 8px 4px rgba(0, 0, 0, 25%);
  border-radius: 110px;
  position: relative;
  z-index: 0;
  display: flex;
  flex-direction: column;
  justify-content: flex-end;
  align-items: center;
}
#circleLogo {
  width: 29%;
  height: 39%;
  border: 1px solid #cfcaca;
  box-shadow: 4px 7px 4px rgba(0, 0, 0, 25%);
  border-radius: 50%;
  position: absolute;
  top: 5%;
  left: 50%;
  transform: translate(-50%, -50%);
  z-index: 2;
  background-color: white;
}
#greenBulb {
  padding-left: 1%;
  width: 100%;
  height: 100%;
}
.button {
  width: 82%;
  height: 15%;
  margin-bottom: 5%;
  border-radius: 15px;
  display: flex;
}
.iconImg {
  height: 100%;
  width: 20%;
  display: flex;
  justify-content: center;
  align-items: center;
}
.iconImg > img {
  height: 70%;
}

.text {
  height: 100%;
  width: 80%;
  /* color: #FFFBFB; */
  color: white;
  font-size: 2rem;
  padding-left: 18%;
  display: flex;
  align-items: center;
}
#kakaoMember {
  margin-bottom: 15%;
  background-color: rgba(16, 15, 80, 100%);
}
#nonMember {
  background-color: rgba(94, 95, 127, 52%);
}
</style>
