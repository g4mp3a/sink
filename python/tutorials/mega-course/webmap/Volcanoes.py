import folium
import pandas

data = pandas.read_csv("Volcanoes.txt")
latitudes = list(data.LAT)
longitudes = list(data.LON)
names = list(data.NAME)
elevations = list(data.ELEV)

map = folium.Map(location=[39.7392, -104.9903], zoom_start=5, tiles="Stamen Terrain")
fg = folium.FeatureGroup(name="Volcanoes")
for lat, lon, name, elevation in zip(latitudes, longitudes, names, elevations):
    fg.add_child(folium.Marker(location=[lat, lon], popup=folium.Popup(name + "\nElevation: " + str(elevation) + "m", parse_html=True), icon=folium.Icon(color='green')))
map.add_child(fg)

map.save("volcanoe_map.html")
