import intercept from "@/utils/intercepter.js";
export function getAllMusicByCollect(data) {
  return intercept.request({
    method: "post",
    url: "/music/getColletMusic",
    data: data
  });
}
