<template>
  <v-footer
    dark
    padless
  >
    <v-card
      flat
      tile
      class="white--text text-center"
      color="#F0EBE3"
      width="100%"
    >
      <v-card-text>
        <div >
        <v-btn
          @click="swGit"
          class="mx-4 lighten grey--text"
          icon
         
        >
        
          <v-icon size="24px" >
            mdi-github
          </v-icon>
          Doo SoWon
        </v-btn>
        </div>
        <div>
          <v-btn
           @click="jhGit"
          class="mx-4 lighten grey--text"
          icon
        >
          <v-icon size="24px" >
            mdi-github
          </v-icon>
          Park JeongHee
        </v-btn>
        </div>
      </v-card-text>

      <v-card-text class="ighten grey--text text-center pt-0">
        SSAFY 8기 14반 두소원 박정희 Where Is My Home 프로젝트
      </v-card-text>

      <v-divider></v-divider>

      <v-card-text class="lighten grey--text">
        2022 — <strong>SSAFY 두소원 박정희</strong>
      </v-card-text>
    </v-card>
  </v-footer>
</template>

<script>
  export default {
    data: () => ({
      icons: [
        'mdi-github',
        'mdi-github',
      ],
    }),
    methods:{
      swGit(){
        window.open("https://github.com/sw0610", "_blank");
      },
      jhGit(){
        window.open("https://github.com/lyeong-gwa", "_blank");
      }
    }
  }
</script>