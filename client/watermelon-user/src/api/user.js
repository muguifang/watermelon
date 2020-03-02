import intercept from "@/utils/intercepter.js";

//用户登录
export function login(data) {
  return intercept.request({
    method: "post",
    url: "/user/login",
    data: data
  });
}
//用户注册
export function register(data) {
  return intercept.request({
    method: "post",
    url: "/user/register",
    data: data
  });
}
//网站建议
export function addAdvice(data) {
  return intercept.request({
    method: "post",
    url: "/advice/addAdvice",
    data: data
  });
}
