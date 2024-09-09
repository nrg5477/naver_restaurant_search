package org.example.restaurant.wishlist.repository;

import org.example.restaurant.db.MemoryDbRepositoryAbstract;
import org.example.restaurant.wishlist.entity.WishListEntity;
import org.springframework.stereotype.Repository;

@Repository
public class WishListRepository extends MemoryDbRepositoryAbstract<WishListEntity> {

}
