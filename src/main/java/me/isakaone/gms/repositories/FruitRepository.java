package me.isakaone.gms.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import me.isakaone.gms.entity.Fruit;

@RepositoryRestResource
public interface FruitRepository extends PagingAndSortingRepository<Fruit, Long> {

}
