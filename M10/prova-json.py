import json

ruta = 'preguntes.json'

# preguntes = json.load(ruta)

with open(ruta, 'r', encoding='utf-8') as contingut:
    preguntes = json.load(ruta)
    print(preguntes)