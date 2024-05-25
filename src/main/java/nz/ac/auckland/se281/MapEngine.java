package nz.ac.auckland.se281;

import java.util.List;
import nz.ac.auckland.se281.countryinfoengine.Country;
import nz.ac.auckland.se281.countryinfoengine.CountryInfoEngine;

/** This class is the main entry point. */
public class MapEngine {
  private CountryInfoEngine countryInfoEngine;

  public MapEngine() {
    // add other code here if you want
    loadMap(); // keep this method invocation
  }

  /** Invoked one time only when constructing the MapEngine class. */
  private void loadMap() {
    List<String> countries = Utils.readCountries();
    List<String> adjacencies = Utils.readAdjacencies();
    // add code here to create your data structures
    this.countryInfoEngine = new CountryInfoEngine(countries);
  }

  /** this method is invoked when the user run the command info-country. */
  public void showInfoCountry() {
    MessageCli.INSERT_COUNTRY.printMessage();

    Country country = this.getCountryScanner();
    System.out.println(country.toString());
  }

  public Country getCountryScanner() {
    while (true) {
      try {
        String userInput = Utils.scanner.nextLine();
        String cappedInput = Utils.capitalizeFirstLetterOfEachWord(userInput);

        return this.countryInfoEngine.getCountry(cappedInput);
      } catch (CountryNotFoundException e) {
        System.out.println(e.toString());
      }
    }
  }

  /** this method is invoked when the user run the command route. */
  public void showRoute() {}
}
