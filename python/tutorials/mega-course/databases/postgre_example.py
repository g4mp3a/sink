import psycopg2

inventory = [("Wine Glass", 12, 12.99),
             ("Chalis", 12, 9.99),
             ("Shot Glass", 8, 15.49),
            ]

def create_table():
    curr.execute("CREATE TABLE IF NOT EXISTS store (item TEXT, quantity INTEGER, price REAL)")
    conn.commit()

def insert_data(data):
    curr.executemany("INSERT INTO store VALUES (%s,%s,%s)", data)
    conn.commit()

def update_quantity_by_item(qty, item):
    curr.execute("UPDATE store SET quantity = %s where item = %s", (qty, item,))
    conn.commit()

def delete_by_item(item):
    curr.execute("DELETE FROM store WHERE item = %s", (item,))
    conn.commit()

def delete_all():
    curr.execute("DELETE FROM store")
    conn.commit()

def drop_table():
    curr.execute("DROP TABLE store")
    conn.commit()

def fetch_all():
    curr.execute("SELECT * FROM store ORDER BY price")
    rows = curr.fetchall()
    print(rows)

conn = psycopg2.connect("dbname='store' user='gautampriya' password='@Simple1!' host='localhost'port='5432'")
curr = conn.cursor()

#delete_all()
#insert_data(inventory)
#delete_by_item("Chalis")

fetch_all()

#insert_data([("Chalis", 6, 20.99),])
update_quantity_by_item(36, "Chalis")

fetch_all()

conn.close()
