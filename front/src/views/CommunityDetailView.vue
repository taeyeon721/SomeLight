<template>
  <div id="detailpage">
    <community-content-comp />
    <half-comp />
  </div>
</template>

<script>
import CommunityContentComp from '@/components/communitydetailview/CommunityContentComp.vue'
import HalfComp from '@/components/communitydetailview/HalfComp.vue'
export default {
  components: { CommunityContentComp, HalfComp},

}
</script>

<style scoped>
#detailpage{
  width: 100vw;
  height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
}

</style>