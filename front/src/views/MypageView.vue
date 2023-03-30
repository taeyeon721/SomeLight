<template>
  <div id="mypageWrapper">
    <div id="mypageback">
      <my-info-comp />
      <my-story-comp />
    </div>
  </div>
</template>

<script>
import MyInfoComp from '@/components/mypageview/MyInfoComp.vue'
import MyStoryComp from '@/components/mypageview/MyStoryComp.vue'

export default {

  components: { MyInfoComp, MyStoryComp },

}
</script>

<style scoped>
#mypageWrapper{
  width: 100vw;
  height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
}
#mypageback{
  width: 70vw;
  margin-top:12vh ;
  height: 88vh;
  display: flex;
  align-items: center;
  justify-content: center;
  background: linear-gradient(
    rgba(250,250,250,0.2), 
    rgba(250,250,250,0.4)),
    url("../../src/assets/img/mypage/mypageback.jpg");
  /* background-image: url("../../src/assets/img/main/mypageback.jpg"); */
  background-repeat: no-repeat;
  background-size: cover;
}

</style>