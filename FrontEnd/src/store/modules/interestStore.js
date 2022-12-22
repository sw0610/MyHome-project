import { getInterest,addInterest,deleteInterest} from "@/api/interest";
const interestStore = {
  namespaced: true,
  state: {
    interest:[]
  },
  getters: {
    getInterest(state) {
      return state.interest;
    },
  },
  mutations: {
    SET_INTEREST: async (state, interest) => {
      state.interest = interest;
    },
  },
  actions: {
    removeAllInterestInfo({state}){
      state.interest=[];
    },

    getInterestInfo({state, commit}) {
      return new Promise((res, rej) => {
        return getInterest(
            ({ data }) => {
              commit("SET_INTEREST", data);
              res(state.interest);
            },
            (error) => {
              console.log(error);
              rej();
            }
          );
      });
    },
    addInterestInfo({state},item){
      return new Promise((res, rej) => {
        return addInterest(
          item.aptCode,
            ({data}) => {
              if(data==1){
                state.interest.push(item);
              }
              res(state.interest);
            },
            (error) => {
              console.log(error);
              rej();
            }
          );
      });
    },
    deleteInterestInfo({state},item){
      return new Promise((res, rej) => {
        return deleteInterest(
          item.aptCode,
            ({data}) => {
              if(data==1){
                state.interest = state.interest.filter(x => x.aptCode!=item.aptCode);
              }
              res(state.interest);
            },
            (error) => {
              console.log(error);
              rej();
            }
          );
      });
    }
  },
};

export default interestStore;

26762407118357

