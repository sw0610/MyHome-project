<template>
    <div>
        <v-container>
            <v-card id="detail" class="pa-4 text-no-wrap rounded-lg" elevation="15">
                    <div>
                        <div style="margin:50px 20px;"  class="text-h6">
                        <font-awesome-icon icon="fa-solid fa-bookmark" style="margin-right:10px"/>{{detail.title}}
                        </div>
                        <hr>
                        <div class="text-right">
                        <span id="memberid"><font-awesome-icon icon="fa-solid fa-user" style="margin-right:10px"/>{{detail.memberid}}</span><span> | </span><span id="date">{{detail.createdate}}</span>

                        </div>
                        <hr>
                        <div id="content">
                            {{detail.content}}
                        </div>

                    </div>
            </v-card>
            <div v-if="userInfo==null"></div>
            <div v-else>
                <div class="text-right" v-if="userInfo.auth=='admin'"><span id="modifyBtn"><v-btn @click="modifyBtn" class="text-white" rounded color="#F3C583">수정</v-btn></span><span id="deleteBtn"><v-btn @click="deleteBtn" class="text-white" rounded color="#E99497">삭제</v-btn></span></div>
            </div>
        </v-container>
    </div>
</template>

<script>
import http from '@/util/http-common'
import { mapState } from "vuex";

const userStore="userStore";
export default {
    name:"NoticeDetail",
    data(){
        return{
            // title:"",
            // createdate:"",
            // content:"",
            // memberid:""
            detail:Object,
    
        }
    },
    computed: {
        ...mapState(userStore, ["userInfo"]),
    },
    created(){
        http.get(`/notice/article/`+this.$route.params.no)
            .then(({data})=>{
                this.detail=data;
            });

    },
    methods:{
        modifyBtn(){
        
            this.$router.push(`/notice/modify/${this.$route.params.no}`);
        },
        deleteBtn(){
            http.delete(`/notice/delete/${this.$route.params.no}`)
                .then(()=>this.$router.push(`/notice`));

        }
    }
}
</script>

<style scoped>
    #detail{
        margin: 50px;
        padding:30px
    }
    #memberid{
        margin-right: 10px;
        color: gray;
    }
    #date{
        margin-left: 10px;
        margin-right:10px;
        color: gray;
    }
    #content{
        height: 500px;
        padding: 50px 30px;
    }

    #deleteBtn{
        margin-right: 50px;
        margin-left: 15px;
    }
</style>