import intercept from "@/util/intercepter.js";

export function getAllMusic() {
  return intercept.request({
    method: "get",
    url: "/file/getHeadPhoto"
  });
}
