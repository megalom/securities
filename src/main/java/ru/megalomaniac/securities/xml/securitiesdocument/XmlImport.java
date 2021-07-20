package ru.megalomaniac.securities.xml.securitiesdocument;

import java.util.List;

//Класс для получения документа из списка элементов в формате xml в зависимости от типа элементов
public class XmlImport {

    public static <E> XmlDocument getDocument(List<E> items, XmlDocumentType documentType){
        XmlDocumentFactory xmlDocumentFactory = createXmlDocumentByType(documentType);
        XmlDocument xmlDocument = xmlDocumentFactory.createXmlDocument();
        xmlDocument.setItems(items);
        return xmlDocument;
    }

    private static XmlDocumentFactory createXmlDocumentByType(XmlDocumentType documentType){
        switch (documentType){
            case SECURITIES:
                return new SecuritiesXmlFactory();
            case HISTORY:
                return new HistoryXmlFactory();
            default:
                throw new RuntimeException("Xml document with type "+documentType+" does not exists.");
        }
    }
}
