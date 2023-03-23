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
  margin-top:10vh ;
  height: 90vh;
  display: flex;
  align-items: center;
  justify-content: center;
  background-image: url("../../src/assets/img/main/mypageback.jpg");
  background-repeat: no-repeat;
  background-size: cover;
}

</style>