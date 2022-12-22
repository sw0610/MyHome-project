import { getQuestion } from "@/api/qna";

const questionStore = {
  namespaced: true,
  state: {
    question: null,
  },
  getters: {
    checkQuestion: function (state) {
      return state.question;
    },
  },
  mutations: {
    SET_QUESTION_INFO: async (state, question) => {
      state.question = question;
    },
  },
  actions: {
    getQuestionInfo({state, commit }, payload) {
      return new Promise((res, rej) => {
        return getQuestion(
            payload.id,
            ({ data }) => {
              commit("SET_QUESTION_INFO", data);
              res(state.question);
            },
            (error) => {
              console.log(error);
              rej();
            }
          );
      });
      
    },
  },
};

export default questionStore;



/*
return getQuestion(
        payload.id,
        ({ data }) => {
          commit("SET_QUESTION_INFO", data);
          return this.checkQuestion;
        },
        (error) => {
          console.log(error);
        }
      );

*/