import { createStore } from "vuex";
import axios from "axios";
import router from "@/router";

const BASE_URL = "http://localhost:8080"

export default createStore({
  state: {
    article:{
        userId:0,
        content:"",
        result:0,
        exposure:false,
        changed:null,
        redPercent:0,
        greenPercent:0,
        voteResult:0,
    },
    results:{
      article:{
        articleId:null,
        userId:null,
        content:"",
        result:null,
        createdDate:null,
        redCount:null,
        greenCount:null,
        isChanged:null,
        isExposure:false,
      },
      keyword:[],
      movie:"",
      movieImage:"",
      book:"",
      bookImage:"",
    }
  },
  getters: {
    // voteR:(state) => {
    //   return state.article.voteResult
    // }

  },
  mutations: {
    GET_ARTICLE_DETAIL(state, data){
      state.article.userId = data.userId
      state.article.content = data.content
      state.article.result = data.result
      state.article.exposure = data.isExposure
      state.article.changed = data.isChanged
      state.article.redPercent = data.redPercent
      state.article.greenPercent = data.greenPercent
      state.article.voteResult = data.voteResult
    },
    POST_RESULT(state, data){
      state.results.article.articleId = data.article.articleId
      state.results.article.userId = data.article.userId
      state.results.article.content = data.article.content
      state.results.article.result = data.article.result
      state.results.article.createdDate = data.article.createdDate
      state.results.article.redCount = data.article.redCount
      state.results.article.greenCount = data.article.greenCount
      state.results.article.isChanged = data.article.isChanged
      state.results.article.isExposure = data.article.isExposure
      state.results.keyword = data.keyword
      state.results.movie = data.movie
      state.results.movieImage = data.movieImage
      state.results.book = data.book
      state.results.bookImage = data.bookImage
    },
  },
  actions: {
    getDetail(context, payload){
      axios({
        method:"get",
        url: `${BASE_URL}/article/${payload.story_id}`,
        headers:{
          Authorization:`Bearer ${sessionStorage.getItem("token")}`
        },
      })
      .then((res)=>{
        context.commit("GET_ARTICLE_DETAIL", res.data)
      })
      .catch((err)=>{
        console.log(err)
      })
    },
    createStory(context, payload){
      axios({
        method:"post",
        url: `${BASE_URL}/result`,
        data:{
          "content":payload.content,
          // "result":payload.result,
          // "keyword":payload.keyword,
        }
      })
      .then(function(res){
        // console.log(res.data)
        context.commit("POST_RESULT", res.data)

        //라우터로 결과 페이지 넘어가게 
        router.push("/story/result")
      })
      .catch(function(err){
        console.log(err)
      })
    }
  },
  modules: {},
});
