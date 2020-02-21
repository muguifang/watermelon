import Vue from "vue";
import VueRouter from "vue-router";
import layout from "../views/Layout/index.vue";
// import Home from "../views/Home.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    redirect: "login",
    hidden: true,
    meta: {
      name: "主页"
    }
  },
  {
    path: "/login",
    name: "Login",
    hidden: true,
    meta: {
      name: "登录"
    },
    component: () => import("../views/Login/index.vue")
  },
  {
    path: "/console",
    name: "Console",
    redirect: "index",
    hidden: true,
    meta: {
      name: "管理员登录首页"
    },
    component: layout,
    children: [
      {
        path: "/index",
        name: "Index",
        hidden: true,
        meta: {
          name: "欢迎页面"
        },
        component: () => import("../views/Admin/index.vue")
      }
    ]
  },
  {
    path: "/update",
    name: "Update",
    hidden: false,
    meta: {
      name: "个人中心",
      icon: "personal"
    },
    component: layout,
    children: [
      {
        path: "/updatePwd",
        name: "UpdatePwd",
        hidden: false,
        meta: {
          name: "修改密码"
        },
        component: () => import("../views/Admin/updatePwd.vue")
      }
    ]
  },
  {
    path: "/lbt",
    name: "Lbt",
    hidden: false,
    meta: {
      name: "首页轮播管理",
      icon: "lbt"
    },
    component: layout,
    children: [
      {
        path: "/lbIndex",
        name: "LbIndex",
        hidden: false,
        meta: {
          name: "首页轮播列表"
        },
        component: () => import("../views/Admin/Lbt/index.vue")
      }
    ]
  },
  {
    path: "/zx",
    name: "Zx",
    hidden: false,
    meta: {
      name: "音乐资讯管理",
      icon: "information"
    },
    component: layout,
    children: [
      {
        path: "/zxIndex",
        name: "ZxIndex",
        hidden: false,
        meta: {
          name: "音乐资讯列表"
        },
        component: () => import("../views/Admin/Zx/index.vue")
      }
    ]
  },
  {
    path: "/user",
    name: "User",
    hidden: false,
    meta: {
      name: "用户管理",
      icon: "user"
    },
    component: layout,
    children: [
      {
        path: "/userIndex",
        name: "UserIndex",
        hidden: false,
        meta: {
          name: "用户列表"
        },
        component: () => import("../views/Admin/User/index.vue")
      }
    ]
  },
  {
    path: "/music",
    name: "Music",
    hidden: false,
    meta: {
      name: "音乐管理",
      icon: "music"
    },
    component: layout,
    children: [
      {
        path: "/typeIndex",
        name: "TypeIndex",
        hidden: false,
        meta: {
          name: "音乐类别列表"
        },
        component: () => import("../views/Admin/Music/typeIndex.vue")
      },
      {
        path: "/musicIndex",
        name: "MusicIndex",
        hidden: false,
        meta: {
          name: "音乐列表"
        },
        component: () => import("../views/Admin/Music/musicIndex.vue")
      }
    ]
  },
  {
    path: "/advice",
    name: "Advice",
    hidden: false,
    meta: {
      name: "网站建议管理",
      icon: "advice"
    },
    component: layout,
    children: [
      {
        path: "/adviceIndex",
        name: "AdviceIndex",
        hidden: false,
        meta: {
          name: "建议信息列表"
        },
        component: () => import("../views/Admin/Advice/index.vue")
      }
    ]
  },
  {
    path: "/count",
    name: "Count",
    hidden: false,
    meta: {
      name: "统计中心",
      icon: "count"
    },
    component: layout,
    children: [
      {
        path: "/djlList",
        name: "DjlList",
        hidden: false,
        meta: {
          name: "按点击量排名"
        },
        component: () => import("../views/Admin/Count/djlList.vue")
      },
      {
        path: "/sclList",
        name: "SclList",
        hidden: false,
        meta: {
          name: "按收藏量排名"
        },
        component: () => import("../views/Admin/Count/sclList.vue")
      }
    ]
  }
];

const router = new VueRouter({
  routes
});

export default router;
