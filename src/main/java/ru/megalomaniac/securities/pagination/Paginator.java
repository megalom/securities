package ru.megalomaniac.securities.pagination;

public interface Paginator {
    // Установить текущую странцу
    void setCurrentPage(int page);

    // Установить лимит записей на странице
    void setRecordsOnPageLimit(int limit);

    // Установить лимит страниц выводимых на экран
    void setPagesLimit(int pagesLimit);

    // Установить количество всех записей
    void setRecordCount(int recordCount);

    // Получить смещение для запроса из таблицы
    int getOffset();

    // Получить начало отсчета страниц для вывода на экран
    int getPageStart();

    // Получить конец отсчета страниц для вывода на экран
    int getPageEnd();

    // Получить количество страниц для вывода на экран
    int getPagesLimit();

    // Получить номер текущей страницы
    int getCurrentPage();

    int getRecordsOnPageLimit();

}
