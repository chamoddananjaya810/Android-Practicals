package lk.jiat.network.controller;


import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lk.jiat.network.dto.TokenDTO;
import lk.jiat.network.util.JwtUtil;

@Path("/auth/refresh")
public class RefeshController {
@POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)

    public Response reNewAccessToken(TokenDTO dto){
    try {
        String  email=JwtUtil.validateToken(dto.getRefreshToken());//subject
        String accessToken=JwtUtil.genarateToken(email);
       TokenDTO tokenDTO =new TokenDTO();
       tokenDTO.setAccessToken(accessToken);
       tokenDTO.setRefreshToken(dto.getRefreshToken());
       return Response.ok().entity(tokenDTO).build();

    } catch (Exception e) {
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

}

}
