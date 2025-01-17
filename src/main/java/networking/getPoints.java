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
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/getPoints")
public class getPoints {
    @EJB
    private ResultBean resultBean;

    @EJB
    private AuthBean authBean;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String getPoints(String json) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        Integer token = null;
        try {
            ObjectNode response = mapper.createObjectNode();
            JsonNode node = mapper.readTree(json);
            token = node.get("token").asInt();

            if(!authBean.isLoggedIn(token)) {
                response.put("status", "error");
                return mapper.writeValueAsString(response);
            }
            response.put("status", "ok");

            List<Result> history = resultBean.getResults(token);
            response.put("history", mapper.valueToTree(history));
            return mapper.writeValueAsString(response);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
