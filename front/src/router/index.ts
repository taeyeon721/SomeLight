import {
  createRouter,
  createWebHistory,
  NavigationGuardNext,
} from "vue-router";

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
    path: "/mypage",
    name: "mypage",
    component: () => import("../views/MypageView.vue"),
    // 라우터 가드 사용하는것.
    meta: { requiresAuth: true },
  },
  {
    path: "/community",
    name: "community",
    component: () => import("../views/CommunityView.vue"),
  },
  {
    path: "/community/:story_id",
    name: "communitydetail",
    component: () => import("../views/CommunityDetailView.vue"),
  },
  {
    path: "/login/kakao",
    component: () => import("../views/Redirect.vue"),
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

function checkAuth() {
  const user = sessionStorage.getItem("token");
  return user !== null;
}

router.beforeEach((to, from, next) => {
  // 로그인 필요한 페이지인 경우
  if (to.meta.requiresAuth) {
    const isAuthenticated = checkAuth(); // 로그인 여부 확인
    if (!isAuthenticated) {
      next("/login"); // 로그인 페이지로 이동
    } else {
      next(); // 로그인되어 있으면 다음 페이지로 이동
    }
  } else {
    next(); // 로그인 필요 없는 페이지는 그냥 이동
  }
});

export default router;
