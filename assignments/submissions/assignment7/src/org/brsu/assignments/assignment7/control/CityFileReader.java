package org.brsu.assignments.assignment7.control;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.brsu.assignments.assignment7.model.City;

/**
 * Class for reading out {@link City} objects from a given file.
 * 
 * @author bastian
 *
 */

public class CityFileReader {

    public Set<City> readCitiesFromFile(String filename) throws IOException {
        HashSet<City> result = new HashSet<City>();
        List<String> lines = Files.readAllLines(Paths.get(filename), Charset.forName("UTF-8"));
        for (String line : lines) {
            if (line.startsWith("#")) {
                // skip header line
                continue;
            }
            String[] split = line.split("\t");
            City city = new City(split[0], Double.parseDouble(split[1]), Double.parseDouble(split[2]));
            result.add(city);
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        CityFileReader reader = new CityFileReader();
        Set<City> cities = reader.readCitiesFromFile("resources/assignment7/cities.txt");
        System.out.println(cities);
    }

}
