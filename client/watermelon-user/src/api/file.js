import intercept from "@/utils/intercepter.js";

//获取所有轮播图
export function getAllPhont() {
  return intercept.request({
    method: "get",
    url: "/file/getAllPhoto"
  });
}
