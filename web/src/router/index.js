import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '@/views/HomeView'
import PkIndexView from '@/views/pk/PkIndexView'
import RankListView from '@/views/rank/RankListView'
import RegisterView from '@/views/user/RegisterView'
import LoginView from '@/views/user/LoginView'
import NoFoundView from '@/views/error/NoFoundView'
import store from '@/store'
const routes = [
  {
    path:'/',
    name:"home",
    component:HomeView,
    meta:{
      requestAuth:true,
    }
    
  },
  {
    path:'/pk/',
    name:"pk",
    component:PkIndexView,
    meta:{
      requestAuth:true,
    }
  },
  {
    path:'/ranklist/',
    name:"ranklist",
    component:RankListView,
    meta:{
      requestAuth:true,
    }
  },
  {
    path:'/login/',
    name:"login",
    component:LoginView,
    meta:{
      requestAuth:false,
    }
  },
  {
    path:'/register/',
    name:"register",
    component:RegisterView,
    meta:{
      requestAuth:false,
    }
  },
  {
    path:'/404/',
    name:"404",
    component:NoFoundView,
    meta:{
      requestAuth:false,
    }
  },
  {
    path:"/:catchAll(.*)",
    redirect:"/404/"
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})



router.beforeEach((to,from,next)=>{


  const jwt_token=localStorage.getItem("jwt_token");
  if(jwt_token)
  {
      store.commit("UpdateAccessToken",jwt_token);
      store.dispatch("GetUserInfo",{
        success(){
          next();
        },
        error(){
          next({name:"login"});
        }
      })
  }
  
  if(to.meta.requestAuth && !store.state.user.is_login)
  {
    if(jwt_token)//如果是已经登录了就不用跳转
    {
      store.dispatch("GetUserInfo",{
        success(){
          next();
        },
      })
      
    }
    else{
      next({name:"login"});
      alert("请重新登录!");
    }
  }
  else next();
});

export default router
