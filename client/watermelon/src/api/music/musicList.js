import intercept from "@/utils/intercepter.js";
export function getMusic(data) {
  return intercept.request({
    method: "post",
    url: "/music/getMusicByConditions",
    data: data
  });
}
