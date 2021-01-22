weights = [5.0, 9.0, 2.0, 4.5, 7.0, 1.0, 3.0, 29.5]
values  = [  4,  11,   4,   3,   7,   2,   4,   40]

def knapsack(capacity, weights, values):
    n = len(weights)

    unitValues = []
    for i in range(0, n):
        unitValue = values[i] / weights[i]
        unitValues.append(unitValue)

    amounts = []
    for i in range(0, n):
        amounts.append(0)

    remainingWeight = capacity
    while remainingWeight > 0:
        i = maxIndex(unitValues)
        if remainingWeight > weights[i]:
            # place the entire object into the
            # knapsack
            remainingWeight = remainingWeight - weights[i]
            amounts[i] = 1.0
        else:
            # fill the rest of the knapsack with
            # part of the next best object
            amounts[i] = remainingWeight / weights[i]
            remainingWeight = 0
        unitValues[i] = -1 # used

    return amounts

def maxIndex(list):
    max = -1
    index = -1
    for i in range(0, len(list)):
        if list[i] > max:
            max = list[i]
            index = i
    return index

amounts = knapsack(25, weights, values)
print "amounts = ",
print amounts
