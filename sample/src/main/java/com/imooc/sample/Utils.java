package com.imooc.sample;

import android.content.Context;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by like on 2017/7/18.
 */

public class Utils {

    //Glide保存图片
    public static void savePicture(final Context context, final String folder, final String url){
        Glide.with(context).load(url).asBitmap().toBytes().into(new SimpleTarget<byte[]>() {
            @Override
            public void onResourceReady(byte[] bytes, GlideAnimation<? super byte[]> glideAnimation) {
                try {
                    String name = url.substring(url.lastIndexOf('/'));
                    File targetFile = new File(folder, name);
                    if (!targetFile.getParentFile().exists())
                        targetFile.getParentFile().mkdirs();
                    if (targetFile.exists()) {
                        targetFile.delete();
                    }
                    targetFile.createNewFile();
                    FileOutputStream output = new FileOutputStream(targetFile);
                    output.write(bytes);
                    output.close();
                    Toast.makeText(context, "图片已成功保存到"+targetFile.getAbsolutePath(), Toast.LENGTH_SHORT).show();
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Toast.makeText(context, "图片保存失败", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
