import connectDB as connect
import json, base64


def updateCoordinator(school_coordinator_id, school_coordinator_profile, email, firstname, middlename, lastname, phone_number, birthdate, address, gender, bio):
    status_message = ""
    try:
        if connect.connect() == "success":
            if connect.mydb.is_connected():
                mycursor = connect.mydb.cursor()
                sql = "CALL EditCoordinatorProfile(%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s)"

                try:
                    if hasattr(school_coordinator_profile, "tobytes"):
                        school_coordinator_profile = school_coordinator_profile.tobytes()
                    else:
                        school_coordinator_profile = bytes(school_coordinator_profile)
                except Exception as conv_err:
                    school_coordinator_profile = None

                if school_coordinator_profile is not None:
                    print(f"Image size = {len(school_coordinator_profile)} bytes")

                values = (school_coordinator_id, school_coordinator_profile, email, firstname, middlename, lastname, phone_number, birthdate, address, gender, bio)
                mycursor.execute(sql, values)
                connect.mydb.commit()
                status_message = "success"
    except Exception as e:
        status_message = "fail"
    return status_message


def getCoordinatorProfileImage(school_coordinator_id):
    try:
        if connect.connect() == "success" and connect.mydb.is_connected():
            mycursor = connect.mydb.cursor()
            sql = "SELECT school_coordinator_profile FROM SchoolCoordinator WHERE school_coordinator_id = %s"
            mycursor.execute(sql, (school_coordinator_id,))
            result = mycursor.fetchone()
            if result and result[0]:
                return bytes(result[0])
    except Exception as e:
        print("Error fetching profile image:", e)
    return None
