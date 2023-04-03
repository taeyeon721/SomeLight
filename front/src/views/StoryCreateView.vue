<template>
  <div class="loadingWrapper" v-if="isLoading">
    <span style="--i: 1">S</span>
    <span style="--i: 2">O</span>
    <span style="--i: 3">M</span>
    <span style="--i: 4">E</span>
    <span style="--i: 5">L</span>
    <span style="--i: 6">I</span>
    <span style="--i: 7">G</span>
    <span style="--i: 8">H</span>
    <span style="--i: 9">T</span>
    <img
      style="--i: 10"
      src="@/assets/img/result/greenheart.png"
      height="10"
      alt=""
    />
    <img
      style="--i: 11"
      src="@/assets/img/result/navyheart.png"
      height="10"
      alt=""
    />
    <img
      style="--i: 12"
      src="@/assets/img/result/redheart.png"
      height="10"
      alt=""
    />
  </div>
  <div v-else id="mainWrapper">
    <div></div>
    <div class="bgImg">
      <div class="bgImg bgImg2">
        <div class="side"></div>
        <div class="center">
          <div>
            <div class="inputBox">
              <textarea
                class="font"
                name=""
                id=""
                cols="30"
                rows="150"
                v-model="texts"
                placeholder="여러분의 사연을 입력해주세요"
              ></textarea>
            </div>
          </div>
          <div>
            <div class="frame">
              <input
                type="checkbox"
                id="cb-1"
                name="cb"
                class="checkbox"
                v-model="isChecked"
              />
              <label
                for="cb-1"
                class="label"
                :class="{ on: !isRecognizing }"
                @click="toggleRecognition"
              ></label>
              <div class="microphone">
                <svg width="100px" height="100px" viewBox="0 0 100 100">
                  <circle class="circle" cx="50" cy="50" r="47" />
                </svg>
                <div class="icon">
                  <div class="body">
                    <div class="fill"></div>
                  </div>
                  <div class="foot_v"></div>
                  <div class="foot_h"></div>
                </div>
                <div class="dots">
                  <div class="dot dot-1"></div>
                  <div class="dot dot-2"></div>
                  <div class="dot dot-3"></div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="side">
          <div>
            <div class="btnSend" v-on:click="createStory">SEND</div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  components: {},
  data() {
    return {
      texts: "",
      isRecognizing: true,
      isChecked: false,
    };
  },
  setup() {},
  created() {},
  mounted() {
    //Web Speech API: https://triplexblog.kr/167
    if (!("webkitSpeechRecognition" in window)) {
      alert("이 브라우저에서는 지원되지 않습니다.");
      return;
    }

    const recognition = new window.webkitSpeechRecognition();
    recognition.interimResults = true;
    recognition.lang = "ko-KR";
    recognition.continuous = false;
    recognition.maxAlternatives = 20000;

    recognition.onspeechend = () => {
      recognition.stop();
      this.isRecognizing = true;
      this.isChecked = false;
    };

    recognition.onresult = (e) => {
      this.texts = Array.from(e.results)
        .map((results) => results[0].transcript)
        .join("");
      console.log(this.texts);
    };
    this.recognition = recognition;
  },
  computed: {
    isLoading() {
      return this.$store.state.isLoading;
    },
  },
  methods: {
    createStory() {
      const content = this.texts;
      const payload = {
        content: content,
      };

      this.$store.dispatch("createStory", payload);
    },
    // STT관련(1)
    toggleRecognition() {
      if (this.isRecognizing && !this.isChecked) {
        this.startRecognition();
      } else {
        this.stopRecognition();
      }
    },
    // STT관련(2)
    startRecognition() {
      this.recognition.start();
    },
    // STT관련(3)
    stopRecognition() {
      this.recognition.stop();
    },
  },
};
</script>

<style scoped lang="scss">
@import "@/assets/css/recording.scss";

#mainWrapper {
  font-family: "Dovemayo_gothic";
  width: 100vw;
  height: 100vh;
  display: flex;
  align-items: center;
  flex-direction: column;
}
#mainWrapper > div:nth-child(1) {
  width: 100%;
  height: 12vh;
}

.bgImg {
  width: 75%;
  height: 88vh;
  background-image: url("@/assets/img/story/bgLaptop.png");
  background-size: 100% 100%;
  background-repeat: no-repeat;
}

.bgImg2 {
  background-image: url("@/assets/img/story/bgMic.png");
  width: 100%;
  height: 100%;
  display: flex;
}
.side {
  width: 28%;
  height: 100%;
  display: flex;
  flex-direction: column-reverse;
}
.side > div {
  width: 100%;
  height: 28%;
  display: flex;
  align-items: center;
}
.center {
  width: 44%;
  height: 100%;
  display: flex;
  flex-direction: column;
}
.center > div:nth-child(1) {
  width: 100%;
  height: 72%;
  display: flex;
  flex-direction: column-reverse;
}
.center > div:nth-child(2) {
  width: 100%;
  height: 28%;
  display: flex;
  justify-content: center;
  align-items: center;
}
.inputBox {
  width: 100%;
  height: 75%;
}
textarea {
  font-family: "Dovemayo_gothic";
  width: 100%;
  height: 100%;
  box-sizing: border-box;
  resize: none;
  font-size: 2rem;
  font-weight: bold;
  background-color: rgba(255, 255, 255, 80%);
  border-radius: 20px;
  border: 1px solid rgba(233, 217, 217, 100%);
  box-shadow: 8px 4px 4px rgba(0, 0, 0, 25%);
  padding: 5%;
  outline: none;
}
.btnRecord {
  width: 24%;
  height: 40%;
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: rgba(255, 251, 251, 100%);
  border-radius: 20px;
  border: 1px solid rgba(233, 217, 217, 100%);
  box-shadow: 8px 4px 4px rgba(0, 0, 0, 25%);
  font-size: 1.5rem;
  font-weight: bold;
}
.btnRecord > h1 {
  text-align: center;
}

.btnSend {
  width: 35%;
  height: 24%;
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: rgba(77, 69, 93, 100%);
  border-radius: 40px;
  color: white;
  font-weight: bold;
  font-size: 2rem;
}
.btnSend:hover{
  background-color: black;
}
/* ----------------------- */
// * {
//   padding: 0;
//   margin: 0;
//   box-sizing: border-box;
// }

// body {
//   background-color: #111;
//   display: flex;
//   justify-content: center;
//   align-items: center;
//   min-height: 100vh;
// }
.loadingWrapper {
  margin-top: 25%;
  margin-left: 35%;
  position: absolute;
  -webkit-box-reflect: below -20px linear-gradient(transparent, rgba(0, 0, 0, 0.2));
}
.loadingWrapper span,
img {
  position: relative;
  display: inline-block;
  font-family: "Dovemayo_gothic";
  font-size: 5rem;
  color: rgba(121, 80, 63, 100%);
  font-weight: 900;
  text-transform: uppercase;
  animation: waviy 1.5s infinite;
  animation-delay: calc(0.1s * var(--i));
}
@keyframes waviy {
  0%,
  40%,
  100% {
    transform: translateY(0);
  }
  20% {
    transform: translateY(-20px);
  }
}
</style>
