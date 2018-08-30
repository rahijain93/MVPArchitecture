package com.example.rahi.demoarch.data.remote;

import android.graphics.Bitmap;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

public class FileHandler implements IDataResource.fileManagement {

FileOutputStream fileOutputStream;
File saveFilePath;

@Override
    public void saveImage(ByteArrayOutputStream byteArrayOutputStream, File filePath,int position) {

        if (!filePath.exists())
            filePath.mkdir();

        saveFilePath=new File(filePath+"/rowUser_"+position+".png");

        try {
            saveFilePath.createNewFile();
            fileOutputStream = new FileOutputStream(saveFilePath);
            fileOutputStream.write(byteArrayOutputStream.toByteArray());
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

