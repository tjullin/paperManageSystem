package tju.web.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by llin on 16/1/12.
 */
public class Page {
    private int totalRows;      //记录查询的全部结果数量
    private int totalPage;     //自动计算总的分页数量
    private int pageSize;       //每页显示的记录的个数
    private int currentPage;    //当前页数
    private int startIndex;
    private int endIndex;

    public int getStartIndex() {
        return (currentPage - 1) * pageSize;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getEndIndex() {
        return currentPage*pageSize;
    }

    public void setEndIndex(int endIndex) {
        this.endIndex = endIndex;
    }

    private List data = new ArrayList();    //每页显示的数据

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPage() {
        /*
            分页算法:
            if(总行数%每页显示个数 == 0）{
                总行数/每页显示个数
            }else {
                总行数/每页显示个数+1
            }

         */
        return totalRows % pageSize == 0 ? totalRows / pageSize : totalRows / pageSize + 1;
    }

    public void setTotalPage(int totalPages) {
        this.totalPage = totalPages;
    }

    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }
}
