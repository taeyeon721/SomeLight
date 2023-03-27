<template>
  <div>
    <button id="backbtn" v-on:click="$router.go(-1)">back</button>
    <div id="resultWrapper">
      <div id="bulbWrapper" v-if="articleId===2">
          <img src="@/assets/img/result/fix_green.png" alt="" />
        </div>
        <div id="bulbWrapper" v-else-if="articleId===1">
          <img src="@/assets/img/result/fix_black.png" alt="" />
        </div>
        <div id="bulbWrapper" v-else-if="articleId===0">
          <img src="@/assets/img/result/fix_red.png" alt="" />
        </div>
    </div>
    <div id="content">
      <div id="contentdetail">
        {{ content }}
      </div>
    </div>
    <delete-comp v-if="loginflag"/>
  </div>
</template>

<script>
import DeleteComp from './mystory/DeleteComp.vue'


export default {
  data(){
    return{
      articleId:this.$store.state.article.result,
    }
  },
  methods:{

  },
  created(){
  },
  components:{
    DeleteComp,
  },
  props:[
    "loginflag",
  ],
  computed:{
    content(){
      return this.$store.state.article.content
    }
  }
}
</script>

<style scoped>
#content{
    width: 30vw;
    height: 50vh;
    border-radius: 20px;
    background-color: #F5E9CF; 
    opacity: 0.8;
    margin-right: 30px;
    margin-top:5vh;
    box-shadow: 3px 3px 3px rgb(216, 216, 216);
    display: flex;
    justify-content: center;
    align-items: center;
    overflow: scroll;
    overflow-x:hidden ;
}

#content::-webkit-scrollbar{
  width:10px;
}

#contentdetail{
  margin-top:20vh ;
  width: 30vw;
  height:50vh;
  margin-left:25px;
  margin-right:10px;
  line-height: 30px;
  font-size:25px;
  display: -webkit-box;
  word-break: break-all;
  resize: none;
}
#resultWrapper {
  width: 25%;
  min-height: 50%;
  /* height: auto; */
  margin-left: 4%;
  margin-top: 3%;
  background-color: rgba(255, 251, 251, 100%);
  border-radius: 20px;
  border: 1px solid rgba(233, 217, 217, 100%);
  box-shadow: 8px 4px 4px rgba(0, 0, 0, 25%);
  position: absolute;
  z-index: 0;
  display: flex;
  justify-content: center;
  /* align-items: center; */
}
#backbtn{
  width: 5vw;
  color: white;
  font-weight: bold;
  font-size: 15px;
  height: 5vh;
  border-radius: 30px;
  border: 0px solid black;
  background-color: #4D455D;
  box-shadow:3px 3px 3px rgb(187, 187, 187);
  margin-top:7vh;
}

#bulbWrapper {
  border-radius: 100%;
  width: 30%;
  height: 30%;
  display: flex;
  justify-content: center;
  align-items: center;
  position: absolute;
  top: -15%;
  left: 50%;
  transform: translateX(-50%);
  z-index: 2;
  border: 1px solid rgba(190, 176, 176, 100%);
  background-color: rgba(255, 251, 251, 100%);
  border-radius: 100%;
  border: 1px solid rgba(233, 217, 217, 100%);
  box-shadow: 8px 4px 4px rgba(0, 0, 0, 25%);
}

#bulbWrapper > img {
  height: 80%;
  /* width: 90%; */
  /* border-radius: 100%; */
}

</style>