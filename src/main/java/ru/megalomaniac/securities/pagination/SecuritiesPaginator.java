package ru.megalomaniac.securities.pagination;

public class SecuritiesPaginator implements Paginator{
    private int pagesLimit=6;// Количество страниц отображаемых на экране
    private int recordCount;// Число записей в таблице
    private int currentPage=0;
    private int recordLimit;

    public SecuritiesPaginator(int limit, int recordCount){
        this.recordLimit=limit;
        this.recordCount=recordCount;
    }
    @Override
    public void setCurrentPage(int page) {
        int pageCount=getPageCount();
        if(page<1)
            page=1;
        if(page>pageCount)
            page=pageCount;
        this.currentPage=page;
    }

    @Override
    public void setRecordsOnPageLimit(int limit) {
        this.recordLimit=limit;
    }

    @Override
    public void setPagesLimit(int pagesLimit) {
        this.pagesLimit=pagesLimit;
    }

    @Override
    public void setRecordCount(int recordCount) {
        this.recordCount=recordCount;
    }

    // Вычисление смещения относительно текущей страницы для запроса записей
    @Override
    public int getOffset() {
        return (currentPage-1)*recordLimit;

    }

    // Начало отсчета страниц для вывода на экран
    @Override
    public int getPageStart() {
        int pageStart=currentPage-pagesLimit/2;
        if(pageStart<1)
            pageStart=1;
        return pageStart;
    }

    // Конец отсчета страниц для вывода на экран
    @Override
    public int getPageEnd() {
        int pageEnd=currentPage+pagesLimit/2;
        int pageCount=getPageCount();
        if(pageEnd>pageCount)
            pageEnd=pageCount;
        return pageEnd;
    }

    @Override
    public int getPagesLimit() {
        return this.pagesLimit;
    }

    @Override
    public int getCurrentPage() {
        return this.currentPage;
    }

    @Override
    public int getRecordsOnPageLimit() {
        return this.recordLimit;
    }

    private int getPageCount(){
        int pageCount=recordCount/recordLimit;
        if(pageCount<1)
            pageCount=1;
        return pageCount;
    }
}
