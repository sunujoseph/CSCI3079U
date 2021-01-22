import networkx as nx
import matplotlib.pyplot as plt
import operator

def createGraph():
    #Create the graph
    G = nx.Graph()

    #Add all the nodes
    list_nodes = ["a","b","c","d","e","f","g","h","i"]
    G.add_nodes_from(list_nodes)

    #Add some edges
    G.add_edge("a","b",weight=4)
    G.add_edge("a","h",weight=8)
    G.add_edge("b","h",weight=11)
    G.add_edge("b","c",weight=8)
    G.add_edge("c","i",weight=2)
    G.add_edge("c","f",weight=4)
    G.add_edge("c","d",weight=7)
    G.add_edge("d","f",weight=14)
    G.add_edge("d","e",weight=9)
    G.add_edge("e","f",weight=10)
    G.add_edge("f","g",weight=2)
    G.add_edge("g","i",weight=6)
    G.add_edge("g","h",weight=1)
    G.add_edge("h","i",weight=7)

    return G


def drawGraph(G):
    #Obtain nodes and edges
    pos = nx.spring_layout(G)
    labels = nx.get_edge_attributes(G,"weight")
    #Display the graph
    nx.draw_networkx(G, pos)
    nx.draw_networkx_edge_labels(G,pos, edge_labels=labels)
    plt.show()

def find_set(subtrees, ele):
    #Looks for ele in all the subtrees
    #if ele is found, it returns True and the subtree in which it is found
    #if ele is not found, returns False and a set containing the element
    for i, subtree in enumerate(subtrees):
        if ele in subtree:
            return True,subtree
    return False,set(ele)

def Kruskal(G):
    ##get all the edges
    edges = nx.get_edge_attributes(G,"weight")
    sorted_edges = sorted(edges.items(), key=operator.itemgetter(1))
    #print(sorted_edges)

    #Init the subtrees
    subtrees = list()

    #Init the solution
    solution = list()
    
    for edge in sorted_edges:
        #print(edge) #(('g', 'h'), 1)
        u = edge[0][0]
        v = edge[0][1]
        #print(u,v)
        u_found, u_set = find_set(subtrees, u)
        v_found, v_set = find_set(subtrees, v)
        if(u_set != v_set):
            #Add edge to solution
            solution.append(set(u)|set(v))
            #Update the subtrees
            if u_found: subtrees.remove(u_set)
            if v_found: subtrees.remove(v_set)
            subtrees.append(u_set | v_set)
    return solution
    

if __name__ ==  "__main__":
    G = createGraph()
    #drawGraph(G)    

    print(Kruskal(G))
