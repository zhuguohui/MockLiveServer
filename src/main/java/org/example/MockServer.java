package org.example;

import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.example.data.Function1;
import org.example.data.Gift;
import org.example.data.MockUser;
import org.example.data.RandomImage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.net.InetAddress;


public class MockServer {

    private static final String TAG = MockServer.class.getSimpleName();

    public static void main(String[] args) {
        MockWebServer mMockWebServer = new MockWebServer();



        mMockWebServer.setDispatcher(new Dispatcher() {
            @NotNull
            @Override
            public MockResponse dispatch(@NotNull RecordedRequest recordedRequest) throws InterruptedException {
                return createResponse();
            }
        });

        try {


            mMockWebServer.start(InetAddress.getLocalHost(),3344);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    private static MockResponse createResponse(){
      return   new MockResponse()
                .withWebSocketUpgrade(new WebSocketListener() {
                    private WebSocket mWebSocket;
                    private Function1<Void, Boolean> callback = new Function1<Void, Boolean>() {
                        @Override
                        public Boolean call(Void unused) {
                            return mWebSocket != null;
                        }
                    };

                    @Override
                    public void onOpen(@NotNull WebSocket webSocket, @NotNull Response response) {
                        super.onOpen(webSocket, response);
                        //有客户端连接时回调
                        Log.e(TAG, "服务器收到客户端连接成功回调：");
                        mWebSocket = webSocket;
//                        mWebSocket.send("我是服务器，你好呀");

                        MockUser user = new MockUser("张三","1001", RandomImage.get(100,100), callback);
                        MockUser user2 = new MockUser("李四","1002", RandomImage.get(100,100),callback);
                        MockUser user3 = new MockUser("王五","1003",RandomImage.get(100,100), callback);
                        MockUser user4 = new MockUser("王六","1004",RandomImage.get(100,100), callback);
                        MockUser user5 = new MockUser("王七","1004", RandomImage.get(100,100),callback);
                        long time=5*60*1000;
                        user.sendGift(time, Gift.CHHH,webSocket);
                        user2.sendGift(time,Gift.HXMT,webSocket);
                        user3.sendGift(time,Gift.LYRM,webSocket);
                        user4.sendGift(time,Gift.LYRM,webSocket);
                        user5.sendGift(time,Gift.LYRM,webSocket);

                    }

                    @Override
                    public void onMessage(@NotNull WebSocket webSocket, @NotNull String text) {
                        super.onMessage(webSocket, text);

                        //   Log.e(TAG, "服务器收到消息：" + text);
                    }

                    @Override
                    public void onClosed(@NotNull WebSocket webSocket, int code, @NotNull String reason) {
                        super.onClosed(webSocket, code, reason);
                        Log.e(TAG, "客户端退出");
                        mWebSocket = null;
                    }

                    @Override
                    public void onFailure(@NotNull WebSocket webSocket, @NotNull Throwable t, @Nullable Response response) {
                        super.onFailure(webSocket, t, response);
                        Log.e(TAG, "连接失败!");
                        mWebSocket = null;
                    }

                    @Override
                    public void onClosing(@NotNull WebSocket webSocket, int code, @NotNull String reason) {
                        super.onClosing(webSocket, code, reason);
                        Log.e(TAG, "正在关闭!");
                        mWebSocket.close(1000,"正常关闭");
                        mWebSocket=null;
                    }
                });
    }
}
