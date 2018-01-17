package com.pwq.phmain.utils;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author：WenqiangPu
 * @Description
 * @Date：Created in 19:33 2018/1/15
 * @Modified By：
 */
public class DownPic {

    public static void downloadPicture(String path,List<String> urlList) {
        URL url = null;
        int imageNumber = 0;
            for (String urlString : urlList) {
                try {
                    url = new URL(urlString);
                    DataInputStream dataInputStream = new DataInputStream(url.openStream());
                    String imageName = "E:\\图片\\"+path+"\\"+imageNumber + ".jpg";
                    imageName = opeFileSeparatorStr(imageName);
                    File file = new File(imageName);
                    if (!file.getParentFile().exists()) {
                        file.getParentFile().mkdirs();
                    }
                    file.createNewFile();
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = dataInputStream.read(buffer)) > 0) {
                        fileOutputStream.write(buffer, 0, length);
                    }
                    dataInputStream.close();
                    fileOutputStream.close();
                    imageNumber++;
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
    }


    private static String opeFileSeparatorStr(String patternStr){
        if(StringUtils.isNotBlank(patternStr)){
            String fileSeparator = System.getProperties().getProperty("file.separator");
            String osName = System.getProperties().getProperty("os.name");
            if(!StringUtils.contains(osName, "Window")){
                patternStr = patternStr.replace("\\", fileSeparator);
            }
        }
        return  patternStr;
    }
}
