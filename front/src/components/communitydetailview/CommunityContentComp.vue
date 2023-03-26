<template>
  <div>
    <button id="backbtn" v-on:click="$router.go(-1)">back</button>
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

</style>