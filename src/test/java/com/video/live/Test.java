package com.video.live;

import cn.hutool.core.io.FastByteArrayOutputStream;
import cn.hutool.crypto.digest.MD5;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.ShortBufferException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * @Author: Deng Yunhu
 * @Date: 2020/3/4 13:38
 */
public class Test {
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        //createPrivateKey();
        try {
            String pwd="{\"machineCode\":asddfasdfdasdfds,\"createData\":\"2020-02-02 00:00:00\",\"expireData\":\"2020-02-02 00:00:00\"}";
            String encrpt = encrpt(pwd);
            /*RSA rsa=new RSA(loadPrivateKey(),loadPubliceKey());
            byte[] encryptByte = rsa.encrypt(pwd.getBytes(StandardCharsets.UTF_8), KeyType.PrivateKey);
            String encrpt = Base64.encode(encryptByte);*/
            System.out.println("密文:");
            System.out.println(encrpt);

            String decrypt = dencrpt(encrpt);

            System.out.println("明文：" + new String(decrypt));
            System.out.println("校验成功:" + pwd.equals(new String(decrypt)));
            System.out.println(MD5.create().digestHex16(encrpt));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static String dencrpt(String cipherData) throws NoSuchAlgorithmException, IOException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, ShortBufferException {
        RSAPublicKey publicKey = loadPubliceKey();
        int key_len = publicKey.getModulus().bitLength() / 8;
        byte[] decode = Base64.decode(cipherData);
        int maxBlockSize = key_len < 0 ? decode.length : key_len;
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        byte[] bytes =doFinal(decode, maxBlockSize, cipher);
        return new String(bytes);
    }

    public static byte[] doFinal(byte[] data, int maxBlockSize, Cipher cipher) throws BadPaddingException, IllegalBlockSizeException, IOException {
        final int dataLength = data.length;

        // 不足分段
        if (dataLength <= maxBlockSize) {
            return cipher.doFinal(data, 0, dataLength);
        }

        // 分段解密
        return doFinalWithBlock(data, maxBlockSize, cipher);
    }

    private static byte[] doFinalWithBlock(byte[] data, int maxBlockSize, Cipher cipher) throws IllegalBlockSizeException, BadPaddingException, IOException {
        final int dataLength = data.length;
        @SuppressWarnings("resource") final FastByteArrayOutputStream out = new FastByteArrayOutputStream();

        int offSet = 0;
        // 剩余长度
        int remainLength = dataLength;
        int blockSize;
        // 对数据分段处理
        while (remainLength > 0) {
            blockSize = Math.min(remainLength, maxBlockSize);
            out.write(cipher.doFinal(data, offSet, blockSize));

            offSet += blockSize;
            remainLength = dataLength - offSet;
        }

        return out.toByteArray();
    }


    public static String encrpt(String plainText) throws NoSuchAlgorithmException, IOException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        RSAPrivateKey privateKey = loadPrivateKey();
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        byte[] bytes = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
        String encode = Base64.encode(bytes);
        return encode;
    }

    public static RSAPublicKey loadPubliceKey() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        BufferedReader br = new BufferedReader(new FileReader("D:\\keystore\\publicKey.keystore"));
        String readLine = null;
        StringBuilder sb = new StringBuilder();
        while ((readLine = br.readLine()) != null) {
            sb.append(readLine);
        }
        br.close();
        byte[] decode = Base64.decode(sb.toString());
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(decode);
        return (RSAPublicKey) keyFactory.generatePublic(keySpec);
    }

    public static RSAPrivateKey loadPrivateKey() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        BufferedReader br = new BufferedReader(new FileReader("D:\\keystore\\privateKey.keystore"));
        String readLine = null;
        StringBuilder sb = new StringBuilder();
        while ((readLine = br.readLine()) != null) {
            sb.append(readLine);
        }
        br.close();
        byte[] decode = Base64.decode(sb.toString());
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(decode);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
    }

    public static String createPrivateKey() throws NoSuchAlgorithmException, IOException {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(1024, new SecureRandom());
        KeyPair keyPair = generator.generateKeyPair();
        PrivateKey aPrivate = keyPair.getPrivate();
        PublicKey aPublic = keyPair.getPublic();
        String pvKey = Base64.encode(aPrivate.getEncoded());
        String pbKey = Base64.encode(aPublic.getEncoded());
        FileWriter fileWriter1 = new FileWriter("D:\\keystore\\publicKey.keystore");
        FileWriter fileWriter2 = new FileWriter("D:\\keystore\\privateKey.keystore");
        BufferedWriter pbWriter = new BufferedWriter(fileWriter1);
        BufferedWriter prWriter = new BufferedWriter(fileWriter2);
        pbWriter.write(pbKey);
        pbWriter.flush();
        prWriter.write(pvKey);
        prWriter.flush();
        fileWriter1.close();
        pbWriter.close();
        fileWriter2.close();
        prWriter.close();
        return null;
    }
}
