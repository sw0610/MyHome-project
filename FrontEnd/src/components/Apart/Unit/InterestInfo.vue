<template>
  <div>
    <h1 style="font-size: 40px;text-align: center;">관심지역</h1>
    <v-list>
      <v-list-item-group>
        <div v-for="(item, i) in getInterest" :key="i">
          <span>
            <ApartCard :apart_data="item"/>
          </span>
        </div>
      </v-list-item-group>
    </v-list>
    </div>
</template>

<script>
import ApartCard from "@/components/Apart/Unit/ApartCard.vue";
import { createNamespacedHelpers } from "vuex";
const interestHelper = createNamespacedHelpers("interestStore");

export default {
  components:{
    ApartCard,
  },
  computed:{
    ...interestHelper.mapGetters(["getInterest"]),
  },
  methods:{
    ...interestHelper.mapActions(["getInterestInfo"]),
  },
  created(){
    this.getInterestInfo();
  }
}
</script>

<style>

</style>