package com.nttdata.btask.services;

import com.nttdata.btask.interfaces.CardService;
import com.nttdata.domain.contract.CardRepository;
import com.nttdata.domain.models.CardDto;
import com.nttdata.infraestructure.entity.Card;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
@ApplicationScoped
public class CardServiceImpl implements CardService {
  private final CardRepository cardRepository;

  public CardServiceImpl(CardRepository cardRepository) {
    this.cardRepository = cardRepository;
  }

  @Override
  public List<Card> getAllCard() {
    return cardRepository.getAllCard();
  }

  @Override
  public Card addCard(CardDto cardDto) {
    return cardRepository.addCard(cardDto);
  }

  @Override
  public Card getByIdCard(Long id) {
    return cardRepository.getByIdCard(id);
  }

  @Override
  public List<Card> updateCardById(Long id, CardDto cardDto) {
    return cardRepository.updateCardById(id, cardDto);
  }

  @Override
  public List<Card> deleteCardById(Long id) {
    return cardRepository.deleteCardById(id);
  }
}
