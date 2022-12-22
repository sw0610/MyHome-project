import axios from "axios";

export default axios.create({
  baseURL: 'https://dapi.kakao.com/v2/local/search',
  headers: { Authorization: "KakaoAK "+process.env.VUE_APP_kakaomapRestkey },
});
