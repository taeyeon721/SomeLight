<template>
  <div id="detailpage">
    <community-content-comp 
    v-bind:loginflag="loginflag" />
    <half-comp 
    v-bind:detail="detail"
    v-bind:loginflag="loginflag"/>
  </div>
</template>

<script>
import CommunityContentComp from '@/components/communitydetailview/CommunityContentComp.vue'
import HalfComp from '@/components/communitydetailview/HalfComp.vue'

const BASE_URL = "http://localhost:8080"

export default {
  data(){
    return{
      // content:this.$store.state.article.content,
      // loginflag:false,
      detail:{
        exposure:this.$store.state.article.exposure,
        changed:this.$store.state.article.changed,
        redPercent:this.$store.state.article.redPercent,
        greenPercent:this.$store.state.article.greenPercent,
        voteResult:this.$store.state.article.voteResult,
      }
    }
  },
  components: { CommunityContentComp, HalfComp },
  methods:{
    getDetail(){
      const story_id = parseInt(this.$route.params.story_id)
      const payload = {
        story_id:story_id,
      }
      this.$store.dispatch("getDetail", payload)
    }
  },
  created(){
    this.getDetail()
  },
  computed:{
    loginflag(){
      if (Number(sessionStorage.getItem("pk")) === this.$store.state.article.userId){
        console.log("내글")
        return true 
      } else {
        console.log("남글")
        return false
      }
    }
  }


}
</script>

<style scoped>
#detailpage{
  width: 100vw;
  height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
}

</style>