<template>
  <div id="menu_wrap" class="bg_white">
    <div sticky>
      <div style="margin-bottom: 20px; z-index: 1">
        <v-tabs v-model="tab_idx" color="#FFADBC" >
          <v-tab>검색</v-tab>
          <v-tab>검색필터</v-tab>
          <v-tab>관심지역 관리</v-tab>
        </v-tabs>
      </div>

      <div class="option">
        <v-row v-if="tab_idx == 0">
          <v-col><v-select :items="sido" v-model="select_sido" label="시도" dense outlined></v-select></v-col>
          <v-col><v-select :items="gugun" v-model="select_gugun" label="구군" dense outlined></v-select></v-col>
          <v-select
            v-model="select_dong"
            :items="dong"
            chips
            label="동"
            multiple
            outlined
            item-text="dongName"
            item-value="dongCode"
          ></v-select>
          <v-btn class="text-white" style="background-color:#A4BE7B" @click="searchDong">검색</v-btn>
        </v-row>
        <v-row v-if="tab_idx == 1">
          <div>
            <p class="font-weight-black">아파트 이름으로 필터</p>
            <v-text-field v-model="target_apart_name" solo label="Solo" clearable></v-text-field>
          </div>
        </v-row>
        <v-row v-if="tab_idx == 2">
          <InterestInfo />
        </v-row>
      </div>
    </div>

    <hr />
    <h1 style="font-size: 30px; text-align: center; margin-top:10px">검색 결과</h1>
    <v-list>
      <v-list-item-group v-model="select_apart">
        <div v-for="(item, i) in apart_data" :key="i">
          <span v-if="i == select_marker" style="position: absolute; left: 70%; z-index: 5">
            <v-btn v-if="getInterest.length > i" class="mx-2" fab dark small color="black" style="margin-top: 25%" @click="deleteInterestArea(item)">
              <v-icon dark> mdi-heart </v-icon>
            </v-btn>
            <v-btn v-else class="mx-2" fab dark small color="pink" style="margin-top: 25%" @click="addInterestArea(item)">
              <v-icon dark> mdi-heart </v-icon>
            </v-btn>
          </span>
          <span v-if="apartFullName(item).includes(target_apart_name)">
            <ApartCard :apart_data="item" :interest_target="getInterest.length > i" />
          </span>
        </div>
      </v-list-item-group>
    </v-list>
  </div>
</template>

<script>
import ApartCard from "@/components/Apart/Unit/ApartCard.vue";
import InterestInfo from "@/components/Apart/Unit/InterestInfo.vue";

import { createNamespacedHelpers } from "vuex";
const interestHelper = createNamespacedHelpers("interestStore");

import http from "@/util/http"
export default {
  components: {
    ApartCard,
    InterestInfo,
  },
  created() {
    http
      .get("/area")
      .then((res) => {
        this.sido = res.data;
      })
      .catch((error) => console.log(error));
      this.getInterestInfo();
  },
  props: {
    select_marker: Number,
  },
  data() {
    return {
      tab_idx: 0,
      select_sido: "",
      select_gugun: "",
      select_dong: [],
      sido: [],
      gugun: [],
      dong: [],
      response_apart_data:[],
      select_search_date: "",
      target_apart_name: "",
      select_apart: "",
    };
  },
  computed: {
    ...interestHelper.mapGetters(["getInterest"]),
    apart_data(){
      let tmp=[];
      for(let i = 0; i < this.getInterest.length ; i++){
        tmp.push(this.getInterest[i].aptCode);
      }
      return [...this.getInterest, ...this.response_apart_data.filter(x => !tmp.includes(x.aptCode))];
    }
  },
  methods: {
    ...interestHelper.mapActions(["addInterestInfo","getInterestInfo","deleteInterestInfo"]),
    apartFullName(apart) {
      return `${apart.roadName} ${apart.dong} ${apart.apartmentName}`;
    },
    searchDong() {
      let formdata = new FormData();
      formdata.append("dongList", this.select_dong);
      http({
        method: "post",
        url: "/house/search/dongList",
        data: formdata,
      })
        .then((response) => {
          this.response_apart_data = response.data;
        })
        .catch(function (error) {
          console.log(error);
        });
    },
    addInterestArea(apart){
      console.log("추가");
      this.addInterestInfo(apart);
    },
    deleteInterestArea(apart){
      console.log("삭제");
      this.deleteInterestInfo(apart);
    },
  },
  watch: {
    select_marker(select_marker_num) {
      this.select_apart = select_marker_num;
    },
    select_sido(new_sido) {
      console.log(new_sido);
      http.get("/area/" + new_sido).then((res) => {
        this.gugun = res.data;
        this.select_gugun = this.gugun[1];
      });
    },
    select_gugun(new_gugun) {
      this.select_dong = [];
      http.get("/area/" + this.select_sido + "/" + new_gugun).then((res) => {
        this.dong = res.data;
      });
    },
    select_apart(new_apart_id) {
      this.$emit("selectListItem", new_apart_id);
    },
    apart_data(new_data) {
      let lat_lng = [];
      for (let i = 0; i < new_data.length; i++) {
        lat_lng.push({
          lat: new_data[i].lat,
          lng: new_data[i].lng,
        });
      }
      this.$emit("changeApartList", { lat_lng: lat_lng, apart_data: new_data });
    },
  },
};
</script>

<style scoped>
#menu_wrap {
  position: absolute;
  top: 0;
  left: 0;
  bottom: 0;
  width: 400px;
  margin: 10px 0 30px 10px;
  padding: 5px;
  overflow-y: auto;
  background: rgb(255, 255, 255);
  z-index: 1;
  font-size: 12px;
  border-radius: 10px;
}
</style>
