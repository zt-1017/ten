package entity;


import java.util.List;

/**
 * @ClsaaName PapgResult
 * Version information 1.0
 * @Date 2020/7/10 15:23
 */
public class PageResult<T> {

    private Long total;
    private List<T> rows;

    public PageResult() {
    }

    public PageResult(Long total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "PapgResult{" +
                "total=" + total +
                ", rows=" + rows +
                '}';
    }
}
