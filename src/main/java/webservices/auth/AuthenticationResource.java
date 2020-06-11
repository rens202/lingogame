package webservices.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import domain.User;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import org.apache.commons.codec.binary.Hex;
import org.json.JSONObject;

import persistence.UserDao;
import persistence.UserDaoImpl;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.AbstractMap;
import java.util.Calendar;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/authentication")
@Consumes(APPLICATION_JSON)
public class AuthenticationResource {
    final static public Key key = MacProvider.generateKey();

    @POST
    @Produces("application/json")
    @Consumes(APPLICATION_JSON)
    public Response authenticateUser(String jsondata) throws IOException {
        try {
            //Authenticate the user against the database
            UserDao dao = new UserDaoImpl();
            JSONObject object = new JSONObject(jsondata);
            String username = object.get("username").toString();
    		String password = object.get("password").toString();
            User user = new User(username, password);
            user.setPassword(hashPassword(user.getPassword()));
            
            Boolean check = dao.checkUser(user.getUsername(), user.getPassword());

            if (!check) {
                throw new IllegalArgumentException("No user found!");
            }

            System.out.println(String.format(" --- Login [Username: %s] [", user.getUsername()));
            String token = createToken(user.getUsername(), "user" );
            AbstractMap.SimpleEntry<String, String> JWT = new AbstractMap.SimpleEntry<>("JWT", token);

            return Response.ok(JWT).build();
        } catch (JwtException | IllegalArgumentException e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    @POST
    @Path("/register")
    @Produces(APPLICATION_JSON)
    @Consumes(APPLICATION_JSON)
    public Response registerUser(String jsondata) throws IOException {
        UserDao dao = new UserDaoImpl();
        JSONObject object = new JSONObject(jsondata);

		String username = object.get("username").toString();
		String password = object.get("password").toString();
        User user = new User(username, password);
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            return Response.status(422, "Password is required!").build();
        }

        user.setPassword(hashPassword(user.getPassword()));

        if (dao.findByUsername(user.getUsername()) != null) {
            return Response.status(401, "Username!").build();
        }
        if (dao.saveNewUser(user)) {
            return Response.ok().build();}
        return Response.serverError().build();
    }

    private String createToken(String username, String role) {
        Calendar expiration = Calendar.getInstance();
        expiration.add(Calendar.MINUTE, 30);

        return Jwts.builder()
                .setSubject(username)
                .setExpiration(expiration.getTime())
                .claim("role", role)
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
    }

    private String hashPassword(final String password) {
        String salt = "6969";
        int iterations = 10000;
        int keyLength = 512;
        char[] passwordChars = password.toCharArray();
        byte[] saltBytes = salt.getBytes();
        byte[] hashedBytes;

        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
            PBEKeySpec spec = new PBEKeySpec(passwordChars, saltBytes, iterations, keyLength);
            SecretKey key = skf.generateSecret(spec);
            hashedBytes = key.getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
        return Hex.encodeHexString(hashedBytes);
    }
}

