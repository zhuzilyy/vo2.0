package com.zl.vo_.common.imageView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ImageLoader {

    public static void onLoadImage(final String imageUrl, final LoadImageListener loadImageListener) {
        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                loadImageListener.onLoadImage((Bitmap) msg.obj, null);
            }
        };

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(imageUrl);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    InputStream is = conn.getInputStream();
                    Bitmap bitmap = BitmapFactory.decodeStream(is);

                    is.close();

                    Message msg = new Message();
                    msg.obj = bitmap;
                    handler.sendMessage(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected void finalize() throws Throwable {
                super.finalize();
            }
        }).start();
    }

    public interface LoadImageListener {
        public void onLoadImage(Bitmap bitmap, String bitmapPath);
    }

}