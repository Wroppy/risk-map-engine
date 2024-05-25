package nz.ac.auckland.se281;

import java.util.List;
import nz.ac.auckland.se281.countryinfoengine.Country;
import nz.ac.auckland.se281.countryinfoengine.CountryEngine;

/** This class is the main entry point. */
public class MapEngine {
  private CountryEngine countryInfoEngine;

  public MapEngine() {
    // add other code here if you want
    loadMap(); // keep this method invocation
  }

  /** Invoked one time only when constructing the MapEngine class. */
  private void loadMap() {
    List<String> countries = Utils.readCountries();
    List<String> adjacencies = Utils.readAdjacencies();

    this.countryInfoEngine = new CountryEngine(countries, adjacencies);
  }

  /**
   * Asks the user for a country on the map. Then displays the name, continent and tax fees
   * associated with it.
   */
  public void showInfoCountry() {
    MessageCli.INSERT_COUNTRY.printMessage();

    Country country = this.getCountryScanner();
    System.out.println(country.toString());
  }

  /**
   * Asks the user for a valid country in the list of countries in the command line.
   *
   * @return the country associated with the user input
   */
  public Country getCountryScanner() {
    while (true) { // Loops until the user gets a valid country
      try {
        // Gets the user input
        String userInput = Utils.scanner.nextLine();
        String cappedInput = Utils.capitalizeFirstLetterOfEachWord(userInput);

        // Returns the country represented by the Country class
        return this.countryInfoEngine.getCountry(cappedInput);
      } catch (CountryNotFoundException e) {
        // Prints out the error message given.
        System.out.println(e.toString());
      }
    }
  }

  /** this method is invoked when the user run the command route. */
  public void showRoute() {}
}
