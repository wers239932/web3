package networking;

import beans.AuthBean;
import beans.ResultBean;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import database.Result;
import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;

@Path("/add_point")
public class AddPoint {
    @EJB
    private ResultBean resultBean;

    @EJB
    private AuthBean authBean;

    @Context
    private SecurityContext securityContext;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPoint(String json) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        try {
            ObjectNode response = mapper.createObjectNode();
            JsonNode node = mapper.readTree(json);
            //Integer token = node.get("token").asInt();
            Integer token = Integer.parseInt(securityContext.getUserPrincipal().getName());
            if(!authBean.isLoggedIn(token)) {
                response.put("status", "error");
                System.out.println(mapper.writeValueAsString(response));
                return Response.ok().entity(response).build();
                //return mapper.writeValueAsString(response);
            }
            Float x, y, r;
            Long timestart = node.get("timestart").asLong();
            x = node.get("x").floatValue();
            y = node.get("y").floatValue();
            r = node.get("r").floatValue();
            Result result = resultBean.addResult(token, x, y, r, timestart);
            if (result == null) {
                response.put("status", "error");
                System.out.println(mapper.writeValueAsString(response));
                return Response.ok().entity(response).build();
                //return mapper.writeValueAsString(response);
            }
            response.put("status", "ok");
            JsonNode resultNode = mapper.valueToTree(result);
            response.put("result", resultNode);
            return Response.ok().entity(response).build();
            //return mapper.writeValueAsString(response);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @OPTIONS
    public Response handlePreflight() {
        // Ответ на preflight-запрос
        return Response.ok()
                .header("Access-Control-Allow-Origin", "http://localhost:3000")
                .header("Access-Control-Allow-Methods", "GET, POST, OPTIONS")
                .header("Access-Control-Allow-Headers", "Content-Type, Authorization, jwt-token")
                .header("Access-Control-Allow-Credentials", "true")
                .build();
    }
}
