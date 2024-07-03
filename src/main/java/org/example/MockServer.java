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



                    }

                    @Override
                    public void onMessage(@NotNull WebSocket webSocket, @NotNull String text) {
                        super.onMessage(webSocket, text);
                        if(text.startsWith("startTest")){
                            try {
                                String numberStr = text.substring(text.lastIndexOf("=")+1);
                                doTest(Integer.parseInt(numberStr));
                            }catch (Exception e){
                                Log.e("server","启动测试失败:"+e.getMessage());
                            }

                        }
                        Log.e(TAG, "服务器收到消息：" + text);
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

                    private static final int testTime=5*60*1000;
                    private  void doTest(int pNumber){

                        for(int i=0;i<pNumber;i++){
                            String id="100"+i;
                            String name="用户"+i;
                            MockUser mockUser=new MockUser(name,id,callback);
                            mockUser.sendGift(testTime,Gift.values()[i%Gift.values().length],mWebSocket);
                        }
                    }
                });
    }




}
