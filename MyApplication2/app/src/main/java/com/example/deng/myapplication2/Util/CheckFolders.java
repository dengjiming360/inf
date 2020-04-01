package com.example.deng.myapplication2.Util;

import android.os.Environment;

import java.io.File;

public class CheckFolders {
    public static String getSDPath(){
        File sdDir = null;
        File sdDir1 = null;
        File sdDir2 = null;
        boolean sdCardExist = Environment.getExternalStorageState()
                .equals(android.os.Environment.MEDIA_MOUNTED);
        if (sdCardExist)
        {
            sdDir = Environment.getExternalStorageDirectory();
            sdDir1 = Environment.getDataDirectory();
            sdDir2 =Environment.getRootDirectory();
            isExist(sdDir.getPath()+"/train");
        }
        return sdDir.getPath()+"/train";
    }
    public static void isExist(String path) {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdir();
        }
    }
}