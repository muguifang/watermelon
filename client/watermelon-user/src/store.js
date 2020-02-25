import Vue from "vue";
import Vuex from "vuex";
// import Cookie from "cookie_js";

Vue.use(Vuex);
export default new Vuex.Store({
  state: {
    //html5本地存储 sessionStorage临时性  localStorage长久性 语法用下
    isCollapse: JSON.parse(sessionStorage.getItem("isCollapse")) || false
    //cookie_js 可存储多个cookie 用于携带请求头
    // isCollapse: Cookie.get("isCollapse") || false
  },
  getters: {
    isCollapse: state => state.isCollapse
  },
  mutations: {
    SET_COLLAPSE(state) {
      state.isCollapse = !state.isCollapse;
      //html5本地存储
      sessionStorage.setItem("isCollapse", JSON.stringify(state.isCollapse));
      // Cookie.set("isCollapse", JSON.stringify(state.isCollapse));
    }
  },
  actions: {}
});
