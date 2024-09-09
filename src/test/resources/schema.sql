CREATE TABLE IF NOT EXISTS `externalservice` (
  `id` int NOT NULL AUTO_INCREMENT,
  `servicename` varchar(100) NOT NULL,
  `url` varchar(100) DEFAULT NULL
);
CREATE TABLE IF NOT EXISTS `mandatoryFields` (
  `id` int NOT NULL AUTO_INCREMENT,
  `tableName` varchar(100) DEFAULT NULL,
  `reqFieldName` varchar(100) DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS `usersDetails` (
  `id` int NOT NULL AUTO_INCREMENT,
  `firstName` varchar(100) DEFAULT NULL,
  `lastName` varchar(100) DEFAULT NULL,
  `birthdate` date DEFAULT NULL,
  `birthplace` varchar(100) DEFAULT NULL,
  `sex` varchar(5) DEFAULT NULL,
  `currentAddress` varchar(500) DEFAULT NULL
);
