package me.zhoubl.zfinal.common.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Base64OutputStream;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * Created by zhoubl
 */
public class UtilCodec {

    private static final String DEFAULT_URL_ENCODING = "UTF-8";

    public final static String NONE = "NONE";
    public final static String MD5 = "MD5";
    public final static String SHA_1 = "SHA-1";
    public final static String SHA_256 = "SHA-256";
    public final static String SHA_384 = "SHA-384";
    public final static String SHA_512 = "SHA-512";

    public static String pwdCodec(String pwd, String salt) {
        return digestString(pwd + "_" + salt, SHA_512);
    }

    /**
     * 加密文件算法
     */
    public static void digestFile(String filename, String algorithm) throws IOException, NoSuchAlgorithmException {
        byte[] b = new byte[1024 * 4];
        int len = 0;
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            fis = new FileInputStream(filename);
            while ((len = fis.read(b)) != -1) {
                md.update(b, 0, len);
            }
            byte[] digest = md.digest();
            StringBuffer fileNameBuffer = new StringBuffer(128).append(filename).append(".").append(algorithm);
            fos = new FileOutputStream(fileNameBuffer.toString());
            OutputStream encodedStream = new Base64OutputStream(fos);
            encodedStream.write(digest);
            encodedStream.flush();
            encodedStream.close();
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                if (fis != null)
                    fis.close();
            } catch (Exception ignored) {
                fis = null;
            }
            try {
                if (fos != null)
                    fos.close();
            } catch (Exception ignored) {
                fos = null;
            }
        }
    }

    /**
     * 加密密码算法
     */
    public static String digestString(String value, String alg) {
        String newPass;
        if (alg == null || MD5.equals(alg)) {
            newPass = DigestUtils.md5Hex(value);
        } else if (NONE.equals(alg)) {
            newPass = value;
        } else if (SHA_256.equals(alg)) {
            newPass = DigestUtils.sha256Hex(value);
        } else if (SHA_384.equals(alg)) {
            newPass = DigestUtils.sha384Hex(value);
        } else if (SHA_512.equals(alg)) {
            newPass = DigestUtils.sha512Hex(value);
        } else if (SHA_1.equals(alg)){
        	newPass = DigestUtils.sha1Hex(value);
        } else {
            newPass = DigestUtils.shaHex(value);
        }
        return newPass;
    }

    public static String digestMD5(String value) {
        if (value != null && !"".equals(value)) {
            return digestString(value, MD5);
        } else
            return null;
    }

    public static String encodeBase64(byte[] input) {
        return new String(Base64.encodeBase64(input));
    }

    public static String encodeBase64(String input) {
        try {
            return new String(Base64.encodeBase64(input.getBytes(DEFAULT_URL_ENCODING)));
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }

    public static byte[] decodeBase64(String input) {
        return Base64.decodeBase64(input.getBytes());
    }

    public static String decodeBase64String(String input) {
        try {
            return new String(Base64.decodeBase64(input.getBytes()), DEFAULT_URL_ENCODING);
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }

}
