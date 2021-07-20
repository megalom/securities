package ru.megalomaniac.securities.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.megalomaniac.securities.model.TradingHistory;
import ru.megalomaniac.securities.pagination.Paginator;
import ru.megalomaniac.securities.pagination.SecuritiesPaginator;
import ru.megalomaniac.securities.service.TradingHistoryService;

import java.util.List;
@Controller
@RequestMapping("/summary")
public class SummaryDataController {
    @Autowired
    TradingHistoryService tradingHistoryService;

    @GetMapping()
    public String showOperations(@RequestParam(name="limit",defaultValue = "10") int limit,
                                 @RequestParam(name="page",defaultValue = "1") int page,
                                 Model model){
        Paginator paginator = new SecuritiesPaginator(limit,(int)tradingHistoryService.getCount());
        paginator.setCurrentPage(page);

        List<TradingHistory> tradingHistories=tradingHistoryService
                .findAllFromPage(paginator.getRecordsOnPageLimit(),paginator.getOffset());

        model.addAttribute("tradingHistories",tradingHistories);
        model.addAttribute("pageStart", paginator.getPageStart());
        model.addAttribute("pageEnd", paginator.getPageEnd());
        model.addAttribute("pagesLimit", paginator.getPagesLimit());
        model.addAttribute("currentPage", paginator.getCurrentPage());
        return "summary/index";
    }
}
