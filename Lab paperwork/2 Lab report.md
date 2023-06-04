# Lab report of project Genome Assembly Graph 

## Xingyi Chen, Vt2023

## Objective:
In this project, our aim was to find and report following:
- The node degree distribution of G.
- The number of components of G with at least three vertices.
- A histogram of the component density distribution. That means: for each graph component, compute the fraction of edges over the number of possible edges.


## Methods:

### Data filtering, calculation and more (Java) 
We used Java to develop a program that parsed the given dataset containing contig overlap information. Each contig, a segment of DNA, was represented as a node in the graph. An edge was drawn between two nodes if their corresponding contigs overlapped by at least 1000 bases. To efficiently represent the graph, we used an adjacency list with a hashmap. The hashmap consisted of contig IDs as keys and Contig objects as values. Each Contig object stored the contig's length, the set of contigs it overlapped with, and a visited flag for traversal purposes.

With the graph constructed, we executed a depth-first search (DFS) algorithm to count the number of components having at least three vertices. <br />
The DFS algorithm was implemented due to its effectiveness in exploring every vertex of a connected component. It ensured we accounted for all vertices in our computations. However, its time complexity is something to be mindful of, especially when dealing with large, complex datasets like ours.

Additionally, we calculated the total number of vertices and edges in the graph, the degree distribution of the vertices, and the densities of the graph's components. The densities were computed as the ratio of actual edges to the total possible edges in each component. All results were exported to .csv files for further analysis.


### Data Visualization (Python)

In order to visually understand the component densities and the degree distribution in the genome assembly graph, we utilized Python's data analysis and visualization libraries: Pandas and Matplotlib.

The first segment of the Python script loads the 'ComponentDensities.csv' file, which contains the data of component densities from our analysis. This data is loaded into a DataFrame, a two-dimensional labeled data structure from Pandas.
The data was then visualized using a histogram plot, the x-axis represents the density of the components while the y-axis represents the count of components within those densities. 

The second part of the script loads the 'DegreeDistribution.csv' file into another DataFrame. This file contains the degree distribution data from our graph analysis.<br />
To visualize this data, we first created a bar plot where the x-axis represents the degree (i.e., the number of edges connected to a node) and the y-axis represents the count of nodes with that degree. <br />
Later we added another perspective, we created a histogram of the degree distribution. In this plot, the y-axis is weighted by the 'Count' column in our DataFrame, providing a different representation of the same data. 
The use of the 'weights' parameter in the histogram function allows us to control the height of the bars, effectively giving us the ability to visualize the degree distribution in terms of the absolute count of nodes having each degree.


### Github
Throughout the project, we maintained a lab notebook documenting our process and observations. All code, scripts, notes, and the report were stored in a GitHub repository to ensure a systematic and accessible work environment.

Instead of committing directly in the main batches, we choose to use the branch funktion in github, it allows us to easily check previous works compared to checking in commit history (at least in my opinion).
We also utilize the project functionality in github to make a small calendar for this work.


## Results:
Running our program with the given dataset, we found the following:

- Number of components with at least three vertices: 273,187
- Total number of vertices: 11,393,435
- Total number of edges: 63,408,137


And here are two graphs


![1](https://github.com/scr0-0ge/Da3018-vt2023-project/blob/Patch-5/Lab%20paperwork/Degree%20distribution%20graph.png)
![2](https://github.com/scr0-0ge/Da3018-vt2023-project/blob/Patch-5/Lab%20paperwork/component%20density%20graph.png)




## Discussion:

Reflecting on this project, I was initially surprised by the grading scheme outlined in the project description. It puzzled me that solving the problem was only worth 2 points. However, after some thought, I believe I understand the rationale behind this.

In a brief discussion with my peers (mind you, I am aware that this was meant to be an individual project, though it wasn't explicitly stated in the description), I realized that our methodologies and results varied widely. For instance, one classmate used a method that potentially could result in hash collisions, which might explain the divergence in our findings. Given the absence of a "correct answer" in the project description, it's challenging to gauge the accuracy of our respective results. Thus, I can see why the coding part has a lower weighting in the overall grading.

One significant revelation from this project was Java's optimization when handling data. You might have noticed from my old lab log that I initially attempted to use Python for this project. At that point, I was unaware that Python was unsuitable for the data part of this project. Even if Python were permissible, it would have proved to be quite the challenge.

At first glance, Python, with its rich scientific library ecosystem, appears ideal for such tasks. However, I encountered issues related to resource utilization. I'm not sure whether it's a Python-specific problem, something related to Spyder, or simply my lack of expertise, but it seemed that Python required extensive manual tuning to fully utilize system resources.

Let's liken the original data to a cake. Ideally, all of my 12 CPU cores should begin processing the cake simultaneously. However, without manual multithreading implementation, only a single core would be processing, while the remaining cores stood idle. This resulted in a significant delay before I could obtain any results. <br />
In hindsight, adhering to the project description's advice—"Be sure to create small test files to test your code on. Doing your first experiments on the full graph is a waste of time"—would have been prudent.

Anyway, to combat the issue, I tried implementing multithreading, it is like splitting the cake into several pieces and feeding each piece to different cores. Yet, I still encountered several issues. For instance, my 64GB RAM would fill up when processing a 7GB data file, leading to system freezing. Even after setting limits on the data chunks, the processing time was still slower than single-threading.
Ultimately, I reverted to a single-threaded approach, rewriting everything in Java (since I didn't realize that the data part needed to be fixed in Java).

I have previously mentioned in my lab log that "reworking is bad news," but in this context, Java outperformed Python in data handling. It made better use of my cores and allowed me to set a maximum RAM usage limit. <br />
However, Python excels in graph visualization, an area where Java falls short, in my opinion.

One anomaly I observed was that even though Java doesn't automatically use all cores unless explicitly programmed to do so, this single-threaded code did utilize all my CPU cores.
![1](https://github.com/scr0-0ge/Da3018-vt2023-project/blob/Patch-5/Lab%20paperwork/single%20threaded%20code%20uses%2060%25%20of%20my%20cpu.PNG)

Despite the challenges, I genuinely enjoyed working on this project. I hope that my experiences could provide insights for future students tackling the Da3018 project.



## Conclusion:
This analysis provides a comprehensive understanding of the genome assembly graph's structure. It points to the necessity for efficient algorithms that can manage and simplify these complex structures, which in turn could make genome assembly more feasible. 


