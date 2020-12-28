-- bank_single_table

CREATE TABLE buyers (
  buyer_id         BIGINT      NOT NULL AUTO_INCREMENT,
  first_name       VARCHAR(50),
  last_name        VARCHAR(50),
  PRIMARY KEY(buyer_id)
)
  ENGINE = InnoDB;

CREATE TABLE billing_details (
  billing_id      BIGINT       NOT NULL AUTO_INCREMENT,
  bill_detail_id  BIGINT,
  account         BIGINT,
  bank_name       VARCHAR(50),
  card_number     VARCHAR(50),
  exp_year        INT(10),
  exp_month       INT(10),
  PRIMARY KEY(billing_id),
  FOREIGN KEY (bill_detail_id) REFERENCES buyers(buyer_id)
)
  ENGINE = InnoDB;
