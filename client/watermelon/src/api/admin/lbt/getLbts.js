import intercept from "@/utils/intercepter.js";
export function getAllPhoto(data) {
  return intercept.request({
    method: "post",
    url: "/file/getAllPhoto",
    data: data
  });
}
