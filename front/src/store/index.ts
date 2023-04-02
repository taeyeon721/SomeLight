import { createStore } from "vuex";
import axios from "axios";
import router from "@/router";

// const BASE_URL = "https://j8a109.p.ssafy.io/api"
const BASE_URL = "http://localhost:8080";

export default createStore({
  state: {
    article: {
      userId: 0,
      content: "",
      result: 0,
      exposure: false,
      changed: null,
      redPercent: 0,
      greenPercent: 0,
      voteResult: 0,
    },
    results: {
      article: {
        articleId: null,
        userId: null,
        content: "",
        result: null,
        createdDate: null,
        redCount: null,
        greenCount: null,
        isChanged: null,
        isExposure: false,
      },
      keyword: null,
      movie: "",
      movieImage: "",
      book: "",
      bookImage: "",
    },
    isLogin: false,
    BASE_URL: BASE_URL,
    isLoading: false,
  },
  getters: {
    // voteR:(state) => {
    //   return state.article.voteResult
    // }
  },
  mutations: {
    IS_LOGIN(state, data) {
      state.isLogin = data;
    },
    GET_ARTICLE_DETAIL(state, data) {
      state.article.userId = data.userId;
      state.article.content = data.content;
      state.article.result = data.result;
      state.article.exposure = data.isExposure;
      state.article.changed = data.isChanged;
      state.article.redPercent = data.redPercent;
      state.article.greenPercent = data.greenPercent;
      state.article.voteResult = data.voteResult;
    },
    POST_RESULT(state, data) {
      state.results.article.articleId = data.article.articleId;
      state.results.article.userId = data.article.userId;
      state.results.article.content = data.article.content;
      state.results.article.result = data.article.result;
      state.results.article.createdDate = data.article.createdDate;
      state.results.article.redCount = data.article.redCount;
      state.results.article.greenCount = data.article.greenCount;
      state.results.article.isChanged = data.article.isChanged;
      state.results.article.isExposure = data.article.isExposure;
      state.results.keyword = data.keyword;
      state.results.movie = data.movie;
      state.results.movieImage = data.movieImage;
      state.results.book = data.book;
      state.results.bookImage = data.bookImage;
    },
    SET_LOADING(state, data) {
      state.isLoading = data;
    },
  },
  actions: {
    getDetail(context, payload) {
      axios({
        method: "get",
        url: `${this.$store.state.BASE_URL}/article/${payload.story_id}`,
        headers: {
          Authorization: `Bearer ${sessionStorage.getItem("token")}`,
        },
      })
        .then((res) => {
          context.commit("GET_ARTICLE_DETAIL", res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    createStory(context, payload) {
      context.commit("SET_LOADING", true);
      console.log("감자");
      axios({
        method: "post",
        url: `${this.$store.state.BASE_URL}/result`,
        headers: {
          Authorization: sessionStorage.getItem("token")
            ? `Bearer ${sessionStorage.getItem("token")}`
            : null,
        },
        data: {
          content: payload.content,
        },
      })
        .then(function (res) {
          context.commit("POST_RESULT", res.data);
          console.log(res.data);
          //라우터로 결과 페이지 넘어가게
          router.push("/story/result");
        })
        .catch(function (err) {
          if (err.response.status == 500) {
            alert("충분한 키워드가 입력되지 않았습니다.");
          }
          console.log(err);
        })
        .finally(() => {
          context.commit("SET_LOADING", false);
        });
    },
  },
  modules: {},
});
