import pandas as pd
import matplotlib.pyplot as plt

# Load the degree distribution data
df = pd.read_csv('DegreeDistribution.csv')

# Create a bar plot for the degree distribution
plt.figure(figsize=(10, 6))
plt.bar(df['Degree'], df['Count'], color='skyblue')
plt.xlabel('Degree')
plt.ylabel('Count')
plt.title('Degree Distribution')
plt.grid(True)
# For Histogram
plt.hist(df['Degree'], weights=df['Count'], bins=50, color='red')
plt.show()
