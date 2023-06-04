# this is python code that I talked about in the old lablog, not a part of submission
import pandas as pd
import igraph as ig
import collections
import matplotlib.pyplot as plt

# Define column names based on the project description
col_names = ['vertex1', 'vertex2', 'similarity', 'identity', 'unused', 
             'overlap_start1', 'overlap_end1', 'length1', 'not_relevant', 
             'overlap_start2', 'overlap_end2', 'length2']

# Specify columns to load
cols_to_load = ['vertex1', 'vertex2', 'overlap_start1', 'overlap_end1', 
                'length1', 'overlap_start2', 'overlap_end2', 'length2']

# Load the data
data = pd.read_csv(r'G:\project\Da3018-vt2023-project\Spruce_fingerprint_2017-03-10_16.48.olp.m4', 
                   sep='\t', names=col_names, usecols=cols_to_load)

# Filter out overlaps that are not "sufficiently large"
data = data[(data['overlap_end1'] - data['overlap_start1'] >= 1000) & 
            (data['overlap_end2'] - data['overlap_start2'] >= 1000)]

# Create an empty graph
g = ig.Graph()

# Add vertices to the graph
vertices = pd.concat([data['vertex1'], data['vertex2']]).unique()
g.add_vertices(vertices.tolist())

# Add edges to the graph
edges = list(zip(data['vertex1'], data['vertex2']))
g.add_edges(edges)

def degree_distribution_igraph(graph):
    degree_sequence = sorted(graph.degree(), reverse=True)  # degree sequence
    degreeCount = collections.Counter(degree_sequence)
    deg, cnt = zip(*degreeCount.items())
    
    fig, ax = plt.subplots()
    plt.bar(deg, cnt, width=0.80, color='b')
    
    plt.title("Degree Histogram")
    plt.ylabel("Count (log scale)")
    plt.xlabel("Degree")
    plt.yscale('log')  # Set the y-axis to a logarithmic scale
    
    plt.show()

# Call the function to show the degree distribution
degree_distribution_igraph(g)

# Print some basic stats
print("Number of vertices:", g.vcount())
print("Number of edges:", g.ecount())

# Calculate the connected components
components = g.connected_components()

# Count the number of components with at least three vertices
large_components = [c for c in components if len(c) >= 3]
print("Number of components with at least three vertices:", len(large_components))

# Extract the largest connected component (often called the "giant component")
giant = components.giant()

print("Number of vertices in the giant component:", giant.vcount())
print("Number of edges in the giant component:", giant.ecount())
degree_distribution_igraph(giant)


def calculate_density(graph):
    m = graph.ecount()
    n = graph.vcount()
    if n == 0 or n == 1:
        return 0  # Avoid division by zero
    else:
        return (2 * m) / (n * (n - 1))

# Get the connected components
components = g.components()

# Calculate the density of each component
densities = [calculate_density(g.subgraph(component)) for component in components]

# Plot a histogram of the densities
plt.hist(densities, bins=50, log=True)
plt.title('Component Density Distribution')
plt.xlabel('Density')
plt.ylabel('Count')
plt.show()



