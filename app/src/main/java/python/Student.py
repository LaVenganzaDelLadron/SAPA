import connectDB as connect
import json, base64


def addStudent(student_profile, student_firstname, student_middlename, student_lastname,
               student_address, phone_number, student_email, student_birthdate,
               student_gender, school_id):
    status_message = ""
    try:
        if connect.connect() == "success" and connect.mydb.is_connected():
            mycursor = connect.mydb.cursor()
            sql = "CALL AddStudent(%s, %s, %s, %s, %s, %s, %s, %s, %s, %s)"

            if student_profile is not None:
                try:
                    if hasattr(student_profile, "tobytes"):
                        student_profile = student_profile.tobytes()
                    else:
                        student_profile = bytes(student_profile)
                except Exception:
                    student_profile = None

            values = (student_profile, student_firstname, student_middlename,
                      student_lastname, student_address, phone_number,
                      student_email, student_birthdate, student_gender, school_id)

            try:
                mycursor.execute(sql, values)
                connect.mydb.commit()
                status_message =  "success"
            except Exception as e:
                status_message = f"fail: {e}"
            finally:
                mycursor.close()
                connect.mydb.close()
        else:
            status_message = "fail: DB not connected"
    except Exception as e:
        status_message = f"fail: {e}"
    return status_message


def getAllStudent(school_id):
    posts = []
    try:
        if connect.connect() == "success" and connect.mydb.is_connected():
            mycursor = connect.mydb.cursor()
            sql = """SELECT student_id, student_firstname, student_middlename,
                            student_lastname, student_address, phone_number,
                            student_email, student_birthdate, student_gender,
                            student_profile
                     FROM Student WHERE school_id = %s"""
            values = (school_id,)
            mycursor.execute(sql, values)
            rows = mycursor.fetchall()

            for row in rows:
                student_id = row[0]
                student_firstname = row[1]
                student_middlename = row[2]
                student_lastname = row[3]
                student_address = row[4]
                phone_number = row[5]
                student_email = row[6]
                student_birthdate = row[7]
                student_gender = row[8]
                student_profile = row[9]

                if student_profile:
                    img_base64 = base64.b64encode(student_profile).decode("utf-8")
                else:
                    img_base64 = None

                posts.append({
                    "student_id": student_id,
                    "student_firstname": student_firstname,
                    "student_middlename": student_middlename,
                    "student_lastname": student_lastname,
                    "student_address": student_address,
                    "phone_number": phone_number,
                    "student_email": student_email,
                    "student_birthdate": str(student_birthdate),
                    "student_gender": student_gender,
                    "imageBase64": img_base64
                })

            mycursor.close()
            connect.mydb.close()

    except Exception as e:
        return json.dumps({"status": "error", "message": str(e)})

    return json.dumps(posts)
