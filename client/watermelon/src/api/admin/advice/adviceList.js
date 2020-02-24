import intercept from "@/utils/intercepter.js";
export function getAdvice(data) {
  return intercept.request({
    method: "post",
    url: "/advice/getAdvice",
    data: data
  });
}
