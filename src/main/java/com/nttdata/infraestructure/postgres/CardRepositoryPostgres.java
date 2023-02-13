package com.nttdata.infraestructure.postgres;

import com.nttdata.infraestructure.entity.Card;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

public interface CardRepositoryPostgres extends PanacheRepository<Card> {
}
