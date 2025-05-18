import { AcGameObject } from "./AcGameObject";
import { Chess } from './Chess';
import { Cells } from './Cells';



export class GameMap extends AcGameObject{
    constructor (ctx,parent,store){//等下在vue文件那边可以通过new一个gamemap使得标签和js连接起来 
        super();
        this.ctx=ctx;
        this.parent=parent;
        this.store=store;
        this.L=0;//动态修改棋盘的大小

        this.flag='BlackChess';

        this.Tg=[];
        this.cols=15;
        this.rows=15;

        this.winner='all';
        this.st=[];
        this.RightSlash=0;
        this.HorizonLine=0;
        this.LeftSlash=0;
        this.VerticalLine=0;
        this.status='playing';


        this.chesses=[ 
            new Chess({id:1,color:'black',r:0,c:0},this),
            new Chess({id:2,color:'white',r:14,c:14},this)
        ];
    }



    init_chess_board()
    {
        // this.g=this.store.state.pk.gamemap;
        // this.Tg=this.store.state.pk.gamemap;

        this.Tg=this.store.state.pk.gamemap;
        // for(let i =0;i<this.cols;i++)
        // {
            
        //     this.Tg[i]=[];
   
        //     for(let j=0;j<this.rows ;j++)
        //     {
        //         this.Tg[i][j]='0';    
        //     }

        // }
    }


    init_chess_flag()
    {
        for(let i =0;i<this.cols;i++)
        {
            this.st[i]=[];
            for(let j=0;j<this.rows ;j++)
            {
                this.st[i][j]=false;
            }
        }
    }
    start(){
        this.init_chess_board();
        this.init_chess_flag();
       
    }

  
    update_size(){
        this.L = Math.min(this.parent.clientWidth/this.cols,this.parent.clientHeight  /this.rows);
        this.ctx.canvas.width=this.L * this.cols;
        this.ctx.canvas.height=this.L* this.rows;

    }


    get_chess_possion(){
        const event=document.querySelector("canvas")
        if(event!==null)
    {
       
        event.onclick=(e)=>{
        
            var x = e.clientX;
            var y = e.clientY;
            //相对于浏览器
            let rect=event.getBoundingClientRect();//找出画布一开始的位置

            
            x -= rect.left;//相对于浏览器的x位置-画布一开始的x位置等于点击的相对位置
            y -= rect.top;
            // console.log("this.store.state.pk.opponent_Id",);
            // console.log("this.store.state.user.id",);
            const UserId=this.store.state.user.id;
            const opponentId=this.store.state.pk.opponent_Id;
            const chesscolor=this.store.state.pk.ChessColor;
            if(UserId!=opponentId&& this.Tg[parseInt(y/this.L)][parseInt(x/this.L)]==='0')
            {
                this.store.state.pk.socket.send(JSON.stringify({
                    event:"Move",
                    userId:UserId,
                    opponentId:opponentId,
                    r:parseInt(y/this.L),
                    c:parseInt(x/this.L),
                    chesscolor:chesscolor
                }))
                this.Tg=this.store.state.pk.gamemap;
         
            }
          
        
        
        // console.log("x:",parseInt(x/this.L),"y:",parseInt(y/this.L));//动态除以L去映射到棋盘中
        // console.log("this.Tg",this.Tg);
        }   

    }

  
    }

    draw_chess(){
        const[chess0,chess1]=this.chesses;

        for(let r=0;r<this.rows;r++)
         {
            for(let c=0;c<this.cols;c++)
            {
                if(this.Tg[r][c]==='1')
                {
                  chess0.cells.push(new Cells(r,c,chess0.color))
                }
                else if(this.Tg[r][c]==='2')
                {
                 chess1.cells.push(new Cells(r,c,chess1.color));
                }
            }
        
         }
    }


    check_is_winner(){
        for(let i=0;i<this.rows;i++)
        {
            for(let j=0;j<this.cols;j++)
            {


                if(this.Tg[i][j]==='1')
                {
                    for(let k=1;k<5;k++)
                    {
                        let a=i-k;
                        let b=j+k;

                        if(a>=0&&a<this.rows&&b>=0&&b<this.cols&&this.Tg[a][b]==='1'&&!this.st[a][b]&&this.Tg[a+1][b-1]=='1')
                        {
                            this.st[a][b]=true;
                            this.RightSlash++;
                     
                        }
                    }//RightSlash

                    for(let k=1;k<5;k++) 
                    {
                        let a=i;
                        let b=j+k;

                        if(a>=0&&a<this.rows&&b>=0&&b<this.cols&&this.Tg[a][b]==='1'&&!this.st[a][b]&&this.Tg[a][b-1]=='1')
                        {
                            this.st[a][b]=true;
                            this.HorizonLine++;
                         
                        }
                    }//HorizonLine
                    

                    for(let k=1;k<5;k++)
                    {
                        let a=i+k;
                        let b=j+k;

                        if(a>=0&&a<this.rows&&b>=0&&b<this.cols&&this.Tg[a][b]==='1'&&!this.st[a][b]&&this.Tg[a-1][b-1]=='1')
                        {
                            this.st[a][b]=true;
                            this.LeftSlash++;
                            
                        }
                    }//LeftSlash

                    for(let k=1;k<5;k++)
                    {
                        let a=i+k;
                        let b=j;

                        if(a>=0&&a<this.rows&&b>=0&&b<this.cols&&this.Tg[a][b]==='1'&&!this.st[a][b]&&this.Tg[a-1][b]=='1')
                        {
                            this.st[a][b]=true;
                            this.VerticalLine++;
                         
                        }
                    }//VerticalLine

           
                    if(this.RightSlash>=4||this.HorizonLine>=4||this.LeftSlash>=4||this.VerticalLine>=4)
                    {
                        this.RightSlash=0;
                        this.HorizonLine=0;
                        this.LeftSlash=0;
                        this.VerticalLine=0;
                        this.init_chess_flag();
                        
                        this.store.commit("updatewinner","winner1");
                        this.store.dispatch("AddPkRecordInfo","BlackChess");
                        return 'winner1'
                    }
                    else{
                        this.RightSlash=0;
                        this.HorizonLine=0;
                        this.LeftSlash=0;
                        this.VerticalLine=0;
                        this.init_chess_flag();
                    }
                }
                if(this.Tg[i][j]==='2')
                {
                    for(let k=1;k<5;k++)
                    {
                        let a=i-k;
                        let b=j+k;

                        if(a>=0&&a<this.rows&&b>=0&&b<this.cols&&this.Tg[a][b]==='2'&&!this.st[a][b]&&this.Tg[a+1][b-1]=='2')
                        {
                            this.st[a][b]=true;
                            this.RightSlash++;
                     
                        }
                    }//RightSlash

                    for(let k=1;k<5;k++) 
                    {
                        let a=i;
                        let b=j+k;

                        if(a>=0&&a<this.rows&&b>=0&&b<this.cols&&this.Tg[a][b]==='2'&&!this.st[a][b]&&this.Tg[a][b-1]=='2')
                        {
                            this.st[a][b]=true;
                            this.HorizonLine++;
                         
                        }
                    }//HorizonLine
                    

                    for(let k=1;k<5;k++)
                    {
                        let a=i+k;
                        let b=j+k;

                        if(a>=0&&a<this.rows&&b>=0&&b<this.cols&&this.Tg[a][b]==='2'&&!this.st[a][b]&&this.Tg[a-1][b-1]=='2')
                        {
                            this.st[a][b]=true;
                            this.LeftSlash++;
                            
                        }
                    }//LeftSlash

                    for(let k=1;k<5;k++)
                    {
                        let a=i+k;
                        let b=j;

                        if(a>=0&&a<this.rows&&b>=0&&b<this.cols&&this.Tg[a][b]==='2'&&!this.st[a][b]&&this.Tg[a-1][b]=='2')
                        {
                            this.st[a][b]=true;
                            this.VerticalLine++;
                         
                        }
                    }//VerticalLine

           
                    if(this.RightSlash>=4||this.HorizonLine>=4||this.LeftSlash>=4||this.VerticalLine>=4)
                    {
                        this.RightSlash=0;
                        this.HorizonLine=0;
                        this.LeftSlash=0;
                        this.VerticalLine=0;
                        this.init_chess_flag();
                                       
                        this.store.commit("updatewinner","winner2");
          
                        this.store.dispatch("AddPkRecordInfo","WhiteChess");
                        return 'winner2'
                    }
                    else{
                        this.RightSlash=0;
                        this.HorizonLine=0;
                        this.LeftSlash=0;
                        this.VerticalLine=0;
                        this.init_chess_flag();
                    }
                }
     
            }
        }
    
    }


   
    print_test(){
        let winner=this.check_is_winner();
        if(winner==='winner1')
        {
            console.log(winner);
            this.status=winner;
        }
        else if(winner==='winner2')
        {
            console.log(winner);

            this.status=winner;
        
        }
    }

    update(){
        this.update_size();
        this.init_chess_board();
        this.get_chess_possion();
        if(this.status==='playing')
        {
        this.draw_chess();
        this.print_test();
        }
        this.render();

    }

    render(){


       for (let  r=0;r<this.rows;r++)
        {
            for(let c=0;c<this.cols;c++)
            {
                this.ctx.fillStyle='#F1D6AB';
                this.ctx.strokeStyle='grey';
                this.ctx.fillRect(r*this.L,c*this.L,this.L,this.L);  
                this.ctx.strokeRect(r*this.L,c*this.L,this.L,this.L);  
            }
        }  
     
    
   
     
       }
}