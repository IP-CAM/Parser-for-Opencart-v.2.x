# OpenCartParser
Парсинг страниц онлайн-магазина, её граббинг в БД и периодическая проверка на актуальность (с настраиваемое периодичностью). 
Функционал парсера включает извлечение названия, характеристик товара, картинки, его остатков по магазинам. 
Для более удобного управления парсером и визуальном представлением собранных данных, была реализована серверная и клиентская часть (клиент для ПК и [клиент на Android](https://github.com/Guha5277/ParserAndroidClient/)). 
Серверная часть построена на базе TCP протокола, на “голых” сокетах.

## Стурктура проекта
* [Парсер онлайн-магазина](https://github.com/Guha5277/IlfumoshopParser/tree/master/parser)

В основе модуля - библиотека Jsoup. Основной функционал заключается в обработке страниц онлайн-магазина, получения из неё информации о продукте (сслыка, название, характеристики, наличие в магазинах и т.д) и сохранение полученной информации в БД. Также, здесь представлены инструменты для обновления уже существующих данных и поиска новых продуктов.

* [Имплементация представления продуктов, категорий, складов и т.д.](https://github.com/Guha5277/IlfumoshopParser/tree/master/product/src/com/guhar4k/product)

* [Вспомогательная библиотека для обмена сообщениями](https://github.com/Guha5277/IlfumoshopParser/tree/master/common/src/com/guhar4k/library)

Обмен сообщениями между клиентом и сервером происходит в JSON формате. Класс Library описывает протокол обмена сообщениями.

* [Серверный модуль](https://github.com/Guha5277/IlfumoshopParser/tree/master/server)

Основные задачи модуля - инициализация настроек, взаимодействие с клиентами по сетевому протоколу, запуск процессов парсинга по расписанию (опционально).

* [Клиентский модуль](https://github.com/Guha5277/IlfumoshopParser/tree/master/client)

Представляет функционал для просмотра (в том числе с фильтрацией) и модерирования данных, управления и мониторинга процессов парсинга с использование гарфического интерфейска (Java FX).

* [Модуль сетевого взаимодействия](https://github.com/Guha5277/IlfumoshopParser/tree/master/network/src/main)

## Клиентский модуль для андроид представлен [отдельным проектом](https://github.com/Guha5277/ParserAndroidClient/).