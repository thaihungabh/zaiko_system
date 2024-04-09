package org.api.repository.inventory_output;

import org.api.bean.jpa.InventoryOutputEntity;
import org.api.repository.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryOutputRepository extends BaseRepository<InventoryOutputEntity, Integer> {

}
