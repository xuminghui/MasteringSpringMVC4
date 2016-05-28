package com.example.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.entity.Publisher;

@RepositoryRestResource
public interface PublisherRepository extends PagingAndSortingRepository<Publisher, Long> {
}