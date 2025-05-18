import { createStore } from 'vuex'
import ModuleUser from './user'
import ModulePk from './pk'
import $ from 'jquery'
export default createStore({
  state: {
  },
  getters: {
  },
  mutations: {
  },
  actions: {
    AddPkRecordInfo(context,winner){

      if(context.state.pk.ChessColor===winner)
      {
        $.ajax({
          url:"http://127.0.0.1:2244/user/pkrecord/addpkrecord/",
          type:"post",
          headers:{
                    Authorization:"Bearer "+context.state.user.access_token
                    },
          data:{
                  userid:context.state.user.id,
                  username:context.state.user.username,
                  userphoto:context.state.user.photo,
                  opponentid:context.state.pk.opponent_Id,
                  opponentname:context.state.pk.opponent_username,
                  opponentphoto:context.state.pk.opponent_photo,
                  winner:context.state.user.username
              },
          success(resp){
              console.log(resp);
          }
      });
      }
      else {
        $.ajax({
          url:"http://127.0.0.1:2244/user/pkrecord/addpkrecord/",
          type:"post",
          headers:{
                    Authorization:"Bearer "+context.state.user.access_token
                    },
          data:{
                  userid:context.state.user.id,
                  username:context.state.user.username,
                  userphoto:context.state.user.photo,
                  opponentid:context.state.pk.opponent_Id,
                  opponentname:context.state.pk.opponent_username,
                  opponentphoto:context.state.pk.opponent_photo,
                  winner:context.state.pk.opponent_username
              },
          success(resp){
              console.log(resp);
          }
      });
      }
 
        
      



    }
  },
  modules: {
    user:ModuleUser,
    pk:ModulePk
  }
})
