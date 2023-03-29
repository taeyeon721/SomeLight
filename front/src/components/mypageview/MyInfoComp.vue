<template>
  <div id="myinfo">
    <h1 id="username">{{ nickname }}</h1>
    <p id="userdelete">회원탈퇴</p>
  </div>
</template>

<script>
import axios from "axios";
// import { reactive } from 'vue';

// const BASE_URL = "http://localhost:8080"
// const BASE_URL = this.$store.state.BASE_URL;

export default {
  // props:{
  //   nickname: String,
  // },
  data() {
    return {
      nickname: "",
    };
  },
  setup() {
    // const state = reactive({ nickname:"" })
    // const info = () => {
    //   axios({
    //     method:"get",
    //     url: `${this.$store.state.BASE_URL}/user/info`,
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
  methods: {
    info() {
      axios({
        method: "get",
        url: `${this.$store.state.BASE_URL}/user/info`,
        headers: {
          Authorization: `Bearer ${sessionStorage.getItem("token")}`,
        },
      })
        .then((res) => {
          console.log(this.nickname);
          this.nickname = res.data.nickname;
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
  // created() {
  //   this.info();
  // },
  mounted() {
    this.info();
  },
};
</script>

<style scoped>
#myinfo {
  font-family: "Dovemayo_gothic";
  width: 20%;
  height: 30%;
  margin-right: 10%;
  margin-bottom: 30%;
  border-radius: 20px;
  background-color: #f5e9cf;
  opacity: 0.85;
  display: flex;
  align-items: center;
  flex-direction: column;
  justify-content: space-around;
  box-shadow: 2px 2px 2px rgb(216, 216, 216);
}
#username{
  padding-top:30%;
  font-size:2.3rem;
  font-weight: bold;
  color: #4d455d;
}
#userdelete {
  color: gray;
  margin-top: 30px;
  margin-bottom: 0px;
  margin-right: 90px;
}
</style>