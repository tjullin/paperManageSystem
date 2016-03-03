package tju.web.entity;

import java.util.Date;

/**
 * Created by llin on 16/1/12.
 */
public class Comment {
    private int cid;//评论ID
    private int pid;//别评论的文章的文章ID
    private int uid;//评论文章的用户的用户ID
    private String content;//评论的内容
    private Date curtime;//评论的时间
    private String uname;//进行评论的用户的账号

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public Date getCurtime() {
        return curtime;
    }

    public void setCurtime(Date curtime) {
        this.curtime = curtime;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
