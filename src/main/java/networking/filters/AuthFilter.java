package networking.filters;

import Exceptions.IncorrectJwtException;
import beans.AuthBean;
import database.User;
import jakarta.annotation.Priority;
import jakarta.ejb.EJB;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Cookie;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;
import jakarta.ws.rs.ext.Provider;
import jwt.AuthConf;
import jwt.JWTUtil;
import jwt.UserJWT;

import java.io.IOException;
import java.nio.file.attribute.UserPrincipal;
import java.security.Principal;

@Provider
@Priority(Priorities.AUTHORIZATION)
public class AuthFilter implements ContainerRequestFilter{

    @EJB
    private AuthBean authBean;


    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        if (AuthConf.freePaths.contains(containerRequestContext.getUriInfo().getPath())){
            return;
        }

        Cookie authorizationCookie = containerRequestContext.getCookies().get("jwt_token");
        if (authorizationCookie == null || authorizationCookie.getValue() == null
                || authorizationCookie.getValue().isBlank()) {
            containerRequestContext.abortWith(Response
                    .status(Response.Status.UNAUTHORIZED)
                    .entity("bad jwt token")
                    .build());
            return;
        }

        String token = authorizationCookie.getValue();
        try {
            String id = JWTUtil.parseUsernameFromJWT(token);
            User user = authBean.getUserById(Integer.parseInt(id));
            if (user==null){
                throw new IncorrectJwtException("Incorrect JWT!");
            }

            containerRequestContext.setSecurityContext(new SecurityContext() {
                @Override
                public Principal getUserPrincipal() {
                    return new UserJWT(id);
                }

                @Override
                public boolean isUserInRole(String s) {
                    return false;
                }

                @Override
                public boolean isSecure() {
                    return false;
                }

                @Override
                public String getAuthenticationScheme() {
                    return null;
                }
            });
        } catch (IncorrectJwtException e) {
            containerRequestContext.abortWith(Response
                    .status(Response.Status.UNAUTHORIZED)
                    .entity(e.getMessage())
                    .build());
        }
    }
}
