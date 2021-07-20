package ru.megalomaniac.securities.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.io.Serializable;

// Класс для сохранения данных из SecuritiesInfo в xml
@JacksonXmlRootElement(localName = "securities")
public class SecuritiesInfoXmlString implements Serializable {
    private static final long serialVersionUID = 1L;

    @JacksonXmlProperty()
    private String id;
    @JacksonXmlProperty()
    private String secid;
    @JacksonXmlProperty()
    private String regnumber;
    @JacksonXmlProperty()
    private String name;
    @JacksonXmlProperty()
    private String emitentTitle;

    public  SecuritiesInfoXmlString(){

    }

    public SecuritiesInfoXmlString(SecuritiesInfo securitiesInfo){
        this.setId(String.valueOf(securitiesInfo.getId()));
        this.setSecid(securitiesInfo.getSecid());
        this.setRegnumber(securitiesInfo.getRegnumber());
        this.setName(securitiesInfo.getName());
        this.setEmitentTitle(securitiesInfo.getEmitentTitle());
    }

    public SecuritiesInfo convertToSecuritiesInfo(){
        SecuritiesInfo securitiesInfo = new SecuritiesInfo();
        securitiesInfo.setId(Integer.parseInt(this.getId()));
        securitiesInfo.setSecid(this.getSecid());
        securitiesInfo.setRegnumber(this.getRegnumber());
        securitiesInfo.setName(this.getName());
        securitiesInfo.setEmitentTitle(this.getEmitentTitle());
        return securitiesInfo;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSecid() {
        return secid;
    }

    public void setSecid(String secid) {
        this.secid = secid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmitentTitle() {
        return emitentTitle;
    }

    public void setEmitentTitle(String emitentTitle) {
        this.emitentTitle = emitentTitle;
    }

    public String getRegnumber() {
        return regnumber;
    }

    public void setRegnumber(String regnumber) {
        this.regnumber = regnumber;
    }
}
