package org.example.data;

import okhttp3.WebSocket;
import org.example.Log;

import java.util.Random;

public class MockUser {
    String name;

    String userId;

    int index = 0;
    Function1<Void, Boolean> callBack;

    String headUrl;

    public MockUser(String name,String userId,String headUrl, Function1<Void, Boolean> callBack) {
        this.name = name;
        this.userId=userId;
        this.headUrl=headUrl;

        this.callBack = callBack;

    }

    public void sendGift(long time, Gift gift, WebSocket webSocket) {
        new Thread(() -> {

            final long endTime = System.currentTimeMillis() + time;
            Random random = new Random();
            long nowTime = System.currentTimeMillis();
            //可用
            boolean webSocketCanUse = callBack.call(null);
            while (nowTime < endTime && webSocketCanUse) {
                try {
                    Thread.sleep(random.nextInt(50));
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

        }).start();
    }
}
