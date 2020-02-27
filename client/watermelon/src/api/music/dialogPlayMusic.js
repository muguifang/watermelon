import intercept from "@/utils/intercepter.js";
export function returnBase64(data) {
  return intercept.request({
    method: "post",
    url: "/music/returnBase64",
    data: data
  });
}
