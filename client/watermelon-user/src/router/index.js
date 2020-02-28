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
        component: () => import("../views/music/index/index.vue")
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
        component: () => import("../views/music/allMusic/allMusic.vue")
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
        component: () =>
          import("../views/music/musicInfomation/musicInfomation.vue")
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
        component: () => import("../views/music/hotMusic/hotMusic.vue")
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
        component: () => import("../views/music/recommended/recommended.vue")
      }
    ]
  },
  {
    path: "/musicInfo",
    redirect: "/musicInfo",
    hidden: false,
    meta: {
      name: "音乐详情"
    },
    component: layout,
    children: [
      {
        path: "/musicInfo",
        component: () => import("../views/music/musicInfo/musicInfo.vue")
      }
    ]
  },
  {
    path: "/detailsInfo",
    redirect: "/detailsInfo",
    hidden: false,
    meta: {
      name: "资讯详情"
    },
    component: layout,
    children: [
      {
        path: "/detailsInfo",
        component: () =>
          import("../views/music/infomationDetails/infomationDetails.vue")
      }
    ]
  },
  {
    path: "/webAdvice",
    redirect: "/webAdvice",
    hidden: false,
    meta: {
      name: "网站建议"
    },
    component: layout,
    children: [
      {
        path: "/webAdvice",
        component: () => import("../views/music/webAdvice/webAdvice.vue")
      }
    ]
  },
  {
    path: "/myCollect",
    redirect: "/myCollect",
    hidden: false,
    meta: {
      name: "我的收藏"
    },
    component: layout,
    children: [
      {
        path: "/myCollect",
        component: () => import("../views/music/myCollect/myCollect.vue")
      }
    ]
  }
];
const router = new VueRouter({
  routes
});

export default router;
