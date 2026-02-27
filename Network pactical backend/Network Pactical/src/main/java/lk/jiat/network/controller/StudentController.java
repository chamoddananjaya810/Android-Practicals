package lk.jiat.network.controller;


import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lk.jiat.network.entity.Student;
import lk.jiat.network.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

@Path("/students")
public class StudentController {
    @GET
    @Path("/get-all")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON})

    public Response getAllStudents() {
        try (Session hibenateSession = HibernateUtil.getSessionFactory().openSession()) {
        List<Student> studentList  = hibenateSession.createQuery("FROM Student s", Student.class)
                    .getResultList();
        return Response.ok().entity(studentList).build();
        }
    }
}
