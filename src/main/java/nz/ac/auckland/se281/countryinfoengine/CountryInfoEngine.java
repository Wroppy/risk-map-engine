package nz.ac.auckland.se281.countryinfoengine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** This class handles parsing the country data and printing the country info. */
public class CountryInfoEngine {
  private Map<String, CountryInfo> countryInfos;

  public CountryInfoEngine(List<String> countries) {
    countryInfos = new HashMap<>();
    this.parseCountries(countries);
  }

  /**
   * Puts all the countries in the
   *
   * @param countries
   */
  private void parseCountries(List<String> countries) {
    // Loops through all the country strings
    for (String line : countries) {
      String[] countryData = line.split(",");

      // Splits it data up
      String name = countryData[0];
      String continent = countryData[1];
      int taxFee = Integer.parseInt(countryData[2]);

      CountryInfo countryInfo = new CountryInfo(name, continent, taxFee);

      this.countryInfos.put(name, countryInfo); // Adds it to the map
    }
  }
}
