<template>
  <div id="apart_wrap" class="bg_white">
    <v-row>
      <ApartCard :apart_data="apart_data"/>
    </v-row>
    <v-btn id="open-road-view" @click="openRoadView" class="text-white" style="background-color:#A4BE7B">로드뷰 보기!</v-btn>
    <hr/>
    <ApartDealList :deal_data="deal_data"/>
  </div>
</template>

<script>
import ApartCard from "@/components/Apart/Unit/ApartCard.vue";
import ApartDealList from "@/components/Apart/Unit/ApartDealList.vue";

import http from "@/util/http.js";
export default {
  components:{
    ApartCard,
    ApartDealList,
    
  },
  props: {
    apart_data: Object,
  },
  data() {
    return {
      deal_data: [],
    };
  },
  created(){
    http.get("/house/search/housedeal/" + this.getAptCode)
      .then((response) => {
          this.deal_data = response.data;
      })
      .catch(function (error) {
          console.log(error);
      });
  },
  computed: {
    getAptCode() {
      return this.apart_data.aptCode;
    },
  },
  methods:{
    openRoadView(){
      this.$emit('openRoadView',{lat:this.apart_data.lat, lng:this.apart_data.lng});
    }
  },
  watch: {
    getAptCode(new_data) {
      http.get("/house/search/housedeal/" + new_data)
      .then((response) => {
          this.deal_data = response.data;
      })
      .catch(function (error) {
          console.log(error);
      });
    },
  },
};
</script>

<style>
#apart_wrap {
  position: absolute;
  top: 0;
  right: 20px;
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
#open-road-view{
  width: 100%;
}
</style>