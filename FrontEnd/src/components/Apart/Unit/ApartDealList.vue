<template>
  <div>
    <LineCompo :chart_data_prop="recent_deal_data"/>
    <v-card v-for="(item, i) in deal_data" :key="i" style="margin-top:10px;" elevation="24">
      <v-list-item three-line>
      <v-list-item-content>
        <v-list-item-title>
          식별번호 : {{ item.no }}
          <span style="position: absolute; right: 0">
            거래시기 : {{`${item.dealYear}년 ${item.dealMonth}월 ${item.dealDay}일`}}
          </span>
        </v-list-item-title>
        <v-col>
          <v-list-item-subtitle> 
            {{item.floor}}층
            <v-spacer></v-spacer>
            면적:{{item.area }}㎡
           </v-list-item-subtitle>
        </v-col>
        <v-col>
          
        <v-list-item-subtitle> 거래가격 : {{item.dealAmount}}(만) </v-list-item-subtitle>
        </v-col>
      </v-list-item-content>
    </v-list-item>
    </v-card>
  </div>
</template>

<script>
import LineCompo from "@/components/Apart/Unit/Line.vue";
export default {
  components:{
    LineCompo,
  },
  props: {
    deal_data: Array,
  },
  computed:{
    recent_deal_data(){
      let tmp_label = [];
      let tmp_data = [];
      for(let i = 0 ; i < Math.min(this.deal_data.length,10);i++){
        let item = this.deal_data[i];
        tmp_label.push(`${item.dealYear}년 ${item.dealMonth}월 ${item.dealDay}일`);
        tmp_data.push(parseInt(item.dealAmount.replace(",", "")));
      }
      return {
        labels: tmp_label.reverse(),
        datasets: [
          {
            label: "최근 10개 거래 추이",
            backgroundColor: "#f87979",
            data: tmp_data.reverse(),
          },
        ],
      };
    },
  }

};
</script>
