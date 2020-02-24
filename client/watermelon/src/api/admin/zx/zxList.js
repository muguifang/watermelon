import intercept from "@/utils/intercepter.js";
export function getZxList(data) {
  return intercept.request({
    method: "post",
    url: "/information/getInfo",
    data: data
  });
}
