package ru.megalomaniac.securities.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.megalomaniac.securities.xml.SecuritiesXmlImport;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

@Controller
@RequestMapping("/upload")
public class FileImportController {
    @Autowired
    SecuritiesXmlImport securitiesXmlImport;

    @GetMapping()
    public String uploadForm(){
        return "upload/upload";
    }

    @PostMapping()
    public String uploadFile(@RequestParam("file") MultipartFile file, Model model){
        boolean isSuccessful=false;

        try(InputStream is = file.getInputStream()){

            isSuccessful=securitiesXmlImport.importFromFile(is);
            if(isSuccessful){
                model.addAttribute("recordsCount",securitiesXmlImport.getRecordsCount());
                model.addAttribute("errors",securitiesXmlImport.getErrors());
                securitiesXmlImport.saveToDb();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        model.addAttribute("isSuccessful",isSuccessful);
        return "upload/upload-result";
    }
}
