package com.justec.mycamera;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class FileUtil {

    public static Bitmap getBitmap(String path) {
        try {
            FileInputStream is = new FileInputStream(path);
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inTempStorage = new byte[100 * 1024];
            options.inPreferredConfig = Bitmap.Config.RGB_565;
            options.inPurgeable = true;
            options.inSampleSize = 4;
            options.inInputShareable = true;
            return BitmapFactory.decodeStream(is, null, options);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static File createImageFile(Context context) throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.SIMPLIFIED_CHINESE).format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        assert storageDir != null;
        if (!storageDir.exists()) {
            storageDir.mkdir();
        }
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );
        return image;
    }
}
