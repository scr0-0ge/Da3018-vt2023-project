import java.io.*;
import java.util.*;

class Contig {
    int length;
    Set<String> overlaps;
    boolean visited;

    public Contig(int length) {
        this.length = length;
        overlaps = new HashSet<>();
        visited = false;
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
                String id1 = parts[0]; // ID of the first contig
                String id2 = parts[1]; // ID of the second contig
                int length1 = Integer.parseInt(parts[7]); // length of the first contig
                int length2 = Integer.parseInt(parts[11]); // length of the second contig
    
                // Calculate the overlap length in both contigs
                int overlapLength1 = Integer.parseInt(parts[6]) - Integer.parseInt(parts[5]);
                int overlapLength2 = Integer.parseInt(parts[10]) - Integer.parseInt(parts[9]);
    
                // Store contigs in the map if they are not already present
                if (!contigMap.containsKey(id1)) {
                    contigMap.put(id1, new Contig(length1));
                }
                if (!contigMap.containsKey(id2)) {
                    contigMap.put(id2, new Contig(length2));
                }
    
                // If overlap length is at least 1000 in both sequences, add overlap
                if (overlapLength1 >= 1000 && overlapLength2 >= 1000) {
                    contigMap.get(id1).overlaps.add(id2);
                    contigMap.get(id2).overlaps.add(id1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

    public int getComponentsWithAtLeastThreeVertices() {
        int count = 0;

        for (String id : contigMap.keySet()) {
            if (!contigMap.get(id).visited) {
                int componentSize = dfs(id);
                if (componentSize >= 3) {
                    count++;
                }
            }
        }

        return count;
    }

    private int dfs(String id) {
        Contig contig = contigMap.get(id);
        contig.visited = true;
        int size = 1;

        for (String neighborId : contig.overlaps) {
            if (!contigMap.get(neighborId).visited) {
                size += dfs(neighborId);
            }
        }

        return size;
    }

    public void printDegreeDistribution(String outputPath) {
        Map<Integer, Integer> degreeDist = new HashMap<>();
        for (Contig contig : contigMap.values()) {
            int degree = contig.overlaps.size();
            if (degree > 0) { // Ignore contigs that do not overlap with any other contig
                degreeDist.put(degree, degreeDist.getOrDefault(degree, 0) + 1);
            }
        }
    
        // Writing to csv file
        try (PrintWriter writer = new PrintWriter(new File(outputPath))) {
            StringBuilder sb = new StringBuilder();
            sb.append("Degree");
            sb.append(',');
            sb.append("Count");
            sb.append('\n');
    
            for (Map.Entry<Integer, Integer> entry : degreeDist.entrySet()) {
                sb.append(entry.getKey());
                sb.append(',');
                sb.append(entry.getValue());
                sb.append('\n');
            }
    
            writer.write(sb.toString());
    
            System.out.println("Degree distribution data written to file: " + outputPath);
    
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
       

    private int countEdges(String id) {
        Contig contig = contigMap.get(id);
        int edges = contig.overlaps.size();
        contig.visited = true;
    
        for (String neighborId : contig.overlaps) {
            if (!contigMap.get(neighborId).visited) {
                edges += countEdges(neighborId);
            }
        }
    
        return edges / 2; // each edge was counted twice
    }
    
    public void computeComponentDensities(String outputPath) {
        try (PrintWriter writer = new PrintWriter(new File(outputPath))) {
            StringBuilder sb = new StringBuilder();
            sb.append("Component");
            sb.append(',');
            sb.append("Density");
            sb.append('\n');
    
            int componentNumber = 1;
    
            for (String id : contigMap.keySet()) {
                if (!contigMap.get(id).visited) {
                    int vertices = dfs(id);
                    int edges = countEdges(id);
    
                    // Compute density
                    if (vertices > 1) { // avoid division by zero
                        double density = (double) 2 * edges / (vertices * (vertices - 1));
                        sb.append("Component_" + componentNumber);
                        sb.append(',');
                        sb.append(density);
                        sb.append('\n');
    
                        componentNumber++;
                    }
                }
            }
    
            writer.write(sb.toString());
            System.out.println("Component densities written to file: " + outputPath);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
    

    public void resetVisited() {
        for (Contig contig : contigMap.values()) {
            contig.visited = false;
        }
    }
    
    


    public static void main(String[] args) {
        GenomeAssemblyGraph g = new GenomeAssemblyGraph();
        g.readData("G:\\project\\Da3018-vt2023-project\\Spruce_fingerprint_2017-03-10_16.48.olp.m4");
        g.printDegreeDistribution("G:\\project\\Da3018-vt2023-project\\DegreeDistribution.csv");
        g.computeComponentDensities("G:\\project\\Da3018-vt2023-project\\ComponentDensities.csv");
        g.resetVisited();
        int componentCount = g.getComponentsWithAtLeastThreeVertices();
        System.out.println("The number of components with at least three vertices: " + componentCount);
    }
}
