import json

ruta = 'preguntes.json'

with open(ruta, 'r', encoding='utf-8') as contingut:
    preguntes = json.load(contingut)  # Cambia 'ruta' por 'contingut'
    print(preguntes)