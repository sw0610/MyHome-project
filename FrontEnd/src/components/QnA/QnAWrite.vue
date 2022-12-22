<template>
  <v-container>
    <h2 class="display-6 text-center font-weight-bold" justify="center" style="margin-top:30px">질문하기</h2>
    <template>
      <v-card id="card" class="pa-4 text-no-wrap rounded-lg" elevation="15" >
        <v-form ref="form" @submit.prevent="submit">
          <v-container fluid>
            <v-row>
              <v-col cols="12" sm="6">
                <v-text-field
                  v-model="form.title"
                  :rules="rules.name"
                  color="purple darken-2"
                  label="제목"
                  required
                ></v-text-field>
              </v-col>
              <hr>
              <v-col cols="12">
                <v-textarea v-model="form.content" color="teal">
                  <template v-slot:label>
                    <div>내용</div>
                  </template>
                </v-textarea>
              </v-col>
            </v-row>
          </v-container>
          <v-card-actions>
            <v-checkbox color="#D7C0AE" class="float-right" v-model="isPrivate" label="질문 비공개"></v-checkbox>

            <v-spacer></v-spacer>
            <div>
            <v-btn text @click="resetForm"> Cancel </v-btn>
            </div>
            <v-btn :disabled="!formIsValid" text color="primary" type="submit"> Register </v-btn>
          </v-card-actions>
        </v-form>
      </v-card>
    </template>
  </v-container>
</template>
<script>
import http from "@/util/http"
import { mapState } from "vuex";

const userStore="userStore";
export default {
  data() {
    const defaultForm = Object({
      title: "",
      content: "",
    });

    return {
      form: Object.assign({}, defaultForm),
      rules: {
        name: [(val) => (val || "").length > 0 || "필수기재"],
        content: [(val) => (val || "").length > 0 || "필수기재"],
      },
      defaultForm,
      isPrivate: false,
    };
  },

  computed: {
    ...mapState(userStore, ["userInfo"]),

    formIsValid() {
      return this.form.title && this.form.content;
      
    },
  },

  methods: {
    resetForm() {
      this.form = Object.assign({}, this.defaultForm);
      this.$refs.form.reset();
    },
    submit() {
      console.log(this.isPrivate);
      if(this.isPrivate==false){
        this.isPrivate=0
      }
      else{
        this.isPrivate=1
      }
      http
        .post(
          "/qna/question/write",
          { content: this.form.content, title: this.form.title ,memberid:this.userInfo.memberid,isprivate:this.isPrivate},
          {
            "Content-Type": "application/json",
          }
        )
        .then((response) => {
          console.log(response);
          this.$router.push('/qna');
        });
    },
  },
};
</script>

<style scoped>
#card{
        margin: 50px;
        padding:30px
}
</style>
