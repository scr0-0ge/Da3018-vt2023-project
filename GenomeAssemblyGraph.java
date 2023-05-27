import java.io.*;
import java.util.*;

class Contig {
    int length;
    Set<Integer> overlaps;

    public Contig(int length) {
        this.length = length;
        overlaps = new HashSet<>();
    }
}

public class GenomeAssemblyGraph {
    private Map<String, Contig> contigMap; 

    public GenomeAssemblyGraph() {
        contigMap = new HashMap<>();
    }

    public void readData(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\s+");
                String id1 = parts[0];
                String id2 = parts[1];
                int length1 = Integer.parseInt(parts[7]);
                int length2 = Integer.parseInt(parts[11]);
                if (!contigMap.containsKey(id1)) {
                    contigMap.put(id1, new Contig(length1));
                }
                if (!contigMap.containsKey(id2)) {
                    contigMap.put(id2, new Contig(length2));
                }
                contigMap.get(id1).overlaps.add(id2.hashCode());
                contigMap.get(id2).overlaps.add(id1.hashCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void printDegreeDistribution() {
        Map<Integer, Integer> degreeDist = new HashMap<>();
        for (Contig contig : contigMap.values()) {
            int degree = contig.overlaps.size();
            degreeDist.put(degree, degreeDist.getOrDefault(degree, 0) + 1);
        }

        for (Map.Entry<Integer, Integer> entry : degreeDist.entrySet()) {
            System.out.println("Degree: " + entry.getKey() + ", Count: " + entry.getValue());
        }
    }

    public static void main(String[] args) {
        GenomeAssemblyGraph g = new GenomeAssemblyGraph();
        g.readData("G:\\project\\Da3018-vt2023-project\\Spruce_fingerprint_2017-03-10_16.48.olp.m4");
        g.printDegreeDistribution();
        // Further processing...
    }
}
