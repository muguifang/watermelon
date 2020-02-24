import intercept from "@/utils/intercepter.js";
export function updateZx(data) {
  return intercept.request({
    method: "put",
    url: "/information/updateInfo",
    data: data
  });
}
