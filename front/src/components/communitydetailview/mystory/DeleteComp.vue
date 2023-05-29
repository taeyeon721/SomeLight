<template>
  <div id="deletebtn" v-on:click="deleteArticle">
    <p style="font-size: 1.3rem; color: white; font-weight: bold">삭제</p>
  </div>
</template>

<script>
import axios from "axios";

export default {
  methods: {
    deleteArticle() {
      axios({
        method: "delete",
        url: `${this.$store.state.BASE_URL}/article/${this.$route.params.story_id}`,
        headers: {
          Authorization: `Bearer ${sessionStorage.getItem("token")}`,
        },
      })
        .then((res) => {
          console.log(res.data);
          this.$router.go(-1);
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
};
</script>

<style scoped>
#deletebtn {
  width: 7vw;
  height: 5vh;
  border-radius: 30px;
  background-color: #f3998a;
  display: flex;
  justify-content: center;
  align-items: center;
  box-shadow: 3px 3px 3px rgb(187, 187, 187);
  margin-left: 12vw;
  margin-top: 7vh;
}

#deletebtn:hover{
  background-color: red;
}
</style>
