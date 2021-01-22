import numpy as np
from math import inf

def floyd(G):
    # Calculates the all pairs shortest paths of a graph G
    
    #Get the number of rows
    n = G.shape[0]

    #Init all the D matrices
    D = list()
    for i in range(n+1):
        D.append(np.zeros([n,n]))
    #Set the first one to the weights of the graph
    D[0] = G

    # Apply the algorithm
    for k in range(1,n+1):
        D_prev = D[k-1]
        for i in range(n):
            for j in range(n):
                D[k][i][j] = min(D_prev[i][j],D_prev[i][k-1] + D_prev[k-1][j])
                
    # Return the last D (all pair shortest path adj. matrix) 
    return D[-1]

    

if __name__ == "__main__":
    # Define the weights of the graph
    W = np.array([
        [0, 3, 8, inf, -4],
        [inf, 0, inf, 1, 7],
        [inf, 4, 0, inf, inf],
        [2, inf, -5, 0, inf],
        [inf, inf, inf, 6, 0]
    ])

    #Print the result
    print(floyd(W))

