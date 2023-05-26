<template>
  <div id="half">
    <div v-if="loginflag">
      <!-- <delete-comp /> -->
      <div v-if="exposure">
        <my-vote-result-comp />
      </div>
      <div v-else>
        <share-comp />
      </div>
      <feedback-comp v-if="changed===null"/>
      <feedback-comp-result v-else/>
    </div>
    <div v-else>
      <div v-if="voteResult===0">
        <vote-comp />
      </div>
      <div v-else-if="voteResult===1|2">
        <vote-result-comp />
      </div>
    </div>
  </div>
</template>

<script>
import DeleteComp from './mystory/DeleteComp.vue'
import FeedbackComp from './mystory/FeedbackComp.vue'
import FeedbackCompResult from './mystory/FeedbackCompResult.vue'
import MyVoteResultComp from './mystory/MyVoteResultComp.vue'
import ShareComp from './mystory/ShareComp.vue'
import VoteComp from './notmystory/VoteComp.vue'
import VoteResultComp from './notmystory/VoteResultComp.vue'



export default {
  data(){
    return{
      // getVote:this.$store.state.article.voteResult,
    }
  },
  methods:{
  
  },
  components: { 
    FeedbackComp, 
    DeleteComp, 
    ShareComp, 
    VoteComp,
    MyVoteResultComp,
    FeedbackCompResult,
    VoteResultComp },
  props:[
    "loginflag",
    // "getVote",
  ],
  computed:{
    // get(){
    //   console.log(this.getVote)
    //   console.log("바인딩 테스트 in half")
    //   return this.$store.dispatch("getDetail", this.$route.params.story_id)
    // },
    exposure(){
      return this.$store.state.article.exposure
    },
    changed(){
      console.log(this.$store.state.article.changed)
      return this.$store.state.article.changed
    },
    voteResult(){
      return this.$store.state.article.voteResult
    },
  },
  created(){
  }

}
</script>

<style scoped>
#half{
    margin-top: 10vh;
    margin-left:5vw;
    width: 40vw;
    height: 87vh;
    background-image:linear-gradient(
      rgba(250,250,250,0.2), 
      rgba(250,250,250,0.4)), 
      url("../../../src/assets/img/community/communitydetailback.jpg");
    background-repeat:no-repeat ;
    background-size: 40vw 88vh;
    display: flex;
    align-items: center;
    justify-content: center;
    flex-direction: column;
}
</style>