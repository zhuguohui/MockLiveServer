package org.example.data;

public enum Gift {
    LYRM("/g1.json", "蓝烟如梦"),
    CHHH("/g2.json", "橙火辉煌"),
    HXMT("/g3.json", "红霞满天"),
    CYAR("/g4.json", "翠意盎然"),
    ;
    String path;
    String name;

    Gift(String path, String name) {
        this.path = path;
        this.name = name;
    }
}
