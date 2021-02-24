# test_task
Тестовое задание

Данный сервис на основе курсов валют, полученных с OpenExchangeRate, обращается к Giphy API и предоставляет gif с необходимым тегом.
Сервис развернут по адресу https://karanda-p.herokuapp.com/ 
Также имеется docker image https://hub.docker.com/repository/docker/pdetective/docker-test-task

Инструкция по использованию:

1. Запрос отправляется на следующий эндпоинт https://karanda-p.herokuapp.com/api/result (Для отправки запросов использовался Postman)
2. В качестве параметра GET Request используется currency (трехсимвольный код валюты)
3. В response содержится json gif'ки, содержащий в себе ссылки на саму gif
