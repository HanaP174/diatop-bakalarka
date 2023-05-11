package com.diatop.model.user;

public class UserDto {

  private Long id;

  private String password;

  private String birthNumber;

  private String email;

  private String firstName;

  private String lastName;

  public UserDto() {}

  public UserDto(String birthNumber, String email, String firstName, String lastName) {
    this.birthNumber = birthNumber;
    this.email = email;
    this.firstName = firstName;
    this.lastName = lastName;
  }
  public UserDto(Long id, String birthNumber, String email, String firstName, String lastName) {
    this.id = id;
    this.birthNumber = birthNumber;
    this.email = email;
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getPassword() {
      return password;
  }

  public void setPassword(String password) {
      this.password = password;
  }

  public String getBirthNumber() {
      return birthNumber;
  }

  public void setBirthNumber(String birthNumber) {
      this.birthNumber = birthNumber;
  }

  public String getEmail() {
      return email;
  }

  public void setEmail(String email) {
      this.email = email;
  }

  public String getFirstName() {
      return firstName;
  }

  public void setFirstName(String firstName) {
      this.firstName = firstName;
  }

  public String getLastName() {
      return lastName;
  }

  public void setLastName(String lastName) {
      this.lastName = lastName;
  }

  public static UserDtoBuilder builder() {
    return new UserDtoBuilder();
  }

  public static final class UserDtoBuilder {
      private String birthNumber;
      private String email;
      private String firstName;
      private String lastName;

      public UserDtoBuilder birthNumber(String birthNumber) {
        this.birthNumber = birthNumber;
        return this;
      }

      public UserDtoBuilder email(String email) {
        this.email = email;
        return this;
      }
      public UserDtoBuilder firstName(String firstName) {
        this.firstName = firstName;
        return this;
      }
      public UserDtoBuilder lastName(String lastName) {
        this.lastName = lastName;
        return this;
      }

      public UserDto build() {
          return new UserDto(this.birthNumber, this.email, this.firstName, this.lastName);
      }
  }
}
