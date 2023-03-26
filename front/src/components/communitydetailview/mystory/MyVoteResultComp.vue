<template>
  <div id="vote">
    <div id="votetext">
    <p>
      투표 결과
    </p>
    </div>
    <div id="greenbox">
        <div 
        v-bind:greenPercent="greenPercent"
        style="padding-bottom:10px;">
        그린라이트 {{ Math.round(greenPercent) }}%
        </div>
        <div id="greenbar"
        style="border: 2px solid #D4E384;">
        <div id="greenlayer" v-bind:style="cssVariablegreen">
        </div>
        </div>
    </div>
    <div id="redbox">
        <div 
        v-bind:redPercent="redPercent"
        style="padding-bottom:10px;">
        레드라이트 {{ Math.round(redPercent) }}%
        </div>
        <div id="redbar"
        style="border: 2px solid #F3998A">
        <div id="redlayer" v-bind:style="cssVariablered"></div>
        </div>
    </div>
  
  </div>
</template>

<script>
export default {
  data(){
    return{

    }
  },
  created(){
  },
  computed:{
    redPercent(){
      return this.$store.state.article.redPercent
    },
    greenPercent(){
      return this.$store.state.article.greenPercent
    },
    cssVariablegreen(){
      return{
        width:`${this.greenPercent}%`,
      }
    },
    cssVariablered(){
      return{
        width: `${this.redPercent}%`
      }
    }
  }
}
</script>

<style scoped>
#vote{
    width: 25vw;
    height: 40vh;
    border-radius: 20px;  
    background-color: #F5E9CF;
    opacity: 0.9;
    box-shadow: 3px 3px 3px gray;
    margin: 10vh;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
}
#votetext{
  font-size: 25px;
  font-weight: bold;
  padding: 10px;
  line-height: 35px;
  text-align: center;
  color: #4D455D;
}
#greenbox, #redbox{
  display: flex;
  flex-direction: column;
  margin: 10px;
}
#greenbar, #redbar{
  font-size: 20px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  background-color:white;
  width: 17vw;
  height: 1vh;
  font-weight: bold;
  color: #4D455D;
  border-radius: 20px;
}
#greenlayer{
  background-color: #D4E384;
  border-radius: 20px;
  height: 1vh;
  animation-name: voteslidegreen;
  animation-duration :1s;
  animation-iteration-count:1,
}

#redlayer{
  background-color: #F3998A;
  border-radius: 20px;
  height: 1vh;
  animation-name: voteslidered;
  animation-duration :1s;
  animation-iteration-count:1,
}

@keyframes voteslidegreen{
  from{
    width: 0%;
    height: 1vh;
    background-color: #D4E384;
  }

  to{
    height: 1vh;
    background-color: #D4E384;
  }
}

@keyframes voteslidered{
  from{
    width: 0%;
    height: 1vh;
    background-color: #F3998A;
  }

  to{
    height: 1vh;
    background-color: #F3998A;
  }
}
</style>