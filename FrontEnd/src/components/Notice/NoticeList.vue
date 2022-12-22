<template>
  <div>
      <v-row>
                <v-col cols="10">
                    <div class="text-left" style="max-width:430px">

                        <v-text-field placeholder="검색" v-model="keyword" @keyup.enter="search" color="#59C1BD">
                                <font-awesome-icon slot="append" icon="fa-solid fa-magnifying-glass" @click="search"/> 
                            </v-text-field>


                    </div>
                </v-col>
                <v-col>

                <div class="text-right">
                    
                        <v-btn id="writebtn" @click="writeNotice" color="#6D8B74" rounded class="text-white">글 작성</v-btn>
                        
                </div>
                </v-col>
            </v-row>
      <v-simple-table style="background-color:#F6F6F6">
        <template v-slot:default>
          <thead>
            <tr>
              <th class="text-left">번호</th>
              <th class="text-left">제목</th>
              <th class="text-left">작성자</th>
              <th class="text-left">작성일</th>
            </tr>
          </thead>
          <!-- <NoticeItem></NoticeItem> -->
          <tbody>
            <tr v-for="item in notice" :key="item.name">
              <td>{{ item.no }}</td>
              <td><router-link :to="`/notice/detail/${item.no}`">{{ item.title }}</router-link></td>
              <td>{{ item.memberid }}</td>
              <td>{{ item.createdate }}</td>
            </tr>
          </tbody>
        </template>
      </v-simple-table>
  </div>
</template>

<script>
import http from "@/util/http-common"
// import NoticeItem from "./NoticeItem.vue"

export default {
    name:"NoticeList",
    // components:{
    //   NoticeItem
    // },
    data(){
        return{
            notice:[],
            keyword:"",
        }
    },
    methods:{
        writeNotice(){
            this.$router.push(`/notice/write`);
        },
        search(){
          http.get(`/notice/search/`+this.keyword)
          .then(({data})=>{
            this.notice=data;
            // this.$router.go(`/notice/search/`+this.keyword);
          });
        }
    },
    created(){
      http.get(`/notice/list`)
          .then(({data})=>{
            this.notice=data;
          });
    },


    
};
</script>

<style scoped>
    #noticetable{
        width: 100%;
    }
    #writebtn:active {
    box-shadow: 0 3px 0 #C9CCD5;
    top: 3px;
  }
</style>
