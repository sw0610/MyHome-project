import Vue from 'vue'
import Vuex from 'vuex'
import createPersistedState from "vuex-persistedstate";

import userStore from "@/store/modules/userStore";
import qnaStore from "@/store/modules/qnaStore";
import interestStore from "@/store/modules/interestStore";

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    user:""
  },
  getters: {
  },
  mutations: {
  },
  actions: {
  },
  modules: {
    userStore,
    qnaStore,
    interestStore
  },
  plugins:[
    createPersistedState({
      storage:sessionStorage,
    }),
  ]
});
