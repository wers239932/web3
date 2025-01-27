package jwt;
import Exceptions.IncorrectJwtException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

public class JWTUtil {
    private static String jwtKey = "763458695";
    public static String generateJWT(String id){
        return JWT.create().withSubject(id).sign(Algorithm.HMAC256(jwtKey));
    }

    public static String parseUsernameFromJWT(String jwt) throws IncorrectJwtException {
        try {
            return JWT.require(Algorithm.HMAC256(jwtKey)).build().verify(jwt).getSubject();
        } catch (JWTVerificationException e){
            throw new IncorrectJwtException("Incorrect auth token!");
        }
    }
}
