<template>
  <div></div>
</template>

<script>
import router from "@/router";
import axios from 'axios';

export default {
    methods:{
        getUser(code){
            axios({
                method:"get",
                url: "http://localhost:8080/login/kakao",
                params:{
                    "code":code,
                },
            })
            .then((res)=>{
                console.log(res.data)
                sessionStorage.setItem("token",res.data.token)
                // console.log(res)
            })
            .catch((err)=>{
                console.log(err)
            })
        }
    },
    created(){
        const code = this.$route.query.code
        console.log('code', code)
        if (code!=null) {
            this.getUser(code)
            router.push({path:"/mypage"})
        } else {
            router.push({path:"/login"})
        }
    }
}
</script>

<style>

</style>