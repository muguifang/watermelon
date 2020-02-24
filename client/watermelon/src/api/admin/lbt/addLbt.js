import intercept from "@/utils/intercepter.js";
export function addLbt(data) {
  return intercept.request({
    method: "post",
    url: "/file/addPhoto",
    data: data
  });
}
