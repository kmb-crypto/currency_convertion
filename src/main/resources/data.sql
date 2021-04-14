INSERT INTO users (name) VALUES ('Vasya');
INSERT INTO users (name) VALUES ('Petya');
INSERT INTO users (name) VALUES ('Ivan');
INSERT INTO users (name) VALUES ('Masha');

INSERT INTO transactions (amount, from_currency, to_currency, exchange_amount, time, user_id) VALUES (1000000000, 'RUB', 'USD', 13333333, CAST('2021-03-05 10:45:06.427' AS DateTime), 1);
INSERT INTO transactions (amount, from_currency, to_currency, exchange_amount, time, user_id) VALUES (100000000, 'RUB', 'USD', 1333333, CAST('2021-03-10 10:55:06.427' AS DateTime), 2);
INSERT INTO transactions (amount, from_currency, to_currency, exchange_amount, time, user_id) VALUES (20000000, 'USD', 'RUB', 1551384000, CAST('2021-03-15 10:45:06.427' AS DateTime), 1);
INSERT INTO transactions (amount, from_currency, to_currency, exchange_amount, time, user_id) VALUES (100000, 'GBP', 'RUB', 10425890, CAST('2021-03-15 10:55:06.427' AS DateTime), 3);
INSERT INTO transactions (amount, from_currency, to_currency, exchange_amount, time, user_id) VALUES (1000000000, 'RUB', 'USD', 13333333, CAST('2021-03-16 10:45:06.427' AS DateTime), 1);
INSERT INTO transactions (amount, from_currency, to_currency, exchange_amount, time, user_id) VALUES (100000000, 'RUB', 'USD', 1333333, CAST('2021-03-16 10:55:06.427' AS DateTime), 2);
INSERT INTO transactions (amount, from_currency, to_currency, exchange_amount, time, user_id) VALUES (10000000, 'RUB', 'USD', 133333, CAST('2021-03-16 10:56:06.427' AS DateTime), 2);
INSERT INTO transactions (amount, from_currency, to_currency, exchange_amount, time, user_id) VALUES (20000000, 'USD', 'RUB', 1551364000, CAST('2021-03-17 10:45:06.427' AS DateTime), 2);
INSERT INTO transactions (amount, from_currency, to_currency, exchange_amount, time, user_id) VALUES (100000, 'USD', 'RUB', 7700000, CAST('2021-03-18 10:55:06.427' AS DateTime), 3);
INSERT INTO transactions (amount, from_currency, to_currency, exchange_amount, time, user_id) VALUES (770000000, 'RUB', 'USD', 10000000, CAST('2021-03-18 10:55:06.427' AS DateTime), 4);
INSERT INTO transactions (amount, from_currency, to_currency, exchange_amount, time, user_id) VALUES (1000000, 'GBP', 'RUB', 104258900, CAST('2021-03-19 10:55:06.427' AS DateTime), 3);
