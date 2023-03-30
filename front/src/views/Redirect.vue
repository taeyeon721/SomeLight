<template>
  <div></div>
</template>

<script>
import router from "@/router";
import axios from "axios";

export default {
  methods: {
    getUser(code) {
      axios({
        method: "get",
        url: "http://localhost:8080/login/kakao",
        params: {
          code: code,
        },
      })
        .then((res) => {
          sessionStorage.setItem("pk", res.data.userId);
          sessionStorage.setItem("token", res.data.token);
          console.log(res.data);
          this.$store.commit("IS_LOGIN", true);
          // console.log(res.data)
          router.push({ path: "/mypage" });
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
  created() {
    const code = this.$route.query.code;
    if (code != null) {
      this.getUser(code);
      console.log("로그인 성공");
    } else {
      router.push({ path: "/login" });
    }
  },
};
</script>

<style></style>
