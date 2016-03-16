package com.Liary.DiaryManipulator;

import android.content.Context;

import java.io.IOException;

/**
 * Created by Laur on 1/19/2015.
 */
public class DiaryWriter {

    public DiaryWriter(){

    }

    public void Write(Context ctx, String i_pass) throws IOException {
        DiaryOpener.OpenDiary('W', ctx);

        DiaryOpener.WriteToDiary(i_pass);
        DiaryOpener.CloseDiary('W');
    }


    public void EraseData(Context ctx) throws  IOException{
        DiaryOpener.OpenDiary('E', ctx);

        DiaryOpener.EraseInfoFromDiary();
        DiaryOpener.CloseDiary('E');
    }

}
