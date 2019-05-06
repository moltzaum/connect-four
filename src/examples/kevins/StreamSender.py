# Jahoon Koo
# The stream sender will put the move into stdout,
# then return a bool representing the success or failure of the stream.
import sys
import json


def send_move(move):
    try:
        moveDict = {}
        moveDict['move'] = move
        msg = json.dumps(moveDict)
        sys.stderr.write("Sending this JSON : " + msg + '\n')
        sys.stdout.write(msg + '\n')
        sys.stdout.flush()
        return True
    except:
        e = sys.exc_info()[0]
        sys.stderr.write("Error " + str(e) + '\n')
        return False
