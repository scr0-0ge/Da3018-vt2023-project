# Da3018-vt2023-project
Project: a genome assembly graph by Xingyi Chen

**Note0**: Patch 5 is the final version, Patch 0 is the prototype

**Lab paperwork** include lab log and lab report <br />
**all code** include java file, python file and a short 1000 line test data for this project <br />
**all csv file** inclide DegreeDistribution.csv and ComponentDensities.csv, you can use python file to visilize the graph <br />

**Here is how you run the code:** <br />
**1. javac GenomeAssemblyGraph.java**  (if it does not work try **javac -encoding UTF8 GenomeAssemblyGraph.java**) <br />  
**2. java -Xss1G -Xms25G GenomeAssemblyGraph** <br />

**Note1**:<br />
you should change xms number into a number that your pc supports, I have 64gb ram so that why I can give it 25gb.<br />
**Note2**:<br />
g.readData("G:\\project\\Da3018-vt2023-project\\Spruce_fingerprint_2017-03-10_16.48.olp.m4"); on line 58, you should change it into where your Spruce_fingerprint_2017-03-10_16.48.olp.m4 is at.<br />
lets say your Spruce_fingerprint_2017-03-10_16.48.olp.m4 is inside programing folder at destop then it will be "C:\\Users\\your username\\Desktop\\programing\\Spruce_fingerprint_2017-03-10_16.48.olp.m4"
