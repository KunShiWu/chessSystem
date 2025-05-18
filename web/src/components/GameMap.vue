<template>
    <div ref="parent" class="gamemap">
        <div class="user">
            <div class="row position">
                <div class="col-6 ">
                        <img class="user_photo" :src="$store.state.user.photo" alt="">
                </div>
               
            </div>
        
            <div class="row position">
                
                <div class="col-6">
                    <div class="user_name">
                        {{ $store.state.user.username }}
                    </div>
                </div>

                <!-- <div class="col-6">
                
                <div v-if="$store.state.pk.ChessColor==='WhiteChess'" class="BlackChess"></div>
                <div v-else class="WhiteChess"></div>
            </div> -->
            <div class="col-6">
                
                <div v-if="$store.state.pk.ChessColor==='BlackChess'" class="BlackChess"></div>
                <div v-else class="WhiteChess"></div>
            </div>
            </div>

        </div>
        <div >
            
            <div v-if="$store.state.pk.winner!=='all'">
            
                <div v-if="$store.state.pk.winner==='winner2'" class="winner_font" >白棋胜利</div>
                <div v-if="$store.state.pk.winner==='winner1'"  class="winner_font">黑棋胜利</div>
                
            </div>
             <canvas  ref="canvas" tabindex=0 ></canvas>
        </div>
   
      
        <div class="opponent">
            <div class="row position">
                <div class="col-6 ">
                        <img class="user_photo" :src="$store.state.pk.opponent_photo" alt="">
                </div>
        
        
            </div>
            <div class="row position">
    
                <div class="col-6">
                    <div class="user_name">
                        {{ $store.state.pk.opponent_username }}
                    </div>
                </div>
                <div class="col-6">
                
                <div v-if="$store.state.pk.ChessColor==='WhiteChess'" class="BlackChess"></div>
                <div v-else class="WhiteChess"></div>
            </div>
         
        
            </div>
        


        </div>
    </div>
</template>



<script>

import { ref } from 'vue';
import { onMounted} from 'vue';
import {GameMap} from '@/assets/scripts/GameMap.js'
import { useStore } from 'vuex';
export default{
    setup(){
            const store=useStore();
            let parent=ref(null);
            let canvas=ref(null);
            const opponentUsername = sessionStorage.getItem('opponent_username');
            const opponentPhoto=sessionStorage.getItem("opponent_photo");
            store.commit("updateOpponent",{
                username:opponentUsername,
                photo:opponentPhoto
            })
           
        onMounted(()=>{
            
            store.commit("updateOpponent",{
                username:opponentUsername,
                photo:opponentPhoto
            })
            new GameMap(canvas.value.getContext('2d'),parent.value,store);
        })


        return{
            parent,
            canvas,

        }
    }
}


</script>


<style scoped>
div.gamemap{
    width: 100%;
    height: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
    box-sizing: border-box;
}

.position{
    margin-top: 15%;
}
.user{
    width: 300px;
    height: 300px;


}
.user_photo{
    width: 100px;
    height: 100px;
    border-radius: 50%;
  
}
.user_name{
    width: 80;
    height: 100px;
    font-size:30px;
    color: antiquewhite;
}
.opponent{
    width: 300px;
    height: 300px;


}
.BlackChess{
    width:30px;
    height: 30px;
    border-radius: 50%;
    background-color: black;
    margin-top: 8px;
}
.WhiteChess{
    margin-top: 8px;
    width:30px;
    height: 30px;
    border-radius: 50%;
    background-color: white;

    
}

.winner_font{
    position: relative;
  font-size: 24px;
  font-weight: bold;
  text-align: center;
  color: rgb(234, 141, 34);
}
</style>