<template>
  <div id="mainWrapper">
    <div class="center">
      <div id="resultWrapper">
        <div id="bulbWrapper" v-if="result === 2">
          <img src="@/assets/img/result/fix_green.png" alt="" />
        </div>
        <div id="bulbWrapper" v-else-if="result === 1">
          <img src="@/assets/img/result/fix_black.png" alt="" />
        </div>
        <div id="bulbWrapper" v-else-if="result === 0">
          <img src="@/assets/img/result/fix_red.png" alt="" />
        </div>
        <div id="textWrapper">
          {{ content }}
        <p>키워드</p>
        <div id="keywordbox" v-for="ent in keyword" v-bind:key="ent[i]">
          #{{ ent }}
        </div>
        <div>{{ movie }}</div>
        <div>{{ book }}</div>
        <img :src="movieImage" alt="">
        <img :src="bookImage" alt="">
        </div>
      </div>
      <div id="btnShare" v-on:click="share">공유하기</div>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  components: {},
  data() {
    return {
 
    };
  },
  setup() {},
  created() {
  },
  computed:{
    content(){
      return this.$store.state.results.article.content
    },
    result(){
      return this.$store.state.results.article.result
    },
    keyword(){
      return this.$store.state.results.keyword
    },
    movie(){
      console.log(this.$store.state.results.movie)
      return this.$store.state.results.movie
    },
    movieImage(){
      console.log(this.$store.state.results.movieImage)
      return this.$store.state.results.movieImage
    },
    book(){
      console.log(this.$store.state.results.book)
      return this.$store.state.results.book
    },
    bookImage(){
      console.log(this.$store.state.results.bookImage)
      return this.$store.state.results.bookImage
    }

    
  },
  mounted() {},
  methods: {
    share() {
      axios({
        method: "put",
        url: `${this.$store.state.BASE_URL}/article/${this.results.article.articleId}`,
        headers: {
          Authorization: `Bearer ${sessionStorage.getItem("token")}`,
        },
        data: {
          isChanged: this.$store.state.article.changed,
          isExposure: true,
          voteResult: this.$store.state.article.voteResult,
        },
      })
        .then((res) => {
          console.log(res.data);
          this.$router.push("/community");
        })
        .catch((err) => {
          console.log(err);
        });
    },
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
  font-size: 2rem;
  height: 85%;
  margin-top: 15%;
  color: rgba(77, 69, 93, 100%);
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

#keywordbox{
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 1.5rem;
  width: 25%;
  height: 10%;
  border-radius: 20px;
  background-color: rgba(245,233,207);
}
</style>
