<template>
    <div class="card container" style="margin-top :12px;width: 150vw;">
        <div class="card-body" >

            <div class="card" v-for="Record,index in RecordRank" :key="index" style="margin-top: 10px;">
                <div class="card-body">
                    <div class="row">
       
                    <img :src="Record.userphoto" alt="" class="col-2 photo">
                    <div class="col-2 name">{{ Record.username }}</div>
                    <div class="col-2 winner" v-if="Record.winner===Record.username">Winner</div>
                    <div class="col-2 loser" v-else-if="Record.winner!==Record.username">loser</div>


                    <img :src="Record.opponentphoto" alt="" class="col-2 photo" >
                    <div class="col-2 name">{{ Record.opponentname }}</div>
                    <div class="col-2 loser"  v-if="Record.winner===Record.username">loser</div>
                    <div class="col-2 winner" v-else-if="Record.winner!==Record.username">Winner</div>
                </div>

                </div>
            </div>
          
        </div>
  
    </div>


</template>

<script>
import { useStore } from 'vuex';
import $ from 'jquery'
import { ref } from 'vue';

export default{
    name:"RankListView",
    setup(){
        const store=useStore();
        let RecordRank=ref([]);
        


            $.ajax({
                url:"http://127.0.0.1:2244/user/pkrecord/pkrecordinfo/",
                type:"post",
                headers:{
                          Authorization:"Bearer "+store.state.user.access_token
                          },
                data:{
                        userid:store.state.user.id,
                    },
                success(resp){
                    store.commit("UpdateRecordRank",resp);
                     RecordRank.value=store.state.user.RecordRank;
                     console.log(RecordRank.value);
                }
            });

          

        


           
        
    


        return {
            RecordRank,
           
        }

    }
  
}
</script>

<style scoped>

.photo{
    width: 100px;
    height: 100px;
    border-radius: 50%;
}


.winner {
  font-size: 20px;
  font-weight: bold;
  color: orange;
  text-shadow: 2px 2px 0px #fff, 4px 4px 0px rgba(0,0,0,0.1);

  display: flex;
  justify-content: center;
  align-items: center;
}


.name {
  color: #8BC34A;
  font-style: italic;
  font-size: 20px;
  font-family: "Microsoft YaHei", sans-serif;
  display: flex;
  justify-content: center;
  align-items: center;
}

.loser {
  color: #999; /* 灰色 */
  text-shadow: 1px 1px 0 #ccc; /* 文本阴影 */
  display: flex;
  justify-content: center;
  align-items: center;
}
</style>