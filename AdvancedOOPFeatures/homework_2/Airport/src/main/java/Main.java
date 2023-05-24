import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;
import com.skillbox.airport.Terminal;
import net.sf.saxon.lib.FeatureKeys;
import org.checkerframework.checker.units.qual.A;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Airport airport = Airport.getInstance();

        findPlanesLeavingInTheNextTwoHours(airport).stream()
                .forEach(System.out::println);
    }

    public static List<Flight> findPlanesLeavingInTheNextTwoHours(Airport airport) {
        //TODO Метод должден вернуть список рейсов вылетающих в ближайшие два часа.
        Calendar nowTime = Calendar.getInstance();
        Calendar futurePoint = Calendar.getInstance();
        int timePeriodHours = 2;
        futurePoint.add(Calendar.HOUR, timePeriodHours);
        List<Flight> filtersFlight = new ArrayList<>();

        airport.getTerminals().stream()
                .forEach(t -> {
                    t.getFlights().stream()
                            .filter(flight -> flight.getType().equals(Flight.Type.DEPARTURE))
                            .filter(flight ->
                            {
                                if (flight.getDate().compareTo(nowTime.getTime()) == 1 &&
                                        futurePoint.getTime().compareTo(flight.getDate()) == 1) {
                                    return true;
                                }
                                return false;
                            })
                            .forEach(filtersFlight::add);
                });

        return filtersFlight;
    }

}