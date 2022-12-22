<template>
    <div>
        <v-container>
            <v-row justify="center" style="margin-bottom:50px">
            <h2  class="display-6 text-center font-weight-bold" justify="center" style="margin-top:30px">JOIN</h2>
            </v-row>
        <v-row justify="center">
        <v-card shaped class="rounded-card mx-auto mt-5" elevation="15" max-width="600px" style="padding-top:80px; padding-bottom:80px;border-radius: 24px;">
            <v-container>
            <div>
                <v-row justify="center">
                <v-col cols="10">
                   ID
                 <v-text-field
                    :rules="idRules"
                    v-model="memberid">
                </v-text-field>
                </v-col>
                <v-col cols="10">
                   PASSWORD
                 <v-text-field
                    :rules="rules"
                    type="password" 
                    v-model="pwd">
                </v-text-field>
                </v-col>
                <v-col cols="10">
                   PASSWORD CONFIRM
                 <v-text-field
                    :rules="pwdRules"
                    type="password" 
                    v-model="confirmpwd">
                </v-text-field>
                </v-col>
                <v-col cols="10">
                   비밀번호 분실시 질문
                 <v-text-field
                    :rules="rules"
                    v-model="ques">
                </v-text-field>
                </v-col>
                <v-col cols="10">
                   비밀번호 분실시 질문 정답
                 <v-text-field
                    :rules="rules"
                    v-model="ans">
                </v-text-field>
                </v-col>
                <v-col cols="10">
                   NAME
                 <v-text-field
                    :rules="rules"
                    hide-details="auto"
                    v-model="username">
                </v-text-field>
                </v-col>
                <v-col cols="10">
                   E-MAIL
                 <v-text-field
                    :rules="emailRule"
                    hide-details="auto"
                    v-model="email">
                </v-text-field>
                </v-col>
                </v-row>
            </div>
            </v-container>
        </v-card>
        </v-row>
        <div class="text-center" style="margin-top:30px">
            <v-btn id="joinbtn" @click="join" color="#F9D371" rounded class="text-white" >가입하기</v-btn>
        </div>

        </v-container>
    </div>
</template>

<script>
import http from "@/util/http-common"
export default {
    name:"UserJoin",
    data(){
      return{
        userList: [],
        memberid:"",
        pwd:"",
        confirmpwd:"",
        username:"",
        email:"",
        ques:"",
        ans:"",
        rules: [
          value => !!value || 'Required.',
        ],
        idRules:[
          value => !!value || 'Required.',
          value => this.checkId(value)
        ],
        emailRule:[
            value => {
            const pattern = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
            return pattern.test(value) || 'Invalid e-mail.'
          },
        ],
        pwdRules:[
          value => !!value || 'Required.',
          value => value === this.pwd || '패스워드가 일치하지 않습니다.'
        ],
      }

    },
    methods: {
      join(){
        http
          .post(`/user/join`,{memberid:this.memberid, pwd:this.pwd, email:this.email, name:this.username ,
                              confirm_question:this.ques, confirm_ans:this.ans})
          .then(()=>{
            this.$router.push(`/`);
          })
          .catch(()=>alert('회원가입 실패'))
      },
      checkId(id){
        http.get(`/admin/userlist`)
            .then(({data})=>{
                    this.userList=data;
                  });
                     
        for(var i in this.userList){
          var check = this.userList[i].memberid;
          if(id==check){
            return '이미 사용중인 아이디입니다';
          }
        }
      }

    },

}
</script>

<style>
#joinbtn:active {
  box-shadow: 0 3px 0 #C9CCD5;
  top: 3px;
}
</style>