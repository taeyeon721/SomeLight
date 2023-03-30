<template>
  <div id="detailpage">
    <div class="detailwrapper">
      <community-content-comp v-bind:loginflag="loginflag" />
      <half-comp v-bind:loginflag="loginflag" />
    </div>
  </div>
</template>

<script>
import CommunityContentComp from "@/components/communitydetailview/CommunityContentComp.vue";
import HalfComp from "@/components/communitydetailview/HalfComp.vue";

export default {
  data() {
    return {
      // content:this.$store.state.article.content,
      // loginflag:false,
      // detail:{
      //   exposure:this.$store.state.article.exposure,
      //   changed:this.$store.state.article.changed,
      //   redPercent:this.$store.state.article.redPercent,
      //   greenPercent:this.$store.state.article.greenPercent,
      //   voteResult:this.$store.state.article.voteResult,
      // }
    };
  },
  components: { CommunityContentComp, HalfComp },
  methods: {
    // getDetail(){
    //   const story_id = this.$route.params.story_id
    //   const payload = { story_id:story_id, }
    //   console.log("여기는 디테일 뷰 get요청")
    //   this.$store.dispatch("getDetail", payload)
    // }
  },
  created() {
    // this.getDetail()
    const story_id = this.$route.params.story_id;
    const payload = { story_id: story_id };
    this.$store.dispatch("getDetail", payload);
  },
  computed: {
    // getVote(){
    //   const story_id = this.$route.params.story_id
    //   const payload = { story_id:story_id, }
    //   console.log("getVote실행 in 커뮤디테일")
    //   this.$store.dispatch("getDetail", payload)
    //   const voteResult = this.$store.state.article.voteResult
    //   console.log(voteResult)
    //   return voteResult
    // },
    loginflag() {
      if (
        Number(sessionStorage.getItem("pk")) ===
        this.$store.state.article.userId
      ) {
        console.log("내글");
        return true;
      } else {
        console.log("남글");
        return false;
      }
    },
  },
};
</script>

<style scoped>
#detailpage {
  font-family: "Dovemayo_gothic";
  width: 100vw;
  height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
}

.detailwrapper {
  margin-top: 3vh;
  height: 88vh;
  display: flex;
  align-items: center;
  justify-content: center;
}
</style>
