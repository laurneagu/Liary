package com.Liary.DiaryManipulator;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Laur on 1/19/2015.
 */
public class DiaryOpener {
    private final static String FILENAME = "pass.txt";

    // I/O Streams
    private static FileInputStream m_fis;
    private static FileOutputStream m_fos;


    public static void OpenDiary(char mode, Context ctx) throws IOException {
        if(mode == 'R') {
            // check if file exists
            File file = ctx.getFileStreamPath(FILENAME);
            if(!file.exists()){
                ctx.openFileOutput(FILENAME, Context.MODE_PRIVATE);
            }

            m_fis = ctx.openFileInput(FILENAME);
        }
        else
         if(mode == 'W'){
            m_fos = ctx.openFileOutput(FILENAME, Context.MODE_PRIVATE);
         }
         else{
             if(mode == 'E'){
                 m_fos = ctx.openFileOutput(FILENAME, Context.MODE_PRIVATE);
             }
         }
    }

    public static void WriteToDiary(String i_pass) throws IOException {
        if(m_fos != null){
            m_fos.write(i_pass.getBytes());
        }
    }

    public static void EraseInfoFromDiary() throws IOException {
        if(m_fos != null){
            m_fos.write("".getBytes());
        }
    }

    public static String ReadFromDiary() throws IOException {
       if(m_fis != null) {
           byte[] bytesDiary = new byte[1024];
           m_fis.read(bytesDiary);

           String diary = new String(bytesDiary, "UTF-8");
           return diary;
       }
        else{
           return '\u0000'+"";
        }

    }

    public static void CloseDiary(char mode) throws IOException {
        if(mode == 'R'){
            m_fis.close();
        }
        else{
            if(mode == 'W' || mode == 'E'){
                m_fos.close();
            }
        }
    }
}
