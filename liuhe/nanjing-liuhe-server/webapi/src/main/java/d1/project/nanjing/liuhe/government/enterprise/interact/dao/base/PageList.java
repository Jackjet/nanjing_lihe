package d1.project.nanjing.liuhe.government.enterprise.interact.dao.base;

import java.util.ArrayList;
import java.util.List;

/**
 * 封装分页对象
 **/
public class PageList {
    private int page;   //当前页
    private int totalElements;   //总行数
    private int pages;    //总页数
    private List content = new ArrayList();

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public List getContent() {
        if (content == null) {
            content = new ArrayList();
        }
        return content;
    }

    public void setContent(List content) {
        this.content = content;
    }

    public int getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(int totalElements) {
        this.totalElements = totalElements;
    }
}
