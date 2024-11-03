import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

// Program for print data in JSON format.x
public class ReadJson {
    static cat catImage = new cat("/");
    static dog dogImage = new dog("/");

    public static void main(String args[]) throws ParseException {
        // In java JSONObject is used to create JSON object
        // which is a subclass of java.util.HashMap.
        ReadJson h = new ReadJson();
        h.pull();

    }

    public  void pull() throws ParseException {
        String output = "default value";
        String totalJson="";
        try {
            URL url = new URL("https://api.thecatapi.com/v1/images/search");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {

                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));


            while ((output = br.readLine()) != null) {
                totalJson+=output;
            }

            conn.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONParser parser = new JSONParser();
        JSONArray jsonArray = (JSONArray) parser.parse(totalJson);


        try {
            JSONObject jsonObj = (JSONObject) jsonArray.get(0);
            String image = (String) jsonObj.get("url");
            catImage.image = image;
            System.out.println(catImage.image);
        }

        catch (Exception e) {
            e.printStackTrace();
        }

        //--------------------------------------------------------------------------------------------

        try {
            totalJson = "";
            URL url = new URL("https://dog.ceo/api/breeds/image/random");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {

                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));


            while ((output = br.readLine()) != null) {
                totalJson+=output;
            }

            conn.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONParser parser2 = new JSONParser();



        try {
            JSONObject jsonObj = (JSONObject) parser2.parse(totalJson);
            String image = (String) jsonObj.get("message");
            dogImage.image = image;
            System.out.println(dogImage.image);
        }

        catch (Exception e) {
            e.printStackTrace();
        }

    }
}


