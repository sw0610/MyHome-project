<template>
    <div id="notice">    

        <v-container>
            <h2 class="display-6 text-left font-weight-bold" style="margin-top:30px">공지사항</h2>

            <NoticeList></NoticeList>
        </v-container>
    </div>

</template>

<script>
import NoticeList from '@/components/Notice/NoticeList.vue'
import { mapState } from "vuex";

const userStore="userStore";
export default {
    name:"NoticeView",
    components:{
        NoticeList
    },
    data(){
        return{

        }
    },
    computed: {
        ...mapState(userStore, ["userInfo"]),
    },
    methods:{

        // search(){
        //     this.$router.push(`/notice/search/${this.keyword}`)
        // } 
    
    }
}
</script>

<style scope>
/* #notice{
    margin-bottom: 30px;
} */
</style>