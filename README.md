# Описание
Согласно заданию весь код был помещен в пакет `com.gridnine.testing`. 

Был создан модуль, состоящий из трех классов и одного интерфейса:

- `ArrivalBeforeOrAfterDepartureFilter`
- `DepartureBeforeOrAfterCurrentTimeFilter`
- `GroundTimeExceedsHoursFilter`

Данные классы реализуют интерфейс `FlightFilter`, который служит для фильтрации списка рейсов по определенным критериям.
Интерфейс определяет метод `filterFlights`, который принимает список рейсов и возвращает отфильтрованный список рейсов, соответствующих критериям фильтрации.

```java
/**
 * This interface defines a method for filtering flights.
 */
public interface FlightFilter {

    /**
     * Filters a list of flights based on custom criteria.
     *
     * @param flights The list of flights to be filtered.
     * @return A list of flights that meet the filter criteria.
     */
    List<Flight> filterFlights(List<Flight> flights);
}
```  

Код для модуля фильтрации покрыт тестами  

![](/assets/img.png)