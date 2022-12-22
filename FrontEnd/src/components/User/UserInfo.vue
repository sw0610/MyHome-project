<template>
  <div>
    <v-container>
      <v-row justify="center" style="margin-bottom: 50px">
        <h2 class="display-6 text-center font-weight-bold" justify="center" style="margin-top:30px">회원 정보</h2>
      </v-row>
      <v-row justify="center">
        <v-card
          shaped
          class="rounded-card mx-auto mt-5"
          elevation="15"
          max-width="600px"
          style="padding-top: 80px; padding-bottom: 80px; border-radius: 24px"
        >
          <v-container>
            <div v-if="modify==false">
                <v-row>
                  <v-col cols="1"></v-col>
                  <v-col cols="2" class="font-weight-bold">ID</v-col> 
                  <v-col>{{userInfo.memberid}}</v-col>
                </v-row>
                <v-row>
                  <v-col cols="1"></v-col>
                  <v-col cols="2" class="font-weight-bold">NAME</v-col> 
                  <v-col>{{userInfo.name}}</v-col>
                </v-row>
                <v-row>
                  <v-col cols="1"></v-col>
                  <v-col cols="2" class="font-weight-bold">E-MAIL</v-col> 
                  <v-col>{{userInfo.email}}</v-col>
                </v-row>
            </div>

            <div v-else>
              <v-row justify="center">
                <v-col cols="10">
                  ID <v-text-field
                    v-model="userInfo.memberid"
                    >
                </v-text-field>
                </v-col>
                <v-col cols="10">
                  NAME <v-text-field
                    v-model="userInfo.name">
                </v-text-field>
                </v-col>
                <v-col cols="10">
                  E-MAIL <v-text-field
                    v-model="userInfo.email">
                </v-text-field>
                </v-col>
              </v-row>
            </div>
          </v-container>
        </v-card>
      </v-row>
              <div class="text-center" style="margin-top:30px">
            <v-btn v-if="modify==false" @click="getModify" color="#F9D371" rounded class="text-white" style="margin-right:10px">정보 수정</v-btn>
            <v-btn v-if="modify==false" @click="goChange" color="#FFAFAF" rounded class="text-white">비밀번호 변경</v-btn>

            <v-btn v-else @click="modifyInfo" color="#F9D371" rounded class="text-white">수정 완료</v-btn>
        </div>
    </v-container>
  </div>
</template>

<script>
import http from "@/util/http-common"
import { mapState } from "vuex";


const userStore="userStore";

export default {
    name: "UserInfo",
    computed: {
        ...mapState(userStore, ["userInfo"]),
    },
    data(){
      return{
        memberid:"",
        name:"",
        email:"",
        modify:false,
      }
    },
    methods:{
      modifyInfo(){
        this.modify=!this.modify;
        if(this.modify==false){
          http.put(`/user/modify`,{
            memberid:this.memberid,
            name:this.name,
            email:this.email,
            pwd:this.userInfo.pwd
          })
        }
      },
      goChange(){
        this.$router.push('/user/changepassword')
      },
      async getModify(){
        this.modify=!this.modify;
        console.log(this.modify);
          console.log(this.userInfo.memberid)
          await http.get(`/user/info/`+this.userInfo.memberid)
              .then(({data})=>{
                console.log(data);
                this.memberid=data.memberid;
                this.name=data.name;
                this.email=data.email;

              })
        console.log(this.name);
      }
    }
};
</script>

<style></style>
