package com.jack.utils.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xerial.snappy.Snappy;

/**
 * @author ：liyongjie
 * @ClassName ：AccessTokenJwtUtils
 * @date ： 2019-11-27 15:17
 * @modified By：
 */
public class AccessTokenJwtUtils {

    private static final Logger logger = LoggerFactory.getLogger(AccessTokenJwtUtils.class);

    public AccessTokenJwtUtils() {
    }

    public static String encrypt(AccessToken param) {
        if (param == null) {
            throw new JwtException("encrypt param is null");
        } else {
            try {
                String jsonStr = JSONUtils.toJSON(param);
                byte[] compressJsonStrBytes = Snappy.compress(jsonStr);
                Base64 base64 = new Base64();
                String base64EncodeStr = base64.encodeToString(compressJsonStrBytes);
                return JWT.create().withIssuer("auth0").withClaim("encodeType", "1").withClaim("compressEncodeData", base64EncodeStr).sign(Algorithm.HMAC256("@!@1zrV*zGzwdNe1"));
            } catch (Exception var5) {
                throw new JwtException(var5);
            }
        }
    }

    public static AccessToken dncrypt(String token) {
        if (StringUtils.isBlank(token)) {
            throw new JwtException("dncrypt param [token] is null");
        } else {
            try {
                JWTVerifier verifier = JWT.require(Algorithm.HMAC256("@!@1zrV*zGzwdNe1")).withIssuer("auth0").build();
                DecodedJWT jwt = verifier.verify(token);
                String compressEncodeData = jwt.getClaim("compressEncodeData").asString();
                Base64 base64 = new Base64();
                byte[] base64DecodeBytes = base64.decode(compressEncodeData);
                byte[] uncompressJsonStrBytes = Snappy.uncompress(base64DecodeBytes);
                AccessToken accessToken = (AccessToken)JSONUtils.convert(new String(uncompressJsonStrBytes), AccessToken.class);
                return accessToken;
            } catch (Exception var8) {
                throw new JwtException(var8);
            }
        }
    }

}
