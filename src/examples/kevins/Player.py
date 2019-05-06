"""
Kevin Macfarlane
CSCI 3508
11 Apr 2019
Player Class - takes in a board object and returns a random valid move
"""
import sys
import random
import Board
# import numpy as np


def random_move(board, playerNum):
    moves = []

    # sys.stderr.write("Board: " + board + '\n')  # unsure if this will work

    moves = board.get_OkayDokeyColumns()
    sys.stderr.write("Valid moves: " + str(moves) + '\n')  # displays the valid moves

    column = random.choice(moves)
    # sys.stderr.write("Random move: " + str(column) + '\n')  # displays the random column choice

    return column


def same_column_move(board, playerNum):
    moves = []
    moves = board.get_OkayDokeyColumns()

    column = moves[0]

    return column



    
    
    
                        
    





























    
