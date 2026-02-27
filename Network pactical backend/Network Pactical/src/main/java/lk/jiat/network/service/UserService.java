package lk.jiat.network.service;

import jakarta.ws.rs.core.Response;
import lk.jiat.network.dto.LoginRequestDTO;
import lk.jiat.network.dto.TokenDTO;
import lk.jiat.network.entity.User;
import lk.jiat.network.util.HibernateUtil;
import lk.jiat.network.util.JwtUtil;
import org.hibernate.Session;

public class UserService {

    public Response validateUser(LoginRequestDTO dto){
     Session session = HibernateUtil.getSessionFactory().openSession();
     User user = session.createQuery("FROM User u WHERE u.email=:email AND u.password=:password", User.class)
               .setParameter("email",dto.getEmail())
               .setParameter("password",dto.getPassword())
               .getSingleResultOrNull();

       session.close();

       if (user==null){
           return Response.status(Response.Status.BAD_REQUEST).build();
       }

        TokenDTO tokenDTO=new TokenDTO();

       tokenDTO.setAccessToken(JwtUtil.genarateToken(user.getEmail()));
       tokenDTO.setRefreshToken(JwtUtil.generateRefreshToken(user.getEmail()));


        return Response.status(Response.Status.OK).entity(tokenDTO).build();
    }



}
