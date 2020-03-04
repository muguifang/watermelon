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
//查询用户信息
export function getUserInfo(data) {
  return intercept.request({
    method: "post",
    url: "/user/getUserInfo",
    data: data
  });
}
//修改用户信息
export function updateUserInfo(data) {
  return intercept.request({
    method: "put",
    url: "/user/updateUser",
    data: data
  });
}
