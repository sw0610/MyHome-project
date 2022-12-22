<template>
    <div id="noticewirte">
        <v-container>
            <h3 class="display-6 text-center font-weight-bold" justify="center" style="margin-top:30px">공지사항 수정</h3>
            <v-card id="card" class="pa-4 text-no-wrap rounded-lg" elevation="15">
                <span>제목 <b-form-input v-model="title" ></b-form-input></span>
                <hr>
                작성자
                <hr>
                내용 <b-form-textarea rows="15" no-resize v-model="content"></b-form-textarea>
            </v-card>
            <div style="margin-right:50px;">
                <v-btn class="float-right" @click="modifyNotice">수정 완료</v-btn>
            </div>
        </v-container>
    </div>
</template>

<script>
import http from "@/util/http-common"
export default {
    name:"NoticeModify",
    data(){
        return{
            detail:Object,
            title:"",
            content:""
        }
    },
    created(){
        http.get(`/notice/article/${this.$route.params.no}`)
            .then(({data})=>{
                this.detail=data;
                this.title=data.title;
                this.content=data.content;
                console.log(this.title);
            })
    },
    methods:{
        modifyNotice(){
            http.put(`/notice/modify`,{
                no:this.$route.params.no,
                title:this.title,
                content:this.content,

            })
            .then(()=>this.$router.push(`/notice/detail/${this.$route.params.no}`))
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