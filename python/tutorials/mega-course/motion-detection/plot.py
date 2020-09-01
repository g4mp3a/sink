from camera_motion_detector import df
from bokeh.plotting import figure, show, output_file
from bokeh.models import HoverTool, ColumnDataSource

df["Start_string"] = df["Start"].dt.strftime("%Y-%m-%d %H:%M:%S")
df["End_string"] = df["End"].dt.strftime("%Y-%m-%d %H:%M:%S")

cds = ColumnDataSource(df)

hover = HoverTool(tooltips=[("Start", "@Start_string"), ("End", "@End_string")])

p = figure(title="Motion Graph", height=200, width=1000, x_axis_type="datetime", )
p.yaxis.minor_tick_line_color = None
p.ygrid[0].ticker.desired_num_ticks = 1
p.add_tools(hover)
#p.quad(left=df["Start"], right=df["End"], bottom=0, top=1, color="green")
p.quad(left="Start", right="End", bottom=0, top=1, color="green", source=cds)

output_file("Motion_Graph.html")
show(p)


