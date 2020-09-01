from flask import Flask, render_template

app = Flask(__name__)

@app.route('/plot/')
def plot():
    import os
    from pandas_datareader import data
    import datetime as dt
    import pandas as pd
    from bokeh.plotting import figure, show, output_file
    from bokeh.embed import components as com
    from bokeh.resources import CDN

    os.environ["IEX_API_KEY"] = "pk_5141b46edbca4b5cb3fcbb39e996ce73"
    width = 12 * 60 * 60 * 1000  # 12 hours in millis

    start_time = dt.datetime(2019, 7, 1)
    end_time = dt.datetime(2019, 10, 16)
    df = data.DataReader(name="ORCL", data_source="iex", start=start_time, end=end_time)

    def get_status(c, o):
        if c > o:
            return "Increase"
        elif c < o:
            return "Decrease"
        else:
            return "Equal"

    df["status"] = [get_status(c, o) for c, o in zip(df.close, df.open)]
    df["average"] = (df.open + df.close) / 2
    df["height"] = abs(df.open - df.close)
    df["datetime"] = pd.to_datetime(df.index)
    df = df.set_index('datetime')

    output_file("stock.html")

    p = figure(x_axis_type="datetime", width=1000, height=500, sizing_mode="scale_width")
    p.title.text = "Oracle"
    p.title.text_color = "red"
    p.grid.grid_line_alpha = 0.5

    p.segment(df.index, df.high, df.index, df.low, color="black")

    p.rect(df.index[df.status == "Increase"], df.average[df.status == "Increase"],
           width, df.height[df.status == "Increase"], fill_color="#00ffff", line_color="black")

    p.rect(df.index[df.status == "Decrease"], df.average[df.status == "Decrease"],
           width, df.height[df.status == "Decrease"], fill_color="#ff0080", line_color="black")

    p.rect(df.index[df.status == "Equal"], df.average[df.status == "Equal"],
           width, df.height[df.status == "Equal"], fill_color="#808080", line_color="black")

    script, div = com(p)

    cdn_js = CDN.js_files

    return render_template("plot.html", script=script, div=div, cdn_js=cdn_js[0])

@app.route('/')
def home():
    return render_template("home.html")

@app.route('/about/')
def about():
    return render_template("about.html")

if __name__ == "__main__":
    app.run(debug=True)