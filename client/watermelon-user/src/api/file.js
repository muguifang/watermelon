import intercept from "@/utils/intercepter.js";

//获取所有轮播图
export function getAllPhont() {
  return intercept.request({
    method: "get",
    url: "/file/getAllPhoto"
  });
}
//修改文件
export function updateFile(data) {
  return intercept.request({
    method: "put",
    url: "/file/updateFile",
    data: data
  });
}
//删除指定路径下的文件
export function deleteServerFile(data) {
  return intercept.request({
    method: "delete",
    url: "/file/deleteServerFile",
    params: { fileName: data }
  });
}
