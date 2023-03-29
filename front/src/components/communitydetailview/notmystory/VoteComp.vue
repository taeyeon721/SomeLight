<template>
  <div id="vote">
    <div id="votetext">
      <p>사연에 대해</p>
      <p>여러분의 생각을</p>
      <p>투표해주세요.</p>
    </div>
    <div id="greenbox">
      <div style="display: flex; margin-bottom: 10px">
        <input
          type="radio"
          id="gchoice"
          name="vote"
          v-model="result"
          value="1"
        />
        <p>그린라이트</p>
      </div>
      <div id="greenbar" style="border: 2px solid #d4e384"></div>
    </div>
    <div id="redbox">
      <div style="display: flex; margin-bottom: 10px">
        <input
          type="radio"
          id="rchoice"
          name="vote"
          v-model="result"
          value="2"
        />
        <p>레드라이트</p>
      </div>
      <div id="redbar" style="border: 2px solid #f3998a"></div>
    </div>
    <div>
      <button id="votesubmit" v-on:click="vote">투표하기</button>
    </div>
  </div>
</template>

<script>
import axios from "axios";

// const BASE_URL = "http://localhost:8080"
// const BASE_URL = this.$store.state.BASE_URL;

export default {
  data() {
    return {
      result: this.$store.state.article.voteResult,
    };
  },
  methods: {
    vote() {
      // const story_id = this.$route.params.story_id
      // const payload = {
      //   story_id:story_id
      // }
      axios({
        method: "put",
        url: `${this.$store.state.BASE_URL}/article/${this.$route.params.story_id}`,
        headers: {
          Authorization: `Bearer ${sessionStorage.getItem("token")}`,
        },
        data: {
          isChanged: this.$store.state.article.changed,
          isExposure: this.$store.state.article.exposure,
          voteResult: this.result,
        },
      })
        .then((res) => {
          this.$store.commit("GET_ARTICLE_DETAIL", res.data);
          // this.$store.dispatch("getDetail", payload)
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
};
</script>

<style scoped>
#vote {
  width: 25vw;
  height: 45vh;
  border-radius: 20px;
  background-color: #f5e9cf;
  opacity: 0.9;
  box-shadow: 3px 3px 3px rgb(216, 216, 216);
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
#votetext {
  font-size: 25px;
  font-weight: bold;
  padding: 10px;
  line-height: 35px;
  text-align: center;
  color: #4d455d;
}
#greenbox,
#redbox {
  display: flex;
  flex-direction: column;
  margin: 10px;
}
#greenbar,
#redbar {
  font-size: 10px;
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: white;
  width: 17vw;
  height: 1vh;
  font-weight: bold;
  color: #4d455d;
  border-radius: 20px;
}
#votesubmit {
  background-color: #4d455d;
  color: white;
  font-size: 18px;
  font-weight: bold;
  border-radius: 30px;
  width: 7vw;
  margin-left: 11vw;
}
#greenbox > input[type="radio"] {
  accent-color: #d4e384;
  width: 10px;
  margin: 5px;
}

#redbox > input[type="radio"] {
  accent-color: #f3998a;
  width: 10px;
  margin: 5px;
}
</style>
