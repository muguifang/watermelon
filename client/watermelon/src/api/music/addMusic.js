import intercept from "@/utils/intercepter.js";
export function addMusic(data) {
  return intercept.request({
    method: "post",
    url: "/music/addMusic",
    data: data
  });
}
