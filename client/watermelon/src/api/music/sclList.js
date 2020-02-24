import intercept from "@/utils/intercepter.js";
export function getAllMusicByCollect(data) {
  return intercept.request({
    method: "get",
    url: "/music/getAllMusicByCollect",
    data: data
  });
}
