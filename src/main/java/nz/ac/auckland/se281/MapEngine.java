package nz.ac.auckland.se281;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
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
  private Country getCountryScanner() {
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
    String pathString = this.getListString(path);
    MessageCli.ROUTE_INFO.printMessage(pathString);

    // Prints out the continents that the user travels in the format [start, ..., end]
    Set<String> continents = this.getContinents(path);
    List<String> continentsList = this.setToString(continents); // Converts to list
    String continentString = this.getListString(continentsList);
    MessageCli.CONTINENT_INFO.printMessage(continentString);

    // Prints out the amount of tax fees to travel
    int taxFees = this.getTaxFees(path);
    String taxFeesString = String.valueOf(taxFees);
    MessageCli.TAX_INFO.printMessage(taxFeesString);
  }

  private Set<String> getContinents(List<String> path) {
    // Uses hash set to remove continents.
    Set<String> continents = new LinkedHashSet<>();

    // Loops through all the countries to get the continents
    for (String countryName : path) {
      Country country = this.countryInfoEngine.getCountry(countryName);

      // Checks that the continent isn't already in the list
      String continent = country.getContinent();
      continents.add(continent);
    }
    return continents;
  }

  private List<String> setToString(Set<String> set) {
    List<String> list = new LinkedList<>();
    list.addAll(set);
    return list;
  }

  /**
   * Given a list of strings, returns the elements in a comma separated string.
   *
   * @param list the list to be printed out
   * @return the string representing the elements of the list in order
   */
  private String getListString(List<String> list) {
    // Gets the string builder and starts adding the country paths.
    StringBuilder string = new StringBuilder();
    string.append("[").append(list.get(0));

    // Loops through all elements apart from the first and adds it
    for (int i = 1; i < list.size(); i++) {
      string.append(", ").append(list.get(i));
    }
    string.append("]");

    return string.toString();
  }

  /**
   * Asks the user for a starting country.
   *
   * @return the country class associated with the country source name
   */
  private Country getSourceCountry() {
    MessageCli.INSERT_SOURCE.printMessage();
    return this.getCountryScanner();
  }

  /**
   * Asks the user for a destination country.
   *
   * @return the country class associated with the country destination name
   */
  private Country getDestinationCountry() {
    MessageCli.INSERT_DESTINATION.printMessage();
    return this.getCountryScanner();
  }

  /**
   * Calculates the amount of tax fees along each border traveled. Note that the initial country is
   * not added.
   *
   * @param path the route between two countries
   * @return the amount of tax fees to be paid
   */
  private int getTaxFees(List<String> path) {
    int fees = 0;

    // Loops through but the initial country to print the total tax fees
    for (int i = 1; i < path.size(); i++) {
      String countryName = path.get(i);
      Country country = this.countryInfoEngine.getCountry(countryName);
      fees += country.getTaxFees();
    }

    return fees;
  }
}
