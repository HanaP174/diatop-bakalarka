package com.diatop.resource.service;

import com.diatop.model.order.Order;
import com.diatop.model.user.UserDto;
import com.diatop.model.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class EmailService {

  @Autowired
  private JavaMailSender javaMailSender;

  @Autowired
  private UserRepository userRepository;

  public void sendEmail(Order order) {
    SimpleMailMessage message = new SimpleMailMessage();
    Optional<UserDto> userOptional = getUser(order.getUserId()).blockOptional();

    if (userOptional.isPresent()) {
      message.setTo("pilkova@topix.sk");
      message.setSubject(getSubject(userOptional.get()));
      message.setText(getMessage(userOptional.get(), order));

      javaMailSender.send(message);
    }
  }

  private Mono<UserDto> getUser(Long id) {
    return userRepository.findUserById(id)
            .map(user -> UserDto.builder()
                    .birthNumber(user.getBirthNumber())
                    .email(user.getEmail())
                    .firstName(user.getFirstName())
                    .lastName(user.getLastName())
                    .build());
  }

  private String getSubject(UserDto user) {
    return "objednávka od " + user.getFirstName() + " " + user.getLastName();
  }

  private String getMessage(UserDto user, Order order) {
    String personalDelivery = order.getPersonalDelivery() ? "Osobné doručenie" : "";
    String address = getAddress(order);
    return "Objednávka:\n"
            + user.getFirstName() + " " + user.getLastName() + " Rodné číslo: " + user.getBirthNumber() + "\n"
            + "Dátum návštevy doktora: " + formatDate(order.getAppointmentDate()) + "\nDátum doručenia objednávky: "
            + formatDate(order.getDeliveryDate()) + "\n"
            + address + "\n" + personalDelivery + "\n"
            + "Poznámka: " + order.getNote();
  }

  private String formatDate(LocalDate date) {
    return date.getDayOfMonth() + "." + date.getMonthValue() + "." + date.getYear();
  }

  private String getAddress(Order order) {
    if (order.getStreet() != null) {
      return "Ulica: " + order.getStreet() + " " + order.getStreetNumber() + " Mesto: " + order.getCity()
              + " Smerovacie číslo: " + order.getZipcode();
    }
    return "";
  }
}
