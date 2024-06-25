package org.example.data;

import java.io.Serializable;


public class MockGiftInfo implements Serializable {

    /**
     * active :
     * adminActive :
     * avatar : https://juchen-1320310092.cos.ap-beijing.myqcloud.com/IMAGE/2024/05/27/1716776552246.png
     * balance :
     * carFlash : https://cos.dlmofei.com/MP4/2024/03/21/7df25215cdf2eff3babe4e3c2efec963.mp4
     * flag : 1
     * giftNum : 10
     * giftsInfo : {"cateid":"1","current":"2024-03-21 09:28:40","flash":"","id":"40","image":"https://cos.dlmofei.com/IMAGE/2024/03/21/172*172*9f794c6d80979a9ddb91e6494aeac935.png","intr":"","ispc":"1","isphone":"0","luckLevel":"2","mp3":"","name":"蓝烟如梦","num":10,"paytype":"0","price":"32","sort":"3","status":"0","tag":"幸运","tagBgColor":"#41F3D6,#1880F1","uptime":"1710984520","winningMultiple":0}
     * isAdmin : 0
     * isAnchor : 0
     * isBeautifulNum : 0
     * isStealth : 0
     * justPushCamara : false
     * liveUserNickName :
     * liveUserid : 10089
     * managedUid :
     * msgContent : 蓝烟如梦
     * msgType : 6
     * nickname : 244
     * richLevel : 58
     * sendGiftTime : 0
     * tagBgColor :
     * userid : 10104
     * username : 10000411
     * usertype : 0
     * winningMultiple : 0
     */

    private String active;
    private String adminActive;
    private String avatar;
    private String balance;
    private String carFlash;
    private String flag;
    private int giftNum;
    private GiftsInfoBean giftsInfo;
    private String isAdmin;
    private String isAnchor;
    private String isBeautifulNum;
    private String isStealth;
    private boolean justPushCamara;
    private String liveUserNickName;
    private String liveUserid;
    private String managedUid;
    private String msgContent;
    private String msgType;
    private String nickname;
    private int richLevel;
    private int sendGiftTime;
    private String tagBgColor;
    private String userid;
    private String username;
    private String usertype;
    private int winningMultiple;


    public static class GiftsInfoBean implements Serializable {
        /**
         * cateid : 1
         * current : 2024-03-21 09:28:40
         * flash :
         * id : 40
         * image : https://cos.dlmofei.com/IMAGE/2024/03/21/172*172*9f794c6d80979a9ddb91e6494aeac935.png
         * intr :
         * ispc : 1
         * isphone : 0
         * luckLevel : 2
         * mp3 :
         * name : 蓝烟如梦
         * num : 10
         * paytype : 0
         * price : 32
         * sort : 3
         * status : 0
         * tag : 幸运
         * tagBgColor : #41F3D6,#1880F1
         * uptime : 1710984520
         * winningMultiple : 0
         */

        private String cateid;
        private String current;
        private String flash;
        private String id;
        private String image;
        private String intr;
        private String ispc;
        private String isphone;
        private String luckLevel;
        private String mp3;
        private String name;
        private int num;
        private String paytype;
        private String price;
        private String sort;
        private String status;
        private String tag;
        private String tagBgColor;
        private String uptime;
        private int winningMultiple;
    }
}
