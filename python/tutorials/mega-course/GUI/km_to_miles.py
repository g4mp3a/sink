from tkinter import *

def km_to_miles():
    text.insert(END, str(float(entry_value.get())*1.6) + " miles")

window = Tk()

button = Button(window, text="Execute", command=km_to_miles)
button.grid(row=0, column=0)

entry_value = StringVar()
entry = Entry(window, textvariable=entry_value)
entry.grid(row=0, column=1)

text = Text(window, height=1, width=20)
text.grid(row=0, column=2)

window.mainloop()
