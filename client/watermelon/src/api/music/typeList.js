import intercept from "@/utils/intercepter.js";
export function getMusicType(data) {
  return intercept.request({
    method: "post",
    url: "/music/getTypeByConditions",
    data: data
  });
}
