import Vue from 'vue'
import VueRouter from 'vue-router'
Vue.use(VueRouter)
import store from "@/store";

//로그인해야 접근 가능하도록
const onlyAuthUser = async (to, from, next) => {
  const checkUserInfo = store.getters["userStore/checkUserInfo"];
  const checkToken = store.getters["userStore/checkToken"];
  let token = sessionStorage.getItem("access-token");
  console.log("로그인 처리 전", checkUserInfo, token);

  if (checkUserInfo != null && token) {
    console.log("토큰 유효성 체크");
    await store.dispatch("userStore/getUserInfo", token);
  }
  if (!checkToken || checkUserInfo === null) {
    console.log(checkUserInfo)
    alert("로그인이 필요한 페이지입니다..");
    // next({ name: "login" });
  } else {
    console.log("로그인");
    next();
  }
};

//admin만 접근 가능하도록
const onlyAdminUser = async(to, from, next)=>{
  const checkUserInfo = store.getters["userStore/checkUserInfo"];
  const checkToken = store.getters["userStore/checkToken"];
  const checkAdmin = store.getters["userStore/checkAdmin"];
  let token = sessionStorage.getItem("access-token");
  console.log("로그인 처리 전", checkUserInfo, token);
  
  if (!checkToken || checkUserInfo === null) {
    console.log(checkUserInfo)
    alert("로그인이 필요한 페이지입니다");
  } else {
    console.log("로그인");
    if (checkUserInfo != null && token) {
      console.log("토큰 유효성 체크");
      console.log(checkAdmin)
      if(checkAdmin=="admin"){ //auth가 admin인지 확인한다
        await store.dispatch("userStore/getUserInfo", token);
        next();

      }else{
        alert("관리자 권한이 필요합니다");
  
      }
  }

  }

};

const checkWriter = async (to, from, next)=>{
  const checkUserInfo = store.getters["userStore/checkUserInfo"];
  const checkAdmin = store.getters["userStore/checkAdmin"];
  let token = sessionStorage.getItem("access-token");
  await store.dispatch("qnaStore/getQuestionInfo",{id:to.params.id}).then((data)=>{
    const checkQuestion = data;
    if(checkQuestion.isprivate==1) {
      if (checkUserInfo != null && token) {
        
        console.log("-=-=-=-=-=-=-=-=");
        console.log("qid "+checkQuestion.memberid);
        console.log("mid "+checkUserInfo.memberid);
        if(checkAdmin=="admin"|| checkQuestion.memberid===checkUserInfo.memberid){
          next();
  
        }else{
          alert("비공개 글입니다");
    
        }
    }else{
      alert("로그인이 필요한 페이지 입니다");
    }
  
    }else{
      next();
    }  
  });
  
  

  // console.log("qid "+checkQuestion.memberid);
  // console.log("mid "+checkUserInfo.memberid);
  // if (question.isprivate=="1"&&(!checkToken || checkUserInfo === null)) {
  //   console.log(checkUserInfo)
  //   alert("로그인이 필요한 페이지입니다");
  // } else 
  
};

const routes = [
  {
    path: '/',
    name: 'home',
    component: () => import('../views/HomeView.vue')
  },
  {
    path: '/notice',
    name: 'notice',
    component: () => import('../views/NoticeView.vue')
  },
  {
    path: '/notice/search/:keyword',
    name: 'noticeSearch',
    component: () => import('../views/NoticeView.vue')
  },
  {
    path: '/apart',
    name: 'apart',
    component: () => import('../views/ApartView.vue')
  },

  {
    path: '/notice/detail/:no',
    name: 'noticedetail',
    component: () => import('../components/Notice/NoticeDetail.vue')
  },
  {
    path: '/notice/write',
    name: 'noticewrite',
    beforeEnter: onlyAdminUser,
    component: () => import('../components/Notice/NoticeWrite.vue')
  },
  {
    path: '/notice/modify/:no',
    name: 'noticemodify',
    beforeEnter: onlyAdminUser,

    component: () => import('../components/Notice/NoticeModify.vue')
  },
  {
    path: '/join',
    name: 'join',
    component: () => import('../components/User/UserJoin.vue')
  },
  {
    path:'/user/info',
    name:'userinfo',
    beforeEnter: onlyAuthUser,

    component: () => import('@/components/User/UserInfo.vue'),
  },
  {
    path:'/user/findpassword',
    name:'findpassword',

    component: () => import('@/components/User/FindPassword.vue'),
  },
  {
    path:'/user/changepassword',
    name:'changepassword',
    beforeEnter: onlyAuthUser,

    component: () => import('@/components/User/ChangePassword.vue'),
  },
  {
    path: '/qna',
    name: 'qna',

    component: () => import('../views/QnAView.vue'),
    children:[
      {
        path: '',
        component: () => import('../components/QnA/QnAList.vue'),
      },
      {
        path: 'read/:id',
        beforeEnter:checkWriter,
        component: () => import('../components/QnA/QnA.vue'),
        meta:{}
      },
      {
        path: 'write',
        beforeEnter: onlyAuthUser,

        component: () => import('../components/QnA/QnAWrite.vue'),
      }
    ]
  },
  // {
  //   path: '/qna/:id',
  //   component: () => import('../components/QnA/QnA.vue'),
  // },
]

const router = new VueRouter({
  routes
})

export default router
