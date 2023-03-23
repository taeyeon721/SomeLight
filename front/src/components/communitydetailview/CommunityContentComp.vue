<template>
  <div id="content">
    <div id="contentdetail">
      {{ content }}
    </div>
  </div>
</template>

<script>
export default {
  props:["content",],
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
    box-shadow: 3px 3px 3px gray;
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