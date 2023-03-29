<template>
  <div id="communityWrapper">
    <div id="commutitle">COMMUNITY</div>
    <community-list-comp />
  </div>
</template>

<script>
import CommunityListComp from '@/components/communityview/CommunityListComp.vue'
export default {
  components: { CommunityListComp },

}
</script>

<style scoped>
#communityWrapper{
    margin-top: 11vh;
    width: 90vw;
    height: 88vh;
    display: flex;
    justify-content: center;
    flex-direction: column;
    align-items: center;
    background-image: linear-gradient(
      rgba(250,250,250,0.2), 
      rgba(250,250,250,0.4)), 
      url("../../src/assets/img/community/communityback.jpg");
    background-repeat: no-repeat;
    background-size: 75vw 90vh;

}
#commutitle{
    font-size:2rem;
    font-weight: bold;
    color: #4D455D;
}

</style>