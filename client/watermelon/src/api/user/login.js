import intercept from "@/utils/intercepter.js";
export function login(data) {
  return intercept.request({
    method: "post",
    url: "/user/login",
    data: data
  });
}
export function updatePassword(data) {
  return intercept.request({
    method: "put",
    url: "/user/updatePassword",
    data: data
  });
}
