CREATE TABLE NEWS
( n_id number(10) NOT NULL,
  n_main_title varchar2(50) NOT NULL,
  n_short_title varchar2(250) NOT NULL,
  n_news_text long NOT NULL, 
  n_date date NOT NULL,
  n_photo varchar2(60),
  n_theme varchar2(20),
  CONSTRAINT n_pk PRIMARY KEY (n_id)
);

CREATE TABLE COMMENTS
( com_id number(10) NOT NULL,
  com_message varchar2(250) NOT NULL,
  com_date date NOT NULL,
  com_id_author number(10) NOT NULL,
  com_id_news number(10) NOT NULL,
  CONSTRAINT com_pk PRIMARY KEY (com_id)
);

CREATE TABLE TAGS
( tg_id number(10) NOT NULL,
  tg_message varchar2(250) NOT NULL,
  CONSTRAINT tg_pk PRIMARY KEY (tg_id)
);

CREATE TABLE AUTHORS
( atr_id number(10) NOT NULL,
  atr_name varchar2(40) NOT NULL,
  atr_surname varchar2(40) NOT NULL,
  CONSTRAINT atr_pk PRIMARY KEY (atr_id)
);

CREATE TABLE USERS
( ur_id number(10) NOT NULL,
  ur_login varchar2(25) NOT NULL,
  ur_password varchar2(100) NOT NULL,
  CONSTRAINT ur_pk PRIMARY KEY (ur_id)
);

CREATE TABLE NEWS_HAVE_TAGS
( n_id number(10) NOT NULL,
  tg_id number(10) NOT NULL,
  CONSTRAINT nht_pk PRIMARY KEY (n_id,tg_id)
);

CREATE TABLE NEWS_HAVE_AUTHORS
( n_id number(10) NOT NULL,
  atr_id number(10) NOT NULL,
  CONSTRAINT nha_pk PRIMARY KEY (n_id,atr_id)
);
 
 ALTER TABLE news_have_authors ADD CONSTRAINT nha_n_fk
    FOREIGN KEY (n_id)
    REFERENCES NEWS (n_id)
 ON DELETE CASCADE;
 
ALTER TABLE news_have_authors ADD CONSTRAINT nha_atr_fk
    FOREIGN KEY (atr_id)
    REFERENCES AUTHORS (atr_id)
 ON DELETE CASCADE;

ALTER TABLE news_have_tags ADD CONSTRAINT nht_n_fk
    FOREIGN KEY (n_id)
    REFERENCES NEWS (n_id)
 ON DELETE CASCADE;
 
 ALTER TABLE news_have_tags ADD CONSTRAINT nht_tg_fk
    FOREIGN KEY (tg_id)
    REFERENCES TAGS (tg_id)
 ON DELETE CASCADE;

 ALTER TABLE comments ADD CONSTRAINT com_id_atr_fk
    FOREIGN KEY (com_id_author)
    REFERENCES USERS (ur_id)
 ON DELETE CASCADE;
 
  ALTER TABLE comments ADD CONSTRAINT com_id_n_fk
    FOREIGN KEY (com_id_news)
    REFERENCES NEWS (n_id)
 ON DELETE CASCADE;