import { createStore } from "vuex";
import axios from "axios";

const BASE_URL = "http://localhost:8080"

export default createStore({
  state: {
    article:{
        userId:0,
        content:"",
        result:0,
        exposure:false,
        changed:false,
        redPercent:0,
        greenPercent:0,
        voteResult:0,
    }
  },
  getters: {

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
    }
  },
  actions: {
    getDetail(context, payload){
      axios({
        method:"get",
        url: `${BASE_URL}/article/${payload.story_id}`
      })
      .then((res)=>{
        context.commit("GET_ARTICLE_DETAIL", res.data)
      })
      .catch((err)=>{
        console.log(err)
      })
    }

  },
  modules: {},
});
