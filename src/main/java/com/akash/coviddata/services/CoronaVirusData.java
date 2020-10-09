package com.akash.coviddata.services;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import javax.annotation.PostConstruct;

import com.akash.coviddata.models.Data;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class CoronaVirusData {
    private List<Data> alldata = new ArrayList<>();
    private String totalRecoveredInWorld;
    private String totalCasesInWorld;
    private String totalActiveCases;
    private String totalDeaths;

    @PostConstruct
    @Scheduled(cron = "* * 1 * * *")
    public void fetchVirusData() throws IOException, InterruptedException, ParseException {

        URL url = new URL("https://api.covid19api.com/summary");

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();

        // Getting the response code
        int responsecode = conn.getResponseCode();
        if (responsecode != 200) {
            throw new RuntimeException("HttpResponseCode: " + responsecode);
        } else {
            List<Data> list = new ArrayList<>();
            String inline = "";
            Scanner scanner = new Scanner(url.openStream());

            // Write all the JSON data into a string using a scanner
            while (scanner.hasNext()) {
                inline += scanner.nextLine();
            }

            // Close the scanner
            scanner.close();

            // Using the JSON simple library parse the string into a json object
            JSONParser parse = new JSONParser();
            JSONObject data_obj = (JSONObject) parse.parse(inline);

            // Get the required object from the above created object
            JSONObject obj = (JSONObject) data_obj.get("Global");

            // Get the required data using its key
            totalRecoveredInWorld = String.valueOf(obj.get("TotalRecovered"));
            totalCasesInWorld = String.valueOf(obj.get("TotalConfirmed"));
            totalDeaths = String.valueOf(obj.get("TotalDeaths"));
            totalActiveCases = String
                    .valueOf(Integer.parseInt(totalCasesInWorld) - Integer.parseInt(totalRecoveredInWorld));
            System.out.println(totalRecoveredInWorld);
            JSONArray arr = (JSONArray) data_obj.get("Countries");

            for (int i = 0; i < arr.size(); i++) {
                Data data = new Data();
                JSONObject new_obj = (JSONObject) arr.get(i);

                data.setCountry(String.valueOf(new_obj.get("Country")));
                data.setTotalCases(Integer.parseInt(String.valueOf(new_obj.get("TotalConfirmed"))));
                data.setTotalRecovered(Integer.parseInt(String.valueOf(new_obj.get("TotalRecovered"))));
                data.setTotalDeaths(Integer.parseInt(String.valueOf(new_obj.get("TotalDeaths"))));
                data.setNewCases(Integer.parseInt(String.valueOf(new_obj.get("NewConfirmed"))));
                data.setNewRecovered(Integer.parseInt(String.valueOf(new_obj.get("NewRecovered"))));
                data.setNewDeath(Integer.parseInt(String.valueOf(new_obj.get("NewDeaths"))));
                // System.out.println(data);
                list.add(data);
            }
            this.alldata = list;
        }

    }

    public List<Data> getAlldata() {
        Collections.sort(alldata);
        return alldata;
    }

    public void setAlldata(List<Data> alldata) {
        this.alldata = alldata;
    }

    public String getTotalRecoveredInWorld() {
        return totalRecoveredInWorld;
    }

    public void setTotalRecoveredInWorld(String totalRecoveredInWorld) {
        this.totalRecoveredInWorld = totalRecoveredInWorld;
    }

    public String getTotalCasesInWorld() {
        return totalCasesInWorld;
    }

    public void setTotalCasesInWorld(String totalCasesInWorld) {
        this.totalCasesInWorld = totalCasesInWorld;
    }

    public String getTotalActiveCases() {
        return totalActiveCases;
    }

    public void setTotalActiveCases(String totalActiveCases) {
        this.totalActiveCases = totalActiveCases;
    }

    public String getTotalDeaths() {
        return totalDeaths;
    }

    public void setTotalDeaths(String totalDeaths) {
        this.totalDeaths = totalDeaths;
    }

}
