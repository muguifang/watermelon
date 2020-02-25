import intercept from "@/utils/intercepter.js";
export function getAllMusicByPlay(data) {
  return intercept.request({
    method: "post",
    url: "/music/getAllMusicByPlay",
    data: data
  });
}
