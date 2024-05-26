package nz.ac.auckland.se281.countryinfoengine;

import java.util.HashMap;
import java.util.LinkedList;
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
   * @param adjacencies the list of country adjacencies from the text file
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

  /**
   * Given the source and destination country, returns the shortest path between the two using the
   * BFS algorithm.
   *
   * @param sourceCountry the starting country
   * @param destinationCountry the ending country
   * @return the list containing the countries name path.
   */
  public List<String> getShortestPath(Country sourceCountry, Country destinationCountry) {
    String start = sourceCountry.getName();
    String end = destinationCountry.getName();
    // Gets the map of parents of the nodes visited
    Map<String, String> parentMap = this.countryGraph.getParentMap(start, end);

    List<String> reversedPath = new LinkedList<>();

    // Works backwards from the destination country
    String currentNode = end;
    reversedPath.add(currentNode);
    String parent = parentMap.get(currentNode);
    while (parent != null) {
      reversedPath.add(parent);

      // Changes the current node to the parent
      currentNode = parent;
      // Then gets the parent of the parent
      parent = parentMap.get(currentNode);
    }

    return this.reverseList(reversedPath);
  }

  /**
   * Given a linked list, returns the same list but in reverse.
   *
   * @param list the list to be reversed
   * @return the reversed list
   */
  private List<String> reverseList(List<String> list) {
    List<String> copy = new LinkedList<>();

    // Loops backwards and adds each element
    for (int i = list.size() - 1; i >= 0; i--) {
      copy.add(list.get(i));
    }
    return copy;
  }
}
