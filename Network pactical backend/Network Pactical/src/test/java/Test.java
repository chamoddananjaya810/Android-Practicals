import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Encoders;
import lk.jiat.network.util.HibernateUtil;
import org.hibernate.SessionFactory;

import javax.crypto.SecretKey;
import java.beans.Encoder;

public class Test {

    public static void main(String[] args){
     SecretKey key = Jwts.SIG.HS256.key().build();
      String secretString = Encoders.BASE64.encode(key.getEncoded());
        System.out.println(secretString);
    }
}
