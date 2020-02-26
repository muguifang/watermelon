import Vue from "vue";
import VueRouter from "vue-router";
import layout from "@/views/Layout/index.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    redirect: "/index",
    meta: {
      name: "首页"
    },
    component: layout,
    children: [
      {
        path: "/index",
        component: () => import("../views/music/index.vue")
      }
    ]
  },
  {
    path: "/allMusic",
    redirect: "/allMusic",
    meta: {
      name: "全部音乐"
    },
    component: layout,
    children: [
      {
        path: "/allMusic",
        name: "AllMusic",
        component: () => import("../views/music/allMusic.vue")
      }
    ]
  },
  {
    path: "/musicInfo",
    redirect: "/MusicInfo",
    meta: {
      name: "音乐资讯"
    },
    component: layout,
    children: [
      {
        path: "/musicInfo",
        component: () => import("../views/music/musicInfomation.vue")
      }
    ]
  },
  {
    path: "/hotMusic",
    redirect: "/hotMusic",
    meta: {
      name: "热歌榜"
    },
    component: layout,
    children: [
      {
        path: "/hotMusic",
        component: () => import("../views/music/hotMusic.vue")
      }
    ]
  },
  {
    path: "/recommended",
    redirect: "/recommended",
    meta: {
      name: "每日推荐"
    },
    component: layout,
    children: [
      {
        path: "/recommended",
        component: () => import("../views/music/recommended.vue")
      }
    ]
  }
];
const router = new VueRouter({
  routes
});

export default router;
