<template >

  <v-app-bar app  color="#FFF5E4" flat id="appbar">
    <a href="/"><v-img contain src="@/assets/wimh_logo.png" max-width="238" max-height="60"></v-img></a>

    <v-container class="py-0 fill-height">
      <v-btn v-for="link in links" :key="link" text link :to="'/' + link">
        {{ link }}
      </v-btn>
    </v-container>   
    
    <!-- 로그인 성공 -->
    <v-responsive right>
      <span v-if="userInfo">
          {{userInfo.name}}님 환영합니다
          <v-menu bottom offset-y min-width="100px">   
            <template v-slot:activator="{ on }" >
              <v-btn small icon v-on="on" >

                <v-avatar color="#FCD1D1" size="28">
                  <v-icon color="#F7F7F7" size="18">
                    mdi-account
                  </v-icon>
                </v-avatar>
              </v-btn>
            </template>
            <v-list>
              <v-list-item @click="movoToUserInfo">
                <font-awesome-icon icon="fa-solid fa-user-gear" style="margin-right:10px"/>회원 정보
              </v-list-item>
              <v-list-item @click="logout">
                <font-awesome-icon icon="fa-solid fa-arrow-right-from-bracket" style="margin-right:10px"/> 로그아웃
              </v-list-item>
            </v-list>
           
          </v-menu>
      </span>

      <span  v-else>
        <UserLogin></UserLogin>
        <v-btn id="btn" @click="join" class="white--text" rounded style="margin-left:10px" color="#F8C4B4">JOIN US</v-btn>
      </span>

      <!-- <v-btn link to="login">login</v-btn> -->

    </v-responsive>
    <!--로그인 안됨-->

  </v-app-bar>
</template>

<script>
import { mapState, mapGetters, mapActions } from "vuex";
import UserLogin from "@/components/User/UserLogin.vue";

import { createNamespacedHelpers } from "vuex";
const interestHelper = createNamespacedHelpers("interestStore");

const userStore = "userStore";


export default {
  data: () => ({
    links: ["apart", "notice", "qna"],
  }),
  components: {
    UserLogin,
  },
    computed: {
    ...mapState(userStore, ["isLogin", "userInfo"]),
    ...mapGetters(["checkUserInfo"]),
  },
  methods:{
    ...mapActions(userStore, ["userLogout"]),
    ...interestHelper.mapActions(["removeAllInterestInfo"]),
    join(){
      this.$router.push('/join');
    },
    movoToUserInfo(){
      this.$router.push(`/user/info`);
    },
    logout(){
      this.userLogout(this.userInfo.userid);
      sessionStorage.removeItem("access-token"); //저장된 토큰 없애기
      sessionStorage.removeItem("refresh-token"); //저장된 토큰 없애기
      if (this.$route.path != "/") this.$router.push('/');

    }
  }  
};
</script>

<style scoped>
#appbar{
  max-height: 150px;
}

#btn:active {
  box-shadow: 0 3px 0 #C9CCD5;
  top: 3px;
}

</style>
