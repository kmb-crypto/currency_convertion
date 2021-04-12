INSERT INTO users (name) VALUES ('Vasya');
INSERT INTO users (name) VALUES ('Petya');
INSERT INTO users (name) VALUES ('Ivan');

INSERT INTO transactions (amount, from_currency, to_currency, exchange_amount, time, user_id) VALUES (1000000000, 'RUB', 'USD', 13333333, CAST('2021-03-05 10:45:06.427' AS DateTime), 1);
INSERT INTO transactions (amount, from_currency, to_currency, exchange_amount, time, user_id) VALUES (100000000, 'RUB', 'USD', 1333333, CAST('2021-03-10 10:55:06.427' AS DateTime), 2);
INSERT INTO transactions (amount, from_currency, to_currency, exchange_amount, time, user_id) VALUES (20000000, 'USD', 'RUB', 1551384000, CAST('2021-03-15 10:45:06.427' AS DateTime), 1);