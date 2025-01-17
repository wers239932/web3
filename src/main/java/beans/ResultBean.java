package beans;


import database.Result;
import database.User;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

import java.util.List;

@Stateless
public class ResultBean {

    @EJB
    PointDBBean pointDBBean;

    @EJB
    AuthBean authBean;

    public Result addResult(int userId, Float x, Float y, Float r, Long timestart) {
        return pointDBBean.createPoint(x, y, r, userId, timestart);
    }

    public List getResults(int userId) {
        User user = authBean.getUserById(userId);
        return pointDBBean.getByOwner(user.getId());
    }
}
