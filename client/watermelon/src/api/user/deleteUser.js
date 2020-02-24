import intercept from "@/utils/intercepter.js";
export function deleteUser(data) {
  return intercept.request({
    method: "delete",
    url: "/user/deleteUser",
    data: data
  });
}
