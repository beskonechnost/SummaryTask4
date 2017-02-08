

INSERT INTO privilege (id, privilege_name) VALUES (1,'administrator');
INSERT INTO privilege (id, privilege_name) VALUES (2,'user');

INSERT INTO edition(name, topic, price, publisher) VALUES ('Classic Rock', 'Music', 200.5, 'Future Publishing');
INSERT INTO edition(name, topic, price, publisher) VALUES ('Anime Insider', 'Anime', 125, 'Wizard Entertainment');
INSERT INTO edition(name, topic, price, publisher) VALUES ('Astronomy', 'Astronomy', 189.99, 'Kalmbach Publishing');
INSERT INTO edition(name, topic, price, publisher) VALUES ('Game Informer', 'Games', 149.99, 'GameStop');
INSERT INTO edition(name, topic, price, publisher) VALUES ('Mojo', 'Music', 167.2, 'Future Publishing');
INSERT INTO edition(name, topic, price, publisher) VALUES ('National Geographic', 'Nature', 199.99, 'World');
INSERT INTO edition(name, topic, price, publisher) VALUES ('Metal Hammer', 'Music', 100.99, 'TeamRock');
INSERT INTO edition(name, topic, price, publisher) VALUES ('MMO Games Magazine', 'Games', 120, 'theGlobe');
INSERT INTO edition(name, topic, price, publisher) VALUES ('Journal of Animal Ecology', 'Nature', 147.5, 'World');
INSERT INTO edition(name, topic, price, publisher) VALUES ('PC Zone', 'Games', 176, 'GameStop');
INSERT INTO edition(name, topic, price, publisher) VALUES ('Rock Sound', 'Music', 111.99, 'TeamRock');
INSERT INTO edition(name, topic, price, publisher) VALUES ('Rolling Stone', 'Music', 143.11, 'TeamRock');
INSERT INTO edition(name, topic, price, publisher) VALUES ('Shojo Beat', 'Anime', 121.99, 'Viz Media');
INSERT INTO edition(name, topic, price, publisher) VALUES ('Sounds', 'Music', 177.99, 'TeamRock');
INSERT INTO edition(name, topic, price, publisher) VALUES ('Q', 'Music', 104.99, 'TeamRock');
INSERT INTO edition(name, topic, price, publisher) VALUES ('The Wire', 'Music', 100, 'Future Publishing');

INSERT INTO account(account_number) VALUES (1234567890);
INSERT INTO account(account_number, balance) VALUES (123321123, 300.45);

INSERT INTO users(login, pass, privilege_id, mail, age, `lock`) VALUES ('admin','admin',1,'admin@gmail.com',33,1);
INSERT INTO users(login, pass, privilege_id, mail) VALUES ('user1','user1',2,'user1@gmail.com');