-- bank_joined_table

CREATE TABLE buyers (
  bill_id       BIGINT       NOT NULL AUTO_INCREMENT,
  first_name       VARCHAR(50)  NOT NULL,
  last_name        VARCHAR(50)  NOT NULL,
  PRIMARY KEY(bill_id)
)
  ENGINE = InnoDB;

CREATE TABLE billing_details (
  billing_id       BIGINT       NOT NULL AUTO_INCREMENT,
  bill_id          BIGINT       NOT NULL,
  PRIMARY KEY (billing_id),
  FOREIGN KEY (bill_id) REFERENCES buyers(bill_id)
)
  ENGINE = InnoDB;

CREATE TABLE bank_account (
  billing_id      BIGINT,
  account         VARCHAR(50),
  bank_name       VARCHAR(50),
  PRIMARY KEY(billing_id),
  FOREIGN KEY (billing_id) REFERENCES billing_details(billing_id)
)
  ENGINE = InnoDB;

CREATE TABLE credit_card (
  billing_id      BIGINT,
  card_number     VARCHAR(50),
  exp_year        INT,
  exp_month       INT,
  PRIMARY KEY(billing_id),
  FOREIGN KEY (billing_id) REFERENCES billing_details(billing_id)
)
  ENGINE = InnoDB;