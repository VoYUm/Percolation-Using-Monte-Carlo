# Percolation-Using-Monte-Carlo

1. Run Perc.java to create a random generated percolation grid. Specify the variables 'sideLength' and 'numOpenSites' in main to change the size of the grid and the number of open sites (sites that can be percolated through) respectively. A random grid with numOpenSites will be generated with 'o' indicating a site being open and 'x' indicating a site being closed. The program will then print out whether the random grid is percolated or not.

2. Run MonteCarloSimulation.java to generate a list of probabilities that x number of open sites will cause the grid to percolate. Specify in main the variables 'sideLength' and 'numOfSimulations' to change the size of the grid to test and the number of simulations to run respectively. Recommended value for 'numOfSimulations' is 30, as this reduces the runtime while promising acceptable accuracy. A list of probabilities will be generated, each entry in the list is the probability of percolation of a specific number of open sites. For example for a 5x5 grid, the simulation will produce probabilities of percolation from 5 open sites to 20 open sites. Probabilities of percolation less than 5 and more than 21 are guranteed to be zero and one hundred, respectively. The program will also calculate the percolation threhold based on its simulations, which is always ±1 of the published threshold that you can find online.

3. A frontend project that can display a graph of numOpenSites vs. PercolationProbability along with other relevant graphs aesthetically is currently being developed.

4. Monte Carlo Simulation output demo using a 5x5 square grid (a total of 25 sites) and a numOfSimulations = 100:  
number of open sites: 9, percolation probability: 2.0%  
number of open sites: 10, percolation probability: 5.0%  
number of open sites: 11, percolation probability: 13.0%  
number of open sites: 12, percolation probability: 15.0%  
number of open sites: 13, percolation probability: 23.0%  
number of open sites: 14, percolation probability: 43.0%  
number of open sites: 15, percolation probability: 55.0%  
number of open sites: 16, percolation probability: 66.0%  
number of open sites: 17, percolation probability: 86.0%  
number of open sites: 18, percolation probability: 92.0%  
number of open sites: 19, percolation probability: 96.0%  
number of open sites: 20, percolation probability: 100.0%  
Percolation threshold: 60.0  
