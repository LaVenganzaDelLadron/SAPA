import socket
import mysql.connector


hostname = socket.gethostname()
lan_ip = socket.gethostbyname(hostname)
mydb = None
index = None
email = None
firstname = None
middlename = None
lastname = None

schoolProfile = None
schoolIndex = None
schoolName = None
schoolNumber = None
schoolCode = None
schoolAddress = None
schoolBio = None

def connect():
    global mydb
    status_message = ""
    try:
        mydb = mysql.connector.connect(
            host="192.168.1.5",
            user="root",
            password="1234",
            database="dbsapa"
        )
        if mydb.is_connected:
            status_message = "success"
    except Exception:
        status_message = "fail"
    return status_message


def getEmail():
    return email
def getFirstname():
    return firstname
def getMiddlename():
    return middlename
def getLastname():
    return lastname
def getId():
    return index



def getSchoolImage():
    return getSchoolImage
def getSchoolIndex():
    return schoolIndex
def getSchoolName():
    return schoolName
def getSchoolNumber():
    return schoolNumber
def getSchoolCode():
    return schoolCode
def getSchoolAddress():
    return schoolAddress
def getSchoolBio():
    return schoolBio




