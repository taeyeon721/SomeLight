<template>
  <div id="mystory">
    <div id="mystorytitle">내가 쓴 사연</div>
    <my-story-list-comp />
  </div>
</template>

<script>
import MyStoryListComp from './MyStoryListComp.vue'

export default {
  components: { MyStoryListComp },

}
</script>

<style scoped>
#mystory{
  width: 40vw;
  height: 70vh;
  border-radius: 20px;
  color:#4D455D;
  background-color: #F5E9CF;
  opacity: 0.85;
  display: flex;
  justify-content: center;
  flex-direction: column;
  box-shadow: 3px 3px 3px rgb(175, 175, 175);
}
#mystorytitle{
  padding-top:10px;
  font-size:30px;
  font-weight: bold;
  color: #4D455D;
  text-align: center;
}


</style>