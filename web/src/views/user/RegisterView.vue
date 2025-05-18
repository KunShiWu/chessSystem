<template>
    <div class="card container " style="width: 45%;margin-top: 50px;">
        <div class="card-body">
            <form @submit.prevent="REGISTER">
                <div class="mb-3 Style container" >
                    <input v-model="username" type="text" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="请输入注册账号">
                </div>
                <div class="mb-3 Style container">
                
                    <input v-model="password" type="password" class="form-control" id="exampleInputPassword1" placeholder="请设置密码">
                </div>
                <div class="mb-3 Style container">
                
                <input v-model="confirmPassword" type="password" class="form-control" id="exampleInputPassword1" placeholder="请确认密码">
            </div>
            
            <div class="mb-3 Style container" style="color: red;">
                  <div>{{ error_message }}</div>
                </div>
            
                <div class="mb-3 Style container">
              
                    <button  type="submit" class="btn btn-outline-secondary " style="width:100%;">注册</button>
                </div>
             
            </form>

        </div>
    </div>
</template>

<script>
import { ref } from 'vue';
import router from '@/router';
import $ from 'jquery'


export default{
    name:"RegisterView",
    setup(){
        let username=ref('');
        let password=ref('');
        let confirmPassword=ref('');
        let error_message=ref('');
        const REGISTER = ()=>{
                $.ajax({
                  url:"http://127.0.0.1:2244/user/account/register/",
                  type:"POST",  
                  data:{
                    username:username.value,
                    password:password.value,
                    confirmPassword:confirmPassword.value
                  },
                  success(resp){
                   if(resp.event==='success')
                   {
                    router.push({name:'login'});
                   } 
                   else{
                    error_message.value=resp.event;
                    username.value='';
                    password.value='';
                    confirmPassword.value='';
                   }
                  },
                  error(resp){
                    error_message.value=resp.event;
                  }

                })
        }

        return{
            username,
            password,
            confirmPassword,
            REGISTER,
            error_message
        }
    }
}
</script>

<style scoped>
.Style{
    width: 50%;
    display: flex;
    
}
</style>