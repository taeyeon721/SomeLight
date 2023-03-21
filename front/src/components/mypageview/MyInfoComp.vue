<template>
  <div id="myinfo">
    <h1 id="username">NICKNAME</h1>
    <p id="userdelete">회원탈퇴</p>
  </div>
</template>

<script>
export default {

}
</script>

<style scoped>
#myinfo{
  width: 200px;
  height: 200px;
  margin-right: 50px;
  margin-bottom: 260px;
  border-radius: 20px;
  background-color: #F5E9CF;
  opacity: 0.85;
  display: flex;
  align-items: center;
  flex-direction: column;
  justify-content: space-around;
  box-shadow: 3px 3px 3px rgb(173, 173, 173);
}
#username{
  padding-top:60px;
  font-size:30px;
  font-weight: bold;
  color: #4D455D;
}
#userdelete{
  color: gray;
  margin-top: 30px;
  margin-bottom: 0px;
  margin-right: 90px;
}
</style>