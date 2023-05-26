<template>
  <div id="detailpage">
    <div class="detailwrapper">
      <community-content-comp v-bind:loginflag="loginflag" />
      <half-comp v-bind:loginflag="loginflag" />
    </div>
  </div>
</template>

<script>
import CommunityContentComp from "@/components/communitydetailview/CommunityContentComp.vue";
import HalfComp from "@/components/communitydetailview/HalfComp.vue";

export default {
  data() {
    return {};
  },
  components: { CommunityContentComp, HalfComp },
  methods: {},
  created() {
    const story_id = this.$route.params.story_id;
    const payload = { story_id: story_id };
    this.$store.dispatch("getDetail", payload);
  },
  computed: {
    loginflag() {
      if (
        Number(sessionStorage.getItem("pk")) ===
        this.$store.state.article.userId
      ) {
        console.log("내글");
        return true;
      } else {
        console.log("남글");
        return false;
      }
    },
  },
};
</script>

<style scoped>
#detailpage {
  font-family: "Dovemayo_gothic";
  width: 100vw;
  height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
}

.detailwrapper {
  margin-top: 3vh;
  height: 88vh;
  display: flex;
  align-items: center;
  justify-content: center;
}
</style>
