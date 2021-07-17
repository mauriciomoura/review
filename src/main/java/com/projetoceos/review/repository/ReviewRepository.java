package com.projetoceos.review.repository;

import java.util.List;

import com.projetoceos.review.entity.Review;

import org.springframework.data.repository.CrudRepository;

public interface ReviewRepository extends CrudRepository<Review, Long> {
    List<Review> findByTitle(String title);
}
