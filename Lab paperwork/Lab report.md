# Analysis of Genome Assembly Graph 

## Xingyi Chen, Vt2023

## Objective:
In this project, our aim was to find and report following:
- The node degree distribution of G.
- The number of components of G with at least three vertices.
- A histogram of the component density distribution. That means: for each graph component, compute the fraction of edges over the number of possible edges.


## Methods:

### Data filtering, calculation and more (Java) 
We used Java to develop a program that parsed the given dataset containing contig overlap information. Each contig, a segment of DNA, was represented as a node in the graph. An edge was drawn between two nodes if their corresponding contigs overlapped by at least 1000 bases. To efficiently represent the graph, we used an adjacency list with a hashmap. The hashmap consisted of contig IDs as keys and Contig objects as values. Each Contig object stored the contig's length, the set of contigs it overlapped with, and a visited flag for traversal purposes.

With the graph constructed, we executed a depth-first search (DFS) algorithm to count the number of components having at least three vertices. 

The DFS algorithm was implemented due to its effectiveness in exploring every vertex of a connected component. It ensured we accounted for all vertices in our computations. However, its time complexity is something to be mindful of, especially when dealing with large, complex datasets like ours.

Additionally, we calculated the total number of vertices and edges in the graph, the degree distribution of the vertices, and the densities of the graph's components. The densities were computed as the ratio of actual edges to the total possible edges in each component. All results were exported to .csv files for further analysis.


### Data Visualization (Python)

In order to visually understand the component densities and the degree distribution in the genome assembly graph, we utilized Python's data analysis and visualization libraries: Pandas and Matplotlib.

The first segment of the Python script loads the 'ComponentDensities.csv' file, which contains the data of component densities from our analysis. This data is loaded into a DataFrame, a two-dimensional labeled data structure from Pandas.
The data was then visualized using a histogram plot, the x-axis represents the density of the components while the y-axis represents the count of components within those densities. 

The second part of the script loads the 'DegreeDistribution.csv' file into another DataFrame. This file contains the degree distribution data from our graph analysis.

To visualize this data, we first created a bar plot where the x-axis represents the degree (i.e., the number of edges connected to a node) and the y-axis represents the count of nodes with that degree. Later we added another perspective, we created a histogram of the degree distribution. In this plot, the y-axis is weighted by the 'Count' column in our DataFrame, providing a different representation of the same data. 

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



## Discussion:
Here are some personal rant

You know, the first time I saw the grading part of the project description I was kinda surprised and frustrated. I didn't really know why solving the problem only gives 2 points, but right now I think I understand why that is the case.

So this morning I had a small chat with a few students in the class (and yes I know it is supposed to be an individual project even though it was never mentioned in the description, but I'm really queries about what result others had), and it seems everyone has vastly different methods and results. 

For the exemple there is one classmate who I'm not gonna mention his/her name uses a method that can lead to hash collisions. That is probably why we have different results.
Given that we don't have a definite answer in the description, it is hard to say if any of us are even close to the correct answer. 
It is probably for the best that the code part stays at a low weight when it comes to the grading.

Something I realized when I worked with this project is that java is very good optimized in the data aspect. 
If you watched my old lab log you might notice that I tried to use python for this project, at that time I didn't realize that you can't use python for the data part of this project, but even if we are allowed, it would be a nightmare. 

At the first glance python with its rich ecosystem of scientific libraries are perfect for such task, however I don't know if it was a spyder thing or it is a python thing (or maybe it’s just that I'm not skilled enough ),  but it seems like for python to utilize all available system resources takes extremely much manually tuning for it to be optimized. 
Stuff like multithreading is just not a thing in python, for the exemple if we see original data as a cake, the ideal situation is that all of my 12 cpu cores start to eat that cake automatically. 

But if I don't manually implement multithreading into my code there is like 1 core that is eating all the cake but the rest of the 11 core just stand there and watch him suffer. 
It led to an extremely long process time before I even could get any result to continue my work.  

(yeah... future me here, Advice section of the project description clearly said that "Be sure to create small test files to test your code on. Doing your first experiments on the full graph is a waste of time." definitely should have watched that in the first place.)

So I decided to implement multithreading. It is like I have to chopping a cake into multiple pieces and spoon feeding into their mouths, even after I did that many inconveniences still pops up.  

For example there is one thing I still couldn't understand till this day, the data is 7gb and my ram is 64 gb, it is almost enough for me to put 10 identical data files into my ram. But when I started to process the data file, how in the world could it fill up the entire 64gb ram and cause my pc to freeze???

Remember the part in the lab log that I said "reworking is a bad news"?  
Well lets just say java is much better at handling data in the comparison to python, it automatically utilize all of my cores and I can easily set a max range when it comes to how much ram it can use.
But what python is good at is visualize the graph, in this area java is much worse than python at least in my opinion.







## Conclusion:
This analysis provides a comprehensive understanding of the genome assembly graph's structure. It points to the necessity for efficient algorithms that can manage and simplify these complex structures, which in turn could make genome assembly more feasible. 


