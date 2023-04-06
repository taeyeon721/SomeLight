<template>
  <div></div>
</template>

<script lang="ts">
import { defineComponent, ComponentPublicInstance } from "vue";
import router from "@/router";
import axios from "axios";

export default defineComponent({
  methods: {
    getUser(this: ComponentPublicInstance<{}, any>, code: string) {
      axios({
        method: "get",
        url: `${this.$store.state.BASE_URL}/login/kakao`,
        params: {
          code,
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
  created(this: ComponentPublicInstance<{}, any>) {
    const code: string | null = this.$route.query.code as string | null;
    if (code !== null) {
      console.log(code)
      this.getUser(code);
      console.log("로그인 성공");
    } else {
      router.push({ path: "/login" });
    }
  },
});
</script>

<style></style>
