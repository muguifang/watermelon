import intercept from "@/utils/intercepter.js";
export function deleteLbt(data) {
  return intercept.request({
    method: "delete",
    url: "/file/deletePhoto",
    data: data
  });
}
