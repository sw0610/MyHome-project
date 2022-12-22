<template>
    <div>
        <v-container>
            <h2 class="display-6 text-center font-weight-bold" justify="center" style="margin-top:30px">비밀번호 찾기</h2>
            <v-card id="card" shaped class="rounded-card mx-auto mt-5 text-center" elevation="15" max-width="600px">
            <v-container>
                <v-row v-if="flag==false" justify="center">
                    <v-row>
                        <v-col cols="3">
                            아이디
                        </v-col>
                        <v-col>
                            <v-text-field @keyup.enter="getQuestion"
                            v-model="memberid">
                            </v-text-field>
                        </v-col>
                    </v-row>
                    
                    <v-row >
                        <v-col cols="3">
                            이메일
                        </v-col>
                        <v-col>
                            <v-text-field
                            v-model="email" @keyup.enter="getQuestion">
                            </v-text-field>
                        </v-col>
                    </v-row>
               
                    <v-row class="text-center">
                        <v-col justify="center" >
                        <v-btn color="#F8C4B4" rounded class="text-white" @click="getQuestion">다음</v-btn>
                        </v-col>
                    </v-row>
                </v-row>

                <v-row v-if="flag==true&&isCorrect==false">
                    
                    <v-row>
                        <v-col cols="3">
                            아이디
                        </v-col>
                        <v-col >
                            {{memberid}}
                        </v-col>
                    </v-row>
                    
                    <v-row >
                        <v-col cols="3">
                            이메일
                        </v-col>
                        <v-col>
                            {{email}}
                        </v-col>
                    </v-row>
                    <v-row justify="center" class="text-center">
                        <font-awesome-icon icon="fa-solid fa-quote-left" />
                        <v-row justify="center" cols="3" class="font-italic" style="font-size:25px; margin-top:15px; margin-bottom:15px">
                            {{question}}
                        </v-row>
                        <font-awesome-icon icon="fa-solid fa-quote-right"/>
                            <v-row>
                            <v-text-field
                            v-model="answer" @keyup.enter="getPassword">
                            </v-text-field>
                        </v-row>
                    <v-row class="text-center">
                        <v-col justify="center" >
                        <v-btn color="#F8C4B4" rounded class="text-white" @click="getPassword">다음</v-btn>
                        </v-col>
                    </v-row>
                    </v-row>
                </v-row>

                <v-row v-if="isCorrect==true" class="text-center">
                    
                    <v-row class="text-center" justify="center" style="font-size:20px">
                            임시 비밀번호가 발급되었습니다

                    </v-row>
                    <v-row class="text-center font-weight-bold" justify="center">
                            {{tmpPwd}}
                    </v-row>

                    <v-row class="text-center" justify="center" style="font-size:15px; margin-top:10px">
                        비밀번호를 변경하세요
                    </v-row>

                </v-row>

                </v-container>
            </v-card>
        </v-container>
    </div>
</template>

<script>
import http from '@/util/http-common'
export default {
    name:"FindPassword",
    data(){
        return{
            memberid:"",
            email:"",
            question:"",
            answer:"",
            dataAnswer:"",
            tmpPwd:"",
            flag:false,
            isCorrect:false
        }
    },

    methods:{
        getQuestion(){
            console.log(this.memberid);
            console.log(this.email);
            http.post(`/user/findpwd`,{
            memberid:this.memberid,
            email:this.email})
            .then(({data})=>{
                this.question=data.confirm_question;
                this.dataAnswer=data.confirm_ans;
                this.flag=true;
            })
            .catch(()=>{
                    alert('아이디와 이메일을 확인해주세요');
            })
        },
        getPassword(){
            if(this.answer==this.dataAnswer){
                this.tmpPwd=Math.random().toString(36).slice(2);
                http.put(`/user/updatepwd`,{
                    memberid:this.memberid,
                    pwd:this.tmpPwd
                })
                .then(()=>{
                    this.isCorrect=true;

                    console.log(this.tmpPwd);
                })
            }else{
                alert('정답이 아닙니다')
            }

        },
        goChange(){
            this.$router.push('/')
        }
    }
}
</script>

<style scoped>
#card{
    padding:80px; 
    border-radius: 24px;
}
</style>