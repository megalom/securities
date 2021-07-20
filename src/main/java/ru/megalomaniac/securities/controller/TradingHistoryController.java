package ru.megalomaniac.securities.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.megalomaniac.securities.model.TradingHistory;
import ru.megalomaniac.securities.service.SecuritiesInfoService;
import ru.megalomaniac.securities.service.TradingHistoryService;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/history")
public class TradingHistoryController {

    @Autowired
    TradingHistoryService tradingHistoryService;
    @Autowired
    SecuritiesInfoService securitiesInfoService;

    @Autowired
    MessageSource messageSource;

    @GetMapping()
    public String index(Model model){
        List<TradingHistory> tradingHistories= tradingHistoryService.findAll();
        model.addAttribute("tradingHistories",tradingHistories);
        return "/history/index";
    }

    @GetMapping("/{id}")
    public String showHistory(@PathVariable(name="id") int id, Model model){
        TradingHistory tradingHistory = tradingHistoryService.findById(id);
        model.addAttribute("tradingHistory",tradingHistory);
        return "history/history";
    }

    @GetMapping("/add")
    public String showNewOperationHisotryForm(Model model){
        TradingHistory tradingHistory = new TradingHistory();
        model.addAttribute("add",true);
        model.addAttribute("tradingHistory",tradingHistory);
        return "history/add";
    }

    @PostMapping()
    public String addNewOperationHistory(Model model,@ModelAttribute("tradingHistory") @Valid TradingHistory tradingHistory,
                                   BindingResult bindingResult){
        if (!securitiesInfoService.existsBySecid(tradingHistory.getSecid()))
            bindingResult.addError(new FieldError(
                    bindingResult.getObjectName(),
                    "secid",
                    messageSource.getMessage("TradingHistory.secid.notexists",null,Locale.getDefault())));
        if(bindingResult.hasErrors()) {
            model.addAttribute("add",true);
            return "/history/add";
        }
        tradingHistory.setId(0);
        tradingHistoryService.save(tradingHistory);
        return "redirect:/history";
    }

    @GetMapping("/{id}/edit")
    public String showEditTradingHistoryForm(@PathVariable(name="id") int id,Model model){
        TradingHistory tradingHistory = tradingHistoryService.findById(id);
        model.addAttribute("tradingHistory",tradingHistory);
        return "history/add";
    }

    @PostMapping("/{id}/edit")
    public String editTradingHistory(@PathVariable(name="id") int id,
                                    @ModelAttribute("tradingHistory") @Valid TradingHistory tradingHistory,
                                    BindingResult bindingResult){
        if (!securitiesInfoService.existsBySecid(tradingHistory.getSecid()))
            bindingResult.addError(new FieldError(
                    bindingResult.getObjectName(),
                    "secid",
                    "TradingHistory.secid.notexists" ));
        if(bindingResult.hasErrors()) {
            return "history/add";
        }
        tradingHistoryService.save(tradingHistory);
        return "redirect:/history";
    }



    @GetMapping("/{id}/delete")
    public String deleteTradingHistory(@PathVariable(name = "id") int id){
        System.out.println("deleting history "+id);
        tradingHistoryService.deleteById(id);
        return "redirect:/history";
    }
}
