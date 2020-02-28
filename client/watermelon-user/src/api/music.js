import intercept from "@/utils/intercepter.js";

//获取所有音乐类别
export function getAllType(data) {
  return intercept.request({
    method: "post",
    url: "/music/getTypeByConditions",
    data: data
  });
}
//通过类别获取该类别下所有音乐
export function getMusicByType(data) {
  return intercept.request({
    method: "/music/getAllMusicByType",
    data: data
  });
}
