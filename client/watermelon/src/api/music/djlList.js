import intercept from "@/utils/intercepter.js";
export function getAllMusicByPlay(data) {
  return intercept.request({
    method: "get",
    url: "/music/getAllMusicByPlay",
    params: { name: data }
  });
}
