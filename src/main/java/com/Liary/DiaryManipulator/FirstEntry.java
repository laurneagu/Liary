package com.Liary.DiaryManipulator;

import android.content.Context;

import java.io.File;
import java.io.IOException;

/**
 * Created by Laur on 2/19/2015.
 */
public class FirstEntry {
    private final static String FILENAME = "firstEntry.txt";

    public static boolean isFirstEntry(Context ctx) throws IOException {
        File file = ctx.getFileStreamPath(FILENAME);
        if(!file.exists()) {
            file.createNewFile();
            return true;
        }
        else
        {
            return  false;
        }
    }
}
