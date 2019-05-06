
class Board:
    def __init__(self, _array):
        self.board_Array = _array
        self.length = len(_array)
        self.width = len(_array[0])
        
    def get_Content(self, row, column):
        return self.board_Array[row][column]
    
    def set_Content(self, row, column, value):
        self.board_Array[row][column] = value

    def isFull(self, column):
        for i in range(self.length):
            if self.board_Array[i][column] == 0:
                return False
        return True

    def isEmpty(self):
        for i in range(self.length):
            for r in range(self.width):         
                if self.board_Array[i][r] != 0:
                    return False
        return True
        
    def get_OkayDokeyColumns(self):
        okayDokeyList = []
        for i in range(self.width):
            if not self.isFull(i):
                okayDokeyList.append(i)
        return okayDokeyList
    
    
 
