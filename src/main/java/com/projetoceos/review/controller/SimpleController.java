package com.projetoceos.review.controller;

import java.util.List;

import com.projetoceos.review.config.ApplicationProperties;
import com.projetoceos.review.entity.Review;
import com.projetoceos.review.service.ReviewService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SimpleController {

    private final int ROW_PER_PAGE = 5;

    @Autowired
    ReviewService reviewService;
    
    @Autowired
    ApplicationProperties applicationProperties;

    @Value("${app.menu}")
    String menu;

    @GetMapping(value = {"/", "/home"})
    public String homePage(Model model) {
        model.addAttribute("title", applicationProperties.getTitle());
        model.addAttribute("menu", menu);
        return "home";
    }

    @GetMapping(value = "/reviews")
    public String getReviews(Model model, @RequestParam(value = "page", defaultValue = "1") int pageNumber) {
        List<Review> reviews = reviewService.findAll(pageNumber, ROW_PER_PAGE);
 
        long count = reviewService.count();
        boolean hasPrev = pageNumber > 1;
        boolean hasNext = (pageNumber * ROW_PER_PAGE) < count;
        model.addAttribute("reviews", reviews);
        model.addAttribute("hasPrev", hasPrev);
        model.addAttribute("prev", pageNumber - 1);
        model.addAttribute("hasNext", hasNext);
        model.addAttribute("next", pageNumber + 1);

        return "review-list";
    }
}
