<template>
  <div id="feedback">
    <p id="feedbacktitle">정확했나요?</p>
    <button id="feedbackbtn" v-bind:disabled="fbResult===true">네</button>
    <button id="feedbackbtn" v-bind:disabled="fbResult===false">아니오</button>
  </div>
</template>

<script>
export default {
  data(){
    return{
      fbResult: this.$store.state.article.changed,
    }
  },

}
</script>

<style scoped>
#feedback{
    width: 30vw;
    height: 7vh;
    margin: 20px;
    border-radius: 30px;
    display: flex;
    justify-content: space-evenly;
    align-items: center;
    background-color: #F5E9CF;
    opacity: 0.9;
    box-shadow: 3px 3px 3px rgb(187, 187, 187);
}
#feedbacktitle{
    color: #4D455D;
    font-size: 25px;
    font-weight: bold;
}
#feedbackbtn{
     font-family: "Dovemayo_gothic";
    background-color: #4D455D;
    color: white;
    font-size: 20px;
    font-weight:bold;
    border-radius: 30px;
    width: 5vw;
}

#feedbackbtn:disabled{
  background-color:white ;
  color:#4D455D;
}

</style>