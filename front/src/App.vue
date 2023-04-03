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
  cursor: url("./assets/img/main/cursor.png"), auto;
}


body::-webkit-scrollbar{
  width: 11px;
}
body::-webkit-scrollbar-thumb{
  height: 30%;
  background: rgba(77, 69, 93, 100%);
  border-radius: 10px;
}
body::-webkit-scrollbar-track{
  background: rgba(77, 69, 93, 50%);

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
