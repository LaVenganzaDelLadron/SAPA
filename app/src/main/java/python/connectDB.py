import socket
import mysql.connector


hostname = socket.gethostname()
lan_ip = socket.gethostbyname(hostname)
mydb = None

def connect():
    global mydb
    status_message = ""
    try:
        mydb = mysql.connector.connect(
            host="192.168.1.2",
            user="root",
            password="1234",
            database="dbsapa"
        )
        if mydb.is_connected:
            status_message = "success"
    except Exception:
        status_message = "fail"
    return status_message



