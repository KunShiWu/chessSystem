<template>
    <div class="card container " style="width: 45%;margin-top: 50px;">
        <div class="card-body">
            <form @submit.prevent="LOGIN">
                <div class="mb-3 Style container" >
                    <input v-model="username" type="text" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="请输入账号">
                </div>
                <div class="mb-3 Style container">
                    <input v-model="password" type="password" class="form-control" id="exampleInputPassword1" placeholder="请输入密码">
                </div>

                <div class="mb-3 Style container" style="color: red;">
                  <div>{{ error_message }}</div>
                </div>
            
                <div class="mb-3 Style container">
                    <button  type="submit" class="btn btn-primary " style="width: 45%;">登录</button>
                    <button @click="TurnToRegister" class="btn btn-outline-secondary " style="width: 45%;margin-left: 10%;">注册</button>
                </div>
             
            </form>

        </div>
    </div>

</template>

<script>

import { ref } from 'vue';
import router from '@/router';
import { useStore } from 'vuex';
export default{
    name:"LoginView",
    setup(){
        const store=useStore();
        let username=ref('');
        let password=ref('');
        let error_message=ref('');

        const LOGIN=()=>{
            error_message.value='';

            store.dispatch("Login",{
                username:username.value,
                password:password.value,
                success(){
                    store.dispatch("GetUserInfo",{
                        success(){
                            router.push({name:"home"});
                        },
                        error(){
                            error_message.value="用户名或者密码错误";
                        }
                    })
                },
                error(){
                    error_message.value="用户名或者密码错误";
                }
            })

        }
        const TurnToRegister=()=>{
            router.push({name:"register"})
        }


        return {
            username,
            password,
            error_message,
            LOGIN,
            TurnToRegister
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