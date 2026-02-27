package lk.jiat.network.api;


import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lk.jiat.network.entity.Student;
import lk.jiat.network.util.HibernateUtil;
import lk.jiat.network.util.JwtUtil;
import org.hibernate.Session;

import java.util.List;

@Path("/test") // මේක තමයි URL එක වෙන්නේ: /api/v1/test
public class Test {

    @GET
    @Produces(MediaType.TEXT_PLAIN) // මේ පාර අපි යවන්නේ සරල Text එකක්
    public Response testEndpoint() {
        return  Response.ok(JwtUtil.genarateToken("admin@gmail.com")).build();
    }

    @GET
    @Path("/student")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllStudent() {
       Session hibeSession = HibernateUtil.getSessionFactory().openSession();
       java.util.List<Student> fromStudentS =hibeSession.createQuery("FROM Student s", Student.class)
               .getResultList();
       hibeSession.close();
        return Response.ok().entity(fromStudentS).build();
    }

}