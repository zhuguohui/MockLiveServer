package org.example.data;

import okhttp3.WebSocket;
import org.example.Log;

import java.util.Random;

public class MockUser {
    String name;
    String giftName;
    int index = 0;
    Function1<Void, Boolean> callBack;

    public MockUser(String name, Function1<Void, Boolean> callBack) {
        this.name = name;

        this.callBack = callBack;

    }

    public void sendGift(long time, String giftName, WebSocket webSocket) {
        new Thread(() -> {
            this.giftName = giftName;
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

                webSocket.send(name + "送出礼物" + giftName + index);
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
