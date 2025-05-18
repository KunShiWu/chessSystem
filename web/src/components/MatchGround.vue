<template>
    <div class="matchground">
        <div class="row">
            <div class="col-6">
                <div class="user-photo">
                    <img :src="$store.state.user.photo" alt="">
                </div>
                <div class="user-username">
                    {{ $store.state.user.username }}
                </div>
            </div>
            <div class="col-6">
                <div class="user-photo">
                    <img :src="$store.state.pk.opponent_photo" alt="">
                </div>
                <div class="user-username">
                    {{ $store.state.pk.opponent_username }}
                </div>
            </div>
            <div class="col-12" style="text-align: center;padding-top: 5vh;">
                <button type="button" v-if="matchBtnInfo==='开始匹配'"  @click="ClickBtnInfo" class="btn btn-info btn-lg">{{matchBtnInfo}}</button>
                <button type="button" v-if="matchBtnInfo==='取消匹配'"  @click="ClickBtnInfo" class="btn btn-warning btn-lg">{{matchBtnInfo}}</button>

            </div>
        </div>
    </div>
</template>

<script>
import { ref } from 'vue';
import { useStore } from 'vuex';

export default{
    setup(){
        const store=useStore();
        let matchBtnInfo=ref("开始匹配");
        const ClickBtnInfo =()=>{
            if(matchBtnInfo.value==="开始匹配")
            {
                matchBtnInfo.value="取消匹配"
                store.state.pk.socket.send(JSON.stringify({
                    event:"startMatching"
                }))
                 
            }
            else {
                matchBtnInfo.value="开始匹配"
                store.state.pk.socket.send(JSON.stringify({
                    event:"stopMatching"
                }))
                 
            }
        }

        return {
            matchBtnInfo,
            ClickBtnInfo
        }
    }
}

</script>


<style scoped>
div.matchground{
width: 70vw;
height: 80vh;
margin: 40px auto;
box-sizing: border-box;
background-color: rgb(98, 100, 100,0.5)  ;
}

div.user-photo{
    text-align: center;
    padding-top: 15vh;
}

div.user-photo>img{
    border-radius: 50%;
    width: 25vh;
}
div.user-username{
    text-align: center;
    font-size: 24px;
    font-weight: 600;
    color: white;
    padding-top: 2vh;
}

</style>