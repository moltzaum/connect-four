"""
Khang Le
CSCI 3508
4/10/19
Board Class - A 2D array that we'll be using to simulate the board of the game
"""

class Board:
    """def __init__(self):                              #Default constructor for the Board class, does nothing
        someArray[][]                                # We would need the length and width and then create an array of 0's using those
        self.board_Array = someArray
        self.length = len(someArray[0])
        self.width = len(someArray)"""
    
    def __init__(self, _array):                      #Accepts a 2D array and initializes itself array = array
        self.board_Array = _array
        self.length = len(_array[0])
        self.width = len(_array)
        
    def get_Content(self, row, column):              #Function that returns the value of an element in the 2D array
        return self.board_Array[row][column]

    def get_length(self):
        return self.length

    def get_width(self):
        return self.width
    
    def set_Content(self, row, column, value):       #Function that sets the value of an element in the 2D array
        self.board_Array[row][column] = value
    
    def isEmpty(self):                              #Reads all values in board and see's if they are all empty (all 0's)
        for i in range(self.length):                #Increments through each element of each row and tests to see if they are equal to 0
            for r in range(self.width):         
                if self.board_Array[r][i] != 0:     #Compares the current element to value 0
                    return False                    #If any element is not equal to 0, then the board is not empty return False
        return True                                 #If all elements are equal to 0, the board is empty and return True
        
    def get_OkayDokeyColumns(self):                  #Function that returns a list of values that show which columns are not full
        okayDokeyList = []                           #Creates a list/array
        for i in range(self.width):                  #for loop that uses the isFull function to test all columns of the board
            if self.board_Array[i][0] == 0:
                okayDokeyList.append(i)
                                                     #if the column is not full then append the column number to the list
                                                     #appends the number of column that is not full onto the list
        return okayDokeyList                         #returns the list
    
    
 
