# VehicleFleet
Проект Автопарк (учёт автомобилей + трекинг).

Чек-лист проекта.

1.	Создание основной модели Vehicle (автомобиль), поля (стоимость, год выпуска, пробег, и т. д.), без добавления марка/бренд с техническими характеристиками это отдельная модель.
2.	Создание максимально простого веб-интерфейса, чтобы можно было просто просмотреть список машин в базе.  Условная "админка" — для удобства разработчика. Использовать Thymeleaf.
3.	Добавление отдельной модели брендов (имя бренда, тип (легковой, грузовой, автобус, ...), 3-4 другие хар-ки - бак, грузоподъёмность, кол-во мест), и привязка её к модели машинки. Просмотр в UI отдельно список брендов и список машинок.
4.	Добавление в UI максимально простого CRUD – редактирование, добавление, удаление автомобилей.
5.	Добавление REST API, по которому (по отдельному url) будет выдаваться список всех машинок в формате json (пока без авторизации и прочего).
6.	Добавление ещё двух базовых модели: Enterprise (предприятие), Driver (водитель). Основные поля этим моделям по усморению. Например, название + город, имя + зарплата.
7.	Организация между моделями таких связи:
Предприятию могут принадлежать несколько автомобилей (один ко многим).
Предприятию могут принадлежать несколько водителей (один ко многим).
Автомобиль и водитель могут принадлежать только одному предприятию.
Каждому автомобилю может быть назначено несколько водителей (один к многим). Водители для разных автомобилей могут пересекаться (один водитель может быть назначен разным автомобилям).
Один из назначенных водителей дополнительно считается "активным" (через связь) — это тот, кто работает на машине в данный момент. Активный водитель может работать только на одной машине (не может быть назначен активным на второй автомобиль).
Создаваемый водитель исходно ни к какой машине не привязан.
Списки водителей и предприятий отдельно выдавать через REST.
Создание 2-3 предприятий, в каждом по нескольку автомобилей с водителями и без.
8.	Добавление модели Manager (менеджер) наследник от User. Предприятию могут принадлежать несколько менеджеров.
Менеджеру могут "принадлежать" (быть видимыми) несколько предприятий.
Менеджер получает в REST-запросах только объекты видимых ему предприятий.
Сделать случай, когда три предприятия 1,2,3 и два менеджера, одному принадлежат предприятия 1,2, другому 2,3.
Машинки и водители должны выдаваться теперь только из видимых предприятий.
9.	Организация с помощью curl запрос машинок и всего остального с авторизацией менеджера, как это выполнено в браузере.
10.	Создание REST-добавление новой/изменение полей существующей заданной машинки и предприятия, и удаление — через PUT/POST/DELETE, после того как менеджер авторизовался. Проверить из curl.
11.	Если обычный User (не менеджер), корректно авторизовавшийся, PUT/POST/DELETE отправит, то получит: Выдать 401, если это обычный User, или 403, если это менеджер, пытающийся получить доступ к недоступным ему ресурсам.
12.	Создание утилиты генерации для предприятия (списка предприятий) заданного количества машинок, содержимое их формировать случайно, и водителей (чтобы примерно каждая 10-я машинка была с активным водителем).
