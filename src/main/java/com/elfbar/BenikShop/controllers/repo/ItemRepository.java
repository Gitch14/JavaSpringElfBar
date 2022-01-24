package com.elfbar.BenikShop.controllers.repo;

import com.elfbar.BenikShop.models.Item;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item, Long> {
}
