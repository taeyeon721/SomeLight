<template>
  <div id="mainWrapper">
    <div class="center">
      <div id="resultWrapper">
        <div id="bulbWrapper">
          <img src="@/assets/img/result/fix_black.png" alt="" />
        </div>
        <div id="textWrapper"></div>
      </div>
      <div id="btnShare">공유하기</div>
    </div>
  </div>
</template>

<script lang="ts">
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
#mainWrapper {
  width: 100vw;
  height: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  position: fixed;
  top: 10vh;
}
.center {
  width: 80%;
  height: 100%;
  background-image: url("@/assets/img/result/background.png");
  background-size: 100% auto;
  background-repeat: no-repeat;
  display: flex;
  flex-direction: column;
  align-items: center;
}
#resultWrapper {
  width: 40%;
  min-height: 60%;
  /* height: auto; */
  margin-top: 10%;
  background-color: rgba(255, 251, 251, 100%);
  border-radius: 20px;
  border: 1px solid rgba(233, 217, 217, 100%);
  box-shadow: 8px 4px 4px rgba(0, 0, 0, 25%);
  position: relative;
  z-index: 0;
  display: flex;
  justify-content: center;
  /* align-items: center; */
}
#bulbWrapper {
  border-radius: 100%;
  width: 30%;
  height: 30%;
  display: flex;
  justify-content: center;
  align-items: center;
  position: absolute;
  top: -15%;
  left: 50%;
  transform: translateX(-50%);
  z-index: 2;
  border: 1px solid rgba(190, 176, 176, 100%);
  background-color: rgba(255, 251, 251, 100%);
  border-radius: 100%;
  border: 1px solid rgba(233, 217, 217, 100%);
  box-shadow: 8px 4px 4px rgba(0, 0, 0, 25%);
}
#bulbWrapper > img {
  height: 80%;
  /* width: 90%; */
  /* border-radius: 100%; */
}
#textWrapper {
  width: 80%;
  height: 85%;
  margin-top: 15%;
}
#btnShare {
  width: 13%;
  height: 7%;
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: rgba(77, 69, 93, 100%);
  border-radius: 40px;
  color: white;
  font-weight: bold;
  font-size: 2rem;
  margin-top: 1%;
}
</style>
