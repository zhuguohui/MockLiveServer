package org.example.data;



/**
 * Created by zhuguohui
 * Date: 2024/6/1
 * Time: 13:39
 * Desc:获取随机图片
 */
public class RandomImage {

    //    static final String url= "https://picsum.photos/%d/%d?random=%d";
    static final String url= "https://source.unsplash.com/%dx%d/?beauty&random=%d";


    public static String get(int width,int height){
        return String.format(url,width,height,(int)(Math.random()*100));
    }
}

