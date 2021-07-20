package ru.megalomaniac.securities.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.megalomaniac.securities.exceptions.SecuritiesNotFoundException;
import ru.megalomaniac.securities.model.SecuritiesInfo;
import ru.megalomaniac.securities.model.SecuritiesInfoXmlString;
import ru.megalomaniac.securities.xml.securitiesdocument.*;
import ru.megalomaniac.securities.service.SecuritiesInfoService;

import java.util.List;

@RestController
@RequestMapping("/api/securities")
public class SecuritiesRestController {
    @Autowired
    private SecuritiesInfoService securitiesInfoService;

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<XmlDocument> findAll() {

        ResponseEntity<XmlDocument> responseEntity;

        try {
            List<SecuritiesInfo> securitiesInfos = securitiesInfoService.findAll();
            if (securitiesInfos.isEmpty()) {
                responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            else {
                XmlDocument xmlDocument = XmlImport.getDocument(securitiesInfos, XmlDocumentType.SECURITIES);
                responseEntity = new ResponseEntity<>(xmlDocument, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<SecuritiesInfoXmlString> findById(@PathVariable("id") int id) {

        ResponseEntity<SecuritiesInfoXmlString> responseEntity;

        try {
            SecuritiesInfo securitiesInfo = securitiesInfoService.findById(id);
            SecuritiesInfoXmlString securitiesInfoXmlString = new SecuritiesInfoXmlString(securitiesInfo);
            responseEntity = new ResponseEntity<>(securitiesInfoXmlString, HttpStatus.OK);
        }
        catch (SecuritiesNotFoundException e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    @PostMapping()
    public ResponseEntity<HttpStatus> postSecurities(@RequestBody SecuritiesInfoXmlString securitiesInfoXmlString) {
        try {
            SecuritiesInfo securitiesInfo = securitiesInfoXmlString.convertToSecuritiesInfo();
            if(!securitiesInfoService.existsBySecid(securitiesInfo.getSecid()))
                securitiesInfoService.save(securitiesInfo);
            else
                return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping()
    public ResponseEntity<HttpStatus> updateSecurities(@RequestBody SecuritiesInfoXmlString securitiesInfoXmlString) {
        try {
            SecuritiesInfo securitiesInfo = securitiesInfoXmlString.convertToSecuritiesInfo();
            if(securitiesInfoService.existsBySecid(securitiesInfo.getSecid())&&
                    securitiesInfoService.existById(securitiesInfo.getId())
            )
                securitiesInfoService.save(securitiesInfo);
            else
                return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteSecurities(@PathVariable("id") int id) {
        try {
            securitiesInfoService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
