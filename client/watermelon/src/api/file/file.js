import intercept from "@/utils/intercepter.js";
export function upload(data) {
  return intercept.request({
    method: "post",
    url: "/file/addPhoto",
    data: data
  });
}
export function updateFile(data) {
  return intercept.request({
    method: "put",
    url: "/file/updateFile",
    data: data
  });
}
