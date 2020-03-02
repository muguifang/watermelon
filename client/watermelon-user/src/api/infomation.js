import intercept from "@/utils/intercepter.js";

//用户登录
export function getAllInfomation(data) {
  return intercept.request({
    method: "post",
    url: "/information/getInfo",
    data: data
  });
}
