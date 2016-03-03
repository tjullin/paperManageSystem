package tju.web.entity;

/**
 * Created by llin on 16/1/12.
 */
public class User {
    private int uid;//用户ID
    private String uname;//用户的账号
    private String pwd;//用户的密码
    private int type;//用户的类型
    private int score;//用户的积分

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
