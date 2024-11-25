package vttp.batch5.ssf.day16.services;

import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

@Service
public class APIService {

    // Data structure
    // object -> array of objects -> "images" (object) -> "fixed-height" (object) -> url (string)
    
    public List<String> getFixedImgs(String url) {
        List<String> fixed = new LinkedList<>();

        RequestEntity<Void> req = RequestEntity.get(url)
            .accept(MediaType.APPLICATION_JSON)
            .build();

        RestTemplate template = new RestTemplate();

        ResponseEntity<String> resp = null;

        try{
            resp = template.exchange(req, String.class);
            String payload = resp.getBody();

            JsonReader reader = Json.createReader(new StringReader(payload));
            JsonObject result = reader.readObject();
            JsonArray dataArr = result.getJsonArray("data");

            for(int i = 0; i < dataArr.size(); i++) {
                String img = dataArr.getJsonObject(i)
                                    .getJsonObject("images")
                                    .getJsonObject("fixed_height")
                                    .getString("url");
                fixed.add(img);
            }
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return fixed;
    }
}
