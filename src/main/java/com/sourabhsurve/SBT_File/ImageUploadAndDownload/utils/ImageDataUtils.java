package com.sourabhsurve.SBT_File.ImageUploadAndDownload.utils;

import java.io.ByteArrayOutputStream;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class ImageDataUtils {

    public static byte[]compressImage(byte[] data){
        Deflater deflater=new Deflater();
        deflater.setLevel(Deflater.BEST_COMPRESSION);
        deflater.setInput(data);
        deflater.finish();

        ByteArrayOutputStream baos=new ByteArrayOutputStream(data.length);
        byte[] tmp=new byte[4*1024];

        while (!deflater.finished()){
            int size=deflater.deflate(tmp);
            baos.write(tmp,0,size);

        }
        try {
            baos.close();
        } catch (Exception ignored) {
        }
        return baos.toByteArray();
    }

    public static byte[] decompressImage(byte[]data){
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream baos = new ByteArrayOutputStream(data.length);
        byte[] tmp = new byte[4*1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(tmp);
                baos.write(tmp, 0, count);
            }
            baos.close();
        } catch (Exception ignored) {
        }
        return baos.toByteArray();

    }
}
