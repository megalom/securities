package ru.megalomaniac.securities.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name = "securities_info")
public class SecuritiesInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JacksonXmlProperty(isAttribute = true)
    private int id;

    // Торговый код
    @Column(name = "secid")
    @NotBlank(message = "{TradingHistory.secid.notblank}")
    @Pattern(regexp = "[a-zA-Z0-9]{4,18}",message = "{TradingHistory.secid.pattern}")
    @JacksonXmlProperty(isAttribute = true)
    private String secid;

    // Регистрационный номер
    @Column(name = "regnumber")
    @Pattern(regexp = "\\d-\\d{2}-\\d{5}-[A-Z]",
            message = "{SecuritiesInfo.regnumber.pattern}")
    @JacksonXmlProperty(isAttribute = true)
    private String regnumber;

    // Наименование ценной бумаги
    @Column(name = "name")
    @Pattern(regexp = "[\"-?!,.а-яА-ЯёЁ0-9\\sa-zA-Z()]{3,}",
            message = "{SecuritiesInfo.name.pattern}")
    @JacksonXmlProperty(isAttribute = true)
    private String name;

    // Полное фирменное наименование/наименование эмитента, управляющей компании,
    // управляющего ипотечным покрытием
    @Column(name = "emitent_title")
    @Pattern(regexp = "[\"-?!,.а-яА-ЯёЁ0-9\\sa-zA-Z()]{3,}",
            message = "{SecuritiesInfo.emitentTitle.pattern}")
    @JacksonXmlProperty(isAttribute = true)
    private String emitentTitle;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "securitiesInfo",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonIgnore
    private Set<TradingHistory> tradingHistory = new HashSet<>();


    public SecuritiesInfo() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSecid() {
        return secid;
    }

    public void setSecid(String secid) {
        this.secid = secid;
    }

    public String getRegnumber() {
        return regnumber;
    }

    public void setRegnumber(String regnumber) {
        this.regnumber = regnumber;
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

    public Set<TradingHistory> getTradingHistory() {
        return tradingHistory;
    }

    public void setTradingHistory(Set<TradingHistory> tradingHistory) {
        if (this.tradingHistory == null) {
            this.tradingHistory = tradingHistory;
        }
        // чтобы не получить current modification exception
        else if (this.tradingHistory != tradingHistory) {
            this.tradingHistory.clear();
            if (tradingHistory != null) {
                this.tradingHistory.addAll(tradingHistory);
            }
        }
    }

    @Override
    public String toString() {
        return "SecuritiesInfo{" +
                "id=" + id +
                ", secId='" + secid + '\'' +
                ", regNumber='" + regnumber + '\'' +
                ", Name='" + name + '\'' +
                ", emitentTitle='" + emitentTitle + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SecuritiesInfo that = (SecuritiesInfo) o;
        return id == that.id && secid.equals(that.secid) &&
                Objects.equals(regnumber, that.regnumber) &&
                Objects.equals(name, that.name) &&
                Objects.equals(emitentTitle, that.emitentTitle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, secid, regnumber, name, emitentTitle);
    }
}
