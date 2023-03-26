<template>
  <div style=
  "height:30vh;
  display:flex;
  align-items:center;">
    <h1 style=
    "font-size:64px;
    font-weight:bold;
    color:#4D455D;
    ">LOGIN</h1>
  </div>
  <div style=
  "height:20vh;
  display:flex;
  flex-direction: column;
  justify-content:space-around;
  align-items:center;">
    <div>
      <button 
        type="button"
        id="nonmemberbtn" 
        v-on:click="nonmemberLogin"
        >
        비로그인 이용
      </button>
    </div>
    <div>
      <button 
        type="button"
        id="kakaobtn" 
        v-on:click="kakaoLogin"
        >
        <img 
        src="../../../src/assets/img/main/kakaoImg.png" 
        width="30" 
        height="30"
        style="margin-right:10px">
        <a 
        href="https://kauth.kakao.com/oauth/authorize?client_id=8147c85395148371709b2199642f9108&redirect_uri=http://localhost:3000/login/kakao&state=kakao&response_type=code"
        style="
        text-decoration:none;
        color:white;"
        >카카오톡 로그인</a>
      </button>

    </div>
  </div>
</template>

<script >

// const BASE_URL = "http://localhost:8080"

export default {
    name: 'LoginComp',
    setup(){

    },
    methods:{
        nonmemberLogin(){

        },
    },
    created(){
      
    }
}
</script>

<style scoped>
#nonmemberbtn{
    width: 20vw;
    height: 7vh;
    background-color: rgb(189, 189, 189);
    border-radius: 25px;
    color: white;
    font-size: 25px;
    font-weight: bold;
    border: 0px solid white;
    display: flex;
    justify-content: center;
    align-items: center;

}
#kakaobtn{
    width: 20vw;
    height: 7vh;
    background-color: #4D455D;
    border-radius: 25px;
    color: white;
    font-size: 25px;
    font-weight: bold;
    border: 0px solid white;
    display: flex;
    justify-content: center;
    align-items: center;
}
</style>