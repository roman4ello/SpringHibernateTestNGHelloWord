use db1;
CREATE TABLE CreditCard (
  id int(11) unsigned NOT NULL AUTO_INCREMENT,
  number varchar(255) NOT NULL DEFAULT '',
  type varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
commit;