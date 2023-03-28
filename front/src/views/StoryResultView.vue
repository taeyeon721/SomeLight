<template>
  <div id="mainWrapper">
    <div class="center">
      <div id="resultWrapper">
        <div id="bulbWrapper" v-if="results.article.result===2">
          <img src="@/assets/img/result/fix_green.png" alt="" />
        </div>
        <div id="bulbWrapper" v-else-if="results.article.result===1">
          <img src="@/assets/img/result/fix_black.png" alt="" />
        </div>
        <div id="bulbWrapper" v-else-if="results.article.result===0">
          <img src="@/assets/img/result/fix_red.png" alt="" />
        </div>
        <div id="textWrapper">{{ results.article.content }}</div>
      </div>
      <div id="btnShare" v-on:click="share">공유하기</div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

const BASE_URL = "http://localhost:8080"

export default {
  components: {},
  data() {
    return {
      results:{
      article:{
        articleId:this.$store.state.results.articleId,
        userId:this.$store.state.results.userId,
        content:this.$store.state.results.content,
        result:this.$store.state.results.result,
        createdDate:this.$store.state.results.createdDate,
        redCount:this.$store.state.results.redCount,
        greenCount:this.$store.state.results.greenCount,
        isChanged:this.$store.state.results.isChanged,
        isExposure:this.$store.state.results.isExposure,
      },
      keyword:this.$store.state.results.keyword,
      movie:this.$store.state.results.movie,
      movieImage:this.$store.state.results.movieImage,
      book:this.$store.state.results.book,
      bookImage:this.$store.state.results.bookImage,
    }
    };
  },
  setup() {},
  created() {},
  mounted() {},
  methods: {
    share(){
      axios({
        method:"put",
        url:`${BASE_URL}/article/${this.results.article.articleId}`,
        headers:{
          Authorization:`Bearer ${sessionStorage.getItem("token")}`
        },
        data:{
          "isChanged":this.$store.state.article.changed,
          "isExposure":true,
          "voteResult":this.$store.state.article.voteResult,
        }
      })
      .then((res)=>{
        console.log(res.data)
        this.$router.push("/community")
      })
      .catch((err)=>{
        console.log(err)
      })
    }
  },
};
</script>

<style scoped>
#mainWrapper {
  width: 100vw;
  height: 100%;
  display: flex;
  font-family: "Dovemayo_gothic";
  flex-direction: column;
  align-items: center;
  position: fixed;
  top: 10vh;
}
.center {
  width: 80%;
  height: 100%;
  background-image: url("@/assets/img/result/background.png");
  background-size: 100% auto;
  background-repeat: no-repeat;
  display: flex;
  flex-direction: column;
  align-items: center;
}
#resultWrapper {
  width: 40%;
  min-height: 60%;
  /* height: auto; */
  margin-top: 10%;
  background-color: rgba(255, 251, 251, 100%);
  border-radius: 20px;
  border: 1px solid rgba(233, 217, 217, 100%);
  box-shadow: 8px 4px 4px rgba(0, 0, 0, 25%);
  position: relative;
  z-index: 0;
  display: flex;
  justify-content: center;
  /* align-items: center; */
}
#bulbWrapper {
  border-radius: 100%;
  width: 30%;
  height: 30%;
  display: flex;
  justify-content: center;
  align-items: center;
  position: absolute;
  top: -15%;
  left: 50%;
  transform: translateX(-50%);
  z-index: 2;
  border: 1px solid rgba(190, 176, 176, 100%);
  background-color: rgba(255, 251, 251, 100%);
  border-radius: 100%;
  border: 1px solid rgba(233, 217, 217, 100%);
  box-shadow: 8px 4px 4px rgba(0, 0, 0, 25%);
}
#bulbWrapper > img {
  height: 80%;
  /* width: 90%; */
  /* border-radius: 100%; */
}
#textWrapper {
  width: 80%;
  height: 85%;
  margin-top: 15%;
}
#btnShare {
  width: 13%;
  height: 7%;
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: rgba(77, 69, 93, 100%);
  border-radius: 40px;
  color: white;
  font-weight: bold;
  font-size: 2rem;
  margin-top: 1%;
}
</style>
