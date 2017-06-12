CREATE DATABASE bot;
CREATE TABLE User(
  idUser BIGINT NOT NULL AUTO_INCREMENT,
  idUserTelegram  BIGINT NOT NULL,
  nameUser VARCHAR(100),
  phoneUser VARCHAR(15),
  dateBthdUser VARCHAR(4),
  isAccess INT,
  stateGettingInformation INT,
  urlPrivateGroup VARCHAR(255),

  PRIMARY KEY(idUser)
);
