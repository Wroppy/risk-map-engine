package nz.ac.auckland.se281.countryinfoengine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/** This class represents the countries on the map on a graph. */
public class CountryGraph {
  private Map<String, List<String>> adjNodes;

  public CountryGraph(List<String> adjacencies) {
    adjNodes = new HashMap<>();
    this.parseAdjacencies(adjacencies);
  }

  /**
   * Given the list of adjacencies, creates the adjacency map.
   *
   * @param adjacencies the countries adjacent to one another
   */
  private void parseAdjacencies(List<String> adjacencies) {
    for (String line : adjacencies) {
      String[] adjacencyData = line.split(",");

      // Puts an empty array in the map
      String node = adjacencyData[0];
      if (!this.adjNodes.containsKey(node)) {
        this.adjNodes.put(node, new ArrayList<String>());
      }

      // Loops through the adjacent nodes and adds it to the hash map
      for (int i = 1; i < adjacencyData.length; i++) {
        this.adjNodes.get(node).add(adjacencyData[i]);
      }
    }
  }

  /**
   * Uses the BFS algorithm to search through the map from a source country to a destination country
   * and returns the map of parent nodes it has visited.
   *
   * @param sourceCountry the starting country
   * @param destinationCountry the ending country
   * @return the map of parents of the nodes visited while searching for the ending country
   */
  public Map<String, String> getParentMap(String sourceCountry, String destinationCountry) {
    List<String> visited = new ArrayList<String>();
    Queue<String> queue = new LinkedList<String>();
    Map<String, String> parentMap = new HashMap<>(); // Sets up parent map for back tracking

    visited.add(sourceCountry);
    queue.add(sourceCountry);

    // Adds the starting node with 0 parent.
    parentMap.put(sourceCountry, null);
    while (!queue.isEmpty()) {
      String node = queue.poll(); // Removes the front element
      for (String n : adjNodes.get(node)) {
        if (!visited.contains(n)) {
          parentMap.put(n, node);

          // Checks if the node is the destination country
          if (isCountryEqual(destinationCountry, n)) {
            return parentMap; // Returns the parent mapping
          }

          visited.add(n);
          queue.add(n);
        }
      }
    }

    return new HashMap<>();
  }

  private boolean isCountryEqual(String country, String otherCountry) {
    return country.equals(otherCountry);
  }
}
