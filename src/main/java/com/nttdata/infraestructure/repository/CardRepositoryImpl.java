package com.nttdata.infraestructure.repository;

import com.nttdata.domain.contract.CardRepository;
import com.nttdata.domain.models.CardDto;
import com.nttdata.infraestructure.entity.Card;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@ApplicationScoped
public class CardRepositoryImpl implements CardRepository {

  @Override
  public List<Card> getAllCard() {
    return Card.listAll();
  }

  @Override
  public Card getByIdCard(Long id) {
    return Card.findById(id);
  }



  @Override
  @Transactional
  public Card addCard(CardDto cardDto) {
    Card card = new Card();
    System.out.println(cardDto);
    card.setNumberCard(cardDto.getNumberCard());
    card.setTypeCard(cardDto.getTypeCard());
    card.setCodeValidation(cardDto.getCodeValidation());
    card.setPin(cardDto.getPin());
    card.setDueDate(this.getDateDueDate());
    card.setCreated_datetime(this.getDateNow());
    card.setUpdated_datetime(this.getDateNow());
    card.setActive("S");
    card.persist();
    return card;
  }



  @Override
  @Transactional
  public List<Card> updateCardById(Long id, CardDto cardDto) {
    List<Card> collect = new ArrayList<>();
    Card customerOp = Card.findById(id);

    if(customerOp == null){
      return collect;
    }else{
      if(customerOp.getActive().equals("S")){
        customerOp.setTypeCard(cardDto.getTypeCard());
        customerOp.setNumberCard(cardDto.getNumberCard());
        customerOp.setPin(cardDto.getPin());
        customerOp.setDueDate(cardDto.getDueDate());
        customerOp.setCodeValidation(cardDto.getCodeValidation());
        customerOp.setUpdated_datetime(this.getDateNow());
        customerOp.persist();
        collect.add(customerOp);
      }else{
        return collect;
      }
    }
    return collect;
  }

  @Override
  @Transactional
  public List<Card> deleteCardById(Long id) {
    List<Card> collect = new ArrayList<>();
    Card cardOp = Card.findById(id);

    if(cardOp == null){
      return collect;
    }else{
      cardOp.setUpdated_datetime(this.getDateNow());
      cardOp.setActive("N");
      cardOp.persist();
      collect.add(cardOp);
      return collect;
    }

  }

  private static String getDateNow(){
    Date date = new Date();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    return formatter.format(date).toString();
  }
  private static String getDateDueDate(){
    Date date = new Date();
    SimpleDateFormat formatter = new SimpleDateFormat("MM/yyyy");
    return formatter.format(date).toString();
  }
}
