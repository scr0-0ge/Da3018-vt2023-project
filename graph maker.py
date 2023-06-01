import pandas as pd
import matplotlib.pyplot as plt

# Load the ComponentDensities data
df1 = pd.read_csv('ComponentDensities.csv')

# Plot the histogram for ComponentDensities
plt.figure(figsize=(10, 6))
plt.hist(df1['Density'], bins=50, alpha=0.5, color='b')
plt.title('Component Density Distribution')
plt.xlabel('Density')
plt.ylabel('Count')
plt.grid(True)
plt.show()

# Load the degree distribution data
df2 = pd.read_csv('DegreeDistribution.csv')

# Create a bar plot for the degree distribution
plt.figure(figsize=(10, 6))
plt.bar(df2['Degree'], df2['Count'], color='skyblue')
plt.xlabel('Degree')
plt.ylabel('Count')
plt.title('Degree Distribution')
plt.grid(True)

# For Histogram
plt.hist(df2['Degree'], weights=df2['Count'], bins=50, color='red')
plt.show()
