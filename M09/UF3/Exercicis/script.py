import sys
import multiprocessing

print("Python script started")

print("Proces ID: ", multiprocessing.current_process().pid)

symbol, count = sys.argv[1], sys.argv[2]

pattern = symbol * count
sys.stdout.write(pattern)

print("\nPython script ended")

exit(5)