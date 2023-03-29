<template>
  <div id="mystorylist">
    <table id="commutable">
      <thead>
        <!-- <th id="area1">No.</th> -->
        <th id="area2">사연</th>
        <th id="area3">결과</th>
      </thead>
      <br />
      <tbody>
        <tr
          v-for="article in articles"
          v-bind:key="article.articleId"
          style="line-height: 25px"
        >
          <!-- <td id="area1">{{ article.articleId }}</td> -->
          <td id="area2">
            <div
              id="articletitle"
              v-on:click="goDetail(article.articleId)"
              style="
                width: 380px;
                white-space: nowrap;
                overflow: hidden;
                text-overflow: ellipsis;
              "
            >
              {{ article.content }}
            </div>
          </td>
          <td id="area3" v-if="article.result === 0">
            <img src="../../../src/assets/img/community/redbulb.png" alt="" />
          </td>
          <td id="area3" v-else-if="article.result === 1">
            <img src="../../../src/assets/img/community/navybulb.png" alt="" />
          </td>
          <td id="area3" v-else-if="article.result === 2">
            <img src="../../../src/assets/img/community/greenbulb.png" alt="" />
          </td>
        </tr>
      </tbody>
    </table>
    <br />
    <div id="page" style="position: absolute; padding-top: 29%">
      <button v-on:click="prevPage">이전</button>
      <span v-for="p in totalpage" v-bind:key="p">
        <button
          v-if="p == page"
          style="text-decoration: underline"
          v-on:click="changePage(p)"
        >
          {{ p }}
        </button>
        <button v-else v-on:click="changePage(p)">
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
      articles: [],
      totalpage: 1,
      page: 1,
    };
  },
  methods: {
    getArticle() {
      axios({
        method: "get",
        url: `${this.$store.state.BASE_URL}/user`,
        headers: {
          Authorization: `Bearer ${sessionStorage.getItem("token")}`,
        },
        params: {
          page: this.page,
        },
      })
        .then((res) => {
          this.articles = [];
          console.log(res.data);
          console.log(this.page);
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
        this.getArticle();
      }
    },
    nextPage() {
      if (this.page < this.totalpage) {
        this.page += 1;
        this.getArticle();
      }
    },
    changePage(p) {
      this.page = p;
      this.getArticle();
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
    this.getArticle();
  },
};
</script>

<style scoped>
#mystorylist {
  font-family: "Dovemayo_gothic";
  width: 30vw;
  height: 70vh;
  display: flex;
  align-items: center;
  justify-content: space-between;
  flex-direction: column;
  margin-left: 5vw;
}

#commutable {
  width: 35vw;
  height: 100wh;
  margin: 5%;
}

thead {
  font-size: 20px;
  font-weight: bold;
  text-align: center;
}
tbody {
  font-size: 15px;
}

#area1,
#area3 {
  width: 10vw;
  text-align: center;
}
#area2 {
  width: 30vw;
  padding-left: 5%;
}

#area3 > img {
  width: 1vw;
  object-fit: fill;
}

button {
  font-family: "Dovemayo_gothic";
  font-size: 20px;
  border: 0px solid black;
  background-color: transparent;
}

#articletitle:hover {
  color: red;
}
</style>
