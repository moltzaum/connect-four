"""
Kevin Macfarlane, Benjamin Stanton
CSCI 3508, Prof. Doug Williams
4/3/19
"""

import sys
import StreamReader as rdr
import Board as brd
import Player as plr
import StreamSender as snd

sys.stderr.write("Connect Four\n")

# assumes driver sends correct arguments
player_num = int(sys.argv[2])  # this gives the player number
width = int(sys.argv[4])  # this gives us the width
height = int(sys.argv[6])  # this gives us the height
turn_num = 0
EOF_flag = False
max_iters = 3

sys.stderr.write("Player: " + str(player_num) + '\n')
sys.stderr.write("Width: " + str(width) + '\n')
sys.stderr.write("Height: " + str(height) + '\n')

while True:
    sys.stderr.write("Turn: " + str(turn_num) + '\n')
    sys.stderr.write("Recieving board from driver\n")

    # read in the json to the board
    board = rdr.stream_read(width, height, EOF_flag)            # ********Can take out EOF_flag?**********

    # board = -1 when the reader detects an end of file and exits out of the while loop
    if type(board) == type(-1):
        sys.stderr.write("Game over. Exiting...\n")
        break
    
    # update log
    if board.isEmpty():
        if turn_num == 0:
            sys.stderr.write("Recieve succeeded\n")
        else:
            sys.stderr.write("Recieve failed\n")
            
    else:
        sys.stderr.write("Recieve succeeded\n")        
        
    sys.stderr.write("Determining move\n")

    bad_move = True  # moved to here, otherwise it never runs the random move function at the bottom
    while bad_move:
        # get random valid move
        # move = plr.random_move(board, player_num)
        move = plr.same_column_move(board, player_num)  # this will make the player choose the first available column every time

        if move >= width:
            sys.stderr.write("Invalid move determined, retrying...\n")
            max_iters -= 1
        elif move not in board.get_OkayDokeyColumns():
            sys.stderr.write("Invalid move determined, retrying...\n")
            max_iters -= 1
        else:
            sys.stderr.write(str(move) + " chosen. This is a valid move.\n")
            bad_move = False
        if max_iters == 0:
            move = plr.random_move(board, player_num)
            sys.stderr.write(str(move) + " chosen. This is a valid move.\n")
            bad_move = False

    sys.stderr.write("Sending move to driver\n")   
    # try to send move 3 times
    for i in range(0,3):
        # send move to driver
        success = snd.send_move(move)

        if success:
            sys.stderr.write("Send succeeded\n")
            turn_num += 1
            break
        else:
            sys.stderr.write("Send failed, retrying...\n")

    if not success:
        sys.stderr.write("Send failed\n")
            

sys.stderr.close()
sys.stdout.close()
sys.stdin.close()
