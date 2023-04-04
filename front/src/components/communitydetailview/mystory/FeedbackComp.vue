<template>
  <div id="feedback">
    <p id="feedbacktitle">정확했나요?</p>
    <button id="feedbackbtn" v-on:click="putFeedbackY">네</button>
    <button id="feedbackbtn" v-on:click="putFeedbackN">아니오</button>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {};
  },
  methods: {
    putFeedbackY() {
      axios({
        method: "put",
        url: `${this.$store.state.BASE_URL}/article/${this.$route.params.story_id}`,
        headers: {
          Authorization: `Bearer ${sessionStorage.getItem("token")}`,
        },
        data: {
          isChanged: true,
          isExposure: this.$store.state.article.exposure,
          voteResult: this.$store.state.article.voteResult,
        },
      })
        .then((res) => {
          console.log(res.data);
          this.$store.commit("GET_ARTICLE_DETAIL", res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    putFeedbackN() {
      axios({
        method: "put",
        url: `${this.$store.state.BASE_URL}/article/${this.$route.params.story_id}`,
        headers: {
          Authorization: `Bearer ${sessionStorage.getItem("token")}`,
        },
        data: {
          isChanged: false,
          isExposure: this.$store.state.article.exposure,
          voteResult: this.$store.state.article.voteResult,
        },
      })
        .then((res) => {
          console.log(res.data);
          this.$store.commit("GET_ARTICLE_DETAIL", res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
};
</script>

<style scoped>
#feedback {
  width: 30vw;
  height: 7vh;
  margin: 5%;
  border-radius: 30px;
  display: flex;
  justify-content: space-evenly;
  align-items: center;
  background-color: #f5e9cf;
  opacity: 0.9;
  box-shadow: 3px 3px 3px gray;
}
#feedbacktitle {
  color: #4d455d;
  font-size: 1.7rem;
  font-weight: bold;
}
#feedbackbtn {
  font-family: "Dovemayo_gothic";
  background-color: #4d455d;
  color: white;
  font-size: 1.4rem;
  font-weight: bold;
  border-radius: 30px;
  width: 5vw;
}

#feedbackbtn:hover{
  background-color: white;
  color:#4d455d;
}

</style>
