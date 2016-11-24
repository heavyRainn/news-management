INSERT INTO USERS (ur_id, ur_login, ur_password) VALUES (101, 'Serega345','123');
INSERT INTO USERS (ur_id, ur_login, ur_password) VALUES (102, 'Vadim546','1443');
INSERT INTO USERS (ur_id, ur_login, ur_password) VALUES (103, 'Grek221','1222');
INSERT INTO USERS (ur_id, ur_login, ur_password) VALUES (104, 'Valera5000','993');
INSERT INTO USERS (ur_id, ur_login, ur_password) VALUES (105, 'Kristi113','321');
INSERT INTO USERS (ur_id, ur_login, ur_password) VALUES (106, 'MichealX1','332211');
INSERT INTO USERS (ur_id, ur_login, ur_password) VALUES (107, 'Ogolteliy9','505050');
INSERT INTO USERS (ur_id, ur_login, ur_password) VALUES (108, 'Serjezniy','102030');
INSERT INTO USERS (ur_id, ur_login, ur_password) VALUES (109, 'Balagan','90000');
INSERT INTO USERS (ur_id, ur_login, ur_password) VALUES (110, 'Superuser007','98789');
INSERT INTO USERS (ur_id, ur_login, ur_password) VALUES (111, 'Lookatme4','4444');
INSERT INTO USERS (ur_id, ur_login, ur_password) VALUES (112, 'Mohamed','989800');
INSERT INTO USERS (ur_id, ur_login, ur_password) VALUES (113, 'Griboedov157','23477');
INSERT INTO USERS (ur_id, ur_login, ur_password) VALUES (114, 'Neotrance','222333111');
INSERT INTO USERS (ur_id, ur_login, ur_password) VALUES (115, 'Vladikavkaz','239124');
INSERT INTO USERS (ur_id, ur_login, ur_password) VALUES (116, 'Kiboorg','44422');
INSERT INTO USERS (ur_id, ur_login, ur_password) VALUES (117, 'EhalGreka6','111111');
INSERT INTO USERS (ur_id, ur_login, ur_password) VALUES (118, 'TommyMontana','1211113');
INSERT INTO USERS (ur_id, ur_login, ur_password) VALUES (119, 'Sylvester','18888');
INSERT INTO USERS (ur_id, ur_login, ur_password) VALUES (120, 'JanKlodVamDam','000');
----------------------------------------------------------------------------------------/
INSERT INTO TAGS (TG_ID, TG_MESSAGE) VALUES (201, '#HOME');
INSERT INTO TAGS (TG_ID, TG_MESSAGE) VALUES (202, '#LUCKY');
INSERT INTO TAGS (TG_ID, TG_MESSAGE) VALUES (203, '#IMPORTANT');
INSERT INTO TAGS (TG_ID, TG_MESSAGE) VALUES (204, '#AWERSOME');
INSERT INTO TAGS (TG_ID, TG_MESSAGE) VALUES (205, '#MUSIC');
INSERT INTO TAGS (TG_ID, TG_MESSAGE) VALUES (206, '#BEAUTY');
INSERT INTO TAGS (TG_ID, TG_MESSAGE) VALUES (207, '#HOME');
INSERT INTO TAGS (TG_ID, TG_MESSAGE) VALUES (208, '#BRIGHT');
INSERT INTO TAGS (TG_ID, TG_MESSAGE) VALUES (209, '#HORRIBLE');
INSERT INTO TAGS (TG_ID, TG_MESSAGE) VALUES (210, '#BEST');
INSERT INTO TAGS (TG_ID, TG_MESSAGE) VALUES (211, '#PICTURE');
INSERT INTO TAGS (TG_ID, TG_MESSAGE) VALUES (212, '#SELFIE');
INSERT INTO TAGS (TG_ID, TG_MESSAGE) VALUES (213, '#BELARUS');
INSERT INTO TAGS (TG_ID, TG_MESSAGE) VALUES (214, '#IT');
INSERT INTO TAGS (TG_ID, TG_MESSAGE) VALUES (215, '#SOCIAL');
INSERT INTO TAGS (TG_ID, TG_MESSAGE) VALUES (216, '#SPORT');
INSERT INTO TAGS (TG_ID, TG_MESSAGE) VALUES (217, '#WORLD');
INSERT INTO TAGS (TG_ID, TG_MESSAGE) VALUES (218, '#WEATHER');
INSERT INTO TAGS (TG_ID, TG_MESSAGE) VALUES (219, '#HOROSCOPE');
INSERT INTO TAGS (TG_ID, TG_MESSAGE) VALUES (220, '#GOODNEWS');
----------------------------------------------------------------------------------------/
INSERT INTO AUTHORS (ATR_ID, ATR_NAME,ATR_SURNAME) VALUES (301, 'Sylwester','Stalone');
INSERT INTO AUTHORS (ATR_ID, ATR_NAME,ATR_SURNAME) VALUES (302, 'John','Lock');
INSERT INTO AUTHORS (ATR_ID, ATR_NAME,ATR_SURNAME) VALUES (303, 'Eve','Slanders');
INSERT INTO AUTHORS (ATR_ID, ATR_NAME,ATR_SURNAME) VALUES (304, 'Chak','Spins');
INSERT INTO AUTHORS (ATR_ID, ATR_NAME,ATR_SURNAME) VALUES (305, 'Michael','Browning');
INSERT INTO AUTHORS (ATR_ID, ATR_NAME,ATR_SURNAME) VALUES (306, 'Pamella','Yowowitch');
INSERT INTO AUTHORS (ATR_ID, ATR_NAME,ATR_SURNAME) VALUES (307, 'Totti','Smith');
INSERT INTO AUTHORS (ATR_ID, ATR_NAME,ATR_SURNAME) VALUES (308, 'Andrew','Spencer');
INSERT INTO AUTHORS (ATR_ID, ATR_NAME,ATR_SURNAME) VALUES (309, 'Sergio','Taccini');
INSERT INTO AUTHORS (ATR_ID, ATR_NAME,ATR_SURNAME) VALUES (310, 'Phil','Attkins');
INSERT INTO AUTHORS (ATR_ID, ATR_NAME,ATR_SURNAME) VALUES (311, 'Scott','Brown');
INSERT INTO AUTHORS (ATR_ID, ATR_NAME,ATR_SURNAME) VALUES (312, 'Mikola','Tesla');
INSERT INTO AUTHORS (ATR_ID, ATR_NAME,ATR_SURNAME) VALUES (313, 'Asap','Rocky');
INSERT INTO AUTHORS (ATR_ID, ATR_NAME,ATR_SURNAME) VALUES (314, 'Travis','Scott');
INSERT INTO AUTHORS (ATR_ID, ATR_NAME,ATR_SURNAME) VALUES (315, 'Jackie','Chan');
INSERT INTO AUTHORS (ATR_ID, ATR_NAME,ATR_SURNAME) VALUES (316, 'Valeria','Kruq');
INSERT INTO AUTHORS (ATR_ID, ATR_NAME,ATR_SURNAME) VALUES (317, 'Zina','Kragel');
INSERT INTO AUTHORS (ATR_ID, ATR_NAME,ATR_SURNAME) VALUES (318, 'Mock','Mockito');
INSERT INTO AUTHORS (ATR_ID, ATR_NAME,ATR_SURNAME) VALUES (319, 'Zeleboba','IsDead');
INSERT INTO AUTHORS (ATR_ID, ATR_NAME,ATR_SURNAME) VALUES (320, 'Gans','Anderson');
----------------------------------------------------------------------------------------/
INSERT INTO NEWS (N_ID, N_MAINTITLE,N_SHORTTITLE,N_NEWSTEXT,N_DATE,N_PHOTO,N_THEME) VALUES (501, 'Solution','To solve this issue,I am using in-memory database. ','A good unit test should leave the database state same as it was before test caseexecution. It should remove all added data; and roll back all updates.',
'13-MAR-2016','FOTO','Criminal');
INSERT INTO NEWS (N_ID, N_MAINTITLE,N_SHORTTITLE,N_NEWSTEXT,N_DATE,N_PHOTO,N_THEME) VALUES (502, 'United','Unit tests for your DAO layer ','This may be the first step for creating unit tests for your DAO layer. Ideally, you should be using same configuration for tests as you are using for application. But there may be some changes which areonly unit test specific. To solve this issue, you should create another test-specific configuration file and add/override the test specific configuration changes E.g. in main application, if configuration file is application-context.xml thenyou should create another file application-context-test.xml and import the original configuration into this file on top.Then override bean definitions which you may want (e.g. use in-memory database instead of regular database).application-context-test.xml',
'23-AUG-2016','FOTO','Criminal');
INSERT INTO NEWS (N_ID, N_MAINTITLE,N_SHORTTITLE,N_NEWSTEXT,N_DATE,N_PHOTO,N_THEME) VALUES (503, 'Scary Clowns','To solve this issue,I am using in-memory database. ','There is a new trend. People are dressing up as clowns. They are dressing up not as funny clowns but as scary clowns. They scare people…',
'4-OCT-2016','FOTO','FASHION');
INSERT INTO NEWS (N_ID, N_MAINTITLE,N_SHORTTITLE,N_NEWSTEXT,N_DATE,N_PHOTO,N_THEME) VALUES (504, 'Very Small Pumpkins','Miniland is in Legoland. It has something new for people','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus vitae leo eget mauris elementum rhoncus id consequat eros. Nam aliquam ante ut velit maximus, id sagittis purus dignissim. Duis aliquam orci id ullamcorper pretium. Quisque at lobortis libero. Suspendisse eget imperdiet sem, nec aliquet diam. Vestibulum dictum, elit et ullamcorper ullamcorper, mauris purus vulputate nulla, sit amet placerat tortor urna eget arcu. Fusce et ultricies purus, in venenatis lorem.',
'02-MAR-2016','FOTO','FASHION');
INSERT INTO NEWS (N_ID, N_MAINTITLE,N_SHORTTITLE,N_NEWSTEXT,N_DATE,N_PHOTO,N_THEME) VALUES (505, 'Scary Clowns','To solve this issue,I am using in-memory database. ','There is a new trend. People are dressing up as clowns. They are dressing up not as funny clowns but as scary clowns. They scare people…',
'02-JAN-2016','FOTO','FASHION');
INSERT INTO NEWS (N_ID, N_MAINTITLE,N_SHORTTITLE,N_NEWSTEXT,N_DATE,N_PHOTO,N_THEME) VALUES (506, 'Donec','Donec ut nisl elit','Aliquam vulputate ultricies lectus, in dictum lacus efficitur ac. Phasellus sit amet dictum ligula, non iaculis tortor. Morbi auctor, nibh sit amet pulvinar accumsan, magna ex facilisis diam, eu mattis est sem vestibulum lectus. Etiam semper dui sit amet orci lacinia, at vulputate justo facilisis. Nunc porta sapien ut risus interdum consectetur. Integer lobortis velit lobortis vulputate dictum. Phasellus lacinia urna nec ante accumsan, in convallis velit tincidunt.',
'02-NOV-2016','FOTO','FASHION');
INSERT INTO NEWS (N_ID, N_MAINTITLE,N_SHORTTITLE,N_NEWSTEXT,N_DATE,N_PHOTO,N_THEME) VALUES (507, 'Nunc','Nunc sit amet risus eu orci consequat ultricies ac sit amet nibh','Aliquam vulputate ultricies lectus, in dictum lacus efficitur ac. Phasellus sit amet dictum ligula, non iaculis tortor. Morbi auctor, nibh sit amet pulvinar accumsan, magna ex facilisis diam, eu mattis est sem vestibulum lectus. Etiam semper dui sit amet orci lacinia, at vulputate justo facilisis. Nunc porta sapien ut risus interdum consectetur. Integer lobortis velit lobortis vulputate dictum. Phasellus lacinia urna nec ante accumsan, in convallis velit tincidunt.',
'26-NOV-2016','FOTO','FASHION');
INSERT INTO NEWS (N_ID, N_MAINTITLE,N_SHORTTITLE,N_NEWSTEXT,N_DATE,N_PHOTO,N_THEME) VALUES (508, 'Nulla','Nulla volutpat tortor ac quam interdum porttitor','Morbi euismod odio ac velit pharetra porta. Aenean sem tortor, tempor vel pellentesque et, iaculis vel diam. Quisque eu massa at ante congue tristique at nec odio. Nulla volutpat tortor ac quam interdum porttitor. Sed metus erat, consectetur ac tortor eu, condimentum aliquam arcu. Vestibulum finibus leo eget lacus commodo commodo. Phasellus ac pharetra ipsum. Pellentesque a nulla id dui lacinia bibendum congue at ligula. Donec justo eros, aliquet quis mauris at, malesuada dignissim turpis. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Praesent diam eros, ultricies at est quis, tincidunt auctor sem. Ut non sem a ex mattis sagittis a et lorem. Morbi tempus eget lorem ut scelerisque.',
'13-SEP-2016','FOTO','FASHION');
INSERT INTO NEWS (N_ID, N_MAINTITLE,N_SHORTTITLE,N_NEWSTEXT,N_DATE,N_PHOTO,N_THEME) VALUES (509, 'Vestibulum','Vestibulum orci lorem, finibus et finibus vitae','Donec justo eros, aliquet quis mauris at, malesuada dignissim turpis. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Praesent diam eros, ultricies at est quis, tincidunt auctor sem. Ut non sem a ex mattis sagittis a et lorem. Morbi tempus eget lorem ut scelerisque.',
'29-JUL-2016','FOTO','FASHION');
INSERT INTO NEWS (N_ID, N_MAINTITLE,N_SHORTTITLE,N_NEWSTEXT,N_DATE,N_PHOTO,N_THEME) VALUES (510, 'Curabitur','Curabitur velit enim','Nunc faucibus, odio interdum consequat euismod, odio elit maximus eros, sit amet rutrum eros metus eget mauris. Nunc sit amet risus eu orci consequat ultricies ac sit amet nibh. Mauris vel sollicitudin nibh, id vehicula dui. Pellentesque augue neque, porttitor sit amet odio vitae, gravida gravida lacus. Quisque ac erat elementum, porta ante eu, laoreet nulla.',
'29-JUL-2016','FOTO','FASHION');
INSERT INTO NEWS (N_ID, N_MAINTITLE,N_SHORTTITLE,N_NEWSTEXT,N_DATE,N_PHOTO,N_THEME) VALUES (511, 'Ipsum','Lorem ipsum','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas eleifend rutrum nisl vel rhoncus. Integer a lacus eu felis ultricies eleifend nec et quam. Duis sed rhoncus dui, a luctus ex. Ut placerat accumsan convallis. Donec id accumsan nibh, sed ultrices orci. Praesent metus urna, interdum vitae pharetra vitae, fringilla eu sapien. Nam malesuada aliquam felis sed efficitur. Donec id sapien eros. Etiam mattis mi vitae rhoncus fermentum.',
'8-NOV-2016','FOTO','POLYTICS');
INSERT INTO NEWS (N_ID, N_MAINTITLE,N_SHORTTITLE,N_NEWSTEXT,N_DATE,N_PHOTO,N_THEME) VALUES (512, 'Proin blandit','Proin blandit bibendum ligula, at mattis lorem semper in.','Proin blandit bibendum ligula, at mattis lorem semper in. Nullam tincidunt mattis ex, in tempus dui accumsan id. Vivamus felis turpis, dictum at congue et, iaculis in diam. Nullam condimentum enim lacus, ut pulvinar felis gravida et. In hac habitasse platea dictumst. Sed sed lacus non libero bibendum euismod non eget augue. Praesent sodales nibh id hendrerit consectetur. Fusce quis bibendum ex. Nunc sed volutpat dui.',
'2-NOV-2016','FOTO','POLYTICS');
INSERT INTO NEWS (N_ID, N_MAINTITLE,N_SHORTTITLE,N_NEWSTEXT,N_DATE,N_PHOTO,N_THEME) VALUES (513, 'Nunc imperdiet','Nunc imperdiet vitae lacus vel pellentesque.','Nunc imperdiet vitae lacus vel pellentesque. Sed in lorem consectetur, tincidunt tortor rutrum, vehicula velit. Quisque neque justo, sodales id neque vitae, porttitor vestibulum diam. Sed ac felis sodales, sagittis turpis eu, consequat elit. Maecenas facilisis pharetra purus, ut mattis erat efficitur nec. Nunc libero sapien, aliquam eget sem a, consectetur placerat neque. Cras sed ante urna. Vestibulum tempus ipsum massa, sit amet dignissim magna vestibulum at. Ut eu tempor sem. Proin in eros orci. Praesent nisl sem, mollis et tortor vitae, pretium lobortis urna. Duis condimentum vel eros at rutrum. Pellentesque ac mollis turpis, eu rhoncus augue. Morbi justo tellus, luctus sed lorem ac, lobortis convallis lectus. Donec commodo, felis eu auctor tempor, mauris ex facilisis mauris, sed ultrices velit risus id ipsum.',
'3-SEP-2016','FOTO','POLYTICS');
INSERT INTO NEWS (N_ID, N_MAINTITLE,N_SHORTTITLE,N_NEWSTEXT,N_DATE,N_PHOTO,N_THEME) VALUES (514, 'Nam porttitor','Nam porttitor neque mauris','Nam porttitor neque mauris, eget pellentesque orci condimentum feugiat. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Sed sagittis vel ex a ultrices. Proin at diam sit amet est scelerisque finibus vitae sed ipsum. Ut dui justo, egestas ut pellentesque a, suscipit ac erat. In hac habitasse platea dictumst. Suspendisse a sem urna.',
'20-SEP-2016','FOTO','POLYTICS');
INSERT INTO NEWS (N_ID, N_MAINTITLE,N_SHORTTITLE,N_NEWSTEXT,N_DATE,N_PHOTO,N_THEME) VALUES (515, 'Praesent','Praesent tristique dolor quis orci laoreet','Praesent tristique dolor quis orci laoreet, sit amet tempus ante consequat. Nam aliquam arcu tincidunt massa dapibus, facilisis pretium magna porta. Phasellus gravida leo ac augue sollicitudin, id eleifend justo blandit. Pellentesque lacus felis, dictum eget elit eget, vehicula pulvinar urna. Etiam feugiat et nibh sed laoreet. Maecenas sit amet tincidunt metus. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Sed diam leo, lobortis ut quam sed, porttitor consequat massa.',
'23-JUL-2016','FOTO','POLYTICS');
INSERT INTO NEWS (N_ID, N_MAINTITLE,N_SHORTTITLE,N_NEWSTEXT,N_DATE,N_PHOTO,N_THEME) VALUES (516, 'Dolor','Dolor sit amet','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc a sapien elit. Nam mollis sem sapien, vitae aliquam arcu auctor ut. Curabitur tincidunt metus facilisis neque semper, a pretium ipsum blandit. Donec interdum vestibulum libero sit amet maximus. In eu ligula a neque dictum bibendum nec at arcu. Nunc fringilla, ante non ultrices vestibulum, arcu augue sollicitudin augue, laoreet ultrices magna velit eget libero. Sed sagittis odio a urna varius malesuada. Integer suscipit ante a sapien congue, ut rutrum augue tempus. Morbi in elementum nisl.',
'1-FEB-2016','FOTO','POLYTICS');
INSERT INTO NEWS (N_ID, N_MAINTITLE,N_SHORTTITLE,N_NEWSTEXT,N_DATE,N_PHOTO,N_THEME) VALUES (517, 'Cras','Cras ex eros','Cras ex eros, blandit at turpis id, lacinia pretium est. In placerat nisi in aliquet venenatis. Praesent a velit suscipit, interdum arcu at, euismod lacus. In ut augue tristique, sagittis libero non, lobortis sem. Aliquam iaculis accumsan augue in elementum. Praesent faucibus metus et auctor fermentum. Proin pretium pharetra fermentum. Ut fermentum, lorem eget posuere pharetra, sem arcu faucibus ex, sed mollis arcu lacus in turpis. Curabitur iaculis volutpat turpis ac sollicitudin. Donec scelerisque tellus eu velit efficitur, a sodales ex luctus. Donec neque ipsum, convallis viverra feugiat id, tincidunt sit amet felis. Ut gravida orci quis lectus ullamcorper, id cursus nisl maximus.',
'13-FEB-2016','FOTO','POLYTICS');
INSERT INTO NEWS (N_ID, N_MAINTITLE,N_SHORTTITLE,N_NEWSTEXT,N_DATE,N_PHOTO,N_THEME) VALUES (518, 'Integer','Integer nec justo felis','Integer nec justo felis. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Quisque leo leo, rutrum et ante scelerisque, sodales vehicula arcu. Interdum et malesuada fames ac ante ipsum primis in faucibus. Integer tincidunt metus sit amet vulputate gravida. Proin sollicitudin, nisl non semper sodales, diam nisl convallis dui, id fringilla justo odio at ipsum. Duis a odio at magna fermentum ullamcorper cursus et sapien. Aliquam egestas enim sed lobortis ultrices. Nam id bibendum dui, ut lacinia lorem. Quisque volutpat lorem quis dignissim fringilla. Duis sit amet magna sagittis, maximus est ut, mattis massa.',
'15-OCT-2016','FOTO','POLYTICS');
INSERT INTO NEWS (N_ID, N_MAINTITLE,N_SHORTTITLE,N_NEWSTEXT,N_DATE,N_PHOTO,N_THEME) VALUES (519, 'Donec','Donec dictum, arcu nec','Donec dictum, arcu nec lobortis accumsan, mi erat pellentesque augue, sit amet varius lorem nisl sit amet ante. Nunc scelerisque accumsan dictum. Donec a rutrum nunc, ac aliquet elit. Curabitur tristique lectus ligula, quis commodo est posuere eu. Aenean efficitur laoreet laoreet. Praesent rutrum lorem ex, placerat iaculis massa consectetur pharetra. Duis quis lorem tortor. Nam vulputate arcu vel magna congue molestie. Sed neque nibh, convallis at mi sit amet, accumsan bibendum ipsum. Mauris fermentum eros vitae ipsum volutpat viverra. Donec vitae eros commodo, lobortis erat in, congue nulla. Fusce placerat quam eu sagittis pellentesque.',
'18-DEC-2016','FOTO','POLYTICS');
INSERT INTO NEWS (N_ID, N_MAINTITLE,N_SHORTTITLE,N_NEWSTEXT,N_DATE,N_PHOTO,N_THEME) VALUES (520, 'Maximus','Donec maximus eu dolor','Donec maximus eu dolor ut finibus. Nunc nisl nibh, pellentesque ut sem ut, bibendum pulvinar odio. Vivamus in quam ac magna interdum venenatis sit amet eu dolor. Vivamus turpis risus, blandit sed nisi id, tristique volutpat magna. Vestibulum lobortis porta semper. Nulla efficitur maximus facilisis. Aliquam egestas, mi non ultricies cursus, nisi felis suscipit lorem, feugiat iaculis ante nulla ut magna. Aliquam rhoncus semper mollis. Vivamus ac vehicula ligula. Ut faucibus semper faucibus. Maecenas rutrum iaculis sapien non cursus. Sed commodo, magna ac congue accumsan, diam mauris rutrum risus, nec tempus leo lorem vitae odio. Etiam risus metus, tincidunt in maximus et, consectetur vel sem. Aenean justo felis, malesuada sit amet tellus eget, euismod molestie mauris. Mauris a lacus fringilla, elementum enim tempor, lobortis metus. Phasellus sit amet blandit odio.',
'19-JUL-2016','FOTO','POLYTICS');
----------------------------------------------------------------------------------------/
INSERT INTO COMMENTS (COM_ID, COM_MESSAGE,COM_DATE,COM_ID_AUTHOR,COM_ID_NEWS) VALUES (401, 'Awersome news','13-DEC-2016',101,501);
INSERT INTO COMMENTS (COM_ID, COM_MESSAGE,COM_DATE,COM_ID_AUTHOR,COM_ID_NEWS) VALUES (402, 'Good news','15-SEP-2016',101,502);
INSERT INTO COMMENTS (COM_ID, COM_MESSAGE,COM_DATE,COM_ID_AUTHOR,COM_ID_NEWS) VALUES (403, 'Very lucky guy','11-NOV-2016',102,503);
INSERT INTO COMMENTS (COM_ID, COM_MESSAGE,COM_DATE,COM_ID_AUTHOR,COM_ID_NEWS) VALUES (404, 'Ohh my god','08-FEB-2016',103,504);
INSERT INTO COMMENTS (COM_ID, COM_MESSAGE,COM_DATE,COM_ID_AUTHOR,COM_ID_NEWS) VALUES (405, 'Did you see that?','23-FEB-2016',104,505);
INSERT INTO COMMENTS (COM_ID, COM_MESSAGE,COM_DATE,COM_ID_AUTHOR,COM_ID_NEWS) VALUES (406, 'What are you mean?','22-NOV-2016',106,506);
INSERT INTO COMMENTS (COM_ID, COM_MESSAGE,COM_DATE,COM_ID_AUTHOR,COM_ID_NEWS) VALUES (407, 'How are you?','14-OCT-2016',107,507);
INSERT INTO COMMENTS (COM_ID, COM_MESSAGE,COM_DATE,COM_ID_AUTHOR,COM_ID_NEWS) VALUES (408, 'Is anybody here?','08-MAR-2016',108,508);
INSERT INTO COMMENTS (COM_ID, COM_MESSAGE,COM_DATE,COM_ID_AUTHOR,COM_ID_NEWS) VALUES (409, 'The best comment','04-JAN-2016',109,509);
INSERT INTO COMMENTS (COM_ID, COM_MESSAGE,COM_DATE,COM_ID_AUTHOR,COM_ID_NEWS) VALUES (410, 'Do u wanna?','18-JUN-2016',110,510);
INSERT INTO COMMENTS (COM_ID, COM_MESSAGE,COM_DATE,COM_ID_AUTHOR,COM_ID_NEWS) VALUES (411, 'I was here','13-SEP-2016',111,511);
INSERT INTO COMMENTS (COM_ID, COM_MESSAGE,COM_DATE,COM_ID_AUTHOR,COM_ID_NEWS) VALUES (412, 'Too much impression','15-DEC-2016',112,512);
INSERT INTO COMMENTS (COM_ID, COM_MESSAGE,COM_DATE,COM_ID_AUTHOR,COM_ID_NEWS) VALUES (413, 'I have a dog','10-MAR-2016',113,513);
INSERT INTO COMMENTS (COM_ID, COM_MESSAGE,COM_DATE,COM_ID_AUTHOR,COM_ID_NEWS) VALUES (414, 'How many years it is?','02-APR-2016',114,514);
INSERT INTO COMMENTS (COM_ID, COM_MESSAGE,COM_DATE,COM_ID_AUTHOR,COM_ID_NEWS) VALUES (415, 'I can fly','08-JUL-2016',115,515);
INSERT INTO COMMENTS (COM_ID, COM_MESSAGE,COM_DATE,COM_ID_AUTHOR,COM_ID_NEWS) VALUES (416, 'Hello I am Nick','05-DEC-2016',116,516);
INSERT INTO COMMENTS (COM_ID, COM_MESSAGE,COM_DATE,COM_ID_AUTHOR,COM_ID_NEWS) VALUES (417, 'Here is your gift','11-AUG-2016',117,517);
INSERT INTO COMMENTS (COM_ID, COM_MESSAGE,COM_DATE,COM_ID_AUTHOR,COM_ID_NEWS) VALUES (418, 'They are smiths','02-SEP-2016',118,518);
INSERT INTO COMMENTS (COM_ID, COM_MESSAGE,COM_DATE,COM_ID_AUTHOR,COM_ID_NEWS) VALUES (419, 'I will do that','13-FEB-2016',119,519);
INSERT INTO COMMENTS (COM_ID, COM_MESSAGE,COM_DATE,COM_ID_AUTHOR,COM_ID_NEWS) VALUES (420, 'I didnt prepare it','02-MAR-2016',120,520);
----------------------------------------------------------------------------------------/
INSERT INTO NEWS_HAVE_AUTHORS(N_ID,ATR_ID)VALUES(501,301);
INSERT INTO NEWS_HAVE_AUTHORS(N_ID,ATR_ID)VALUES(501,302);
INSERT INTO NEWS_HAVE_AUTHORS(N_ID,ATR_ID)VALUES(502,302);
INSERT INTO NEWS_HAVE_AUTHORS(N_ID,ATR_ID)VALUES(502,303);
INSERT INTO NEWS_HAVE_AUTHORS(N_ID,ATR_ID)VALUES(502,304);
INSERT INTO NEWS_HAVE_AUTHORS(N_ID,ATR_ID)VALUES(503,301);
INSERT INTO NEWS_HAVE_AUTHORS(N_ID,ATR_ID)VALUES(503,303);
INSERT INTO NEWS_HAVE_AUTHORS(N_ID,ATR_ID)VALUES(504,304);
INSERT INTO NEWS_HAVE_AUTHORS(N_ID,ATR_ID)VALUES(504,312);
INSERT INTO NEWS_HAVE_AUTHORS(N_ID,ATR_ID)VALUES(505,305);
INSERT INTO NEWS_HAVE_AUTHORS(N_ID,ATR_ID)VALUES(505,315);
INSERT INTO NEWS_HAVE_AUTHORS(N_ID,ATR_ID)VALUES(506,311);
INSERT INTO NEWS_HAVE_AUTHORS(N_ID,ATR_ID)VALUES(506,306);
INSERT INTO NEWS_HAVE_AUTHORS(N_ID,ATR_ID)VALUES(507,318);
INSERT INTO NEWS_HAVE_AUTHORS(N_ID,ATR_ID)VALUES(507,307);
INSERT INTO NEWS_HAVE_AUTHORS(N_ID,ATR_ID)VALUES(508,313);
INSERT INTO NEWS_HAVE_AUTHORS(N_ID,ATR_ID)VALUES(508,311);
INSERT INTO NEWS_HAVE_AUTHORS(N_ID,ATR_ID)VALUES(509,309);
INSERT INTO NEWS_HAVE_AUTHORS(N_ID,ATR_ID)VALUES(509,302);
INSERT INTO NEWS_HAVE_AUTHORS(N_ID,ATR_ID)VALUES(510,301);
INSERT INTO NEWS_HAVE_AUTHORS(N_ID,ATR_ID)VALUES(510,319);
INSERT INTO NEWS_HAVE_AUTHORS(N_ID,ATR_ID)VALUES(511,311);
INSERT INTO NEWS_HAVE_AUTHORS(N_ID,ATR_ID)VALUES(511,316);
INSERT INTO NEWS_HAVE_AUTHORS(N_ID,ATR_ID)VALUES(512,318);
INSERT INTO NEWS_HAVE_AUTHORS(N_ID,ATR_ID)VALUES(512,312);
INSERT INTO NEWS_HAVE_AUTHORS(N_ID,ATR_ID)VALUES(513,313);
INSERT INTO NEWS_HAVE_AUTHORS(N_ID,ATR_ID)VALUES(513,304);
INSERT INTO NEWS_HAVE_AUTHORS(N_ID,ATR_ID)VALUES(514,309);
INSERT INTO NEWS_HAVE_AUTHORS(N_ID,ATR_ID)VALUES(514,314);
INSERT INTO NEWS_HAVE_AUTHORS(N_ID,ATR_ID)VALUES(515,318);
INSERT INTO NEWS_HAVE_AUTHORS(N_ID,ATR_ID)VALUES(515,320);
INSERT INTO NEWS_HAVE_AUTHORS(N_ID,ATR_ID)VALUES(516,313);
INSERT INTO NEWS_HAVE_AUTHORS(N_ID,ATR_ID)VALUES(516,305);
INSERT INTO NEWS_HAVE_AUTHORS(N_ID,ATR_ID)VALUES(517,317);
INSERT INTO NEWS_HAVE_AUTHORS(N_ID,ATR_ID)VALUES(517,309);
INSERT INTO NEWS_HAVE_AUTHORS(N_ID,ATR_ID)VALUES(518,319);
INSERT INTO NEWS_HAVE_AUTHORS(N_ID,ATR_ID)VALUES(518,309);
INSERT INTO NEWS_HAVE_AUTHORS(N_ID,ATR_ID)VALUES(519,301);
INSERT INTO NEWS_HAVE_AUTHORS(N_ID,ATR_ID)VALUES(519,305);
INSERT INTO NEWS_HAVE_AUTHORS(N_ID,ATR_ID)VALUES(520,301);
INSERT INTO NEWS_HAVE_AUTHORS(N_ID,ATR_ID)VALUES(520,313);
----------------------------------------------------------------------------------------/
INSERT INTO NEWS_HAVE_TAGS(N_ID,TG_ID)VALUES(501,201);
INSERT INTO NEWS_HAVE_TAGS(N_ID,TG_ID)VALUES(501,202);
INSERT INTO NEWS_HAVE_TAGS(N_ID,TG_ID)VALUES(502,202);
INSERT INTO NEWS_HAVE_TAGS(N_ID,TG_ID)VALUES(502,203);
INSERT INTO NEWS_HAVE_TAGS(N_ID,TG_ID)VALUES(502,204);
INSERT INTO NEWS_HAVE_TAGS(N_ID,TG_ID)VALUES(503,201);
INSERT INTO NEWS_HAVE_TAGS(N_ID,TG_ID)VALUES(503,203);
INSERT INTO NEWS_HAVE_TAGS(N_ID,TG_ID)VALUES(504,204);
INSERT INTO NEWS_HAVE_TAGS(N_ID,TG_ID)VALUES(504,212);
INSERT INTO NEWS_HAVE_TAGS(N_ID,TG_ID)VALUES(505,205);
INSERT INTO NEWS_HAVE_TAGS(N_ID,TG_ID)VALUES(505,215);
INSERT INTO NEWS_HAVE_TAGS(N_ID,TG_ID)VALUES(506,211);
INSERT INTO NEWS_HAVE_TAGS(N_ID,TG_ID)VALUES(506,206);
INSERT INTO NEWS_HAVE_TAGS(N_ID,TG_ID)VALUES(507,218);
INSERT INTO NEWS_HAVE_TAGS(N_ID,TG_ID)VALUES(507,207);
INSERT INTO NEWS_HAVE_TAGS(N_ID,TG_ID)VALUES(508,213);
INSERT INTO NEWS_HAVE_TAGS(N_ID,TG_ID)VALUES(508,211);
INSERT INTO NEWS_HAVE_TAGS(N_ID,TG_ID)VALUES(509,209);
INSERT INTO NEWS_HAVE_TAGS(N_ID,TG_ID)VALUES(509,202);
INSERT INTO NEWS_HAVE_TAGS(N_ID,TG_ID)VALUES(510,201);
INSERT INTO NEWS_HAVE_TAGS(N_ID,TG_ID)VALUES(510,219);
INSERT INTO NEWS_HAVE_TAGS(N_ID,TG_ID)VALUES(511,211);
INSERT INTO NEWS_HAVE_TAGS(N_ID,TG_ID)VALUES(511,216);
INSERT INTO NEWS_HAVE_TAGS(N_ID,TG_ID)VALUES(512,218);
INSERT INTO NEWS_HAVE_TAGS(N_ID,TG_ID)VALUES(512,212);
INSERT INTO NEWS_HAVE_TAGS(N_ID,TG_ID)VALUES(513,213);
INSERT INTO NEWS_HAVE_TAGS(N_ID,TG_ID)VALUES(513,204);
INSERT INTO NEWS_HAVE_TAGS(N_ID,TG_ID)VALUES(514,209);
INSERT INTO NEWS_HAVE_TAGS(N_ID,TG_ID)VALUES(514,214);
INSERT INTO NEWS_HAVE_TAGS(N_ID,TG_ID)VALUES(515,218);
INSERT INTO NEWS_HAVE_TAGS(N_ID,TG_ID)VALUES(515,220);
INSERT INTO NEWS_HAVE_TAGS(N_ID,TG_ID)VALUES(516,213);
INSERT INTO NEWS_HAVE_TAGS(N_ID,TG_ID)VALUES(516,205);
INSERT INTO NEWS_HAVE_TAGS(N_ID,TG_ID)VALUES(517,217);
INSERT INTO NEWS_HAVE_TAGS(N_ID,TG_ID)VALUES(517,209);
INSERT INTO NEWS_HAVE_TAGS(N_ID,TG_ID)VALUES(518,219);
INSERT INTO NEWS_HAVE_TAGS(N_ID,TG_ID)VALUES(518,209);
INSERT INTO NEWS_HAVE_TAGS(N_ID,TG_ID)VALUES(519,201);
INSERT INTO NEWS_HAVE_TAGS(N_ID,TG_ID)VALUES(519,205);
INSERT INTO NEWS_HAVE_TAGS(N_ID,TG_ID)VALUES(520,201);
INSERT INTO NEWS_HAVE_TAGS(N_ID,TG_ID)VALUES(520,213);
