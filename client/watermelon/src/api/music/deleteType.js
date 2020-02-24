import intercept from "@/utils/intercepter.js";
export function deleteType(data) {
  return intercept.request({
    method: "delete",
    url: "/music/deleteType",
    data: data
  });
}
