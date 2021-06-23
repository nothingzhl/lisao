CREATE TABLE tbl_department
(
    id   varchar(32) NOT NULL,
    name varchar(32) NOT NULL,
    tel  varchar(18) DEFAULT NULL,
    PRIMARY KEY (id)
);

INSERT INTO tbl_department(id, name, tel)
VALUES ('00000000000000000000000000000000', '全部部门', '-');
INSERT INTO tbl_department(id, name, tel)
VALUES ('18ec781fbefd727923b0d35740b177ab', '开发部', '123');
INSERT INTO tbl_department(id, name, tel)
VALUES ('53e3803ebbf4f97968e0253e5ad4cc83', '测试产品部', '789');
INSERT INTO tbl_department(id, name, tel)
VALUES ('ee0e342201004c1721e69a99ac0dc0df', '运维部', '456');