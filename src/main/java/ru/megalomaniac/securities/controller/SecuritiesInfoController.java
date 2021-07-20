package ru.megalomaniac.securities.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.megalomaniac.securities.model.SecuritiesInfo;
import ru.megalomaniac.securities.service.SecuritiesInfoService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/securities")
public class SecuritiesInfoController {
    @Autowired
    SecuritiesInfoService securitiesInfoService;

    @GetMapping()
    public String index(Model model){
        List<SecuritiesInfo> securitiesInfos= securitiesInfoService.findAll();
        model.addAttribute("securitiesInfos",securitiesInfos);
        return "/securities/index";
    }

    @GetMapping("/{id}")
    public String index(@PathVariable(name="id") int id, Model model){
        SecuritiesInfo securitiesInfo = securitiesInfoService.findById(id);
        model.addAttribute("securitiesInfo",securitiesInfo);
        return "securities/securities";
    }

    @GetMapping("/add")
    public String showNewSecuritiesInfoForm(Model model){
        SecuritiesInfo securitiesInfo = new SecuritiesInfo();
        model.addAttribute("add",true);
        model.addAttribute("securities",securitiesInfo);
        return "securities/add";
    }

    @PostMapping()
    public String addNewSecurities(Model model,@ModelAttribute("securities") @Valid SecuritiesInfo securitiesInfo,
                                   BindingResult bindingResult){
        if (securitiesInfoService.existsBySecid(securitiesInfo.getSecid()))
            bindingResult.addError(new FieldError(
                    bindingResult.getObjectName(),
                    "secid",
                    "Ценная бумага с таким кодом уже существует" ));
        if(bindingResult.hasErrors()) {
            model.addAttribute("add",true);
            return "/securities/add";
        }
        securitiesInfo.setId(0);
        securitiesInfoService.save(securitiesInfo);
        return "redirect:/securities";
    }

    @GetMapping("/{id}/edit")
    public String showEditSecuritiesInfoForm(@PathVariable(name="id") int id,Model model){
        SecuritiesInfo securitiesInfo = securitiesInfoService.findById(id);
        model.addAttribute("securities",securitiesInfo);
        return "securities/add";
    }

    @PostMapping("/{id}/edit")
    public String editNewSecurities(@PathVariable(name="id") int id,
                                    @ModelAttribute("securities") @Valid SecuritiesInfo securitiesInfo,
                                    BindingResult bindingResult){

        if(bindingResult.hasErrors()) {
            return "securities/add";
        }
        securitiesInfoService.save(securitiesInfo);
        return "redirect:/securities";
    }



    @GetMapping("/{id}/delete")
    public String deleteSecurities(@PathVariable(name = "id") int id){
        securitiesInfoService.deleteById(id);
        return "redirect:/securities";
    }

}
