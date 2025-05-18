<template>

<nav class="navbar navbar-expand-lg  ">
  <div class="container ">
  
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavDropdown">
      <ul class="navbar-nav">
        <li class="nav-item">
          <router-link class="nav-link" active-class="active" aria-current="page" :to="{name:'home'}">我的主页</router-link>
        </li>
        <li class="nav-item">
          <router-link  class="nav-link "  active-class="active" :to="{name:'pk'}" >游戏界面</router-link>
        </li>
        <li class="nav-item">
          <router-link  class="nav-link" active-class="active"  :to="{name:'ranklist'}" >排行榜</router-link>
        </li>
    
      </ul>
      <ul class="navbar-nav " v-if="!$store.state.user.is_login">
        <div class="distance">
        </div>
        <li class="nav-item " >
          <router-link  class="nav-link" active-class="active" :to="{name:'login'}" >登录</router-link>
        </li>
        <li class="nav-item">
          <router-link class="nav-link" active-class="active"  :to="{name:'register'}" >注册</router-link>
        </li>
 
      </ul>
      <ul class="navbar-nav " v-else>
        <div class="distance">
        </div>
      
        
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            {{ $store.state.user.username }}
          </a>
          <ul class="dropdown-menu">
            <li class="dropdown-item" style="user-select: none;   cursor: pointer; " @click="logout" >退出登录</li>
          </ul>
        </li>
      </ul>

 

    </div>
   
  </div>
</nav>
</template>

<script>
import { useStore } from 'vuex';
import router from "@/router"

export default{
  setup(){


    const store=useStore();

    const logout=()=>{
      store.commit("Logout");
      router.push({name:'login'})
    }
    
    return{
      logout
    }

   


  }
}

</script>

<style scoped>

.distance{
   margin-left: 900px;
 
}

</style>