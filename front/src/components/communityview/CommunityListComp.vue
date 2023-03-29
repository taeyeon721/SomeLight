<template>
  <div id="commulist">
    <table id="commutable">
      <thead>
        <div>
        <!-- <th id="area1">No.</th> -->
        <th id="area2">사연</th>
        <th id="area3">결과</th>
        </div>
      </thead>
      <br />
      <tbody>
        <div v-for="article in articles" v-bind:key="article.articleId">
        <!-- v-if="article.exposure" -->
        <tr 
        style="
        line-height:28px;"
        >
          <!-- <td id="area1">{{ article.articleId }}</td> -->
          <td 
          id="area2"
          ><div
          id="articletitle"
          v-on:click="goDetail(article.articleId)"
          style="
          width:380px;
          white-space:nowrap;
          overflow:hidden;
          text-overflow:ellipsis;">
            {{ article.content }}
          </div></td>
          <td id="area3" v-if="article.result===0">
            <img 
            src="../../../src/assets/img/community/redbulb.png" 
            alt=""
            >
            </td>
          </tr>
        </div>
      </tbody>
    </table>
    <br>
  <div id="page" 
  style="
  position:absolute;
  padding-left:6%;
  padding-top:75%;"
  >
  <button v-on:click="prevPage">이전</button>
  <span v-for="p in totalpage" v-bind:key="p">
    <button v-if="p==page" style="text-decoration:underline;" v-on:click="changePage(p)" >
      {{ p }}
    </button>
    <button v-else v-on:click="changePage(p)" >
      {{ p }}
    </button>
  </span>
  <button v-on:click="nextPage">다음</button>
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
      page: 1, // 페이지네이션 초기값
      articles: [],
      totalpage: 1,
    };
  },
  methods: {
    getAllArticle() {
      axios({
        method: "get",
        url: `${this.$store.state.BASE_URL}/article`,
        params: {
          page: this.page,
        },
      })
        .then((res) => {
          this.articles = [];
          for (let i = 0; i < res.data.numberOfElements; i++) {
            this.articles.push(res.data.content[i]);
          }
          this.totalpage = res.data.totalPages;
        })
        .catch((err) => {
          console.log(err);
        });
    },
    prevPage() {
      if (this.page >= 1) {
        this.page -= 1;
        this.getAllArticle();
      }
    },
    nextPage() {
      if (this.page < this.totalpage) {
        this.page += 1;
        this.getAllArticle();
      }
    },
    changePage(p) {
      this.page = p;
      this.getAllArticle();
    },
    goDetail(pk) {
      axios({
        method: "get",
        url: `${this.$store.state.BASE_URL}/article/${pk}`,
      })
        .then((res) => {
          this.$router.push({
            name: "communitydetail",
            params: { story_id: pk },
          });
          console.log(res);
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
  created() {
    this.getAllArticle();
  },
};
</script>

<style scoped>
#commulist{
    font-family: "Dovemayo_gothic";
    position:absolute;
    width: 42vw;
    height: 70vh;
    background-color: white;
    color: #4D455D;
    border-radius: 20px;
    box-shadow: 3px 3px 3px rgb(205, 205, 205);
    display: flex;
    align-items: center;
    flex-direction: column;
    opacity: 0.9;
}

#commutable {
  position: absolute;
  width: 35vw;
  height: 100vh;
  margin: 5%;
}

thead {
  position: absolute;
  font-size: 24px;
  font-weight: bold;
  text-align: center;
}
tbody {
  font-size: 20px;
  text-align: center;
}

#area1,
#area3 {
  width: 5vw;
  text-align: center;
}

#area2 {
  width: 400px;
  padding-left: 5%;
}

#area3 > img {
  width: 1vw;
  object-fit: fill;
}

button{
  font-family: "Dovemayo_gothic";
  font-size: 23px;
  border: 0px solid black;
  background-color: transparent;
}

#articletitle:hover {
  color: red;
}
</style>
