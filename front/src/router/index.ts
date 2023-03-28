import { createRouter, createWebHistory, NavigationGuardNext } from "vue-router";

const routes = [
  {
    path: "/",
    name: "main",
    component: () => import("../views/MainView.vue"),
  },
  {
    path: "/login",
    name: "login",
    component: () => import("../views/LoginView.vue"),
  },
  {
    path: "/story/create",
    name: "storyCreate",
    component: () => import("../views/StoryCreateView.vue"),
  },

  {
    path: "/story/result",
    name: "storyResult",
    component: () => import("../views/StoryResultView.vue"),
  },
  
  {
    path:"/mypage",
    name:"mypage",
    component: () => import("../views/MypageView.vue"),
    beforeEnter(to:any, next:any){
      if (sessionStorage.getItem("token")==null){
        alert("로그인하세요")
        return next({name:"login"})
      } else {
        return 
      }
    }
    
  },
  {
    path:"/community",
    name:"community",
    component: () => import("../views/CommunityView.vue")
  },
  {
    path:"/community/:story_id",
    name:"communitydetail",
    component: () => import("../views/CommunityDetailView.vue")
  },
  {
    path:"/login/kakao",
    component:()=>import("../views/Redirect.vue")

  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});


export default router;
