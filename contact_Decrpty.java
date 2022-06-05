import sun.rmi.runtime.Log;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Logger;

public class contact_Decrypt {



/*
* GDA[Hook f(java.io.File,java.io.File,java.lang.String,c.h.a.d.p.p0)]
        arg0 = /storage/emulated/0/SmartSwitch/OtgBackupTemp/CONTACT/Contact.bk
        arg1 = /storage/emulated/0/SmartSwitch/OtgBackupTemp/CONTACT/CONTACT.zip
        arg2 = 4AC4B05457336E3C59E6CD6F2F67703B1F08
        arg3 = LEVEL_1
        return true
*
* */
    public static boolean f(File p0, File p1, String key){
        File parentFile;
        InputStream inputStream;
        FileOutputStream fileOutputStream;
        Object[] objArray;
        boolean b = true;
        int i2;
//        if ((parentFile = p1.getParentFile()) != null) {
//            t.X0(parentFile);
//        }
//        t.X0(parentFile);
        try{
            int i = 0;
            inputStream = l(new FileInputStream(p0),  key);
            fileOutputStream = new FileOutputStream(p1);
            byte[] uobyteArray = new byte[1024];
            while ((i2 = inputStream.read(uobyteArray, i, 1024)) != -1) {
                fileOutputStream.write(uobyteArray, i, i2);
            }

            inputStream.close();
        }catch(java.lang.Exception e8){
            Logger.getLogger(null, "decryptInternal in[%s], out[%s], ex[%s]");
            b =false;
        }
        return b;
    }



    public static InputStream l(InputStream p0,  String key) throws NoSuchPaddingException, NoSuchAlgorithmException, UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException {
        Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
        IvParameterSpec ivParameterS = new IvParameterSpec(D(p0, instance.getBlockSize()));
        SecretKeySpec secretKeySpec = H(key);
        instance.init(2, secretKeySpec, ivParameterS);
        return new CipherInputStream(p0, instance);
    }


    public static SecretKeySpec H(String p0) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest instance = MessageDigest.getInstance("SHA-256");
        instance.update(p0.getBytes("UTF-8"));
        byte[] uobyteArray = new byte[16];
        System.arraycopy(instance.digest(), 0, uobyteArray, 0, 16);
        return new SecretKeySpec(uobyteArray, "AES");
    }

    public static byte[] D(InputStream p0,int p1){
        int size;
        Object[] objArray;
        Object[] objArray1;
        if (p0 == null) {
            Logger.getLogger(null, "getBlock null param");
            return null;
        }else {
            byte[] uobyteArray = new byte[p1];
            int i = 1;
            try{
                if ((size = p0.read(uobyteArray)) != p1) {
                    objArray1 = new Object[i];
                    objArray1[0] = Integer.valueOf(size);
                    Logger.getLogger("setBlock wrong size of salt %d");
                    return null;
                }
            }catch(java.io.IOException e6){

                Logger.getLogger(null, "setBlock ex : %s");
            }
            return uobyteArray;
        }
    }




    public static void main(String[] args) {
        File infile = new File("C:\\Users\\Administrator\\Documents\\Samsung\\SmartSwitch\\backup\\SM-G920I\\SM-G920I_8616675413419\\SM-G920I_20220604113507\\CONTACT\\TEMP - 副本\\Contact.bk");
        File outfile = new File("C:\\Users\\Administrator\\Documents\\Samsung\\SmartSwitch\\backup\\SM-G920I\\SM-G920I_8616675413419\\SM-G920I_20220604113507\\CONTACT\\TEMP - 副本\\Contact.zip");
        f(infile,outfile,"92A9A88DB98FC51A9BDD9049C52242E2182B");
    }
}
