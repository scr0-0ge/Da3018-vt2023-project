# **Here is reworked version**

# Day 5 Lab Log

## Date: (2th june 2023)

### Objective
1. Calculate and print out total number of vertices and edges
2. Make lab report
3. Upload all csv file
### Work Done:

#### Vertices and edges
I decide to add public int getTotalVertices and public int getTotalEdges so the code calculate total number of vertices and edges.
Later print out the numbers.

#### Lab report
Details can be found in Lab report.

#### Upload all csv file
After some searching in github I found some predecessors repositories. 

Even though I didn't found a comparable "correct answer" for our project, it seems like apart from upload 2 visualized graph, upload 2 csv file is apparently also a thing. 
Soooo.... I decide to upload both of them.

### Next Steps
This probably will be the final shot.


---


# Day 4 Lab Log

## Date: (1th june 2023)

### Objective
The goal for today is to generate visualizations of the component densities and degree distributions of the genome assembly graph that we have been studying. This will give us more insight into the characteristics of the graph and help us understand its structure.

### Work Done:

#### Histogram of Component Densities
We have loaded the component densities data from a csv file and plotted a histogram using matplotlib. This histogram will show the distribution of component densities in the genome assembly graph. 

#### Degree Distribution Plot
We have also loaded the degree distribution data and plotted a bar chart using matplotlib. This bar chart shows the distribution of degrees (number of overlaps) in the contigs of the genome assembly graph. 

We have also added a histogram to the degree distribution plot to show the distribution in a different format. In this histogram, the height of each bar represents the number of contigs with a degree falling into the corresponding bin.

### Performance Optimization Attempts
No specific performance optimization attempts were required for today's work, as the focus was mainly on data visualization, and the used data structures and libraries (Pandas and Matplotlib) are generally efficient for these purposes.

### Issues and Concerns
No major issues or concerns were encountered today. However, it's important to ensure that the csv files are correctly formatted and located in the specified path for data loading to work correctly.

### Result:
The number of components with at least three vertices: 273187
Total number of vertices: 11393435
Total number of edges: 63408137

Here is Degree distribution graph

![1](https://github.com/scr0-0ge/Da3018-vt2023-project/blob/Patch-5/Lab%20paperwork/Degree%20distribution%20graph.png)

Here is component density graph

![2](https://github.com/scr0-0ge/Da3018-vt2023-project/blob/Patch-5/Lab%20paperwork/component%20density%20graph.png)




### Next Steps
Make a lab report

### Talks
Due to lack of a standard answer in project description, we won't be able to know if our answer is 100% correct. But the program runs well and if we didn't misunderstand the description, then I have 96% confidence on the current process on this project. 

If nothing goes horribly wrong then next lab note will be the final nail on the coffin.



---

# Day 3 Lab Log

## Date: (31th May 2023)

### Objective
The goal for today is to calculate the Component densities and written to a csv file, also adjusting our degree distribution computation to ignore contigs that do not overlap with any other contig.

### Work Done:

#### Degree Distribution Adjustment
We've updated the 'printDegreeDistribution' method to ignore contigs that do not overlap with any other contig. This helps to give us a more meaningful degree distribution since contigs with no overlaps are not relevant for the distribution.

#### Edge Count Calculation
We added a private method 'countEdges' that calculates the number of edges in a connected component. The method uses DFS and counts the size of the overlap sets for each contig. Since each edge is counted twice, once for each of its endpoints, the method returns half of the total count. This method also sets the 'visited' attribute for each contig it visits.

#### Component Density Calculation
We implemented a 'computeComponentDensities' method that calculates the density of each connected component in the graph and writes the results to a csv file. The density of a component is computed as the ratio of the number of edges to the maximum possible number of edges for a graph with the same number of vertices, which is `(vertices * (vertices - 1)) / 2`. Note that this computation is only done for components with at least two vertices to avoid division by zero.

#### Visited Flag Reset
We added a 'resetVisited' method that resets the 'visited' attribute of all contigs to false. This method is necessary because we are performing multiple traversals of the graph, and we need to reset the 'visited' attributes in between traversals.

#### Main Method Execution
In the main method, we added a call to 'computeComponentDensities' to compute the component densities after reading the data and printing the degree distribution. We also added a call to 'resetVisited' before computing the number of components with at least three vertices to ensure the 'visited' attributes are correctly set for this computation.

### Performance Optimization Attempts
The use of a DFS algorithm for counting edges and computing component densities is an efficient approach given the data structure of our problem. The 'resetVisited' function helps ensure that our DFS runs correctly multiple times.

### Issues and Concerns
version 1 gave us component densities and at the same time written data to a csv file, but this version gave us the number of components of G with at least three vertices =0

### Next Steps
visualize the graph with python, do Unit Testing if necessary.



---

# Day 2 Lab Log

## Date: (28th May 2023)

### Objective
Refine overlap calculations and calculate the number of connected components with at least three vertices in the genome assembly graph.

### Work Done:

#### Overlap Calculation Refinement
We modified the 'readData' method to calculate the overlap length in both contigs. For each pair of overlapping contigs, if the overlap length is at least 1000 in both sequences, the overlap is recorded.

#### Contig Class Update
We updated the Contig class to include a 'visited' attribute, which is used in the depth-first search (DFS) algorithm.

#### DFS Implementation for Connected Components Calculation
We implemented a private 'dfs' method in the GenomeAssemblyGraph class. This method takes a contig ID as input and performs a DFS starting from that contig, marking each visited contig along the way and returning the size of the connected component.

#### Calculation of Components with At Least Three Vertices
We added a 'getComponentsWithAtLeastThreeVertices' method to the GenomeAssemblyGraph class. This method iterates over all contigs in 'contigMap'. For each unvisited contig, it performs a DFS to find the size of the connected component and increments a count if the size is at least three.

#### Main Method Execution
In the main method, we created an instance of GenomeAssemblyGraph, read in the data, printed the degree distribution, and calculated and printed the number of components with at least three vertices.

### Performance Optimization Attempts
The usage of a DFS algorithm to calculate the number of connected components is an efficient approach for such a task on a large genome assembly graph.

### Issues and Concerns
No significant issues or concerns were encountered during this process.

### Next Steps
Try to calculate component densities and written data to csv file

---
# Day 1 Lab Log

## Date: (27th May 2023)

### Objective
Export degree distribution to a csv file and create a visualization using Python.

### Work Done:

#### Export Degree Distribution to CSV
We modified the 'printDegreeDistribution' method in the GenomeAssemblyGraph class to not only calculate the degree distribution but also write it to a csv file. This method creates a StringBuilder and appends the degree and count for each entry in the 'degreeDist' HashMap, formatted as a csv file. It then uses a PrintWriter to write the StringBuilder's contents to a specified output file.

#### Degree Distribution Visualization
We wrote a Python script to read in the degree distribution csv file using pandas. It creates a bar plot of the degree distribution using matplotlib, with the degree on the x-axis and the count on the y-axis. This provides a visual representation of the degree distribution, making it easier to see the most common degrees and the general distribution.

#### Main Method Execution
In the main method of the GenomeAssemblyGraph class, we specified an output file path for the 'printDegreeDistribution' method. After the data was read in and the degree distribution was calculated, it was written to this output file. 

### Performance Optimization Attempts
Exporting the degree distribution to a csv file allows us to handle large amounts of data more efficiently. It also enables us to use more powerful tools for analyzing the data, such as Python's pandas and matplotlib libraries for data analysis and visualization.

### Issues and Concerns
No significant issues or concerns were encountered during this process. 

### Next Steps
In the next step, we will be facing assignment 2, The number of components of G with at least three vertices.

Lets see how that will go.

---
# Day 0 Lab Log

## Date: (26th May 2023)

### Objective
Efficiently handle large genome assembly data, calculate node degree distribution and print the results in Java.

### Work Done:

#### Contig Class Creation
We started by defining a Contig class to represent each node (contig) in the graph. Each Contig has an integer 'length' and a set of 'overlaps'. The 'overlaps' set stores the hashCodes of the IDs of the other contigs that overlap with the current one. 

#### GenomeAssemblyGraph Class Creation
Next, we defined a GenomeAssemblyGraph class to hold all the Contigs. This class has a private attribute, 'contigMap', which is a HashMap that maps the ID of each contig to the corresponding Contig object.

#### Data Reading and Processing
In the GenomeAssemblyGraph class, we implemented a 'readData' method which reads in the data from the input file line by line. For each line, it splits the line into parts, extracts the contig IDs and their lengths, and creates Contig objects for each new contig. It also stores the overlaps between contigs by adding the hashCode of the overlapping contig's ID to the 'overlaps' set of each contig.

#### Degree Distribution Calculation
We also implemented a 'printDegreeDistribution' method in the GenomeAssemblyGraph class. This method first creates a HashMap, 'degreeDist', to store the distribution of node degrees in the graph. It then iterates over all the contigs in 'contigMap' and calculates the degree of each contig as the size of its 'overlaps' set. The degree of each contig and its count is then printed out.

#### Main Method Execution
Finally, in the main method, we created an instance of GenomeAssemblyGraph, read in the data, and printed the degree distribution. The output shows the degree of each node and the count of nodes with that degree.

### Performance Optimization Attempts
The usage of 'hashCode' for storing the overlaps in an integer set instead of the actual String ID significantly reduces the memory footprint. Moreover, the usage of BufferedReader for file reading helps manage system resources efficiently, given that it reads text from a character-input stream, buffering characters to provide efficient reading of characters, arrays, and lines.

### Next Steps
The degree distribution data we got is essentially a histogram that shows the frequency of each degree (number of overlaps), next step is export them into csv format and use python to create a visualized graph.


---
# **Here is old lab log**


We got some bad news.
1. We cant use python on the data part, only java, this means we need do some rework.
2. Visualize the Graph part are allowed to use python 

We need to do rework on our project.


---
# Day 0 Lab Log

## Date: (19th May 2023)

### Objective
Efficient preprocessing of a large (7GB) contig overlap dataset with Python.

### Work Done:

#### Data Loading and Cleaning
We began with load and clean the data. Utilizing the Pandas library in Python, we successfully loaded the data from the text file and created a DataFrame. The contig identifiers were translated into integer identifiers to save memory.

#### Overlap Calculation and Filtering
We calculated the overlap length for both sequences by subtracting start position from end position for each contig. We then filtered out rows where overlap is at least 1000 in both sequences.

### Performance Optimization Attempts
Due to available system resources we decide to use some "extreme method" for faster results.

- **Multithreading:**  It seems like spyder are kinda poorly optimized, it isn't smart enough to using multithreading automatically when needed, due to available system resources we decide to speed things up. We applied multithreading using Python's concurrent.futures module. However, this directly led to 100% CPU and RAM utilization, causing the system to freeze.

- **Memory Management:** To address the high memory usage, we considered chunking the data when loading it in, to reduce the memory footprint. Although this solution did not expedite the process, it allowed the program to run on machines with limited memory and most importantly we can decide how much ram we want to allocate (increasing or decreasing the chunk size).

- **Dask:** We explored Dask, a flexible library for parallel computing in Python. Dask is built with the ethos of "scaling Python," making it a suitable solution for working with larger-than-memory datasets. However, we did not implement this due to the available system resources.

- **GPU Acceleration with RAPIDS:** We also considered the RAPIDS library, a suite of software libraries that enable end to end GPU acceleration for data science. But the system requirements for RAPIDS were quite stringent, and the current system did not meet all the prerequisites. Especially when we need to install a new system on my pc, giving is out of proportion to reward.

### Next Steps
Rewrite the code with multi thread in mind. Or consider buying a new GPU and exploring GPU acceleration in the future.


---
# Day 1 Lab Log
## Date: (22nd May 2023)

### Objective
Objective: Revising the approach to efficiently preprocess the large contig overlap dataset with Python, with a focus on parallelization.
### Work Done:

#### Multiprocessing Attempt
We revised our Python script to leverage Python's multiprocessing library, hoping to increase the performance by parallelizing the data processing. The revised script split the data into chunks and processed each chunk with a separate process, aiming to utilize all available CPU cores.
(basically script splits the filtered DataFrame into several chunks equal to the number of cores on our machine and processes each chunk independently. After all cores finish their work, it merges the results back into one adjacency list)

#### Performance Results
While our multiprocessing approach indeed maximized CPU utilization (reaching 100%), it did not lead to an improvement in execution time as we hoped. The script took significantly longer to run (more than 30 minutes without completion), as compared to the single-threaded approach that took around 10 minutes. 
Interestingly, RAM utilization was also much lower during the multiprocessing attempt (around 3GB/64GB), compared to the single-threaded run (20GB/64GB).
(even tho I did try to increase chunk size so the script could use more ram, however it maxed out on 25 gb/64 gb ram usage, and result did not meet expectations,more than 30 minutes without completion) 

#### Re Evaluations
Due to these unexpected results, we considered that the task might be more I/O-bound rather than CPU-bound. In this case, adding more processes might not speed up the work and could even slow it down due to the overhead of managing multiple processes.

### Talks:
In todays work, we encountered a significant challenge related to memory usage while constructing a large graph. The root cause of the problem was an inadequate approach to handling large data, which resulted in excessive RAM usage, nearing the system's limit (50GB out of 64GB). 

#### The primary learnings from this experience are as follows:
- **Dataframe Operations** Performing operations like filtering and translating identifiers directly on a pandas DataFrame can be resource-intensive, especially with large data. It's crucial to optimize these operations, for example by selecting only necessary columns while loading the data or applying filters on chunks of data instead of the whole DataFrame at once.

- **Data Storage** Storing processed data as a pandas DataFrame consumes a lot of memory. In our case, after pre-processing, the data was stored as a DataFrame before feeding it into the graph, which was unnecessary and led to increased memory usage.

- **Graph Construction:** The initial approach was to create a NetworkX DiGraph from a pandas DataFrame. This method is not very memory efficient for large datasets, and  led to high memory consumption. An alternative approach is to use the igraph library, which is faster and more memory efficient for large graphs.

### Next Steps
1. We are returning to Single threaded Approach,even though it didn't maximize CPU usage, it completed faster and made more effective use of available memory.
2. Consider other optimization strategies, maybe testing with a subset of the data for faster feedback during development, and then scale up to the full dataset for the final run.
3. Instead of using NetworkX, we use a more memory-efficient graph library like igraph, igraph is better wen dealing with large graph.


---
# Day 2 Lab Log
## Date: (24th May 2023)

### Objective
1. Based on the issues encountered in our previous attempt, we gave up on the multithreading approach and went back to the single-threaded approach.
2. To efficiently load, preprocess, and analyze a large genomic overlap dataset using Python. The focus was on constructing a genome assembly graph, examining its properties, and identifying connected components.

### Work Done:

#### Data Loading and Preprocessing
The genomic overlap dataset was loaded using pandas, which allowed for efficient processing of the large text file. Only the necessary columns were loaded to save memory. The dataset was then preprocessed to ensure that all overlaps were sufficiently large.

#### Graph Construction
The genome assembly graph was constructed using the igraph library, which was chosen for its memory efficiency. Each vertex in the graph represented a contig, and each edge represented an overlap between two contigs. 

#### Node Degree Distribution Calculation
A function was implemented to calculate the degree of each node in the graph (i.e., the number of edges connected to each node). The distribution of these degrees was then plotted to understand the connectivity of the graph.


#### Components Calculation
The connected components of the graph were found, and the number of components with at least three vertices was counted. The giant component (the largest connected component) was also identified.


#### Component Density Calculation
For each connected component, the density (the ratio of the number of edges to the number of possible edges) was calculated. A histogram of these densities was then plotted.


### Results:
The genome assembly graph consisted of **11380820 vertices** and **63962895 edges**. There were **273187 connected components with at least three vertices**. The density of the components varied widely, as shown in the density histogram.

# End of the log



