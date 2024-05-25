package nz.ac.auckland.se281.countryinfoengine;

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
}
