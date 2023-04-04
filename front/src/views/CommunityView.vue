<template>
  <div id="communitywrapper">
    <div id="commuback">
      <div id="commutitle">커뮤니티</div>
      <community-list-comp />
    </div>
  </div>
</template>

<script>
import CommunityListComp from "@/components/communityview/CommunityListComp.vue";
export default {
  components: { CommunityListComp },
};
</script>

<style scoped>
#communitywrapper {
  width: calc(100vw - (100vw - 100%));
  height: calc(100vh - (100vh - 100%));
  display: flex;
  align-items: center;
  justify-content: center;
}

#commuback {
  margin-top: 12vh;
  width: 69vw;
  height: 88vh;
  display: flex;
  justify-content: center;
  flex-direction: column;
  align-items: center;
  background-image: linear-gradient(
      rgba(250, 250, 250, 0.2),
      rgba(250, 250, 250, 0.4)
    ),
    url("../../src/assets/img/community/communityback.jpg");
  background-repeat: no-repeat;
  background-size: 75vw 90vh;
}
#commutitle {
  margin-bottom: 55%;
  font-family: "Dovemayo_gothic";
  font-size: 2rem;
  font-weight: bold;
  color: #4d455d;
}
</style>
