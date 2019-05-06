
import sys
import random
import Board


def random_move(board, playerNum):
    moves = []

    # sys.stderr.write("Board: " + np.matrix(board) + '\n')  # unsure if this will work

    moves = board.get_OkayDokeyColumns()
    # sys.stderr.write("Valid moves: " + str(for m in moves) + '\n')  # displays the valid moves

    column = random.choice(moves)
    # sys.stderr.write("Random move: " + str(column) + '\n')  # displays the random column choice

    return column
