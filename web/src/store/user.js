import $ from 'jquery'
export default ({
    state: {
        id:'',
        username:'',
        photo:'',
        access_token:"",
        is_login:false,
        RecordRank:null,
    },
    getters: {
    },
    mutations: {
        UpdateAccessToken(state,token){
            state.access_token=token;
        },
        UpdateUserInfo(state,info)
        {
          state.id=info.id;
          state.username=info.username;
          state.photo=info.photo;
          state.is_login=info.is_login;
        },
        Logout(state){
          localStorage.removeItem("jwt_token");
          state.id='';
          state.username='';
          state.photo='';
          state.is_login='';
          state.access_token=''
        },
        UpdateRecordRank(state,RecordRank){
         
            state.RecordRank=RecordRank;
        }


    },
    actions: {
        Login(context,data){
          $.ajax({
            url:"http://127.0.0.1:2244/user/account/token/",
            type:"post",
            data:{
              username:data.username,
              password:data.password
            },
            success(resp){
          
              if(resp.event=="success")
              {
                  localStorage.setItem("jwt_token",resp.access_token);
                  context.commit("UpdateAccessToken",resp.access_token);
                  data.success();
              }
              else data.error();
            },
            error(){
              data.error();
            }
          })
        },

        GetUserInfo(context,data)
        { 
              $.ajax({
                url:"http://127.0.0.1:2244/user/info/",
                type:"get",
                headers:{
                          Authorization:"Bearer "+context.state.access_token
                          },
                success(resp)
                {
     
                  if(resp.event==="success")
                  {
       
                      context.commit("UpdateUserInfo",{
                        ...resp,
                        is_login:true,
                      })
                      data.success();
                  }
                 
                },
                error(){
                  data.error();
                }
              })

        },



        // AddPkRecordInfo(context){
        //   console.log(context);
        // //   $.ajax({
        // //     url:"http://127.0.0.1:2244/user/pkrecord/addpkrecord/",
        // //     type:"post",
        // //     headers:{
        // //               Authorization:"Bearer "+context.state.access_token
        // //               },
        // //     data:{
        // //             userid:"1",
        // //             username:'dwj',
        // //             userphoto:"123",
        // //             opponentid:'2',
        // //             opponentname:"wsk",
        // //             opponentphoto:"456",
        // //             winner:"wsk"
        // //         },
        // //     success(resp){
        // //         console.log(resp);
        // //     }
        // // });

        // }



    },
    modules: {
    }
  })