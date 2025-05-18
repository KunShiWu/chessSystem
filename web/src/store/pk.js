
export default ({
    state: {
        status:'matching',
        socket:null,
        opponent_username:"我的对手",
        opponent_photo:"https://cdn.acwing.com/media/article/image/2022/08/09/1_1db2488f17-anonymous.png",
        ChessColor:"",
        gamemap:null,
        opponent_Id:"",
        winner:'all',
    },
    getters: {
    },
    mutations: {
        updateSocket(state,socket)
        {
            state.socket=socket;
        },
        updateOpponent(state,opponent)
        {
            state.opponent_username=opponent.username;
            state.opponent_photo=opponent.photo;
        },
        updateStatus(state,status)
        {
            state.status=status;
        },
        updateGamemap(state,gamemap){
            let g=[];
            for(let i=0 ;i<15;i++)
            {
                g[i]=[];
                for(let j=0;j<15;j++)
                {
                    g[i][j]=gamemap[i].gamemap[j];
                }
          
            }

            state.gamemap=g;


        },
        updateChessColor(state,chesscolor)
        {
            state.ChessColor=chesscolor;
        },
        updateopponentId(state,id)
        {
            state.opponent_Id=id;
       
        },
        updatewinner(state,winner){
            state.winner=winner;
        }

    },
    actions: {
    },
    modules: {
    }
  })