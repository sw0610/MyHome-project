<template>
  <v-container>
    <h3 class="text-h4">질문 보기</h3>
    <!--작성자와 admin만 수정삭제완료 가능-->
    <div
      class="text-right"
      v-if="userInfo != null && (userInfo.memberid == question.memberid || userInfo.auth == 'admin')"
    >
      <v-btn
        v-if="question.iscomplete == '0'"
        @click="complete"
        style="margin-right: 10px"
        class="text-white"
        rounded
        color="#6D8B74"
        >해결 완료</v-btn
      >
      <v-btn v-else @click="complete" style="margin-right: 10px" class="text-white" rounded color="#73777B"
        >해결 미완료</v-btn
      >

      <v-btn
        v-if="modifyToggle"
        @click="modifyToggle = false"
        class="text-white"
        rounded
        color="#F3C583"
        style="margin-right: 10px"
        >수정</v-btn
      >
      <v-btn v-else @click="modifyQuestion" style="margin-right: 10px" class="text-white" rounded color="#F3C583"
        >수정완료</v-btn
      >
      <v-btn @click="deleteQuestion" class="text-white" rounded color="#E99497">삭제</v-btn>
    </div>
    <v-card style="margin: 30px; padding: 30px" class="pa-4 text-no-wrap rounded-lg" elevation="15">
      <v-row style="height: 40px">
        <v-col>
          <v-card-subtitle style="padding: 0px"> {{ question.memberid }}님의 질문 </v-card-subtitle>
        </v-col>
        <v-col cols="2">
          <v-card-subtitle style="padding: 0px"> 조회수:{{ question.hit }}</v-card-subtitle>
        </v-col>
        <v-col cols="2">
          <v-card-subtitle style="padding: 1px"> 등록일:{{ question.createdate }}</v-card-subtitle>
        </v-col>
      </v-row>
      <hr />
      <h1>
        <v-text-field flat solo v-model="question.title" :disabled="modifyToggle ? '' : disabled"></v-text-field>
      </h1>
      <hr style="border: dotted" />
      <v-textarea v-model="question.content" :disabled="modifyToggle ? '' : disabled"> </v-textarea>
    </v-card>
    <v-card
      style="margin: 50px; padding: 80px"
      class="pa-4 text-no-wrap rounded-lg, overflow-y-auto"
      elevation="15"
      v-scroll.self="onScroll"
      max-height="400"
    >
      <v-timeline align-top dense>
        <v-timeline-item v-for="message in messages" :key="message.answer_no" :color="message.color" small>
          <div>
            <v-row>
              <v-col>
                <div class="font-weight-normal">
                  <strong>{{ message.memberid }}@<span v-text="message.createdate"></span></strong>
                  <span v-if="userInfo != null">
                    <font-awesome-icon
                      v-if="userInfo.memberid == message.memberid || userInfo.auth == 'admin'"
                      @click="removeAnswer(message.answer_no)"
                      icon="fa-solid fa-trash-can"
                      style="margin-left: 10px"
                      color="#B73E3E"
                    />
                  </span>
                </div>
                <div>{{ message.content }}</div>
              </v-col>
            </v-row>
          </div>
        </v-timeline-item>
      </v-timeline>
    </v-card>
    <v-card style="margin: 50px; padding: 80px" class="pa-4 text-no-wrap rounded-lg, overflow-y-auto" elevation="15">
      <v-row>
        <v-col>
          <v-text-field
            label="답변하기"
            dense
            style="padding-top: 10px"
            v-model="chat"
            @keyup.enter="sendChat"
          ></v-text-field>
        </v-col>
        <v-col cols="1">
          <v-btn @click="sendChat" class="text-white" rounded color="#8BBCCC">전송</v-btn>
        </v-col>
      </v-row>
    </v-card>
  </v-container>
</template>

<script>
import http from "@/util/http";
import http_common from "@/util/http-common";
import { mapState } from "vuex";

const userStore = "userStore";
export default {
  
  created() {
    http.get("/qna/question/" + this.$route.params.id).then((response) => {
      this.question = response.data;
      this.question.hit/=2;
    });
    http.get("/qna/answer/" + this.$route.params.id).then((response) => {
      this.messages = response.data;
      for (let i = 0; i < this.messages.length; i++) {
        if (this.messages[i].memberid == "admin") {
          this.messages[i].color = "#81C6E8";
        } else {
          this.messages[i].color = "#FFB9B9";
        }
      }
      console.log(response.data);
    });
  },
  computed: {
    ...mapState(userStore, ["userInfo"]),
  },
  data() {
    return {
      modifyToggle: true,
      question: "",
      chat: "",
      messages: [],
    };
  },
  methods: {
    removeAnswer(answer_id) {
      http
        .delete("/qna/answer/delete/" + answer_id)
        .then(() => {
          alert("해당 댓글 삭제완료");
          http.get("/qna/answer/" + this.$route.params.id).then((response) => {
            this.messages = response.data;
            for (let i = 0; i < this.messages.length; i++) {
              if (this.messages[i].memberid == "admin") {
                this.messages[i].color = "#81C6E8";
              } else {
                this.messages[i].color = "#FFB9B9";
              }
            }
            console.log(response.data);
          });
        })
        .catch(() => {
          alert("해당 댓글 삭제실패");
        });
    },
    modifyQuestion() {
      http
        .put(
          "/qna/question/modify",
          {
            title: this.question.title,
            content: this.question.content,
            isprivate: this.question.isprivate,
            question_no: this.question.question_no,
          },
          {
            "Content-Type": "application/json",
          }
        )
        .then(() => {
          this.modifyToggle = true;
          alert("수정하였습니다");
        })
        .catch(() => {
          alert("수정에 실패했습니다.");
        });
    },
    deleteQuestion() {
      http.delete("/qna/question/delete/" + this.$route.params.id).then(()=>this.$router.push("/qna"));
    },

    sendChat() {
      http
        .post(
          "/qna/answer/write",
          {
            content: this.chat,
            question_no: this.$route.params.id,
            memberid: this.userInfo.memberid,
          },
          {
            "Content-Type": "application/json",
          }
        )
        .then(() => {
          http.get("/qna/answer/" + this.$route.params.id).then((response) => {
            this.messages = response.data;
            for (let i = 0; i < this.messages.length; i++) {
              if (this.messages[i].memberid == "admin") {
                this.messages[i].color = "#81C6E8";
              } else {
                this.messages[i].color = "#FFB9B9";
              }
            }
            console.log(response.data);
            this.chat = "";
          });
        });
    },
    complete() {
      http_common
        .put(`/qna/question/complete/${this.$route.params.id}`, {
          question_no: this.$route.params.id,
        })
        .then(this.$router.go());
    },
  },
};
</script>
