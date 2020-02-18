import axios from "axios";
import router from "@/router";
import { Message } from "element-ui";
const BASEURL = process.env.NODE_ENV === "production" ? "" : "/api";
//创建axios 赋给变量intercept
const intercept = axios.create({
  baseURL: BASEURL,
  timeout: 10000
});
// 添加请求拦截器
intercept.interceptors.request.use(
  function(config) {
    return config;
  },
  function(error) {
    // 对请求错误做些什么
    return Promise.reject(error);
  }
);

// 添加响应拦截器
intercept.interceptors.response.use(
  function(response) {
    const data = response.data;
    if (data.code === 500) {
      Message.error("系统异常！");
    }
    return response;
  },
  function(error) {
    // 对响应错误做点什么
    console.log(error);
  }
);
export default intercept;
