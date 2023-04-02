<template>
  <div id="loginWrapper">
    <div id="loginComp">
      <login-comp />
    </div>
  </div>
</template>

<script lang="ts">
import LoginComp from "@/components/loginview/LoginComp.vue";

export default {
  setup() {},
  components: {
    LoginComp,
  },
};
</script>

<style scoped>
#loginWrapper {
  width: 100vw;
  height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
}
#loginComp {
  width: 28vw;
  height: 65vh;
  display: flex;
  border: 1px solid rgb(242, 242, 242);
  border-radius: 20px;
  box-shadow: 3px 3px 10px rgb(223, 222, 222);
  align-items: center;
  flex-direction: column;
  justify-content: center;
}
</style>
