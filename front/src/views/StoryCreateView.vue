<template>
  <div id="mainWrapper">
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
// const BASE_URL = "http://localhost:8080"
// const BASE_URL = this.$store.state.BASE_URL;

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
  methods: {
    createStory() {
      const content = this.texts;
      // const result =
      // const keyword =
      // keyword type -> list

      const payload = {
        content: content,
        // result: result,
        // keyword:keyword,
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
/* ----------------------- */
</style>
