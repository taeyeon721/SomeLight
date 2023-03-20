import { createRouter, createWebHistory } from "vue-router";

const routes = [
  {
    path: "/",
    name: "main",
    component: () => import("../views/MainView.vue"),
  },
  {
    path:"/login",
    name:"login",
    component: () => import("../views/LoginView.vue")
  },
  {
    path:"/mypage",
    name:"mypage",
    component: () => import("../views/MypageView.vue")
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
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
