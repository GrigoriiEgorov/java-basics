import java.util.ArrayList;
import java.util.List;

public class Line {
    private String numberLine;
    private String nameLine;
    private List<Station> stationsList = new ArrayList<>();

    public Line(String numberLine, String nameLine) {
        this.numberLine = numberLine;
        this.nameLine = nameLine;
    }

    public void addStation (Station station){
        if(!stationsList.contains(station)){
            stationsList.add(station);
        } else {
            System.out.println("Station: " + station.getNameStation() + " has already had on line named " + getNameLine());
        }
    }

    public String getNumberLine() {
        return numberLine;
    }

    public String getNameLine() {
        return nameLine;
    }

    public List<Station> getStationsList() {
        return stationsList;
    }

    @Override
    public String toString() {
        return numberLine + ".\t" + nameLine;
    }
}
