import intercept from "@/utils/intercepter.js";
export function updateType(data) {
  return intercept.request({
    method: "put",
    url: "/music/updateType",
    data: data
  });
}
