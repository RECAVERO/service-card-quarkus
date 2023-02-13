package com.nttdata.infraestructure.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Card")
public class Card extends PanacheEntity {

  private int typeCard;
  private String numberCard;
  private String numberAccountAssociated;
  private String pin;
  private String dueDate;
  private String codeValidation;
  private String created_datetime;
  private String updated_datetime;
  private String active;

}
