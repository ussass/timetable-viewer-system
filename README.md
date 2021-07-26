#Timetable viewer system

##Общее

* В университете проводится 6 пар в день ПН-ВС, Воскресенье обычно выходной

* Студенты разделены на группы

* Пары привязаны к группам

* Для простоты считаем что обледенённых лекций для нескольких групп не бывает

* Аудитории не имеют специализации и все одинаковы

* Преподаватели имеют специализацию

##Расписание
  Расписание задается один раз в начале учебного периода(семестра) его изменение
  имеет немедленный эффект, любые дополнительные изменения происходят в частном порядке
  
##Пользователи
* Любой пользователь может посмотреть общее для всех групп (или по выбору) расписание  на неделю, день (сегодня или на дату)

* По ссылке полученной из деканата пользователь может быть зарегистрирован в системе и получить набор ролей. Далее эти роли могут быть отредактированы пользователем с ролью Admin

* пользователь может обладать как одной ролью, так и набором ролей

##Роли
####Сотрудник(stuff)

* Кто угодно не связанный с процессом - уборщица, техник и тд)

* по умолчанию видит расписание аудиторий


####Студент (student)

* по умолчанию видит расписание для своей группы

####Преподаватель (teacher)

* по умолчанию видит свое расписание

####Администратор/Деканат (admin)

* может редактировать рассписние

* может редактировать роли пользователя