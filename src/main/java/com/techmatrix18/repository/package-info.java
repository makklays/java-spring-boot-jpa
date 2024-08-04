package com.techmatrix18.repository;

// CrudRepository -> PagingAndSortingRepository -> JpaRepository

// --- for paging -----

//public interface ProductRepository extends PagingAndSortingRepository<Product, Integer> {
//    Page<Product> allProductsSortedByName = productRepository.findAll(Sort.by("name"));
//}
// Pageable firstPageWithTwoElements = PageRequest.of(0, 2);
// Pageable secondPageWithFiveElements = PageRequest.of(1, 5);
// Page<Product> allProductsSortedByName = productRepository.findAll(Sort.by("name"));
// Pageable sortedByName =
//  PageRequest.of(0, 3, Sort.by("name"));
//
//Pageable sortedByPriceDesc =
//  PageRequest.of(0, 3, Sort.by("price").descending());
//
//Pageable sortedByPriceDescNameAsc =
//  PageRequest.of(0, 5, Sort.by("price").descending().and(Sort.by("name")));
