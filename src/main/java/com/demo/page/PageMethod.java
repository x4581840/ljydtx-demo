package com.demo.page;

/**
 * @Description
 * @Author longjianyong
 * @Date 2020/8/27 3:19 PM
 * @Version 1.0
 **/

/**
 * 基础分页方法
 *
 * @author liuzh
 */
public abstract class PageMethod {

    protected PageMethod() {
    }

    protected static final ThreadLocal<PageInfo> LOCAL_PAGE = new ThreadLocal<>();
    protected static final ThreadLocal<Sort> LOCAL_SORT = new ThreadLocal<>();

    /**
     * 获取 Page 参数
     *
     * @return Page参数
     */
    public static PageInfo getLocalPage() {
        return LOCAL_PAGE.get();
    }

    /**
     * 设置 Page 参数
     *
     * @param info info
     */
    protected static void setLocalPage(PageInfo info) {
        LOCAL_PAGE.set(info);
    }

    /**
     * 移除本地变量
     */
    public static void clearPage() {
        LOCAL_PAGE.remove();
    }

    /**
     * 设置 Sort 参数
     *
     * @param sort sort
     */
    protected static void setLocalSort(Sort sort) {
        LOCAL_SORT.set(sort);
    }

    /**
     * 获取 Sort 参数
     *
     * @return Sort参数
     */
    public static Sort getLocalSort() {
        return LOCAL_SORT.get();
    }

    /**
     * 移除本地变量
     */
    public static void clearSort() {
        LOCAL_SORT.remove();
    }


    /**
     * 获取任意查询方法的count总数
     *
     * @param select select
     * @return count总数
     */
    public static long count(Select select) {
        PageInfo info = startPage(0, Integer.MAX_VALUE, true);
        select.doSelect();
        return info.getTotal();
    }

    /**
     * 开始分页
     *
     * @param pageNum  页码
     * @param pageSize 每页显示数量
     * @return pageInfo
     */
    public static PageInfo startPage(int pageNum, int pageSize) {
        return startPage(pageNum, pageSize, true);
    }

    /**
     * 开始分页
     *
     * @param pageNum  页码
     * @param pageSize 每页显示数量
     * @param count    是否进行count查询
     * @return pageInfo
     */
    public static PageInfo startPage(int pageNum, int pageSize, boolean count) {
        PageInfo page = new PageInfo(pageNum, pageSize, count);
        setLocalPage(page);
        return page;
    }

    /**
     * 开始分页和排序
     *
     * @param pageRequest 分页封装对象
     * @return pageInfo
     */
    public static PageInfo startPageAndSort(PageRequest pageRequest) {
        return startPageAndSort(pageRequest, true);
    }

    /**
     * 开始分页和排序
     *
     * @param pageRequest 分页封装对象
     * @param count       是否进行count查询
     * @return pageInfo
     */
    public static PageInfo startPageAndSort(PageRequest pageRequest, boolean count) {
        PageInfo page = new PageInfo(pageRequest.getPage(), pageRequest.getSize(), count);
        setLocalPage(page);
        setLocalSort(pageRequest.getSort());
        return page;
    }

    public static void startSort(Sort sort) {
        setLocalSort(sort);
    }

}

