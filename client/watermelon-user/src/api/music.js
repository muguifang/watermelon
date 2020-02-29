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
