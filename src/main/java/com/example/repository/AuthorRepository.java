package com.example.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.entity.Author;

@RepositoryRestResource
public interface AuthorRepository extends PagingAndSortingRepository<Author, Long> {
}