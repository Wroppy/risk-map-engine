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
  public void showRoute() {
    // Gets the start and end countries from the user
    Country sourceCountry = this.getSourceCountry();
    Country destinationCountry = this.getDestinationCountry();

    // Checks if they are equal
    if (sourceCountry.equals(destinationCountry)) {
      MessageCli.NO_CROSSBORDER_TRAVEL.printMessage();
      return;
    }

    // Gets the shortest path from the source to the destination
    List<String> path = this.countryInfoEngine.getShortestPath(sourceCountry, destinationCountry);

    // Prints out the path to the user in the format [start, ..., end] 
    String pathString = this.getPathString(path);
    MessageCli.ROUTE_INFO.printMessage(pathString);
  }

  /**
   * Given the list of a path, returns the string displaying the path to the user.
   *
   * @param path the list of the path
   * @return the string representing the path taken to the user
   */
  public String getPathString(List<String> path) {
    // Gets the string builder and starts adding the country paths.
    StringBuilder pathString = new StringBuilder();
    pathString.append("[").append(path.get(0));

    // Loops through all elements apart from the first and adds it
    for (int i = 1; i < path.size(); i++) {
      pathString.append(", ").append(path.get(i));
    }
    pathString.append("]");

    return pathString.toString();
  }

  /**
   * Asks the user for a starting country.
   *
   * @return the country class associated with the country source name
   */
  public Country getSourceCountry() {
    MessageCli.INSERT_SOURCE.printMessage();
    return this.getCountryScanner();
  }

  /**
   * Asks the user for a destination country.
   *
   * @return the country class associated with the country destination name
   */
  public Country getDestinationCountry() {
    MessageCli.INSERT_DESTINATION.printMessage();
    return this.getCountryScanner();
  }
}
