import intercept from "@/utils/intercepter.js";
export function updateMusic(data) {
  return intercept.request({
    method: "put",
    url: "/music/updateMusic",
    data: data
  });
}
