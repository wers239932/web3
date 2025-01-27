package jwt;

import java.util.ArrayList;
import java.util.List;

public class AuthConf {
    public static List<String> freePaths = new ArrayList<String>();
    static {
        freePaths.add("/");
    }
}
