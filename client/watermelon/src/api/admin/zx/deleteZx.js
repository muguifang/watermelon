import intercept from "@/utils/intercepter.js";
export function deleteZx(data) {
  return intercept.request({
    method: "delete",
    url: "/information/deleteInfo",
    data: data
  });
}
