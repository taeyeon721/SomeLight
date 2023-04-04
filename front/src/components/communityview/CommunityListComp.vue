<template>
  <div id="commulist">
    <table id="commutable">
      <div>
        <tr>
          <th id="area2">사연</th>
          <th id="area3">결과</th>
        </tr>
      </div>
      <div v-for="article in articles" v-bind:key="article.articleId">
        <tr>
          <td id="area2">
            <div id="articletitle" v-on:click="goDetail(article.articleId)">
              {{ article.content }}
            </div>
          </td>
          <td id="area3" v-if="article.result === 0">
            <img src="../../../src/assets/img/result/redheart.png" alt="" />
          </td>
          <td id="area3" v-else-if="article.result === 1">
            <img src="../../../src/assets/img/result/navyheart.png" alt="" />
          </td>
          <td id="area3" v-else-if="article.result === 2">
            <img src="../../../src/assets/img/result/greenheart.png" alt="" />
          </td>
        </tr>
      </div>
    </table>
    <br />
    <div id="page">
      <button id="pbtn" v-on:click="prevPage">이전</button>
      <span v-for="p in totalpage" v-bind:key="p">
        <button
          id="pbtn"
          v-if="p == page"
          style="text-decoration: underline"
          v-on:click="changePage(p)"
        >
          {{ p }}
        </button>
        <button id="pbtn" v-else v-on:click="changePage(p)">
          {{ p }}
        </button>
      </span>
      <button id="pbtn" v-on:click="nextPage">다음</button>
    </div>
  </div>
</template>

<script>
import axios from "axios";

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
#commulist {
  font-family: "Dovemayo_gothic";
  position: absolute;
  width: 42vw;
  height: 70vh;
  background-color: white;
  color: #4d455d;
  border-radius: 20px;
  box-shadow: 3px 3px 3px rgb(205, 205, 205);
  display: flex;
  align-items: center;
  flex-direction: column;
  opacity: 0.75;
}

#commutable {
  position: absolute;
  width: 35vw;
  font-size: 1.7rem;
  line-height: 170%;
  /* height: 100vh; */
  margin: 5%;
}

tr {
  display: flex;
  align-items: center;
}

#area3 {
  width: 10%;
  text-align: center;
}

#area2 {
  width: 60%;
  padding-left: 15%;
}

#area3 > img {
  margin-top: 1vh;
  width: 40%;
  object-fit: fill;
}

button {
  font-family: "Dovemayo_gothic";
  font-size: 1.5rem;
  background-color: transparent;
}

#articletitle {
  width: 90%;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}

#articletitle:hover {
  /* color: red; */
  /* border-bottom: 2px solid #4D455D ; */
  text-decoration: underline;
}

#page {
  position: absolute;
  margin-top: 70%;
}

button {
  font-family: "Dovemayo_gothic";
  font-size: 1.5rem;
  border: 0px solid black;
  background-color: transparent;
}

#pbtn:hover {
  color: red;
}
</style>
