package tju.web.entity;

import java.util.Date;

/**
 * Created by llin on 16/1/14.
 * 用户信息的值对象
 */
public class UserVo {
    private User user;//绑定的用户的用户信息
    private int score;//绑定用户的积分
    private Date startDate;//日期区间的开始日期
    private Date endDate;//日期区间的终止日期

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
