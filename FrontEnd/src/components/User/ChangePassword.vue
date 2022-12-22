<template>
      <div>
        <v-container>
            <v-row justify="center" style="margin-bottom:50px">
            <h2  class="display-6 text-center font-weight-bold" justify="center" style="margin-top:30px">비밀번호 변경</h2>
            </v-row>
        <v-row justify="center">
        <v-card shaped class="rounded-card mx-auto mt-5" elevation="15" max-width="600px" style="padding-top:80px; padding-bottom:80px;border-radius: 24px;">
            <v-container>
            <div>
                <v-row justify="center">
                <v-col cols="10">
                   현재 비밀번호
                 <v-text-field
                    :rules="rules"
                    type="password" 
                    v-model="nowPwd">
                </v-text-field>
                </v-col>
                <v-col cols="10">
                   변경할 비밀번호
                 <v-text-field
                    :rules="rules"
                    type="password" 
                    v-model="pwd">
                </v-text-field>
                </v-col>
                <v-col cols="10">
                   비밀번호 확인
                 <v-text-field
                    :rules="pwdRules"
                    type="password" 
                    v-model="confirmpwd">
                </v-text-field>
                </v-col>
                </v-row>

            </div>
            </v-container>
        </v-card>
        </v-row>
        <div class="text-center" style="margin-top:30px">
            <v-btn @click="changePwd" color="#F9D371" rounded class="text-white">변경 완료</v-btn>
        </div>

        </v-container>
    </div>
</template>

<script>
import { mapState } from "vuex";
import http from "@/util/http-common"

const userStore="userStore";

export default {
    name:"ChangePassword",
    computed: {
        ...mapState(userStore, ["userInfo"]),
    },
    data(){
        return{
            nowPwd:"",
            pwd:"",
            confirmpwd:"",
            rules: [
            value => !!value || 'Required.',
            ],

            pwdRules:[
            value => !!value || 'Required.',
            value => value === this.pwd || '패스워드가 일치하지 않습니다.'
            ],
        }
    },
    methods:{
        changePwd(){
            if(this.nowPwd!=this.userInfo.pwd){
                alert('비밀번호가 일치하지 않습니다');
            }else{
                http.put(`/user/updatepwd`,{
                    memberid:this.userInfo.memberid,
                    pwd:this.pwd
                })
                .then(()=>{
                    alert('변경되었습니다');
                    this.$router.push('/');
                })
            }


            }

        
    }
}
</script>

<style>

</style>