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


@Path("/auth")
public class Auth {
    @EJB
    private AuthBean authBean;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String authenticate(String json) {
        ObjectMapper mapper = new ObjectMapper();
        Integer token = null;
        try {
            JsonNode node = mapper.readTree(json);
            String mode = node.get("access_type").asText();
            switch (mode) {
                case "login":
                    token = authBean.login(node.get("username").asText(), node.get("password").asText());
                    break;
                case "register":
                    token = authBean.register(node.get("username").asText(), node.get("password").asText());
                    break;
            }
            ObjectNode response = mapper.createObjectNode();
            if (token != -1) {
                response.put("token", token);
                response.put("result", "success");
            } else {
                response.put("result", "fail");
            }
            return mapper.writeValueAsString(response);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
