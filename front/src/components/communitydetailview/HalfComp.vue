<template>
  <div id="half">
    <vote-comp />
    <!-- <share-comp /> -->
    <feedback-comp />
    <delete-comp />
  </div>
</template>

<script>
import DeleteComp from './DeleteComp.vue'
import FeedbackComp from './FeedbackComp.vue'
import ShareComp from './ShareComp.vue'
import VoteComp from './VoteComp.vue'
export default {
  components: { FeedbackComp, DeleteComp, ShareComp, VoteComp },

}
</script>

<style scoped>
#half{
    margin-top: 10vh;
    margin-left:30px;
    width: 40vw;
    height: 90vh;
    background-image: url("../../../src/assets/img/main/communitydetailback.jpg");
    background-repeat:no-repeat ;
    background-size: 40vw 90vh;
    display: flex;
    align-items: center;
    justify-content: center;
    flex-direction: column;
}
</style>