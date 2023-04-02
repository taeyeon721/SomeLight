<template>
  <div class="loadingWrapper">
    <span style="--i:1">S</span>
   <span style="--i:2">O</span>
   <span style="--i:3">M</span>
   <span style="--i:4">E</span>
   <span style="--i:5">L</span>
   <span style="--i:6">I</span>
   <span style="--i:7">G</span>
   <span style="--i:8">H</span>
   <span style="--i:9">T</span>
   <!-- <span style="--i:10">.</span>
   <span style="--i:11">.</span>
   <span style="--i:12">.</span> -->
   <img style="--i:10" src="@/assets/img/result/greenheart.png" height="10" alt="">
   <img style="--i:11" src="@/assets/img/result/navyheart.png" height="10" alt="">
   <img style="--i:12" src="@/assets/img/result/redheart.png" height="10" alt="">
   <!-- <img style="--i:13" src="@/assets/img/nav/bulb2.png" height="80" alt=""> -->
  </div>
</template>

<script>
import router from '@/router';
export default {
    created(){
        setTimeout(()=>{
          console.log("로딩로딩로딩로딩")  
        //   router.push("/story/result")
        }, 200);
    }

}
</script>

<style scoped>
* {
  padding: 0;
  margin: 0;
  box-sizing: border-box;
}

body {
  background-color: #111;
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
}
.loadingWrapper {
  margin-top: 25%;
  margin-left: 35%;
  position: absolute;
  -webkit-box-reflect: below -20px linear-gradient(transparent, rgba(0,0,0,.2));
}
.loadingWrapper span, img {
  position: relative;
  display: inline-block;
  font-family: "Dovemayo_gothic";
  font-size:5rem;
  color: rgba(121, 80, 63, 100%);
  font-weight: 900;
  text-transform: uppercase;
  animation: waviy 1.5s infinite;
  animation-delay: calc(.1s * var(--i))
}
@keyframes waviy {
  0%,40%,100% {
    transform: translateY(0)
  }
  20% {
    transform: translateY(-20px)
  }
}
</style>