<template>
  <div id="communityWrapper">
    <p id="commutitle">COMMUNITY</p>
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
    margin-top: 10vh;
    width: 100vw;
    height: 90vh;
    display: flex;
    justify-content: center;
    flex-direction: column;
    align-items: center;
    background-image: url("../../src/assets/img/main/communityback.jpg");
    background-repeat: no-repeat;
    opacity: 0.8;
    background-size: 75vw 90vh;

}
#commutitle{
    font-size:40px;
    font-weight: bold;
    color: #4D455D;
    padding-bottom: 10px;
}

</style>