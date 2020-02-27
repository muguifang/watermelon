import Vue from "vue";
import VueRouter from "vue-router";
import layout from "@/views/Layout/index.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    redirect: "/index",
    hidden: true,
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
    hidden: true,
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
    path: "/musicInfomation",
    redirect: "/musicInfomation",
    hidden: true,
    meta: {
      name: "音乐资讯"
    },
    component: layout,
    children: [
      {
        path: "/musicInfomation",
        name: "MusicInfomation",
        component: () => import("../views/music/musicInfomation.vue")
      }
    ]
  },
  {
    path: "/hotMusic",
    redirect: "/hotMusic",
    hidden: true,
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
    hidden: true,
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
  },
  {
    path: "/musicInfo",
    redirect: "/musicInfo",
    hidden: false,
    meta: {
      name: ""
    },
    component: layout,
    children: [
      {
        path: "/musicInfo",
        component: () => import("../views/music/musicInfo.vue")
      }
    ]
  },
  {
    path: "/detailsInfo",
    redirect: "/detailsInfo",
    hidden: false,
    meta: {
      name: ""
    },
    component: layout,
    children: [
      {
        path: "/detailsInfo",
        component: () => import("../views/music/infomationDetails.vue")
      }
    ]
  }
];
const router = new VueRouter({
  routes
});

export default router;
