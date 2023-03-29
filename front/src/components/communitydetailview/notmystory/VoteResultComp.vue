<template>
  <div id="vote">
    <div id="votetext">
      <p>투표결과</p>
    </div>
    <div id="greenbox">
      <div style="display: flex; flex-direction: column">
        <div style="display: flex">
          <input
            type="radio"
            id="gchoice"
            name="vote"
            v-model="result"
            value="1"
          />
          <div v-bind:greenPercent="greenPercent" style="padding-bottom: 10px">
            그린라이트 {{ Math.round(greenPercent) }}%
          </div>
        </div>
        <div id="greenbar" style="border: 2px solid #d4e384">
          <div id="greenlayer" v-bind:style="cssVariablegreen"></div>
        </div>
      </div>
    </div>
    <div id="redbox">
      <div style="display: flex; flex-direction: column">
        <div style="display: flex">
          <input
            type="radio"
            id="rchoice"
            name="vote"
            v-model="result"
            value="2"
          />
          <div v-bind:redPercent="redPercent" style="padding-bottom: 10px">
            레드라이트 {{ Math.round(redPercent) }}%
          </div>
        </div>
        <div id="redbar" style="border: 2px solid #f3998a">
          <div id="redlayer" v-bind:style="cssVariablered"></div>
        </div>
      </div>
    </div>
    <div>
      <button id="votesubmit" v-on:click="vote">투표하기</button>
    </div>
  </div>
</template>

<script>
import axios from "axios";

// const BASE_URL = "http://localhost:8080"
// const BASE_URL = this.$store.state.BASE_URL;

export default {
  data() {
    return {
      result: this.$store.state.article.voteResult,
    };
  },
  methods: {
    vote() {
      axios({
        method: "put",
        url: `${this.$store.state.BASE_URL}/article/${this.$route.params.story_id}`,
        headers: {
          Authorization: `Bearer ${sessionStorage.getItem("token")}`,
        },
        data: {
          isChanged: this.$store.state.article.changed,
          isExposure: this.$store.state.article.exposure,
          voteResult: this.result,
        },
      })
        .then((res) => {
          this.$store.commit("GET_ARTICLE_DETAIL", res.data);
          // console.log(res.data)
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
  created() {},
  computed: {
    redPercent() {
      return this.$store.state.article.redPercent;
    },
    greenPercent() {
      return this.$store.state.article.greenPercent;
    },
    cssVariablegreen() {
      return {
        width: `${this.greenPercent}%`,
      };
    },
    cssVariablered() {
      return {
        width: `${this.redPercent}%`,
      };
    },
  },
};
</script>

<style scoped>
#vote {
  width: 25vw;
  height: 40vh;
  border-radius: 20px;
  background-color: #f5e9cf;
  opacity: 0.9;
  box-shadow: 3px 3px 3px rgb(187, 187, 187);
  margin: 10vh;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
#votetext {
  font-size: 25px;
  font-weight: bold;
  padding: 10px;
  line-height: 35px;
  text-align: center;
  color: #4d455d;
}
#greenbox,
#redbox {
  display: flex;
  flex-direction: column;
  margin: 10px;
}
#greenbar,
#redbar {
  font-size: 10px;
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: white;
  width: 17vw;
  height: 1vh;
  font-weight: bold;
  color: #4d455d;
  border-radius: 20px;
}
#votesubmit {
  background-color: #4d455d;
  color: white;
  font-size: 18px;
  font-weight: bold;
  border-radius: 30px;
  width: 7vw;
  margin-left: 11vw;
}
#greenbox > input[type="radio"] {
  accent-color: #d4e384;
  width: 10px;
  margin: 5px;
}

#redbox > input[type="radio"] {
  accent-color: #f3998a;
  width: 10px;
  margin: 5px;
}

#greenlayer {
  background-color: #d4e384;
  border-radius: 20px;
  height: 1vh;
  animation-name: voteslidegreen;
  animation-duration: 1s;
  animation-iteration-count: 1;
}

#redlayer {
  background-color: #f3998a;
  border-radius: 20px;
  height: 1vh;
  animation-name: voteslidered;
  animation-duration: 1s;
  animation-iteration-count: 1;
}

@keyframes voteslidegreen {
  from {
    width: 0%;
    height: 1vh;
    background-color: #d4e384;
  }

  to {
    height: 1vh;
    background-color: #d4e384;
  }
}

@keyframes voteslidered {
  from {
    width: 0%;
    height: 1vh;
    background-color: #f3998a;
  }

  to {
    height: 1vh;
    background-color: #f3998a;
  }
}
</style>
