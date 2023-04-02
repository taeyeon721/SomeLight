<template>
  <div id="mystorylist">
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
  width: 40vw;
  height: 70vh;
  display: flex;
  align-items: center;
  flex-direction: column;
}

#commutable {
  width: 35vw;
  height: 50vh;
  margin: 3%;
  font-size: 1.7rem;
  line-height: 170%;
}

tr {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

#area3 {
  width: 10%;
  text-align: center;
  padding-right: 10%;
}

#area2 {
  width: 60%;
  padding-left: 10%;
}

#area3 > img {
  width: 50%;
  object-fit: fill;
}

button {
  font-family: "Dovemayo_gothic";
  font-size: 1.5rem;
  border: 0px solid black;
  background-color: transparent;
}

#articletitle {
  width: 90%;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}

#articletitle:hover {
  text-decoration: underline;
}

#page {
  position: absolute;
  margin-top: 27%;
}
</style>
