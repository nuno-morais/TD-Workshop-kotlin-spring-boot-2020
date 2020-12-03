DROP TABLE IF EXISTS Bill;

CREATE TABLE Bill (
  id INT PRIMARY KEY,
  description VARCHAR(250) NOT NULL,
  price INT,
  accountId VARCHAR(250) DEFAULT NULL
);

INSERT INTO Bill (id, description, price, accountId) VALUES
  (1, 'Room with out windows', 400, 'Myself' ),
  (2, 'Room with windows', 1200, 'Talkdesk' ),
  (3, 'House', 3000, 'Tiago' );
