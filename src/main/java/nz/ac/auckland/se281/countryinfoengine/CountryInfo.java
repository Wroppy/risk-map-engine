package nz.ac.auckland.se281.countryinfoengine;

import nz.ac.auckland.se281.MessageCli;

/** A class that holds a country's info */
public class CountryInfo {
  private String name;
  private String continent;
  private int taxFees;

  public CountryInfo(String name, String continent, int taxFees) {
    this.name = name;
    this.continent = continent;
    this.taxFees = taxFees;
  }

  @Override
  public String toString() {
    String taxFeesStr = String.valueOf(taxFees);
    return MessageCli.COUNTRY_INFO.getMessage(name, continent, taxFeesStr);
  }
}
