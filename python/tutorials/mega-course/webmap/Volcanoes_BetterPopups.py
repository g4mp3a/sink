import folium
import pandas

data = pandas.read_csv("Volcanoes.txt")
latitudes = list(data.LAT)
longitudes = list(data.LON)
names = list(data.NAME)
elevations = list(data.ELEV)

html = """
<h4><a href="https://www.google.com/search?q=%%22%s%%22" target="_blank">%s</a></h4><br>
Height: %f m
"""

map = folium.Map(location=[39.7392, -104.9903], zoom_start=5, tiles="Stamen Terrain")
fg = folium.FeatureGroup(name="Volcanoes")
for lat, lon, name, elevation in zip(latitudes, longitudes, names, elevations):
    iFrame = folium.IFrame(html % (name, name, elevation), width=200, height=100)
    fg.add_child(folium.Marker(location=[lat, lon], popup=folium.Popup(iFrame), icon=folium.Icon(color='green')))
map.add_child(fg)

map.save("volcanoe_map_betterpopups.html")
