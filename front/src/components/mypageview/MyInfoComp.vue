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
  created() {
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
  background-color: linear-gradient(
      rgb(158, 158, 158, 0.2),
      rgba(250, 250, 250, 0.9)
    )
    #f5e9cf;
  opacity: 0.85;
  display: flex;
  align-items: center;
  flex-direction: column;
  justify-content: space-around;
  box-shadow: 2px 2px 2px rgb(216, 216, 216);
}
#username {
  padding-top: 30%;
  font-size: 2.3rem;
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
