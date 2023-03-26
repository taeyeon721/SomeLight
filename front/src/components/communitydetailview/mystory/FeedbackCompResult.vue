<template>
  <div id="feedback">
    <p id="feedbacktitle">정확했나요?</p>
    <button id="feedbackbtn">YES</button>
    <button id="feedbackbtn">NO</button>
  </div>
</template>

<script>
export default {

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
    background-color: #4D455D;
    color: white;
    font-size: 20px;
    font-weight:bold;
    border-radius: 30px;
    width: 5vw;

}

</style>