package com.nttdata.application.rest;

import com.nttdata.btask.interfaces.CardService;
import com.nttdata.domain.models.CardDto;
import com.nttdata.domain.models.ResponseDto;
import com.nttdata.infraestructure.entity.Card;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Path("/card")
public class CardController {
  private final CardService cardService;

  public CardController(CardService cardService) {
    this.cardService = cardService;
  }
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response getAllCard() {
    List<Card> listCard = cardService.getAllCard()
        .stream()
        .filter(card ->card.getActive().equals("S"))
        .collect(Collectors.toList());
    return Response.ok(listCard).status(200).build();
  }

  @GET
  @Path("{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getByIdCard(@PathParam("id") Long id) {
    List<Card> list = new ArrayList<>();
    Card card = cardService.getByIdCard(id);
    list.add(card);
    List<Card> listCard = list.stream()
        .filter(c ->c.getActive().equals("S"))
        .collect(Collectors.toList());
    return Response.ok(listCard).status(200).build();
  }
  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public Response addCard(CardDto cardDto) {
    return Response.ok(cardService.addCard(cardDto)).status(201).build();
  }

  @PUT
  @Path("{id}")
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public Response updateCard(Long id, CardDto cardDto) {
    ResponseDto responseDto = new ResponseDto();
    List<Card> customer = this.cardService.updateCardById(id, cardDto);
    if(customer.size() == 0){
      responseDto.setStatus("204");
      responseDto.setMessage("customer not found");
      return Response.ok(responseDto).status(204).build();

    }else{
      responseDto.setStatus("200");
      responseDto.setMessage("Se proceso Correctamente");
      responseDto.setCard(customer);
      return Response.ok(responseDto).build();
    }
  }
  @DELETE
  @Path("{id}")
  public Response deleteCustomer(@PathParam("id") Long id) {
    ResponseDto responseDto = new ResponseDto();
    List<Card> customer = this.cardService.deleteCardById(id);

    if(customer.size() == 0){
      responseDto.setStatus("204");
      responseDto.setMessage("Card not found");
      return Response.ok(responseDto).status(204).build();

    }else{
      responseDto.setStatus("200");
      responseDto.setMessage("Se proceso Correctamente");
      responseDto.setCard(customer);
      return Response.ok(responseDto).build();
    }

  }
  @POST
  @Path("/search/card")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getByNumberCard(CardDto cardDto) {

    ResponseDto responseDto = new ResponseDto();
    List<Card> listCard = cardService.getAllCard()
        .stream()
        .filter(card ->card.getActive().equals("S"))
        .filter(cb -> cb.getNumberCard().equals(cardDto.getNumberCard()))
        .filter(c -> c.getNumberAccountAssociated().equals(cardDto.getNumberAccountAssociated()))
        .filter(a -> a.getCodeValidation().equals(cardDto.getCodeValidation()))
        .filter(s -> s.getDueDate().equals(cardDto.getDueDate()))
        .filter(x -> x.getPin().equals(cardDto.getPin()))
        .collect(Collectors.toList());

    if(listCard.size() == 0){
      responseDto.setStatus("204");
      responseDto.setMessage("not content card");
      responseDto.setCard(listCard);
    }else{
      responseDto.setStatus("200");
      responseDto.setMessage("Se Proceso Correctamente");
      responseDto.setCard(listCard);
    }
    System.out.println(listCard);

    return Response.ok(responseDto).status(200).build();
  }


}
