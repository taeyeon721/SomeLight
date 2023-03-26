<template>
  <div id="vote">
    <div id="votetext">
    <p>
     투표결과
    </p>
    </div>
   <div id="greenbox">
      <div style="display:flex;">
      <input type="radio" id="gchoice" name="vote" v-model="result" value="1">
        <div id="greenbar"
        style="border: 2px solid #D4E384;">
        <div id="greenlayer" v-bind:style="cssVariablegreen">
        </div>
        </div>
    </div>
   </div>
    <div id="redbox">
      <div style="display:flex;">
      <input type="radio" id="rchoice" name="vote" v-model="result" value="2">
        <div v-bind:redPercent="redPercent">레드라이트 {{ redPercent }}%</div>
        <div id="redbar"
        style="border: 2px solid #F3998A">
        <div id="redlayer" v-bind:style="cssVariablered"></div>
        </div>
    </div>
    </div>
    <div>
      <button id="votesubmit" v-on:click="vote">투표하기</button>
    </div>
  </div>
</template>

<script>
import axios from "axios";

const BASE_URL = "http://localhost:8080"

export default {
  data(){
    return{
      result:this.$store.state.article.voteResult,
    }
  },
  methods:{
    vote(){
      axios({
        method:"put",
        url:`${BASE_URL}/article/${this.$route.params.story_id}`,
        headers:{
          Authorization:`Bearer ${sessionStorage.getItem("token")}`
        },
        data:{
          "changed":this.$store.state.article.changed,
          "exposure":this.$store.state.article.exposure,
          "voteResult":this.result,
        }
      })
      .then((res)=>{
        console.log(this.result)
        console.log(res.data)
      })
      .catch((err)=>{
        console.log(err)
      })
    }
  }

}
</script>

<style scoped>
#vote{
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
#votetext{
  font-size: 25px;
  font-weight: bold;
  padding: 10px;
  line-height: 35px;
  text-align: center;
  color: #4D455D;
}
#greenbox, #redbox{
  display: flex;
  flex-direction: column;
  margin: 10px;
}
#greenbar, #redbar{
  font-size: 10px;
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: white;
  width: 17vw;
  height: 1vh;
  font-weight: bold;
  color: #4D455D;
  border-radius: 20px;
}
#votesubmit{
    background-color: #4D455D;
    color: white;
    font-size: 18px;
    font-weight:bold;
    border-radius: 30px;
    width: 7vw;
    margin-left: 11vw;
}
#greenbox > input[type=radio]{
  accent-color: #D4E384;
  width: 10px;
  margin: 5px;
}

#redbox > input[type=radio]{
  accent-color: #F3998A;
  width: 10px;
  margin: 5px;
}

</style>