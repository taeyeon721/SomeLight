<template>
  <div id="vote">
    <div id="votetext">
    <p>
      사연에 대해</p> 
      <p>여러분의 생각을</p>
      <p> 투표해주세요.
    </p>
    </div>
    <div id="greenbox">
      <input type="radio" value="green">
        <div id="greenbar"
        style="border: 2px solid #D4E384;">
          그린라이트
        </div>
    </div>
    <div id="redbox">
      <input type="radio" value="red">
        <div id="redbar"
        style="border: 2px solid #F3998A">
          레드라이트
        </div>
    </div>
    <div>
      <button id="votesubmit">투표하기</button>
    </div>
  </div>
</template>

<script>
export default {

}
</script>

<style scoped>
#vote{
    width: 25vw;
    height: 40vh;
    border-radius: 20px;  
    background-color: #F5E9CF;
    opacity: 0.9;
    box-shadow: 3px 3px 3px gray;
    margin: 10vh;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
}
#votetext{
  font-size: 25px;
  font-weight: bold;
  padding: 10px;
  line-height: 35px;
  text-align: center;
  color: #4D455D;
}
#greenbox, #redbox{
  display: flex;
  flex-direction: row;
  margin: 10px;
}
#greenbar, #redbar{
  font-size: 20px;
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: white;
  width: 17vw;
  height: 4vh;
  font-weight: bold;
  color: #4D455D;
  border-radius: 20px;
}
#votesubmit{
    background-color: #4D455D;
    color: white;
    font-size: 18px;
    font-weight:bold;
    border-radius: 30px;
    width: 7vw;
    margin-left: 11vw;
}

</style>