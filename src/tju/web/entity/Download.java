package tju.web.entity;

import java.util.Date;

/**
 * Created by llin on 16/1/12.
 * 描述一次下载操作的类型
 */
public class Download {
    private int did;//下载操作的下载ID
    private int pid;//下载的文章的文章ID
    private int uid;//执行本次下载操作的用户的用户ID
    private int fuid;//下载的文章归属的用户的用户ID
    private Date curtime;//执行下载操作的时间

    public int getDid() {
        return did;
    }

    public void setDid(int did) {
        this.did = did;
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

    public int getFuid() {
        return fuid;
    }

    public void setFuid(int fuid) {
        this.fuid = fuid;
    }

    public Date getCurtime() {
        return curtime;
    }

    public void setCurtime(Date curtime) {
        this.curtime = curtime;
    }
}
