DROP TABLE IF EXISTS Bill;

CREATE TABLE Bill (
  id INT AUTO_INCREMENT PRIMARY KEY,
  description VARCHAR(250) NOT NULL,
  price INT,
  account_id VARCHAR(250) DEFAULT NULL
);

INSERT INTO Bill (id, description, price, account_id) VALUES
  (1, 'Room with out windows', 400, 'Myself' ),
  (2, 'Room with windows', 1200, 'Talkdesk' ),
  (3, 'House', 3000, 'Tiago' );
