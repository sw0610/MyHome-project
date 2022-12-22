<template>
  <router-link :to="'/qna/read/'+item.question_no" style="text-decoration: none;">
    <v-list-item>
    <v-list-item-icon class = "text-center"> 
      {{ item.question_no }}
    </v-list-item-icon>

    <v-list-item-content>
      <v-list-item-title>{{ item.title }} 
        <span>&nbsp;</span>
       <font-awesome-icon v-if="item.isprivate==1" icon="fa-solid fa-lock" size="xs" color="#748DA6" />
      </v-list-item-title>
    </v-list-item-content>
    
    <v-col cols="1" class = "text-center"><font-awesome-icon v-if="item.iscomplete=='1'" icon="fa-regular fa-circle-check" color="#62A388"/></v-col>
    <v-col cols="1" class = "text-center">{{item.memberid}}</v-col>
    <v-col cols="1" class = "text-center">{{item.hit/2}}</v-col>
    <v-col cols="2" class = "text-center">{{item.createdate}}</v-col>
  </v-list-item>
  </router-link>
</template>

<script>
export default {
  props:{
    item:Object,
  }
};
</script>