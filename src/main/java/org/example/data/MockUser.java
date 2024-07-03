package org.example.data;

import okhttp3.WebSocket;
import org.example.Log;

import java.awt.*;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MockUser {
    String name;

    String userId;

    int index = 0;
    Function1<Void, Boolean> callBack;

    String headUrl;

    Random random = new Random();

    static ExecutorService executor = Executors.newCachedThreadPool();

    public MockUser(String name,String userId , Function1<Void, Boolean> callBack) {
        this.name = name;
        this.userId=userId;
        //生成随机图片
        this.headUrl=RandomImage.get(100,100,getRandomColor(),getRandomColor(),name);

        this.callBack = callBack;

    }

    private static Color getRandomColor(){
        Random random=new Random();
        Color color=new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255));
        return color;
    }

    public void sendGift(long time, Gift gift, WebSocket webSocket) {

       executor.submit(() -> {

            final long endTime = System.currentTimeMillis() + time;

            long nowTime = System.currentTimeMillis();
            //可用
            boolean webSocketCanUse = callBack.call(null);
            while (nowTime < endTime && webSocketCanUse) {
                try {
//                    Thread.sleep(random.nextInt(50));
                    Thread.sleep(20);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                boolean win= random.nextInt(10)>8;
                int winningMultiple=win?random.nextInt(1000):0;
                String info = GiftUtil.createGift(gift, name,userId,headUrl, winningMultiple);
                webSocket.send(info);
                index++;
                nowTime = System.currentTimeMillis();
                webSocketCanUse = callBack.call(null);
                if (!webSocketCanUse ) {
                    Log.e("User","用户"+name+" 退出");
                }
            }
        });
    }
}
