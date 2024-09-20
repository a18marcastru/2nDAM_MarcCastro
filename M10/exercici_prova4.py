h = int(input("h: "))
m = int(input("m: "))
s = int(input("s: "))
segundoTotal = int(input("segundoTotal: "))

s = s + segundoTotal
s = s%60
m = m + (s/60)
m = m%60
h = h + (m/60)
h = h%24

print("Hora: " + str(int(h)) + " Minutos: " + str(int(m)) + " Segundos: " + str(int(s)))