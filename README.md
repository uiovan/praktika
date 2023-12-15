# praktika вариант - ЗЕМЛЕТРЯСЕНИЯ
Практика по курсу программирование на java

Задания:
1) Постройте график по среднему количеству землетрясений для каждого года.
2) Выведите в консоль среднюю магнитуду для штата "West Virginia".
3) Выведите в консоль название штата, в котором произошло самое глубокое землетрясение в 2013 году.


Решение.
1) Для начала нужно подключить все необходимые библиотеки: sqlite, JFreechart, jcommon. Если коротко, то подключение библиотек происходит следующим образом:
File -> Project Structure -> Libraries -> + -> Java -> <Выбрать путь до архива .jar> и файл землетрясения.csv должен быть в папке проекта.
2) Сначала я создал 2 класса (Main, Earthquake), в Main я распарсил землетрясения.csv (как в модуле 8) и создал массив объектов Earthquake.
3) Потом создал класс db, в нём написал методы для создания таблицы и добавления в неё данных, после чего в папке проекта появляется файл demo.db, его можно загрузить на сайт https://inloop.github.io/sqlite-viewer/ и посмотреть содержание таблицы, очень удобно!
4) После приступил к выполнению 3х заданий, все методы для выполнения этих заданий находятся в классе db.
5) Когда все задания были выполнены, результат 1го задания нужно было представить в виде графика, для этого был написан класс BarChart.
6) В итоге получаем такой вывод программы.
![image](https://github.com/uiovan/praktika/assets/152408698/b322a66e-9b9f-45ea-9a33-43d28f75952c)
![image](https://github.com/uiovan/praktika/assets/152408698/66117604-1d65-4639-89a1-9c4ff9d89f12)

