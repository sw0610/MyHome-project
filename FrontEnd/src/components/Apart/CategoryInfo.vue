<template>
  <div id="category">
    <v-select
      :items="constant"
      v-model="select_category"
      dense
      outlined
      item-text="label"
      item-value="value"
    ></v-select>
    <v-btn
      elevation="2"
      class="text-white"
      style="width: 130px; margin-top: -25px; background-color:#F3C583"
      @click="search_local_target"
      :disabled="select_marker_data == undefined"
      >상권검색</v-btn
    >
  </div>
</template>

<script>
import kakaoHttp from '@/util/kakao-http';
export default {
  created() {
    this.select_category = this.constant[0].value;
  },
  props: {
    select_marker_data: Object,
  },
  data() {
    return {
      select_category: "",
      constant: [
        { value: "MT1 ", label: "대형마트" },
        { value: "CS2 ", label: "편의점" },
        { value: "PS3 ", label: "어린이집, 유치원" },
        { value: "SC4 ", label: "학교" },
        { value: "AC5 ", label: "학원" },
        { value: "PK6 ", label: "주차장" },
        { value: "OL7 ", label: "주유소, 충전소" },
        { value: "SW8 ", label: "지하철역" },
        { value: "BK9 ", label: "은행" },
        { value: "CT1 ", label: "문화시설" },
        { value: "AG2 ", label: "중개업소" },
        { value: "PO3 ", label: "공공기관" },
        { value: "AT4 ", label: "관광명소" },
        { value: "AD5 ", label: "숙박" },
        { value: "FD6 ", label: "음식점" },
        { value: "CE7 ", label: "카페" },
        { value: "HP8 ", label: "병원" },
        { value: "PM9 ", label: "약국" },
      ],
    };
  },//category.json?category_group_code=CS2&page=1&size=15&sort=distance&x=127.11041421774634&radius=100&y=37.395636935312076
  methods: {
    search_local_target() {
        // alert(`category.json?category_group_code=${this.select_category}&sort=distance&x=${this.select_marker_data.lng}&y=${this.select_marker_data.lat}&radius=100`+
        // `category.json?category_group_code=${this.select_category}&page=1&size=15&sort=distance&x=127.11041421774634&radius=100&y=37.395636935312076`);
        kakaoHttp.get(
            `/category.json?category_group_code=${this.select_category}&page=1&size=15&sort=distance&x=${this.select_marker_data.lng}&radius=300&y=${this.select_marker_data.lat}`
        )
        .then((res) => {
            this.$emit("res_local_data",res.data.documents);
        })
        .catch((err) => {console.log(err)});
    },
  },
};
</script>

<style>
#category {
  position: absolute;
  top: 0;
  left: 410px;
  bottom: 0;
  width: 150px;
  height: 100px;
  margin: 10px 0 30px 10px;
  padding: 10px;
  background: rgb(255, 255, 255);
  z-index: 1;
  font-size: 12px;
  border-radius: 10px;
}
</style>