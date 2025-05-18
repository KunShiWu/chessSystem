import { AcGameObject } from "./AcGameObject";


export class Chess extends AcGameObject{
    constructor(info,gamemap){
        super();

        this.id=info.id;
        this.r=info.r;
        this.c=info.c;
        this.color=info.color;
        this.gamemap=gamemap;
        this.cells=[]
        this.status='playing';//playing 代表正在游玩，winner1表示玩家一获胜，winner2表示玩家二获胜
    }


    update(){

        this.render();
    }


   
    render(){

        const L= this.gamemap.L;
        const ctx=this.gamemap.ctx;

      

        if(this.cells!=null)
        {



            for (const cell of this.cells)
            {
                
                ctx.fillStyle=this.color;
                ctx.beginPath();
                ctx.arc(cell.x*L,cell.y*L,0.4*L ,0,Math.PI*2);
                ctx.fill();
            }
        }

     
     
    }
}