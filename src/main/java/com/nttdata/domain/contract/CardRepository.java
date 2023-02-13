package com.nttdata.domain.contract;

import com.nttdata.domain.models.CardDto;
import com.nttdata.infraestructure.entity.Card;

import java.util.List;

public interface CardRepository {
  List<Card> getAllCard();

  Card addCard(CardDto cardDto);

  Card getByIdCard(Long id);

  List<Card> updateCardById(Long id,CardDto cardDto);
  List<Card> deleteCardById(Long id);
}
