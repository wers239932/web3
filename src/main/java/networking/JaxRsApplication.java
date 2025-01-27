package networking;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import networking.filters.AuthFilter;
import networking.filters.CorsFilter;

import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/rest")
public class JaxRsApplication extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();
        classes.add(AddPoint.class);
        classes.add(Auth.class);
        classes.add(getPoints.class);
        classes.add(Logout.class);
        classes.add(CorsFilter.class);
        classes.add(AuthFilter.class);
        return classes;
    }
}
