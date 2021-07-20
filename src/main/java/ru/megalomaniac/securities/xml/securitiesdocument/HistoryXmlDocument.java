package ru.megalomaniac.securities.xml.securitiesdocument;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import ru.megalomaniac.securities.model.TradingHistory;

import java.util.ArrayList;
import java.util.List;

// Класс для сохранения сохранения истории торговых операций в документ xml
@JacksonXmlRootElement(localName = "document")
public class HistoryXmlDocument implements XmlDocument{
    @JacksonXmlProperty()
    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    @Override
    public <E> void setItems(List<E> items) {
        if(data==null)
            System.out.println("data is null");
        this.data.setRows((List<TradingHistory>) items);
    }


    class Data{
        @JacksonXmlProperty(isAttribute = true)
        private String id;

        @JacksonXmlProperty()
        private Metadata metadata;

        @JacksonXmlElementWrapper(localName = "rows")
        @JacksonXmlProperty(localName = "row")
        private List<TradingHistory> rows = new ArrayList<>();

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public List<TradingHistory> getRows() {
            return rows;
        }

        public void setRows(List<TradingHistory> rows) {
            this.rows = rows;
        }

        public Metadata getMetadata() {
            return metadata;
        }

        public void setMetadata(Metadata metadata) {
            this.metadata = metadata;
        }
    }

    class Metadata{
        private List<Column> columns = new ArrayList<>();

        public List<Column> getColumns() {
            return columns;
        }

        public void setColumns(List<Column> columns) {
            this.columns = columns;
        }
    }

    class Column{
        @JacksonXmlProperty(isAttribute = true)
        private String name;
        @JacksonXmlProperty(isAttribute = true)
        private String type;
        @JacksonXmlProperty(isAttribute = true)
        private String bytes;
        @JacksonXmlProperty(isAttribute = true)
        private String max_size;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getBytes() {
            return bytes;
        }

        public void setBytes(String bytes) {
            this.bytes = bytes;
        }

        public String getMax_size() {
            return max_size;
        }

        public void setMax_size(String max_size) {
            this.max_size = max_size;
        }
    }
}
