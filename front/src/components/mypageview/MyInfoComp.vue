<template>
  <div id="myinfo">
    <h1 id="username">{{ nickname }}</h1>
    <p id="userdelete">회원탈퇴</p>
  </div>
</template>

<script>
import axios from "axios";
// import { reactive } from 'vue';

export default {
  // props:{
  //   nickname: String,
  // },
  data(){
    return{
      nickname:"",
    }
  },
  setup(){
    // const state = reactive({ nickname:"" })

    // const info = () => {
    //   axios({
    //     method:"get",
    //     url:"http://localhost:8080/user/info",
    //     headers:{
    //       Authorization:`Bearer ${sessionStorage.getItem("token")}`
    //     }
    //   })
    //   .then(function(res){
    //     state.nickname = res.data.nickname
    //   })
    //   .catch(function(err){
    //     console.log(err)
    //   })

    // }
    // return{
    //   state,
    //   info
    // }
  },
  methods:{
    info(){
      axios({
        method:"get",
        url:"http://localhost:8080/user/info",
        headers:{
          Authorization:`Bearer ${sessionStorage.getItem("token")}`
        }
      })
      .then((res)=>{
        console.log(this.nickname)
        this.nickname = res.data.nickname
      })
      .catch((err)=>{
        console.log(err)
      })
    }
  
  },
  created(){
    this.info()
  
  }
}
</script>

<style scoped>
#myinfo{
  width: 200px;
  height: 200px;
  margin-right: 50px;
  margin-bottom: 260px;
  border-radius: 20px;
  background-color: #F5E9CF;
  opacity: 0.85;
  display: flex;
  align-items: center;
  flex-direction: column;
  justify-content: space-around;
  box-shadow: 2px 2px 2px rgb(216, 216, 216);
}
#username{
  padding-top:60px;
  font-size:30px;
  font-weight: bold;
  color: #4D455D;
}
#userdelete{
  color: gray;
  margin-top: 30px;
  margin-bottom: 0px;
  margin-right: 90px;
}
</style>