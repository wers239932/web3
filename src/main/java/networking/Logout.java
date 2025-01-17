package networking;

import beans.AuthBean;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.ejb.EJB;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/logout")
public class Logout {
    @EJB
    private AuthBean authBean;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String logout(String json) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode node = mapper.readTree(json);
            Integer token = node.get("token").asInt();
            authBean.logout(token);

            ObjectNode response = mapper.createObjectNode();
            response.put("status", "ok");
            return mapper.writeValueAsString(response);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
