import intercept from "@/utils/intercepter.js";
export function deleteMusic(data) {
  return intercept.request({
    method: "delete",
    url: "/music/deleteMusic",
    data: data
  });
}
