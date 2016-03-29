package moc5.canteenchecker;

import android.net.Uri;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ServiceProxy {
    public Canteen[] getCanteens(String filter) throws ServiceCallException {
        return queryDataFromService("/GetCanteens" + (filter != null ? "?filter=" + Uri.encode(filter) : ""), Canteen[].class);
    }

    public CanteenDetails getCanteenDetails(int canteenId) throws ServiceCallException {
        return queryDataFromService("/GetCanteenDetails?id=" + Uri.encode(Integer.toString(canteenId)), CanteenDetails.class);
    }

    private <T> T queryDataFromService(String url, Class<T> resultClass) throws ServiceCallException {
        //execute get request
        StringBuffer result = new StringBuffer();
        executeGetRequest(url, result);
        //parse JSON response
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(result.toString(), resultClass);
    }

    private void executeGetRequest(String urlString, StringBuffer result) throws ServiceCallException {
        try {
            URL url = new URL("https://demo.nexperts.com/MOC5/CanteenService/CanteenService.svc" + urlString);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            try {
                BufferedReader reader = null;
                try {
                    reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    String line;
                    boolean isFirst = true;
                    while ((line = reader.readLine()) != null) {
                        if (isFirst) {
                            isFirst = false;
                        } else {
                            result.append(System.getProperty("line.separator"));
                        }
                        result.append(line);
                    }
                } finally {
                    if (reader != null) {
                        reader.close();
                    }
                }
            } finally {
                urlConnection.disconnect();
            }
        } catch (IOException e) {
            throw new ServiceCallException(e);
        }
    }
}

class ServiceCallException extends Exception {
    public ServiceCallException() {
        super();
    }

    public ServiceCallException(String detailMessage) {
        super(detailMessage);
    }

    public ServiceCallException(Throwable throwable) {
        super(throwable);
    }

    public ServiceCallException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }
}