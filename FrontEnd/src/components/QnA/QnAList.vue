<template>
  <div>
    <h2 class="display-6 text-left font-weight-bold" style="margin-top:30px">QnA</h2>
    <div class="text-right" style="margin-bottom:10px;">
      <v-btn id="writebtn" link :to="'/qna/write'" color="#6D8B74" rounded class="text-white">글 작성</v-btn>
    </div>
  <v-list style="background-color:#F6F6F6">
    <v-list-item>
      <v-list-item-icon class = "text-center"> 번호 </v-list-item-icon>
      <v-list-item-content>
        <v-list-item-title class = "text-left">제목</v-list-item-title>
      </v-list-item-content>
      <v-col cols="1" class = "text-center">답변 완료</v-col>
      <v-col cols="1" class = "text-center">작성자</v-col>
      <v-col cols="1" class = "text-center">조회수</v-col>
      <v-col cols="2" class = "text-center">작성 시간</v-col>
    </v-list-item>
    <v-list-item-group v-model="select_qna">
      <QnAItem v-for="(item, i) in items" :key="i" :item="item"/>
    </v-list-item-group>
  </v-list>
  </div>
</template>

<script>
import http from "@/util/http";
import QnAItem from "./Unit/QnAListItem.vue";
export default {
  components: {
    QnAItem,
  },
  data: () => ({
    select_qna: "",
    items: [],
  }),
  created() {
    console.log(this.$store.state.user);
    http.get("/qna/question").then(({ data }) => (this.items = data));
  },
};
</script>

<style scoped>
    #writebtn:active {
      box-shadow: 0 3px 0 #C9CCD5;
      top: 3px;
    }
</style>
