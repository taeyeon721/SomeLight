<template>
  <div id="detailpage">
    <community-content-comp v-bind:content="content" />
    <half-comp />
  </div>
</template>

<script>
import CommunityContentComp from '@/components/communitydetailview/CommunityContentComp.vue'
import HalfComp from '@/components/communitydetailview/HalfComp.vue'
import axios from "axios";

const BASE_URL = "http://localhost:8080"

export default {
  data(){
    return{
      content:"",
    }
  },
  components: { CommunityContentComp, HalfComp},
  methods:{
    getDetail(){
      axios({
        method:"get",
        url: `${BASE_URL}/article/${this.$route.params.story_id}`
      })
      .then((res)=>{
        console.log(res.data)
        this.content = res.data.content
      })
      .catch((err)=>{
        console.log(err)
      })
    }
  },
  created(){
    this.getDetail()
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