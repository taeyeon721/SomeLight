<template>
  <div id="half">
    <div v-if="loginflag">
      <div v-if="exposure">
        <my-vote-result-comp />
      </div>
      <div v-else>
        <share-comp />
      </div>
      <feedback-comp v-if="changed===null" />
      <feedback-comp-result v-else/>
      <delete-comp />
    </div>
    <div v-else>
      <div v-if="voteResult==0">
        <vote-comp />
      </div>
      <div v-else>
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

    }
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
  ],
  computed:{
    get(){
      return this.$store.dispatch("getDetail", this.$route.params.story_id)
    },
    exposure(){
      return this.$store.state.article.exposure
    },
    changed(){
      return this.$store.state.article.changed
    },
    voteResult(){
      return this.$store.state.article.voteResult
    },
  }

}
</script>

<style scoped>
#half{
    margin-top: 10vh;
    margin-left:30px;
    width: 40vw;
    height: 90vh;
    background-image: url("../../../src/assets/img/main/communitydetailback.jpg");
    background-repeat:no-repeat ;
    background-size: 40vw 90vh;
    display: flex;
    align-items: center;
    justify-content: center;
    flex-direction: column;
}
</style>