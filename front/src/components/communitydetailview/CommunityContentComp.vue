<template>
  <div>
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
    margin-top:10vh;
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

</style>