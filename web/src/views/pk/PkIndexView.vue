<template>
    <PlayGround v-if="$store.state.pk.status==='playing'" />
    <MatchGround v-if="$store.state.pk.status==='matching'" />
</template> 

<script>
import PlayGround from '@/components/PlayGround'
import { onMounted } from 'vue';
import { onUnmounted } from 'vue';
import { useStore } from 'vuex';
import MatchGround from '@/components/MatchGround.vue';
export default{
    name:"PkIndexView",
    components:{
        PlayGround,
        MatchGround
    },
    setup(){
        const store=useStore();
        const socketUrl=`ws://127.0.0.1:2244/websocket/${store.state.user.access_token}/`;
        let socket=null;

        onMounted(()=>{

            // store.commit("updateOpponent",{
            //     username:,
            //     photo:
            // })


            socket=new WebSocket(socketUrl);
            socket.onopen=()=>{
                console.log("connected");
                store.commit("updateSocket",socket);
            }


            socket.onmessage=(msg)=>{
                const data=JSON.parse(msg.data);
                if(data.event==="startMatching")
                {

                    sessionStorage.setItem('opponent_username', data.opponent_username);
                    sessionStorage.setItem('opponent_photo', data.opponent_photo);
                    store.commit("updateOpponent",{
                        username:data.opponent_username,
                        photo:data.opponent_photo
                    })
                    setTimeout(()=>{
                        store.commit("updateStatus","playing");
                    },2000)
                    store.commit("updateGamemap",data.gamemap);
                    store.commit("updateChessColor",data.ChessColor);
                    store.commit("updateopponentId",data.opponent_id);
                }
                else if(data.event==="Move")
                {
                    store.commit("updateopponentId",data.opponent_id);
                    store.commit("updateGamemap",data.gamemap)
               
                }
           
            }
            socket.onclose=()=>{
                console.log("onMounted    disconnected");
            }


        })

        onUnmounted(()=>{
            socket.close();
        })
        

    }
    
    
}
</script>

<style scoped>


</style>