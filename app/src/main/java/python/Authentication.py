import connectDB as connect

def signup(email, firstname, middlename, lastname, password):
    status_message = ""
    try:
        if connect.connect() == "success":
            if connect.mydb.is_connected():
                mycursor = connect.mydb.cursor()
                sql = "CALL RegisterSchoolCoordinator(%s, %s, %s, %s, %s)"
                values = (email, firstname, middlename, lastname, password)
                mycursor.execute(sql, values)
                connect.mydb.commit()
                status_message = "success"
    except Exception:
        status_message = "fail"
    return status_message


def login(email, password):
    status_message = ""
    try:
        if connect.connect() == "success":
            mycursor = connect.mydb.cursor(dictionary=True)
            sql = "SELECT * FROM SchoolCoordinator WHERE email = %s AND password = %s AND is_approved = TRUE"
            values = (email, password)
            mycursor.execute(sql, values)
            account = mycursor.fetchone()
            if account:
                connect.index = account["school_coordinator_id"]
                connect.firstname = account['firstname']
                connect.middlename = account['middlename']
                connect.lastname = account['lastname']
                status_message = "success"
            else:
                status_message = "fail"
    except Exception:
        status_message = "fail"
    return status_message