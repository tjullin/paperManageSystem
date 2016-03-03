package tju.web.entity;

/**
 * Created by llin on 16/1/12.
 *
 */
public class Paper {
    private int pid;//论文的编号
    private String title;//论文的题目
    private String keyword;//论文的关键字
    private String description;//论文的描述
    private int score;//论文下载所需要的积分
    private int uid;//上传该论文的用户ID
    private String type;//论文的类型
    private String filename;//上传的论文存储的文件名

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
