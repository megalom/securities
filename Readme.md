В данном проекте используется Spring Boot(Web, Data, шаблонизатор Thymeleaf).
Выбрана СУБД H2 для простоты запуска приложения.
Для парсинга XML используется библиотека Jackson.

Запустить проект можно открыв и запустив его в IntelliJ IDEA, либо с помощью команды mvnw spring-boot:run (./mvnw spring-boot:run для Linux/MacOs) из каталога проекта.
После запуска проект доступен по адресу: http://localhost:8080/

REST API
Протестировать можно через curl либо через Postman.

Securities:

GET:
curl http://localhost:8080/api/securities
curl http://localhost:8080/api/securities/1

POST:
curl -X POST http://localhost:8080/api/securities -H "Content-Type: application/xml" -H "Accept: application/xml" -d "<securities><id>10</id><secid>SECD</secid><regnumber>1-01-07335-A</regnumber><name>Walt</name><emitentTitle>Disney</emitentTitle></securities>"

PUT:
curl -X PUT http://localhost:8080/api/securities -H "Content-Type: application/xml" -H "Accept: application/xml" -d "<securities><id>10</id><secid>SECD</secid><regnumber>1-01-07335-A</regnumber><name>WaltY</name><emitentTitle>Disney</emitentTitle></securities>"

DELETE:
curl -X DELETE http://localhost:8080/api/securities/1

Trading History:

GET:
curl http://localhost:8080/api/history
curl http://localhost:8080/api/history/1

POST:
curl -X POST http://localhost:8080/api/history -H "Content-Type: application/xml" -H "Accept: application/xml" -d "<history><id>1</id><secid>AAPL</secid><tradedate>2021-03-21</tradedate><numtrades>3.0</numtrades><open>0.0</open><close>1.0</close></history>"

PUT:
curl -X PUT http://localhost:8080/api/history -H "Content-Type: application/xml" -H "Accept: application/xml" -d "<history><id>1</id><secid>AAPL</secid><tradedate>2019-01-22</tradedate><numtrades>2.0</numtrades><open>0.0</open><close>1.0</close></history>"

DELETE:
curl -X DELETE http://localhost:8080/api/history/1