<template>
  <v-app id="inspire">
        <v-img v-show="isMain==false" src="./assets/banner.jpg"></v-img>

    <AppBar />
    <v-main style="padding:0px;padding-bottom:30px; background-color:#F6F6F6"> 
      <router-view /> 
    </v-main>
    <AppFooter />
  </v-app>
</template>

<script>
import AppBar from "./components/Core/AppBar";
import AppFooter from "@/components/Core/AppFooter.vue"
export default {
  name: "App",
  components: {
    AppBar,
    AppFooter
  },
  data(){
    return{
      isMain:false,
    }
  },
    watch: {
    '$route' (to) {
      if(to.name=="home"){
        this.isMain=true;
      }else{
        this.isMain=false;
      }
    }
  }
  // beforeCreate(){
  //   console.log(this.$route.path
  //   // console.log(document.location.pathname);
  //   switch(document.location.pathname){
  //     case '/':
  //       this.isMain = true;
  //       break;
  //     default:
  //       this.isMain = false;
  //       break;

  //   }

  // }  



};
</script>

