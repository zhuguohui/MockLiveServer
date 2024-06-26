package org.example.data;


import org.example.Log;

import java.awt.*;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by zhuguohui
 * Date: 2024/6/1
 * Time: 13:39
 * Desc:获取随机图片
 */
public class RandomImage {



    static final String url="https://fakeimg.pl/%dx%d/%s/%s/?font=noto&text=%s";



    public static String get(int width,int height,Color backgroundColor,Color textColor,String text){

        try {
            text= URLEncoder.encode(text,"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String url = String.format(RandomImage.url, width, height, getColor(backgroundColor), getColor(textColor), text);

        return url;
    }

    public static void main(String[] args) {
        String url = get(100, 100, Color.GREEN, Color.YELLOW, "猪猪侠");
        Log.e("zzz",url);

    }

    private static String getColor(Color color){
        int rgb = color.getRGB();
        String s = Integer.toHexString(rgb);
        s= s.substring(2,8);
        return s;
    }
}

