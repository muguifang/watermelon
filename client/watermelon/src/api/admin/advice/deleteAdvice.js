import intercept from "@/utils/intercepter.js";
export function deleteAdvice(data) {
  return intercept.request({
    method: "delete",
    url: "/advice/deleteAdvice",
    params: { id: data }
  });
}
