package com.projetoceos.review.service;

import java.util.ArrayList;
import java.util.List;

import com.projetoceos.review.entity.Review;
import com.projetoceos.review.repository.ReviewRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
    
    @Autowired
    private ReviewRepository reviewRepository;

    public List<Review> findAll(int pageNumber, int rowPerPage){
        List<Review> reviews = new ArrayList<>();
        Pageable sortedByIdAsc = PageRequest.of(pageNumber - 1, rowPerPage, Sort.by("id").ascending());
        
        reviewRepository.findAll(sortedByIdAsc).forEach(reviews::add);

        return reviews;
    }

    public Long count() {
        return reviewRepository.count();
    }
}
