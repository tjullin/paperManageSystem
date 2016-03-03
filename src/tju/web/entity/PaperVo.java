package tju.web.entity;

import java.util.Date;

/**
 * Created by llin on 16/1/14.
 */
public class PaperVo {
    private Paper paper;
    private int downloadCnt;//记录论文的下载次数
    private Date startDate;//记录论文的下载的开始日期

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

    private Date endDate;

    public Paper getPaper() {
        return paper;
    }

    public void setPaper(Paper paper) {
        this.paper = paper;
    }

    public int getDownloadCnt() {
        return downloadCnt;
    }

    public void setDownloadCnt(int downloadCnt) {
        this.downloadCnt = downloadCnt;
    }
}
