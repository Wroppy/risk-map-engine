package nz.ac.auckland.se281;

public class CountryNotFoundException extends RuntimeException {
  private String country;

  public CountryNotFoundException(String country) {
    this.country = country;
  }

  @Override
  public String toString() {
    return MessageCli.INVALID_COUNTRY.getMessage(this.country);
  } 
}
