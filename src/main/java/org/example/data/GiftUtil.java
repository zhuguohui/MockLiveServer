package org.example.data;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class GiftUtil {

    static Gson gson=new Gson();


    public static String createGift(Gift giftName, String userName, String userId, String headUrl, int winningMultiple){
        InputStream path = GiftUtil.class.getResourceAsStream(giftName.path);
        BufferedReader reader = new BufferedReader(new InputStreamReader(path));
        MockGiftInfo mockGiftInfo = gson.fromJson(reader, MockGiftInfo.class);
        mockGiftInfo.userid=userId;
        mockGiftInfo.avatar=headUrl;
        mockGiftInfo.nickname=userName;
        mockGiftInfo.giftsInfo.winningMultiple=winningMultiple;
        return gson.toJson(mockGiftInfo);
    }



}
