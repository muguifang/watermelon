import intercept from "@/utils/intercepter.js";

//获取所有音乐类别
export function getAllType(data) {
  return intercept.request({
    method: "post",
    url: "/music/getTypeByConditions",
    data: data
  });
}
//通过type获取所有音乐
export function getMusicByType(data) {
  return intercept.request({
    method: "post",
    url: "/music/getAllMusicByType",
    data: data
  });
}
//初始化页面获取最新的10首音乐
export function getTenMusic() {
  return intercept.request({
    method: "get",
    url: "/music/getTenMusic"
  });
}
//获取播放最多的十首音乐
export function getRecommendMusic() {
  return intercept.request({
    method: "get",
    url: "/music/getRecommendMusic"
  });
}
//通过音乐id获取音乐详情
export function getMusicById(data) {
  return intercept.request({
    method: "get",
    url: "/music/getMusicById",
    params: { id: data }
  });
}
//收藏音乐
export function collectMusic(data) {
  return intercept.request({
    method: "post",
    url: "/music/collectMusic",
    data: data
  });
}
//获取收藏音乐
export function getMyCollect(data) {
  return intercept.request({
    method: "get",
    url: "/music/getMyCollect",
    params: { id: data }
  });
}
//获取收藏音乐
export function cancelCollect(data) {
  return intercept.request({
    method: "delete",
    url: "/music/cancelCollect",
    params: { id: data }
  });
}
//根据音乐名称搜索音乐
export function getMusicByConditions(data) {
  return intercept.request({
    method: "post",
    url: "/music/getMusicByConditions",
    data: data
  });
}
//转换音乐
export function returnBase64(data) {
  return intercept.request({
    method: "post",
    url: "/music/returnBase64",
    data: data
  });
}
