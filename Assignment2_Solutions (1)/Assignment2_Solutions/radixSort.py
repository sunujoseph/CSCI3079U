A=[7764,1881,522,3843,2917,4068,2305]
k=10

def getDigit(num, digit):
	working = num / 10**(digit-1)
	return working % 10

def countingSort(A, k, digitNum):
        C = []
        B = []

        # initialize the destination array
        for i in range(0, len(A)):
                B.append(0)
        
        # initialize the counts
        for i in range(0, k+1):
                C.append(0)
        
        # count the occurrences of each value
        for j in range(0, len(A)):
                digit = getDigit(A[j],digitNum)
                C[digit] = C[digit] + 1
        
        # determine the cumulative frequencies
        for i in range(1, k+1):
                C[i] = C[i] + C[i - 1]

        # order the elements by their frequency
        i = 0
        for j in range(len(A)-1,-1,-1):
                digit = getDigit(A[j],digitNum)
                B[C[digit]-1] = A[j]
                C[digit] = C[digit] - 1

        return B

def radixSort(A,d):
        B = []
        for j in range(0, len(A)):
                B.append(0)
                
        for i in range(1,d+1):
                A = countingSort(A, 10, i)
                
        return A

print "A: ",
print A
sortedA = radixSort(A,4)
print "sortedA: ",
print sortedA
