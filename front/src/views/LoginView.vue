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
  width: 30vw;
  height: 70vh;
  display: flex;
  border: 1px solid rgb(198, 198, 198);
  border-radius: 20px;
  box-shadow: 3px 3px 3px gray;
  align-items: center;
  flex-direction: column;
  justify-content: center;
}
</style>
