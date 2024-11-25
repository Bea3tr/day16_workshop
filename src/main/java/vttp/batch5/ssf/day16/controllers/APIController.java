package vttp.batch5.ssf.day16.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vttp.batch5.ssf.day16.services.APIService;

@Controller
@RequestMapping
public class APIController {

    @Autowired
    private APIService apiSvc;

    

    @GetMapping("/search")
    public String getSearch(Model model, @RequestParam MultiValueMap<String, String> form) {

        List<String> imgList = apiSvc.getFixedImgs(form);
        model.addAttribute("imgList", imgList);

        return "search";
    }
}
