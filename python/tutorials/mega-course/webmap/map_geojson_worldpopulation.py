import folium
map = folium.Map(location=[39.7392, -104.9903], zoom_start=2, tiles="Stamen Terrain")

fg = folium.FeatureGroup(name="World Population")
fg.add_child((folium.GeoJson(data=open("world.json", "r", encoding="utf-8-sig").read(),
                             style_function = lambda x: {
                             'fillColor':'green' if x['properties']['POP2005'] < 10000000
                             else 'orange' if 10000000 <= x['properties']['POP2005'] < 100000000
                             else 'red'})))
map.add_child(fg)

map.save("map_country_populations.html")