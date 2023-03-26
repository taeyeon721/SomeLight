import { createRouter, createWebHistory } from "vue-router";

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
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
