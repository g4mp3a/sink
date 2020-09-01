import sqlite3

inventory = [("Wine Glass", 12, 12.99),
             ("Chalis", 12, 9.99),
             ("Shot Glass", 8, 15.49),
            ]

def create_table():
    curr.execute("CREATE TABLE IF NOT EXISTS store (item TEXT, quantity INTEGER, price REAL)")
    conn.commit()

def insert_data(data):
    curr.executemany("INSERT INTO store VALUES (?,?,?)", data)
    conn.commit()

def update_quantity_by_item(qty, item):
    curr.execute("UPDATE store SET quantity = ? where item = ?", (qty, item,))
    conn.commit()

def delete_by_item(item):
    curr.execute("DELETE FROM store WHERE item = ?", (item,))
    conn.commit()

def delete_all():
    curr.execute("DELETE FROM store")
    conn.commit()

def drop_table():
    curr.execute("DROP TABLE store")
    conn.commit()

conn = sqlite3.connect("store.db")
curr = conn.cursor()

delete_by_item("Chalis")

curr.execute("SELECT * FROM store")
rows = curr.fetchall()
print(rows)

insert_data([("Chalis", 6, 20.99),])

for row in curr.execute("SELECT * FROM store ORDER BY price"):
    print(row)

conn.close()
