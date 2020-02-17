import intercept from "@/utils/intercepter.js";
export function login(data) {
  return intercept.request({
    method: "post",
    url: "/common/login",
    data: data
  });
}
