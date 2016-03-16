package com.Liary.DiaryManipulator;

import android.content.Context;

import java.io.IOException;

/**
 * Created by Laur on 1/19/2015.
 */
public class DiaryReader {

    public DiaryReader(){

    }

    public String Read(Context ctx) throws IOException {
        DiaryOpener.OpenDiary('R', ctx);
        String diary = DiaryOpener.ReadFromDiary();
        DiaryOpener.CloseDiary('R');

        return  diary;
    }

}

