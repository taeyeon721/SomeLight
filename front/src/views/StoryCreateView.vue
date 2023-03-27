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
                v-model="content"
                placeholder="여러분의 사연을 입력해주세요"
              ></textarea>
            </div>
          </div>
          <div>
            <div class="btnRecord">녹음시작</div>
            <!-- <div class="btnRecord">
              <h1>
                녹음중 <br />
                30
              </h1>
            </div> -->
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

const BASE_URL = 'http://localhost:8080'

export default {
  components: {},
  data() {
    return {
      content:"",
    };
  },
  setup() {},
  created() {},
  mounted() {},
  methods: {
    createStory(){
      const content = this.content
      // const result = 
      // const keyword = 
      // keyword type -> list

      const payload = {
        content: content,
        // result: result,
        // keyword:keyword,
      }
      
      this.$store.dispatch("createStory", payload)
    }
  },
};
</script>

<style scoped>
#mainWrapper {
  width: 100vw;
  height: 100vh;
  display: flex;
  align-items: center;
  flex-direction: column;
}
#mainWrapper > div:nth-child(1) {
  width: 100%;
  height: 10vh;
}

.bgImg {
  width: 75%;
  height: 90vh;
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
</style>
