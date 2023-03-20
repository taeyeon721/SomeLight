<template>
  <div id="navWrapper" class="font">
    <div id="logoWrapper">
      <span>S</span>
      <img src="@/assets/img/nav/bulb2.png" alt="" />
      <span>ME</span>
    </div>
    <div id="itemWrapper">
      <ul>
        <!-- 메인화면일경우 -->
        <li>START</li>
      </ul>
    </div>
  </div>
</template>
<script lang="ts">
export default {
  setup() {},
};
</script>

<style scoped>
#navWrapper {
  width: 100vw;
  height: 10vh;
  position: fixed;
  top: 0px;
  display: flex;
  background-color: rgba(255, 255, 255, 50%);
}

#logoWrapper {
  /* width: 100%; */
  height: 100%;
  margin-left: 10vw;
  white-space: nowrap;
}
#logoWrapper > span {
  font-size: 4em;
  color: rgba(121, 80, 63, 100%);
  font-weight: bold;
  height: 100%;
}
#logoWrapper > img {
  height: 90%;
}

#itemWrapper {
  height: 100%;
  margin-left: 55vw;
}
ul {
  height: 100%;
  display: flex;
  font-size: 2rem;
  font-weight: bold;
  color: rgba(77, 69, 93, 100%);
}
li {
  height: 100%;
  /* line-height: 100%; */
  text-align: center;
  display: flex;
  align-items: center;
  margin-left: 3vw;
}
</style>
