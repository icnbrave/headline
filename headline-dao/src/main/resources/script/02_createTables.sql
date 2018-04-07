CREATE TABLE dr.headline (
	headline_pk	INTEGER 	NOT NULL DEFAULT NEXTVAL('hl.seq_baseinfo'),
	price	NUMERIC(5,2)	NULL,
	name		VARCHAR(64)	NOT NULL,
	descript		VARCHAR(1024)	NULL,
	select_flag	SMALLINT		NOT NULL 	DEFAULT 1,
	delete_flag	SMALLINT NOT NULL	DEFAULT 1,
	create_time			TIMESTAMP		NOT NULL,
	update_time			TIMESTAMP		NOT NULL,
	create_user			VARCHAR(32)		NULL,
	update_user			VARCHAR(32)		NULL,
	author				VARCHAR(32)		NULL,
	title				VARCHAR(64)		NULL,
	constraint pk_headline_pk primary key(headline_pk)
);
