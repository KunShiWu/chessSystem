package com.chess.backend.consumer;

import com.alibaba.fastjson.JSONObject;
import com.chess.backend.consumer.utils.JwtAuthentication;
import com.chess.backend.mapper.ChessBoardMapper;
import com.chess.backend.mapper.UserMapper;
import com.chess.backend.pojo.Chessboard;
import com.chess.backend.pojo.User;
import com.chess.backend.server.user.chessboard.ChessBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

@Component
@ServerEndpoint("/websocket/{token}")
public class WebSocketServer {

    private final static ConcurrentHashMap<Integer,WebSocketServer> users=new ConcurrentHashMap<>();

    private final static CopyOnWriteArraySet<User> matchpool=new CopyOnWriteArraySet<>();
    private User user;
    private Session session=null;

    private static UserMapper userMapper;

    private static  Game game=new Game();
    public static ChessBoardService  chessBoardService;

    @Autowired
    public  void setUserMapper(UserMapper userMapper)
    {
        WebSocketServer.userMapper=userMapper;
    }

    @Autowired
    public void setChessBoardMapper(ChessBoardService chessBoardService){
        WebSocketServer.chessBoardService=chessBoardService;
    }

    @OnOpen
    public void onOpen(Session session, @PathParam("token") String token) throws IOException {
        this.session=session;//通过token将前端创建的socket类session传给后端，与后端创建的websocket类绑定
        // 建立连接
        System.out.printf("用户连接成功\n");
        Integer userId= JwtAuthentication.getUserId(token);

        this.user=userMapper.selectById(userId);

        System.out.println(users.size());

        if(this.user!=null)
        {
            users.put(userId,this);
        }
        else {
            this.session.close();
        }



    }

    @OnClose
    public void onClose() {
        // 关闭链接
        System.out.printf("用户断开连接成功\n");

        if(this.user!=null){
            users.remove(this.user.getId());
            matchpool.remove(this.user);
        }
    }

    private void startMatching(){
        System.out.println("开始匹配");
        matchpool.add(this.user);

        while (matchpool.size() >= 2) {
            Iterator<User> it=matchpool.iterator();
            User a=it.next(),b=it.next();


            List<Chessboard> list= game.Init_chessboard();
            matchpool.remove(a);
            matchpool.remove(b);

            JSONObject respA=new JSONObject();

            respA.put("event","startMatching");
            respA.put("opponent_username",b.getUsername());
            respA.put("opponent_photo",b.getPhoto());
            respA.put("ChessColor","BlackChess");
            respA.put("opponent_id",b.getId());
            respA.put("gamemap",list);
            users.get(a.getId()).sendMessage(respA.toJSONString());


            JSONObject respB=new JSONObject();

            respB.put("event","startMatching");
            respB.put("opponent_username",a.getUsername());
            respB.put("opponent_photo",a.getPhoto());
            respB.put("gamemap",list);
            respB.put("ChessColor","WhiteChess");
            respB.put("opponent_id",b.getId());
            users.get(b.getId()).sendMessage(respB.toJSONString());



        }

    }

    private void stopMatching(){
        System.out.println("停止匹配");
        matchpool.remove(this.user);
    }

    private void Move(Integer userId,Integer opponentId,String r,String c,String chesscolor){
        JSONObject resp=new JSONObject();

        resp.put("event","Move");
        if(!user.getId().equals(opponentId))
        {

            resp.put("opponent_id",user.getId());
            List<Chessboard> list=game.Move_chess(r,c,chesscolor);
            resp.put("gamemap",list);

            users.get(user.getId()).sendMessage(resp.toJSONString());
            users.get(opponentId).sendMessage(resp.toJSONString());
        }

    }

    @OnMessage
    public void onMessage(String message, Session session) {
        // 从Client接收消息
        System.out.println("接收消息\n");

        JSONObject data=JSONObject.parseObject(message);
        String event=data.getString("event");
        if("startMatching".equals(event))
        {
            startMatching();

        }else if("stopMatching".equals(event)){
            stopMatching();
        }else if("Move".equals(event))
        {
          Integer userId=data.getInteger("userId");
          Integer opponentId =data.getInteger("opponentId");
          String r=data.getString("r");
          String c=data.getString("c");
          String chesscolor=data.getString("chesscolor");
          Move(userId,opponentId,r,c,chesscolor);
        }
    }


    public void sendMessage(String message)//自己设计的向前端发送消息
    {
        synchronized (this.session)
        {
            try{
                this.session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }


}
