package nz.ac.auckland.se281.countryinfoengine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nz.ac.auckland.se281.CountryNotFoundException;

/** This class handles parsing the country data and printing the country info. */
public class CountryInfoEngine {
  private Map<String, CountryInfo> countryInfos;

  public CountryInfoEngine(List<String> countries) {
    countryInfos = new HashMap<>();
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

      CountryInfo countryInfo = new CountryInfo(name, continent, taxFee);

      this.countryInfos.put(name, countryInfo); // Adds it to the map
    }
  }

  /**
   * Given the country name, returns the country info message.
   *
   * @param countryName the country name
   * @return the string of the country info
   */
  public String getCountryInfo(String countryName) throws CountryNotFoundException {
    // Checks the country code exists
    if (!this.countryInfos.containsKey(countryName)) {
      throw new CountryNotFoundException(countryName);
    }

    CountryInfo countryInfo = this.countryInfos.get(countryName);
    return countryInfo.toString();
  }
}
