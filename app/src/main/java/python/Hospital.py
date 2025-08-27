import connectDB as connect
import base64
import json

def getAllHospital():
    status_message = ""
    posts = []
    try:
        if connect.connect() == "success":
            if connect.mydb.is_connected():
                mycursor = connect.mydb.cursor()
                mycursor.execute("SELECT hospital_name, hospital_address, hospital_profile FROM Hospital")
                rows = mycursor.fetchall()

                for row in rows:
                    hospital_name = row[0]
                    hospital_address = row[1]
                    img_data = row[2]
                    if img_data:
                        img_base64 = base64.b64encode(img_data).decode("utf-8")
                    else:
                        img_base64 = None

                    posts.append({
                        "hospital_name": hospital_name,
                        "hospital_address": hospital_address,
                        "imageBase64": img_base64
                    })
                mycursor.close()
                status_message = "success"
            else:
                status_message = "not connected"
        else:
            status_message = "fail"
    except Exception as e:
        status_message = str(e)

    return json.dumps(posts)
