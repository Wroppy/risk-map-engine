package nz.ac.auckland.se281.countryinfoengine;

import nz.ac.auckland.se281.MessageCli;

/** A class that holds a country's info. */
public class Country {
  private String name;
  private String continent;
  private int taxFees;

  /**
   * Sets the class attributes.
   *
   * @param name the countries nam
   * @param continent the continent the country is located
   * @param taxFees the tax fee of the country
   */
  public Country(String name, String continent, int taxFees) {
    this.name = name;
    this.continent = continent;
    this.taxFees = taxFees;
  }

  @Override
  public String toString() {
    String taxFeesStr = String.valueOf(taxFees);
    return MessageCli.COUNTRY_INFO.getMessage(name, continent, taxFeesStr);
  }

  @Override
  public int hashCode() {
    // Auto generated by VS Code
    final int prime = 31;
    int result = 1;
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    // Auto generated by VS Code with braces added on for codestyle
    if (this == obj) {
      return true;
    }

    if (obj == null) {
      return false;
    }

    if (getClass() != obj.getClass()) {
      return false;
    }

    Country other = (Country) obj;

    if (name == null) {
      if (other.name != null) {
        return false;
      }
    } else if (!name.equals(other.name)) {
      return false;
    }
    return true;
  }

  /**
   * A getter for the name attribute.
   *
   * @return the name
   */
  public String getName() {
    return this.name;
  }

  /**
   * A getter for the continent attribute.
   *
   * @return the continent the country is located in
   */
  public String getContinent() {
    return this.continent;
  }

  /**
   * A getter for the tax fees attribute.
   *
   * @return the tax fee
   */
  public int getTaxFees() {
    return this.taxFees;
  }
}
