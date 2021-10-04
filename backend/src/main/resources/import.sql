INSERT INTO users(username, password) VALUES ('ensolvers', '$2a$10$0PI43KcQ4kpr2Ks6m.FmqerYt1.HCzND2UgOmUcZyI6I66HASl8im');
INSERT INTO users(username, password) VALUES ('test', '$2a$10$0PI43KcQ4kpr2Ks6m.FmqerYt1.HCzND2UgOmUcZyI6I66HASl8im');
INSERT INTO folders(name, user_id) VALUES ('General', 1);
INSERT INTO folders(name, user_id) VALUES ('General', 2);
INSERT INTO items(content, folder_id, checked) VALUES ('Uncheck item', 1, false);
INSERT INTO items(content, folder_id, checked) VALUES ('Check item', 1, true);