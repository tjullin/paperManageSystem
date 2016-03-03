package tju.web.service;

import tju.web.dao.DownloadDao;
import tju.web.dao.PaperDao;
import tju.web.dao.UserDao;
import tju.web.entity.Download;
import tju.web.entity.Paper;
import tju.web.entity.User;

import java.sql.SQLException;
import java.util.Date;

/**
 * Created by gmy on 16/1/12.
 */
public class DownloadService {
    private DownloadDao downloadDao = new DownloadDao();
    private UserDao userDao = new UserDao();
    private PaperDao paperDao = new PaperDao();

    /**
     * 记录下载论文操作,成功返回1,积分不够返回0,失败返回-1,返回2代表已下载过(非首次下载不扣积分)
     * @param pid 文章ID
     * @param uid 用户ID
     * @return 成功返回>0,积分不够返回0,失败返回-1,返回2代表已下载过
     */
    public int downPaper(int pid, int uid){
        try {
            boolean everDownloaded = downloadDao.findDownload(pid, uid);//记录当前用户是否下载过
            Paper paper = paperDao.findPaperById(pid);//根据pid获取文章信息
            if (!everDownloaded) {//如果文章未被下载，那么积分足够直接下载，否则终止下载操作
                User user = userDao.findUserById(uid);
                if (user.getScore() < paper.getScore()) {
                    return 0;
                }//如果该用户的积分不足，那么直接终止当前下载操作，返回0（表示积分不够）
                //TODO 更新该下载操作造成的积分的变化
                user.setScore(user.getScore() - paper.getScore());
                userDao.updateUser(user);
                User fuser = userDao.findUserById(paper.getUid());
                fuser.setScore(fuser.getScore() + paper.getScore());
                userDao.updateUser(fuser);
            }
            //TODO 添加下载操作的记录，1代表首次下载，2代表非首次下载
            Download download = new Download();
            download.setPid(pid);
            download.setUid(uid);
            download.setFuid(paper.getUid());
            download.setCurtime(new Date());
            if (downloadDao.addDownload(download)) {
                return everDownloaded ? 2 : 1;
            } else {
                return -1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
}
