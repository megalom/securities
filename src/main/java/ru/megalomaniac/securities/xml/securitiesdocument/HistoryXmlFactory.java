package ru.megalomaniac.securities.xml.securitiesdocument;

public class HistoryXmlFactory implements XmlDocumentFactory{
    @Override
    public XmlDocument createXmlDocument() {
        HistoryXmlDocument historyXmlDocument = new HistoryXmlDocument();
        historyXmlDocument.setData(historyXmlDocument.new Data());
        historyXmlDocument.getData().setId("history");
        return historyXmlDocument;
    }
}
