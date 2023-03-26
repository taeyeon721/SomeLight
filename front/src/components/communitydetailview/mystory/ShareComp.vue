<template>
  <div id="share">
    <p>커뮤니티에 사연을 공유하고</p>
    <p>다른 사람들의 생각을</p>
    <p> 들어보세요</p>
    <button v-on:click="share">공유하기</button>
  </div>
</template>

<script>
import axios from 'axios';

const BASE_URL = "http://localhost:8080"

export default {
  props:[
 
  ],
  methods:{
    share(){
      axios({
        method:"put",
        url:`${BASE_URL}/article/${this.$route.params.story_id}`,
        headers:{
          Authorization:`Bearer ${sessionStorage.getItem("token")}`
        },
        data:{
          "isChanged":this.$store.state.article.changed,
          "isExposure":true,
          "voteResult":this.$store.state.article.voteResult,
        }
      })
      .then((res)=>{
        console.log(res.data)
        this.$router.push("/community")
      })
      .catch((err)=>{
        console.log(err)
      })
    }
  },
  created(){

  }

}
</script>

<style scoped>
#share{
    width: 25vw;
    height: 40vh;
    border-radius: 20px;  
    background-color: #F5E9CF;
    opacity: 0.9;
    box-shadow: 3px 3px 3px rgb(187, 187, 187);
    margin: 10vh;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
}
button{
    background-color: #4D455D;
    color: white;
    font-size: 23px;
    font-weight:bold;
    border-radius: 30px;
    width: 10vw;
    height: 7vh;
}
p{
  font-size: 23px;
  font-weight: bold;
  padding: 10px;
  line-height: 35px;
  text-align: center;
  color: #4D455D;
}

</style>