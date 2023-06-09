import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.xml.crypto.Data;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.BiConsumer;

public class Main {
    static private final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public static void main(String[] args) {
        String siteAddress = "https://skillbox-java.github.io/";

        HashMap<String, Line> linesHashMap = new HashMap<>();
        HashMap<String, Station> stationHashMap = new HashMap<>();

        ArrayList<File> jsonFiles = new ArrayList<>();
        ArrayList<File> csvFiles = new ArrayList<>();

        HashMap<String, Double> depthHashMap = new HashMap<>();
        HashMap<String, LocalDate> dataOpenHashMap = new HashMap<>();

        Document doc = getInformationFromSite(siteAddress);

        fillingTheListOfLines(doc, linesHashMap);
        fillingTheListOfStation(doc, stationHashMap, linesHashMap);

        searchFiles(new File("data"), jsonFiles, ".json");
        searchFiles(new File("data"), csvFiles, ".csv");

        jsonFiles.forEach(file -> parsJsonFile(file, dataOpenHashMap, depthHashMap));
        csvFiles.forEach(file -> parsCSVFile(file, dataOpenHashMap, depthHashMap));


        fillingInformationDateOpenAndDepth(stationHashMap, dataOpenHashMap, depthHashMap, linesHashMap);

        writeInformationInJsonFile(stationHashMap, linesHashMap);


        stationHashMap.forEach(new BiConsumer<String, Station>() {
            @Override
            public void accept(String key, Station station) {
                System.out.println(station.toString());
            }
        });

        System.out.println("JSON files: ");
        jsonFiles.forEach(e -> {
            System.out.println(e.getAbsolutePath());
        });

        System.out.println("CSV files: ");

        csvFiles.forEach(e -> {
            System.out.println(e.getAbsolutePath());
        });
    }

    private static void searchFiles(File rootFile,
                                    List<File> fileList,
                                    String fileExtensions) {
        if (rootFile.isDirectory()) {
//            System.out.println("searching at: " + rootFile.getAbsolutePath());
            File[] directoryFiles = rootFile.listFiles();
            if (directoryFiles != null) {
                for (File file : directoryFiles) {
                    if (file.isDirectory()) {
                        searchFiles(file, fileList, fileExtensions);
                    } else {
                        if (file.getName().toLowerCase().endsWith(fileExtensions)) {
                            fileList.add(file);
                        }
                    }
                }
            }
        }
    }

    static private void fillingTheListOfLines(Document doc,
                                              HashMap<String, Line> linesHashMap) {
        Elements linesElements = doc.select("span.js-metro-line.t-metrostation-list-header.t-icon-metroln");

        for (Element line : linesElements) {
            linesHashMap.put(line.attr("data-line"),
                    new Line(line.attr("data-line"), line.text()));
        }
    }

    static private void fillingTheListOfStation(Document doc,
                                                HashMap<String, Station> stationHashMap,
                                                HashMap<String, Line> lineHashMap) {

        Elements stationsElements = doc.select("p.single-station");
        String keyStation;

        for (Element station : stationsElements) {
            keyStation = station.child(1).text() + " line " + station.parent().attr("data-line");

            stationHashMap.put(keyStation,
                    new Station(station.child(0).text(),
                            station.child(1).text(),
                            station.parent().attr("data-line")));

            lineHashMap.get(station.parent().attr("data-line"))
                    .addStation(stationHashMap.get(keyStation));
        }


        Elements connections = doc.select("span.t-icon-metroln");
        for (Element connection : connections) {
            if (!connection.attr("title").isEmpty()) {
                String keyStationConnection = connection.parent().child(1).text() + " line " + connection.parent().parent().attr("data-line");
                stationHashMap.get(keyStationConnection).setConnection(connection.attr("title"));
            }
        }

    }

    static private void parsJsonFile(File jsonFile,
                                     HashMap<String, LocalDate> dateHashMap,
                                     HashMap<String, Double> depthHashMap) {

        try {
            FileReader fileReader = new FileReader(jsonFile);
            JSONParser parser = new JSONParser();
            JSONArray jsonArray = (JSONArray) parser.parse(fileReader);

            if (jsonFile.getName().contains("depths")) {
                jsonArray.forEach(jsonObject -> {
                    JSONObject depthJsonObject = (JSONObject) jsonObject;
                    String nameStation = (String) depthJsonObject.get("name");
                    String depthStation;
                    if (nameStation == null) {
                        nameStation = (String) depthJsonObject.get("station_name");
                        depthStation = String.valueOf(depthJsonObject.get("depth_meters"));
                    } else {
                        depthStation = String.valueOf(depthJsonObject.get("depth"));
                    }

                    if (depthStation.equals("?")) {
                        depthHashMap.put(nameStation, null);
                        String str = null;
                    } else {
                        depthHashMap.put(nameStation,
                                Double.parseDouble(depthStation
                                        .replaceAll("[^0-9-,.]", "")
                                        .replace(",", ".")));
                    }
                });
            }

            if (jsonFile.getName().contains("date")) {
                jsonArray.forEach(jsonObject -> {
                    JSONObject dateJsonObject = (JSONObject) jsonObject;
                    String nameStation = (String) dateJsonObject.get("name");
                    LocalDate dateOpenStation = LocalDate.parse(
                            String.valueOf(dateJsonObject.get("date")),
                            DATE_FORMATTER
                    );
                    dateHashMap.put(nameStation, dateOpenStation);
                });
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    static private void parsCSVFile(File csvFile,
                                    HashMap<String, LocalDate> dateHashMap,
                                    HashMap<String, Double> depthHashMap) {
        try {
            CSVParser parser = new CSVParser(new FileReader(csvFile), CSVFormat.DEFAULT.withHeader());

            if (csvFile.getName().contains("date")) {

                for (CSVRecord record : parser) {
                    String nameStation = record.get("Название станции");
                    LocalDate dateOpenStation = LocalDate.parse(record.get("Дата открытия"), DATE_FORMATTER);
                    dateHashMap.put(nameStation, dateOpenStation);
                }
                parser.close();
            }

            if (csvFile.getName().contains("depths")) {
                for (CSVRecord record : parser) {
                    String nameStation = record.get("Название");
                    String depthStation = String.valueOf(record.get("Глубина"));

                    if (depthStation.equals("?")) {
                        depthHashMap.put(nameStation, null);
                    } else {
                        depthHashMap.put(nameStation,
                                Double.parseDouble(depthStation
                                        .replaceAll("[^0-9-,.]", "")
                                        .replace(",", ".")));
                    }
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    static private Document getInformationFromSite(String siteAddress) {
        Document doc = null;
        try {
            doc = Jsoup.connect(siteAddress)
                    .userAgent("Chrome/4.0.249.0 Safari/532.5")
                    .referrer("http://www.google.com")
                    .get();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return doc;
    }

    static private void fillingInformationDateOpenAndDepth(HashMap<String, Station> stationHashMap,
                                                           HashMap<String, LocalDate> dateHashMap,
                                                           HashMap<String, Double> depthHashMap,
                                                           HashMap<String, Line> linesHashMap) {
        stationHashMap.forEach(new BiConsumer<String, Station>() {
            @Override
            public void accept(String key, Station station) {
                String nameStation = station.getNameStation();

                if (depthHashMap.containsKey(nameStation)) {
                    station.setDepth(depthHashMap.get(nameStation));
                }

                if (dateHashMap.containsKey(nameStation)) {
                    station.setDateOpenStation(dateHashMap.get(nameStation));
                }
                station.setNameLine(linesHashMap.get(station.getNumberLine()).getNameLine());

                System.out.println(station.toString());
            }
        });
    }

    static private void writeInformationInJsonFile(HashMap<String, Station> stationHashMap,
                                                   HashMap<String, Line> linesHashMap) {

        String pathStationJson = "target/stations.json";
        String pathLineJson = "target/lines.json";
        JSONObject stationJsonObject = new JSONObject();
        JSONArray stationArray = new JSONArray();

        try {
            stationHashMap.forEach(new BiConsumer<String, Station>() {
                @Override
                public void accept(String key, Station station) {
                    JSONObject stationObject = new JSONObject();
                    stationObject.put("name", station.getNameStation());
                    stationObject.put("line", station.getNameLine());
                    if (station.getDateOpenStation() != null) {
                        stationObject.put("date", station.getDateOpenStation().format(DATE_FORMATTER));
                    }
                    if (station.getDepth() != null) {
                        stationObject.put("depth", station.getDepth());
                    }
                    stationObject.put("has connection", station.isStationHasConnection());

                    stationArray.add(stationObject);
                }
            });
            stationJsonObject.put("stations", stationArray);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        try (PrintWriter out = new PrintWriter(new FileWriter(pathStationJson))) {
            out.write(stationJsonObject.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        JSONObject mapObject = new JSONObject();
        JSONObject mapStationObject = new JSONObject();
        JSONArray mapLinesArray = new JSONArray();

        try {
            linesHashMap.forEach(new BiConsumer<String, Line>() {
                @Override
                public void accept(String key, Line line) {
                    JSONArray stationOnLine = new JSONArray();
                    line.getStationsList().forEach(station -> {
                        stationOnLine.add(station.getNameStation());
                    });
                    mapStationObject.put(line.getNameLine(),stationOnLine);

                    JSONObject lineObject = new JSONObject();
                    lineObject.put("number",line.getNumberLine());
                    lineObject.put("name",line.getNameLine());

                    mapLinesArray.add(lineObject);
                }

            });
            mapObject.put("stations", mapStationObject);
            mapObject.put("lines", mapLinesArray);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        try (PrintWriter out = new PrintWriter(new FileWriter(pathLineJson))) {
            out.write(mapObject.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}
