<template>
  <div>카카오 로그인2</div>
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
      // const URL = "http://localhost:8080";
      // const URL = "http://i8a706.p.ssafy.io:8080";
      // let tmp = window.location;
      let query = window.location.search;
      let param = new URLSearchParams(query);
      let code = param.get("code");
      console.log(code);
      // console.log(tmp);
      // console.log(query);
      // console.log(param);
      // console.log(code);
      axios({
        method: "get",
        url: `https://j8a109.p.ssafy.io:8080/login/kakao?code=${code}`,
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
