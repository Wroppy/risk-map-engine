package nz.ac.auckland.se281.countryinfoengine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import nz.ac.auckland.se281.CountryNotFoundException;

/** This class handles parsing the country data and printing the country info. */
public class CountryEngine {
  private Map<String, Country> countryInfos;
  private CountryGraph countryGraph;

  /**
   * Sets up initial class attributes.
   *
   * @param countries the list of countries in the format of [name],[continent],[taxFees]
   * @param adjacencies
   */
  public CountryEngine(List<String> countries, List<String> adjacencies) {
    countryInfos = new HashMap<>();
    this.countryGraph = new CountryGraph(adjacencies);
    this.parseCountries(countries);
  }

  /**
   * Puts all the countries in the map.
   *
   * @param countries the countries in string format of [name],[continent],[taxFees]
   */
  private void parseCountries(List<String> countries) {
    // Loops through all the country strings
    for (String line : countries) {
      String[] countryData = line.split(",");

      // Splits it data up
      String name = countryData[0];
      String continent = countryData[1];
      int taxFee = Integer.parseInt(countryData[2]);

      Country countryInfo = new Country(name, continent, taxFee);

      this.countryInfos.put(name, countryInfo); // Adds it to the map
    }
  }

  /**
   * Given the country name, returns the country class.
   *
   * @param countryName the country name
   * @return the Country class associated with the country code
   */
  public Country getCountry(String countryName) throws CountryNotFoundException {
    if (!this.countryInfos.containsKey(countryName)) {
      throw new CountryNotFoundException(countryName);
    }

    return this.countryInfos.get(countryName);
  }
}