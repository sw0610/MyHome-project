<template>
  <v-card id="apartcard" :class="{ 'mx-auto' : true,'pink lighten-3':interest_target}" width="95%"  >
    <v-list-item id="info" two-line>
      <v-list-item-content disabled>
        <v-list-item-title class="text-h6 mb-1"> [{{apart_data.dong}}] {{apart_data.apartmentName}}</v-list-item-title>
        <v-list-item-subtitle >{{apart_data.roadName}}</v-list-item-subtitle>
      </v-list-item-content>

      <v-list-item-avatar id="apart-img" >
        <v-img :src="randomProfile.name" />
      </v-list-item-avatar>
    </v-list-item>
  </v-card>
</template>

<script>
export default {
  props: {
    apart_data: Object,
    interest_target:{
      type:Boolean,
      default:false,
    }
  },
  created(){
      let randomNum = Math.floor(Math.random()*this.imgList.length);
      this.randomProfile = this.imgList[randomNum];
      },
  data(){
    return{
      randomProfile:{
        name:require("../../../assets/house2.jpg"),
      },

      imgList:[
        {name:require("../../../assets/house1.jpg")},
        {name:require("../../../assets/house2.jpg")},
        {name:require("../../../assets/house3.jpg")},
        {name:require("../../../assets/house4.jpg")},
        {name:require("../../../assets/house5.jpg")},
        {name:require("../../../assets/house6.jpg")},
        {name:require("../../../assets/house7.jpg")},
        {name:require("../../../assets/house8.jpg")},
        {name:require("../../../assets/house9.jpg")},
        {name:require("../../../assets/house10.jpg")},
      ]
    }
  },
};
</script>
<style scoped>
#apartcard {
  margin-top: 10px;
  margin-bottom: 10px;
}
#apart-img{
  margin-right: 10px;
}
#info{
  margin-left:10px;
}
</style>
