package org.example;

import okhttp3.*;
import okio.ByteString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

public class Client {
    private static final String TAG = Client.class.getSimpleName();

    public static void main(String[] args) {
        init();
    }

    //初始化WebSocket
    public static void init() {
        String ip="";
        try {
             ip= InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
        String mWbSocketUrl = "ws://"+ip+":3344";
        OkHttpClient mClient = new OkHttpClient.Builder()
                .pingInterval(1, TimeUnit.SECONDS)
                .build();
        Request request = new Request.Builder()
                .url(mWbSocketUrl)
                .build();
        mWebSocket = mClient.newWebSocket(request, new WsListener());
      //  mWebSocket.close(0,"不喜欢");
    }


    private static WebSocket mWebSocket;

    //监听事件，用于收消息，监听连接的状态
    static class WsListener extends WebSocketListener {
        @Override
        public void onClosed(@NotNull WebSocket webSocket, int code, @NotNull String reason) {
            super.onClosed(webSocket, code, reason);
        }

        @Override
        public void onClosing(@NotNull WebSocket webSocket, int code, @NotNull String reason) {
            super.onClosing(webSocket, code, reason);
        }

        @Override
        public void onFailure(@NotNull WebSocket webSocket, @NotNull Throwable t, @Nullable Response response) {
            super.onFailure(webSocket, t, response);
            Log.e(TAG,"连接失败:"+t.getMessage());

        }

        int i=0;
        @Override
        public void onMessage(@NotNull WebSocket webSocket, @NotNull String text) {
            super.onMessage(webSocket, text);
            Log.e(TAG, "客户端收到消息:" + text);
//            onWSDataChanged(DATE_NORMAL, text);
            //测试发消息
//            webSocket.send("我是客户端，你好啊");
            i++;
            if(i>1000){

                Log.e(TAG, "客户端不需要礼物了" );
                //1000表示正常关闭
                webSocket.close(1000,"不需要了");
            }
        }

        @Override
        public void onMessage(@NotNull WebSocket webSocket, @NotNull ByteString bytes) {
            super.onMessage(webSocket, bytes);
        }

        @Override
        public void onOpen(@NotNull WebSocket webSocket, @NotNull Response response) {
            super.onOpen(webSocket, response);
            Log.e(TAG, "连接成功！");
            webSocket.send("aaa");
        }
    }


    //发送String消息
    public void send(final String message) {
        if (mWebSocket != null) {
            mWebSocket.send(message);
        }
    }

    //发送byte消息
    public void send(final ByteString message) {
        if (mWebSocket != null) {
            mWebSocket.send(message);
        }
    }

    //主动断开连接
    public void disconnect(int code, String reason) {
        if (mWebSocket != null)
            mWebSocket.close(code, reason);
    }

}