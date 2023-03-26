<!-- eslint-disable vue/multi-word-component-names -->
<template>
  <div>카카오 로그인4</div>
</template>

<script>
import axios from "axios";

export default {
  components: {},
  data() {
    return {};
  },
  setup() {},
  created() {
    this.kakaoLogIn();
  },
  mounted() {},
  methods: {
    kakaoLogIn() {
      let query = window.location.search;
      let param = new URLSearchParams(query);
      let code = param.get("code");
      console.log(code);
      axios({
        method: "get",
        url: `https://j8a109.p.ssafy.io/api/login/kakao?code=${code}`,
      })
        .then((res) => {
          console.log(res);
        })
        .catch((err) => {
          console.log("실패");
          console.log(err);
        });
    },
  },
};
</script>

<style scoped></style>
