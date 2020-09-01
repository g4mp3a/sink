import folium
import pandas

def get_colour(integer):
    if integer < 1000:
        return "green"
    elif integer < 3000:
        return "orange"
    else:
        return "red"


data = pandas.read_csv("Volcanoes.txt")
latitudes = list(data.LAT)
longitudes = list(data.LON)
names = list(data.NAME)
elevations = list(data.ELEV)

html = """
<h4><a href="https://www.google.com/search?q=%%22%s%%22" target="_blank">%s</a></h4>
Height: %f m
"""

map = folium.Map(location=[39.7392, -104.9903], zoom_start=5, tiles="Stamen Terrain")

# Volcanoe feature group
fg_volcanoes = folium.FeatureGroup(name="Volcanoes")
for lat, lon, name, elevation in zip(latitudes, longitudes, names, elevations):
    iFrame = folium.IFrame(html % (name, name, elevation), width=200, height=100)
    fg_volcanoes.add_child(folium.CircleMarker(location=[lat, lon], popup=folium.Popup(iFrame),  color=get_colour(elevation),
                                     fill=True, fill_opacity=0.5, fill_color=get_colour(elevation)))
map.add_child(fg_volcanoes)

# Downtown feature group
fg_downtown = folium.FeatureGroup(name="San Jose Downtown")
fg_downtown.add_child(folium.Marker(location=[37.3382, -121.893028], popup="Marker", icon=folium.Icon(color='blue')))
fg_downtown.add_child(folium.CircleMarker(location=[37.3389, -121.893020], popup="Circle Marker", color="green",
                                 fill=True, fill_opacity=0.7))
fg_downtown.add_child(folium.ClickForMarker())
fg_downtown.add_child(folium.RegularPolygonMarker(location=[37.3350, -121.893028], popup="RegularPolygonMarker", color="purple",
                                         fill=True))
map.add_child(fg_downtown)

# Population feature group
fg_population = folium.FeatureGroup(name="World Population")
fg_population.add_child((folium.GeoJson(data=open("world.json", "r", encoding="utf-8-sig").read(),
                             style_function = lambda x: {
                             'fillColor':'green' if x['properties']['POP2005'] < 10000000
                             else 'yellow' if 10000000 <= x['properties']['POP2005'] < 100000000
                             else 'red'})))
map.add_child(fg_population)

# Layer control
map.add_child(folium.LayerControl())

map.save("multilayermap.html")
