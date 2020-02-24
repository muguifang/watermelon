import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import ElementUI from "element-ui";
import moment from "moment";
import "element-ui/lib/theme-chalk/index.css";
//自定义全局组件
import "./icons/index.js";

Vue.use(ElementUI);
Vue.config.productionTip = false;

Vue.filter("dateFmt", (input, formatString = "YYYY-MM-DD") => {
  //es5函数参数设置默认值
  //const lastFormatString = formatString || ''
  // moment(input) 把时间字符串转成时间对象
  // format(formatString) 把时间对象，按照指定格式，格式化成符合条件的字符串
  return moment(input).format(formatString);
});

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount("#app");
