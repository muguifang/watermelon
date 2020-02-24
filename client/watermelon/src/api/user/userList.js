import intercept from "@/utils/intercepter.js";
export function getUsers(data) {
  return intercept.request({
    method: "get",
    url: "/user/getAllUser",
    params: { username: data }
  });
}
