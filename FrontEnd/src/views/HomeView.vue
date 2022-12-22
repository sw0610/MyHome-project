<template>
  <div>
    <div style="positon:relative;">
    <v-img src="../assets/maincity.jpg"/>
    </div>
    <div  class="fade-in-box" style="width:100%; height:100%">
            <h1 class="animate__animated animate__fadeInDown" id="logo">Where Is My Home?</h1>
            <h3 class="animate__animated animate__fadeInDown" id="desc">Find your own place</h3>

<div id="go" @click="goApart">
<section id="intro">
  <div id="intro-content" class="center-content">
    <div class="center-content-inner">
      <div class="content-section content-section-margin">
        <div class="content-section-grid clearfix">
        <a href="#" class="button nav-link">
          <div class="bottom"></div>
          <div class="top">
          <div class="label">GO >></div>
            <div class="button-border button-border-left"></div>
            <div class="button-border button-border-top"></div>
            <div class="button-border button-border-right"></div>
            <div class="button-border button-border-bottom"></div>
          </div>
          </a>
        </div>
       </div>
      </div>
     </div>

  </section>
</div>  

    </div>

  </div>
</template>

<script>
// import HomeOther from "@/components/Home/HomeOther.vue";
// import HomeSearch from "@/components/Home/HomeSearch.vue";
export default {
  name: "Home-Compo",
  methods:{
    goApart(){
        this.$router.push('/apart')
    }
  }

  // components: {
  //   HomeOther,
  //   HomeSearch,
  // },
};
</script>

<style scoped>
.fade-in-box {
  background-color: rgba(0, 0, 0, 0.5);
  position: absolute;
  padding: 10px;
  top:0%;
  left: 0%;
  animation: fadein 3s;
  -moz-animation: fadein 3s; /* Firefox */
  -webkit-animation: fadein 3s; /* Safari and Chrome */
  -o-animation: fadein 3s; /* Opera */
}
@keyframes fadein {
    from {
        opacity: 0;
    }
    to {
        opacity: 1;
    }
}
@-moz-keyframes fadein { /* Firefox */
    from {
        opacity: 0;
    }
    to {
        opacity: 1;
    }
}
@-webkit-keyframes fadein { /* Safari and Chrome */
    from {
        opacity: 0;
    }
    to {
        opacity: 1;
    }
}
@-o-keyframes fadein { /* Opera */
    from {
        opacity: 0;
    }
    to {
        opacity: 1;
    }
}

#logo{
  color:white;
    position: absolute;
    top:70%;
    left: 4%;
    font-size: 100px;
    --animate-duration: 2s;
    --animate-delay: 2s;
}

#desc{
  color:rgba(255, 255, 255, 0.8);
    position: absolute;
    top:85%;
    left: 4%;
    font-size: 30px;
    --animate-duration: 2s;
    --animate-delay: -2s;

}
#go{
  /* color:black; */
position: absolute;
top:80%;
right: 5%;
/* font-size: 30px; */
--animate-duration: 3s;
--animate-delay: -2s;

}

#intro {
  width: 200px;

}

.button {
    display: inline-block;
    text-decoration: none;
    position: relative;
    margin-top: 40px;
}

.button .bottom {
    position: absolute;
    left: 7px;
    top: 7px;
    width: 100%;
    height: 100%;
    background-color: #B2B2B2;
    display: block;
    -webkit-transition: all .15s ease-out;
    -moz-transition: all .15s ease-out;
    -o-transition: all .15s ease-out;
    transition: all .15s ease-out;
}

.button .top {
    position: relative;
    left: 0;
    top: 0;
    width: 100%;
    height: 100%;
    padding: 24px 34px 22px 34px;
    border: 2px solid #65647C;
}

.button-dark .top {
    border: 2px solid #fff;
}

.button .top .label {
    font-family: sans-serif;
    font-weight: 600;
    color: rgba(255, 255, 255, 0.8);
    font-size: 30px;
    line-height: 110%;
    letter-spacing: 2px;
    text-align: center;
    text-transform: uppercase;
    -webkit-transition: all .15s ease-out;
    -moz-transition: all .15s ease-out;
    -o-transition: all .15s ease-out;
    transition: all .15s ease-out;
}

.button-dark .top .label {
    color: #fff;
}

.button:hover .bottom {
    left: 0;
    top: 0;
    background-color: #f3f3f3;
}

.button:hover .top .label {
    color: #282A3A;
}

.button-border {
    position: absolute;
    background-color: #D6E4E5;
    -webkit-transition: all .25s ease-out;
    -moz-transition: all .25s ease-out;
    -o-transition: all .25s ease-out;
    transition: all .25s ease-out;
}

.button:hover .top .button-border-left,.button:hover .top .button-border-right {
    height: calc(100% + 2px);
}

.button:hover .top .button-border-top,.button:hover .top .button-border-bottom {
    width: calc(100% + 2px);
}

.button-border-left {
    left: -2px;
    bottom: -2px;
    width: 2px;
    height: 0;
}

.button-border-top {
    left: -2px;
    top: -2px;
    width: 0;
    height: 2px;
}

.button-border-right {
    right: -2px;
    top: -2px;
    width: 2px;
    height: 0;
}

.button-border-bottom {
    right: -2px;
    bottom: -2px;
    width: 0;
    height: 2px;
}

</style>
