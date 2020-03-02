//cookie存储
export function saveCookie(cookieName, cookieValue, cookieDates) {
  var d = new Date();
  d.setDate(d.getDate() + cookieDates);
  document.cookie =
    cookieName + "=" + cookieValue + ";expires=" + d.toGMTString();
}
//获取cookie值
export function getCookie(cookieName) {
  var cookieStr = unescape(document.cookie);
  var arr = cookieStr.split("; ");
  var cookieValue = "";
  for (var i = 0; i < arr.length; i++) {
    var temp = arr[i].split("=");
    if (temp[0] == cookieName) {
      cookieValue = temp[1];
      break;
    }
  }
  return cookieValue;
}
//删除cookie
export function removeCookie(cookieName) {
  document.cookie = encodeURIComponent(cookieName) + "=; expires=" + new Date();
}
