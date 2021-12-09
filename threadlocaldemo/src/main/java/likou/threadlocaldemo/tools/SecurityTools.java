package likou.threadlocaldemo.tools;

import javax.crypto.Cipher;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;


public class SecurityTools {

	public static class RsaKeyPair {
        private String publicKey ="";
        private String privateKey ="";

        public RsaKeyPair(String publicKey, String privateKey) {
            super();
            this.publicKey = publicKey;
            this.privateKey = privateKey;
        }

        public String getPublicKey() {
            return publicKey;
        }
        public String getPrivateKey() {
            return privateKey;
        }
    }

    private static final String ALGORITHM = "RSA";
    private static final String ALGORITHMS_SHA1WithRSA = "SHA1WithRSA";
    private static final String ALGORITHMS_SHA256WithRSA = "SHA256WithRSA";
    private static final String DEFAULT_CHARSET = "UTF-8";
    private static String getAlgorithms(boolean isRsa2) {
        return isRsa2 ? ALGORITHMS_SHA256WithRSA : ALGORITHMS_SHA1WithRSA;
    }

    /**
     * 生成秘钥对
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static RsaKeyPair generaterKeyPair() throws NoSuchAlgorithmException{  
        KeyPairGenerator keygen = KeyPairGenerator.getInstance(ALGORITHM);  
        SecureRandom random = new SecureRandom();  
        //            SecureRandom random = new SecureRandom(seedStr.getBytes()); // 随机因子一样，生成出来的秘钥会一样
        // 512位已被破解，用1024位,最好用2048位 
        keygen.initialize(2048, random);
        // 生成密钥对  
        KeyPair keyPair = keygen.generateKeyPair();  
        RSAPrivateKey privateKey = (RSAPrivateKey)keyPair.getPrivate();  
        RSAPublicKey publicKey = (RSAPublicKey)keyPair.getPublic();   
        String privateKeyStr = Base64.getEncoder().encodeToString(privateKey.getEncoded());
        String publicKeyStr = Base64.getEncoder().encodeToString(publicKey.getEncoded());  
        System.out.println("publicKeyStr=" + publicKeyStr);
        System.out.println("privateKeyStr=" + privateKeyStr);
        return new RsaKeyPair(publicKeyStr,privateKeyStr);
    }

    public static void main(String[] args) {
    	try {
			generaterKeyPair();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    /**
     * 获取公钥
     * @param publicKey
     * @return
     * @throws Exception
     */
    public static RSAPublicKey getPublicKey(String publicKey) throws Exception{  
        byte[] keyBytes = Base64.getDecoder().decode(publicKey);  
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);  
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);  
        return (RSAPublicKey) keyFactory.generatePublic(spec);  
    }

    /**
     * 获取私钥
     * @param privateKey
     * @return
     * @throws NoSuchAlgorithmException 
     * @throws InvalidKeySpecException 
     * @throws Exception
     */
    public static RSAPrivateKey getPrivateKey(String privateKey) throws Exception{  
        byte[] keyBytes = Base64.getDecoder().decode(privateKey);  
//        System.out.println("-----------:"+keyBytes.length);
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);  
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);  
        return (RSAPrivateKey) keyFactory.generatePrivate(spec);  
    }

    /**
     * 公钥加密
     * @param content
     * @param publicKeyStr
     * @return
     */
    public static String encrypt(String content, String publicKeyStr) throws Exception{
        Cipher cipher = null;
        cipher = Cipher.getInstance(ALGORITHM); 
        cipher.init(Cipher.ENCRYPT_MODE, getPublicKey(publicKeyStr));
        byte[] result = cipher.doFinal(content.getBytes(DEFAULT_CHARSET));
        return Base64.getEncoder().encodeToString(result);
    }

    /**
     * 私钥解密
     * @param content
     * @param privatekeyStr
     * @return
     */
    public static String decrypt(String content, String privatekeyStr) throws Exception {
        Cipher cipher = null;
        cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, getPrivateKey(privatekeyStr));  
        byte[] result = cipher.doFinal(Base64.getDecoder().decode(content));
        return new String(result);
    }


}