package vttp.batch5.ssf.day16.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;

import vttp.batch5.ssf.day16.services.APIService;

@Controller
@RequestMapping
public class APIController {

    @Value("${api.key}")
    private String API_KEY;

    @Autowired
    private APIService apiSvc;

    public static final String URL = "https://api.giphy.com/v1/gifs/search";

    @GetMapping("/search")
    public String getSearch(Model model, @RequestParam MultiValueMap<String, String> form) {

        String url = UriComponentsBuilder.fromUriString(URL)
                                        .queryParam("api_key", API_KEY)
                                        .queryParam("q", form.getFirst("query"))
                                        .queryParam("limit", form.getFirst("limit"))
                                        .queryParam("offset", 0)
                                        .queryParam("rating", form.getFirst("rating"))
                                        .queryParam("lang", "en")
                                        .queryParam("bundle", "messaging_non_clips")
                                        .toUriString();

        List<String> imgList = apiSvc.getFixedImgs(url);
        model.addAttribute("imgList", imgList);

        return "search";
    }
}
