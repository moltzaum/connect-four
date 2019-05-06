import json
import sys
import Board as brd
import time as tm


def stream_read(width, height):
    print("HELLO-2")
    for line in sys.stdin:
        print("HELLO")
        sys.stdout.flush()
        sys.stderr.write(line)
        sys.stderr.flush()
        parse = json.loads(line)
        board_array = parse['grid']
        board = brd.Board(board_array)
        return board
    print("HELLO-3")


"""
def stream_read(width, height):
    gotBoard = False
    if sys.stdin:
        sys.stderr.write("GOT EM\n")
    else:
        sys.stderr.write("NOTHING\n")

    readin = sys.stdin
    readin = readin.read()
    
    while gotBoard is False:
        readin = sys.stdin
        readin = readin.read()

        if len(readin) == 0:
            tm.sleep(0.5)
            continue
        else:
            gotBoard = True

    sys.stderr.write("\"" + readin + "\"" + '\n')
    sys.stderr.write(str(type(readin)) + '\n' + str(len(readin)))
    parse = json.loads(readin)

    board_array = parse['grid']
    board = brd.Board(board_array)
    return board
"""

