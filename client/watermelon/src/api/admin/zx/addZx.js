import intercept from "@/utils/intercepter.js";
export function addZx(data) {
  return intercept.request({
    method: "post",
    url: "/information/addInfo",
    data: data
  });
}
