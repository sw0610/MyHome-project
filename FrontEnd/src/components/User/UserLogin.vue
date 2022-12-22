<template>
  <v-dialog v-model="dialog" max-width="400px">
    <template v-slot:activator="{ on, attrs }">
      <v-btn
        id="loginbtn"
        rounded
        v-bind="attrs"
        v-on="on"
        color="#B6C9F0"
        class="white--text"
      >
        LOGIN
      </v-btn>
    </template>
    <v-card :rounded="rounded" elevation="21">
      <b-alert show variant="danger" v-if="isLoginError"
        >아이디 또는 비밀번호를 확인하세요.</b-alert
      >
      <v-card-title>
        <span class="text-h5">Login</span>
      </v-card-title>
      <v-card-text>
        <v-container>
          <v-col cols="15">
            <v-text-field
              id="memberid"
              v-model="user.memberid"
              label="ID"
              required
            ></v-text-field>
          </v-col>
          <v-col cols="15">
            <v-text-field
              id="pwd"
              v-model="user.pwd"
              label="Password"
              type="password"
              required
            ></v-text-field>
          </v-col>
          <v-row  justify="center" >
            <v-col class="text-center">
            <a @click="kakaoLoginBtn" class="text-center"
            ><v-img
              src="../../assets/kakao_login.png"
          /></a>
            </v-col>
          </v-row>
          <v-row justify="center">

            <v-col class="text-center" @click="moveFindPwd"
              >비밀번호 찾기</v-col
            >
          </v-row>
        </v-container>
      </v-card-text>
      <v-card-actions>
        <v-spacer></v-spacer>
        <v-btn color="red darken-1" text @click="moveJoin"> JOIN </v-btn>
        <v-btn color="blue darken-1" text @click="confirm"> LOGIN </v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script>
import { mapState, mapActions } from "vuex";
// import FindPassword from "@/components/User/FindPassword.vue"

const userStore = "userStore";

export default {
  name: "UserLogin",
  // components:{
  //   FindPassword
  // },
  data() {
    return {
      dialog: false,
      user: {
        memberid: null,
        pwd: null,
      },
    };
  },
  computed: {
    ...mapState(userStore, ["isLogin", "isLoginError", "userInfo"]),
  },
  methods: {
    ...mapActions(userStore, ["userConfirm", "getUserInfo", "userKakaoLogin"]),
    async confirm() {
      await this.userConfirm(this.user);
      let token = sessionStorage.getItem("access-token");
      if (this.isLogin) {
        this.dialog = false;
        await this.getUserInfo(token);
        this.$router.push("/");
      }
    },
    moveJoin() {
      this.dialog = false;
      this.$router.push(`/join`);
    },
    moveFindPwd() {
      this.dialog = false;
      this.$router.push(`/user/findpassword`);
    },
    async kakaoLoginBtn() {
      if (!window.Kakao.isInitialized()) {
        window.Kakao.init(process.env.VUE_APP_kakaomapkey); // Kakao Developers에서 요약 정보 -> JavaScript 키
      }

      if (!window.Kakao.Auth.getAccessToken()) {
        window.Kakao.Auth.login({
          success: function () {
          },
          fail: function (error) {
            console.log(error);
          },
        });
      }
      await this.userKakaoLogin(window.Kakao.Auth.getAccessToken());
      let token = sessionStorage.getItem("access-token");
      if (this.isLogin) {
        this.dialog = false;
        await this.getUserInfo(token);
        this.$router.push("/");
      }
    },
  },
};
</script>

<style scoped>
/* #logindialog{
    margin-right: 40px;
  } */
#loginbtn:active {
  box-shadow: 0 3px 0 #C9CCD5;
  top: 3px;
}
</style>
