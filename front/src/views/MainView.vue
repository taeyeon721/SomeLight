<template>
  <div id="mainWrapper">
    <div id="logoWrapper">
      <div id="logoContainer"></div>
    </div>
    <div id="textWrapper">
      <div id="textContainer">
        <div id="textHeader">
          <span>SOME LIGHT</span>
        </div>
        <div id="textBody">
          <span>설명 - 요즘 따라 내꺼인듯 내꺼 아닌</span><br />
          <span>내꺼 같은 너~</span><br />
          <span>썸인지 아닌지 AI가 구분해준다!</span>
        </div>
        <div id="textFooter">
          <div id="startButtonWrapper">
            <div id="startButton">Get Started</div>
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
    return {};
  },
  setup() {},
  created() {},
  mounted() {},
  methods: {},
};
</script>

<style scoped>
/* 너비 체크용 */
div {
  box-shadow: 0 0 0 1px #f00 inset;
}

#mainWrapper {
  width: 100vw;
  height: 100vh;
  display: flex;
}

#logoWrapper {
  height: 100vh;
  width: 50vw;
  display: flex;
}

#logoContainer {
  height: 58%;
  width: 74%;
  margin: auto;
  align-items: center;
}
#textWrapper {
  height: 100vh;
  width: 50vw;
  display: flex;
}

#textContainer {
  height: 39%;
  width: 63%;
  margin: auto;
  align-items: center;
}
#textHeader {
  width: 100%;
  height: 20%;
}
#textBody {
  width: 100%;
  height: 40%;
}
#textFooter {
  width: 100%;
  height: 40%;
}
#startButtonWrapper {
  width: 100%;
  height: 19%;
}
#startButton {
  width: 38%;
  height: 100%;
  background-color: rgba(93, 90, 136, 100%);
  border-radius: 40px;
  text-align: center;
  color: white;
}
</style>
