import folium
map = folium.Map(location=[37.3382, -121.893028], zoom_start=10, tiles="Stamen Terrain")

fg = folium.FeatureGroup(name="San Jose Downtown")
fg.add_child(folium.Marker(location=[37.3382, -121.893028], popup="Marker", icon=folium.Icon(color='blue')))
fg.add_child(folium.CircleMarker(location=[37.3, -121.8], popup="Circle Marker", color="red", fill=True))
fg.add_child(folium.ClickForMarker())
fg.add_child(folium.RegularPolygonMarker(location=[37.2, -121.893028], popup="RegularPolygonMarker",
                                         icon=folium.Icon(color='darkpurple')))
map.add_child(fg)

map.save("map.html")