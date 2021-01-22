
def edit_distance(s1, s2):
    m=len(s1)+1 #length of string 1
    n=len(s2)+1 #Lenght of string 2
    #gets the length and adds 1 to compentsate
    #the out of bounds in array error
    medistance = {}
    for i in range(m):
        medistance[i,0]=i#store values of [i,0]
    for j in range(n):
        medistance[0,j]=j#store values of [0,j]
    for i in range(1, m):
        for j in range(1, n):
            if s1[i-1] == s2[j-1]:
                check = 0
            else:
                check = 1
            medistance[i,j] = min(medistance[i, j-1]+1,
            medistance[i-1, j]+1,
            medistance[i-1, j-1] + check) #dynamic programming
    return medistance[i,j]

var1 = input("Enter First String: ")#user input
var2 = input("Enter Second String: ")
print("Rule: Each operation has a cost of 1")#following normal rule where each operation is 1 cost 
print("===========================================")
print("Minimum Edit Distance(MED): ",(edit_distance(var1, var2)))
