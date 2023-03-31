<template>
  <div>
    <h1>Loading</h1>
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

<style>

</style>