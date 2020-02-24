import intercept from "@/utils/intercepter.js";
export function getAdvice(data) {
  return intercept.request({
    method: "get",
    url: "/advice/getAdviceByConditions",
    params: { phone: data }
  });
}
