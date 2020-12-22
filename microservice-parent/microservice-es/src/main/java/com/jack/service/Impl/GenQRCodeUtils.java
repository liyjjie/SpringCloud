package com.jack.service.Impl;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

/**
 * @author ：liyongjie
 * @ClassName ：GenQRCodeUtils
 * @date ： 2020-12-22 09:53
 * @modified By： 根据一定的规则进行加密解密
 */
public class GenQRCodeUtils {

//    private static final String SECRET_KEY = "d42b763081466f789e3ff1d91ea48ee2";

    //加密解密所使用的密钥 同 SECRET_KEY
    private static byte[] KEY_VALUE = new byte[]{-44, 43, 118, 48, -127, 70, 111,
            120, -98, 63, -15, -39, 30, -92, -114, -30};

    private static final String SECURE_RANDOM = "5A09";

    /**
     * 获取密钥
     *
     * @return 密钥
     * @throws NoSuchAlgorithmException
     */
    public static byte[] initKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128, new SecureRandom(SECURE_RANDOM.getBytes()));
        SecretKey secretKey = keyGenerator.generateKey();
        return secretKey.getEncoded();
    }

    /**
     * AES加密
     *
     * @param data 要加密的数据
     * @return 加密后的数据
     * @throws Exception
     */
    public static String encrypt(byte[] data) throws Exception {
        SecretKey secretKey = new SecretKeySpec(KEY_VALUE, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptResult = cipher.doFinal(data);
        return Hex.encodeHexString(encryptResult);
    }

    /**
     * AES加密
     *
     * @param data 要加密的数据
     * @return 加密后的数据
     * @throws Exception
     */
    public static String encrypt(byte[] data,byte[] key) throws Exception {
        SecretKey secretKey = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptResult = cipher.doFinal(data);
        return Hex.encodeHexString(encryptResult);
    }

    /**
     * AES解密
     *
     * @param data 要解密的数据
     * @return 解密后的数据, 即源数据
     * @throws Exception
     */
    public static String decrypt(byte[] data) throws Exception {
        SecretKey secretKey = new SecretKeySpec(KEY_VALUE, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptResult = cipher.doFinal(data);
        return new String(decryptResult);
    }

    /**
     * AES解密
     *
     * @param data 要解密的数据
     * @return 解密后的数据, 即源数据
     * @throws Exception
     */
    public static String decrypt(char[] data) throws Exception {
        SecretKey secretKey = new SecretKeySpec(KEY_VALUE, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptResult = cipher.doFinal(Hex.decodeHex(data));
        return new String(decryptResult);
    }

    public static void main(String[] args) {
        String data = "31258370TST120";
        try {
            /**
             * 密钥
             */
            byte[] aesKey = initKey();
            System.out.println(Arrays.toString(aesKey));
            System.out.println("AES key: " + Hex.encodeHexString(aesKey));
            /**
             * 加密后的数据
             */
            String encryptResult = encrypt(data.getBytes());
            System.out.println("AES 加密: " + encryptResult);
            /**
             * 解密后的数据
             */
            String decryptResult = decrypt(Hex.decodeHex(encryptResult.toCharArray()));
            System.out.println("AES 解密: " + decryptResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
