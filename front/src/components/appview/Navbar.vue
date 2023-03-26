<template>
  <div id="navWrapper" class="font">
    <div id="logoWrapper">
      <span>S</span>
      <img src="@/assets/img/nav/bulb2.png" alt="" />
      <span>ME</span>
    </div>
    <div v-if="!isLogin" id="itemWrapper">
      <ul>
        <!-- 메인화면일경우 -->
        <li>
          <router-link 
          to="/login"
          style="
          text-decoration:none;
          ">START</router-link></li>
      </ul>
    </div>
    <div v-else id="itemWrapperLogin">
      <!-- 카카오 로그인 이후 -->
      <ul>
        <li>
          <router-link 
          to="/story/create"
          style="
          text-decoration:none;
          ">STORY</router-link>
        </li>
        <li>
          <router-link 
          to="/community"
          style="
          text-decoration:none;
          "> COMMUNITY</router-link>
        </li>
        <li>
          <router-link 
          to="/mypage"
          style="
          text-decoration:none;
          "> MYPAGE</router-link>
        </li>
        <li>
          <router-link 
          to="/"
          style="
          text-decoration:none;"
          v-on:click="logout">LOGOUT</router-link>
        </li>
      </ul>
    </div>
  </div>
</template>
<script>
// import router from "@/router"

export default {
  data(){
    return {
      isLogin: sessionStorage.getItem("token"),
    }
  },
  // setup() {
  //   const isLogin = ref(sessionStorage.getItem("token"))
    
  //   console.log(isLogin)

  //   return{
  //     isLogin
  //   }
  // },
  methods:{
    logout(){
      sessionStorage.removeItem("token")
      sessionStorage.removeItem("pk")
      this.isLogin = false
    },
  },
  created(){
  
    
  },
  computed:{

  },
 
}
</script>

<style scoped>
#navWrapper {
  width: 100vw;
  height: 10vh;
  position: fixed;
  top: 0px;
  display: flex;
  background-color: rgba(255, 255, 255, 50%);
  margin-top:1vh;
  margin-bottom:1vh;
}

#logoWrapper {
  /* width: 100%; */
  height: 95%;
  margin-left: 15vw;
  white-space: nowrap;
}
#logoWrapper > span {
  font-size: 3.9em;
  color: rgba(121, 80, 63, 100%);
  font-weight: bold;
  height: 95%;
}
#logoWrapper > img {
  height: 85%;
}

#itemWrapper {
  height: 100%;
  margin-left: 55vw;
}
ul {
  height: 100%;
  display: flex;
  font-size: 2rem;
  font-weight: bold;
  color: rgba(77, 69, 93, 100%);
}
li {
  height: 100%;
  /* line-height: 100%; */
  font-size: 0.8em;
  text-align: center;
  display: flex;
  align-items: center;
  margin-left: 3vw;
}
#itemWrapperLogin{
  height: 100%;
  margin-left: 11vw;
}
</style>
