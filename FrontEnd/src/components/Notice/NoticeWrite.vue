<template>
    <div id="noticewirte">
        <v-container>
            <h3 class="display-6 text-center font-weight-bold" justify="center" style="margin-top:30px">공지사항 작성</h3>
            <v-card id="card" class="pa-4 text-no-wrap rounded-lg" elevation="15">
                <span>제목 <b-form-input v-model="title"></b-form-input></span>
                <hr>
                작성자 {{userInfo.memberid}}
                <hr>
                내용 <b-form-textarea rows="15" no-resize v-model="content"></b-form-textarea>

            </v-card>
            <div style="margin-right:50px;">
                <v-btn class="float-right text-white" @click="postNotice" color="#FEBE8C" rounded>게시하기</v-btn>
            </div>
        </v-container>
    </div>
</template>

<script>
import http from "@/util/http-common"
import { mapState } from "vuex";

const userStore="userStore";
export default {
    name:"NoticeWrite",
    
    data(){
        return{
            title:"",
            content:""
        }
    },
    computed: {
        ...mapState(userStore, ["userInfo"]),
    },
    methods:{
        postNotice(){
            if(this.title.length==0){
                alert("제목을 입력하세요");
            }else if(this.content.length==0){
                alert("내용을 입력하세요")
            }else{
                http.post(`/notice/write`,{title:this.title, memberid:this.userInfo.memberid,content:this.content}).then(()=>this.$router.push(`/notice`));
                
            }
        }
    }
}
</script>

<style scoped>
#card{
        margin: 50px;
        padding:30px
}
</style>