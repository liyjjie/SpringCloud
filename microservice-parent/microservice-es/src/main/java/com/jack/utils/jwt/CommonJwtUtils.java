package com.jack.utils.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.util.Map;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xerial.snappy.Snappy;

/**
 * @author ：liyongjie
 * @ClassName ：CommonJwtUtils
 * @date ： 2019-11-27 15:23
 * @modified By：
 */
public class CommonJwtUtils {

    private static final Logger logger = LoggerFactory.getLogger(CommonJwtUtils.class);

    public CommonJwtUtils() {
    }

    public static String encrypt(Map paramMap) {
        if (paramMap == null) {
            throw new JwtException("encrypt param is null");
        } else {
            try {
                String jsonStr = JSONUtils.toJSON(paramMap);
                byte[] compressJsonStrBytes = Snappy.compress(jsonStr);
                Base64 base64 = new Base64();
                String base64EncodeStr = base64.encodeToString(compressJsonStrBytes);
                return JWT.create().withIssuer("auth0").withClaim("encodeType", "1").withClaim("compressEncodeData", base64EncodeStr).sign(Algorithm.HMAC256("OuTnAsP@6V4lQSML"));
            } catch (Exception var5) {
                throw new JwtException(var5);
            }
        }
    }

    public static Map dncrypt(String token) {
        if (StringUtils.isBlank(token)) {
            throw new JwtException("dncrypt param [token] is null");
        } else {
            try {
                JWTVerifier verifier = JWT.require(Algorithm.HMAC256("OuTnAsP@6V4lQSML")).withIssuer("auth0").build();
                DecodedJWT jwt = verifier.verify(token);
                String compressEncodeData = jwt.getClaim("compressEncodeData").asString();
                Base64 base64 = new Base64();
                byte[] base64DecodeBytes = base64.decode(compressEncodeData);
                byte[] uncompressJsonStrBytes = Snappy.uncompress(base64DecodeBytes);
                return JSONUtils.convertMap(new String(uncompressJsonStrBytes));
            } catch (Exception var7) {
                throw new JwtException(var7);
            }
        }
    }
}
