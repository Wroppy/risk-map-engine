package nz.ac.auckland.se281.countryinfoengine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
      String[] adjacency_data = line.split(",");

      // Puts an empty array in the map
      String node = adjacency_data[0];
      if (!this.adjNodes.containsKey(node)) {
        this.adjNodes.put(node, new ArrayList<String>());
      }

      // Loops through the adjacent nodes and adds it to the hash map
      for (int i = 1; i < adjacency_data.length; i++) {
        this.adjNodes.get(node).add(adjacency_data[i]);
      }
    }
  }
}
