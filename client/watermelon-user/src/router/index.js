import Vue from "vue";
import VueRouter from "vue-router";
import layout from "@/views/Layout/index.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Music",
    redirect: "index",
    meta: {
      name: "音乐"
    },
    component: layout,
    children: [
      {
        path: "/index",
        name: "Index",
        meta: {
          name: "音乐页面"
        },
        component: () => import("../views/music/music.vue")
      }
    ]
  },
  {
    path: "/person",
    name: "Person",
    meta: {
      name: "个人中心"
    },
    component: layout,
    children: [
      {
        path: "/info",
        name: "Info",
        meta: {
          name: "我的信息"
        },
        component: () => import("../views/music/myMusic.vue")
      }
    ]
  }
];

const router = new VueRouter({
  routes
});

export default router;
