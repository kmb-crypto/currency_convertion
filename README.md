<h1>Сервис для конвертации валют и сбора статистики операций<br><br> API</h1>


<h3>/api/exchange<br>операция обмена</h3>
запрос: id пользователя, исходная валюта, целевая валюта, сумма<br>
ответ:  id операции, сумма в целевой валюте

<h3>/api/stat/users_sum<br>пользователи, суммарный объем конвертации больше порога по валюте</h3>
запрос: валюта, порог<br>
ответ: валюта, порог, id пользователей

<h3>/api/stat/by_transaction<br>пользователи, запросившие конвертацию больше порога по валюте за один запрос</h3>
запрос: валюта, порог<br>
ответ: валюта, порог, id пользователей

<h3>/api/stat/transactions_rank<br>рейтинг направлений конвертации валют</h3>
ответ: пары валют по убыванию частотности