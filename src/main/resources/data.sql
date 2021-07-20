DROP TABLE IF EXISTS trading_history;
DROP TABLE IF EXISTS securities_info;


CREATE TABLE securities_info (
  id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  secid VARCHAR(36) NOT NULL UNIQUE,
  regnumber VARCHAR(189) NOT NULL,
  name VARCHAR(765) NOT NULL,
  emitent_title VARCHAR(765) NOT NULL
);

CREATE TABLE trading_history (
  id INT AUTO_INCREMENT PRIMARY KEY,
  secid VARCHAR(36) NOT NULL,
  tradedate DATE NOT NULL,
  numtrades DOUBLE NOT NULL,
  open DOUBLE NOT NULL,
  close DOUBLE NOT NULL,
  FOREIGN KEY (secid) references securities_info(secid)
);



INSERT INTO securities_info (secid, regnumber, name,emitent_title) VALUES
    ('AAPL','1-11-11111-A','Warner','WB'),
    ('BOPD','2-11-11111-A','Walt','Disney'),
    ('KTYL','3-11-11111-A','Stan','Marvel');

INSERT INTO trading_history (secid,tradedate,numtrades,open,close) VALUES
    ('AAPL', '2021-03-21', 3.0, 0.0, 1.0),
    ('BOPD', '2021-02-28', 1.0, 2.0, 3.0),
    ('KTYL', '2021-04-13', 4.0, 4.0, 5.0);