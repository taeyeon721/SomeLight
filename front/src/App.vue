<template>
  <Navbar />
  <router-view />
</template>

<script lang="ts">
import Navbar from "@/components/appView/Navbar.vue";
// import Navbar from "@/components/appView/Navbar.vue";

export default {
  components: {
    Navbar,
  },
  setup() {},
};
</script>
<style>
@import "@/assets/css/reset.css";
/* 너비 체크용 */
div {
  box-shadow: 0 0 0 1px #f00 inset;
}

.font {
  font-family: "DM Sans", sans-serif;
}
</style>
