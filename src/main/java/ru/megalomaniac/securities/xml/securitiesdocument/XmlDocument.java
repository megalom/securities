package ru.megalomaniac.securities.xml.securitiesdocument;

import java.util.List;

public interface XmlDocument {
    <E> void setItems(List<E> items);
}
