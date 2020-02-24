import intercept from "@/utils/intercepter.js";
export function addType(data) {
  return intercept.request({
    method: "post",
    url: "/music/addType",
    data: data
  });
}
