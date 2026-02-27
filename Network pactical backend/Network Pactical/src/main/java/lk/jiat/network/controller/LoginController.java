package lk.jiat.network.controller;


import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lk.jiat.network.dto.LoginRequestDTO;
import lk.jiat.network.service.UserService;

@Path("/auth/login")
public class LoginController {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response userLogin(LoginRequestDTO dto){
        return new UserService().validateUser(dto);
    }
}
