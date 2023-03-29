<template>
  <Navbar />
  <router-view />
</template>

<script lang="ts">
import Navbar from "@/components/appview/Navbar.vue";

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
/* div {
  box-shadow: 0 0 0 1px #f00 inset;
} */

body,
html {
  height: 100%;
}
.font {
  font-family: "DM Sans", sans-serif,'Dovemayo_gothic';
}
@font-face {
    font-family: 'Dovemayo_gothic';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2302@1.1/Dovemayo_gothic.woff2') format('woff2');
    font-weight: normal;
    font-style: normal;
}
</style>
