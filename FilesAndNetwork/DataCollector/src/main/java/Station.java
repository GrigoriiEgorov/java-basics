import java.time.LocalDate;

public class Station {
    private String nameStation;
    private String numberStation;
    private String numberLine;
    private boolean stationHasConnection;
    private String informationAboutConnection;
    private LocalDate dateOpenStation;
    private Double depth;
    private String nameLine;

    public Station(String numberStation, String nameStation, String numberLine) {
        this.numberStation = numberStation;
        this.nameStation = nameStation;
        this.numberLine = numberLine;
        this.stationHasConnection = false;
        this.dateOpenStation = null;
        this.depth = null;
    }

    public void setDateOpenStation(LocalDate dateOpenStation) {
        this.dateOpenStation = dateOpenStation;
    }

    public void setDepth(Double depth) {
        this.depth = depth;
    }

    public void setConnection (String informationAboutConnection){
        this.stationHasConnection = true;
        this.informationAboutConnection = informationAboutConnection;
    }

    public void setNameLine(String nameLine) {
        this.nameLine = nameLine;
    }

    public String getNameLine() {
        return nameLine;
    }

    public String getNumberStation() {
        return numberStation;
    }

    public String getNameStation() {
        return nameStation;
    }

    public String getNumberLine() {
        return numberLine;
    }

    public LocalDate getDateOpenStation() {
        return dateOpenStation;
    }

    public Double getDepth() {
        return depth;
    }

    public String getInformationAboutConnection() {
        return informationAboutConnection;
    }

    public boolean isStationHasConnection() {
        return stationHasConnection;
    }

    @Override
    public String toString() {
        return "station name: " + nameStation +";\t" +
                "number line: " + numberLine + ";\t" +
                "number station: " + numberStation + ";\t" +
                "information about connection: " + informationAboutConnection + "\t" +
                "data open: " + dateOpenStation + ";\t" +
                "depth: " + depth;
    }
}
