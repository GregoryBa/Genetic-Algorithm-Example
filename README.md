# Genetic Alrorithm
This code includes solution to problems listed below:

## Problem 1
The first program is a black box function that takes two real-valued numbers as input. The function has a unique minimum for specific pair of inputs. Your task is to write a genetic algorithm to find this minimum value. You can safely assume that the minimum can be found for input vaues between -500 and +500 for both variables. So, you do not need to search smaller or larger values than that.

## Problem 2
The second problem is a version of the well-known knapsack problem. The idea is that you want to pack a bag. You have 100 items that you would like to take with you. Each item has a utility value and a weight (assumed to be expressed in kg). You want to maximise your utility (which is the sum of the utilities of all the items you take with you) but you are not allowed to pack more than 500 kgsof weight altogether. This means that you need to choose the items that maximise your total utility while staying within the weight limit. The knapsack problem is already implemented for you, so you do not need to worry about the list ofitems and their utilities of weight. The functiondouble[] 
```
Assess.getTest2(boolean[])
```
accepts a boolean array of length 100 as a candidate solution. The item you want to take with you corresponds to the index value and the boolean at the relevant index encodes whether or not you take the item with you. So, for example, if the array is given by:
```
true,true,false,true,false,false,....
```
Then this means that you take with you items 0,1,3,...  The function 
```
getTest2(boolean[]) 
```
returns an array of doubles. The first entry (index 0) is the weight of your combination and the second (index 1) is the sum of the utilities. If unsure, check the example code where I show how to use the function. Before starting to implement your GA, play a bit with the example code to make sure you understand how fitness is returned.

Your task is to find the a combination of items to pack that maximises utility while staying within the weight constraints.