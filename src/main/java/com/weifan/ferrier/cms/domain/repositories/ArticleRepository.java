package com.weifan.ferrier.cms.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.weifan.ferrier.cms.domain.entities.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long>{

}
