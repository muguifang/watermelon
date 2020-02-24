import intercept from "@/utils/intercepter.js";
export function getAllPhoto() {
  return intercept.request({
    method: "get",
    url: "/file/getAllPhoto"
  });
}
