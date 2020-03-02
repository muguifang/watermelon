import intercept from "@/utils/intercepter.js";

//获取音乐评论
export function getComments(data) {
  return intercept.request({
    method: "get",
    url: "/comment/getComment",
    params: { id: data }
  });
}
//获取音乐评论
export function insertComment(data) {
  return intercept.request({
    method: "post",
    url: "/comment/insertComment",
    data: data
  });
}
