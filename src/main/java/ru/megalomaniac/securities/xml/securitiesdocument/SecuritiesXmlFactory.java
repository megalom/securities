package ru.megalomaniac.securities.xml.securitiesdocument;

public class SecuritiesXmlFactory implements XmlDocumentFactory{
    @Override
    public XmlDocument createXmlDocument() {
        SecuritiesXmlDocument securitiesXmlDocument = new SecuritiesXmlDocument();
        securitiesXmlDocument.setData(securitiesXmlDocument.new Data());
        securitiesXmlDocument.getData().setId("securities");
        return securitiesXmlDocument;
    }
}
