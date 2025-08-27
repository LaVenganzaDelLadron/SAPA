import connectDB as connect
import json, base64


def addSchool(school_logo, school_name, school_number, school_code, school_address, school_bio, school_coordinator_id):
    status_message = ""
    try:
        if connect.connect() == "success":
            if connect.mydb.is_connected():
                mycursor = connect.mydb.cursor()
                sql = "CALL AddSchool(%s, %s, %s, %s, %s, %s, %s)"

                try:
                    if hasattr(school_logo, "tobytes"):
                        school_logo = school_logo.tobytes()
                    else:
                        school_logo = bytes(school_logo)
                except Exception as conv_err:
                    school_logo = None

                if school_logo is not None:
                    print(f"🔍 Image size = {len(school_logo)} bytes")

                values = (school_logo, school_name, school_number, school_code, school_address, school_bio, school_coordinator_id)
                mycursor.execute(sql, values)
                connect.mydb.commit()
                status_message = "success"
    except Exception as e:
        status_message = "fail"
    return status_message




def getAllSchool(school_coordinator_id):
    posts = []
    try:
        if connect.connect() == "success":
            if connect.mydb.is_connected():
                mycursor = connect.mydb.cursor()
                sql = "SELECT school_id, school_name, school_number, school_code, school_address, school_bio, school_profile FROM School WHERE school_coordinator_id = %s"
                values = (school_coordinator_id,)
                mycursor.execute(sql, values)
                rows = mycursor.fetchall()

                for row in rows:
                    school_id = row[0]
                    school_name = row[1]
                    school_number = row[2]
                    school_code = row[3]
                    school_address = row[4]
                    school_bio = row[5]
                    img_data = row[6]

                    if img_data:
                        img_base64 = base64.b64encode(img_data).decode("utf-8")
                    else:
                        img_base64 = None

                    posts.append({
                        "school_id": school_id,
                        "school_name": school_name,
                        "school_number": school_number,
                        "school_code": school_code,
                        "school_address": school_address,
                        "school_bio": school_bio,
                        "imageBase64": img_base64
                    })

                mycursor.close()
    except Exception as e:
        return str(e)
    return json.dumps(posts)



















