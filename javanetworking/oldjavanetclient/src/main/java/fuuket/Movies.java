package fuuket;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Movies {

  static final String url="https://jsonmock.hackerrank.com/api/movies/search/?Title=";

  private static String sendRequest(String url) throws Exception {

    StringBuilder result=new StringBuilder();
    URL u=new URL(url);
    HttpURLConnection c=(HttpURLConnection) u.openConnection();
    c.setRequestMethod("GET");
    BufferedReader r=new BufferedReader(new InputStreamReader(c.getInputStream()));
    String line;
    while ( (line=r.readLine())!=null ) {
      result.append(line);
    }
    r.close();
    return result.toString();
  }

  private static String[] fetchMovieTitles(String query) {

    List<String> result=new ArrayList<>();
    try {
      String response=sendRequest(url+query);
      JsonParser p=new JsonParser();
      JsonObject body=p.parse(response).getAsJsonObject();
      JsonElement totalPages=body.get("total_pages");

      int currPage=0;
      while ( currPage++<Integer.parseInt(totalPages.toString()) ) nextPage(result,currPage,query);
      Collections.sort(result);
    } catch ( Exception e ) {
      e.printStackTrace();
    }
    return result.toArray(new String[result.size()]);
  }

  private static void nextPage(List<String> result, int currPage, String query) throws Exception {

    String response=sendRequest(url+query+"&page="+currPage);
    JsonParser p=new JsonParser();
    JsonObject body=p.parse(response).getAsJsonObject();
    JsonElement data=body.get("data");
    JsonArray dataArray=data.getAsJsonArray();

    for (JsonElement e : dataArray) {
      String title=e.getAsJsonObject().get("Title").getAsString();
      result.add(title);
    }
  }

  public static void main(String[] args) {

    for (String title : fetchMovieTitles("spiderman")) System.out.println(title);
  }

}
