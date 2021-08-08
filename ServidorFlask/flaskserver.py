from flask import Flask, request
import logging
import json
from flask.wrappers import Response
import tensorflow as tf
from tensorflow.keras.preprocessing import image
from tensorflow.keras.models import load_model
import base64
from PIL import Image
from io import BytesIO
import cv2
import numpy as np

app = Flask(__name__)
app.config["DEBUG"] = True

model = load_model("C:/ProjetosJupyterNotebooks/graffitiDetectionModel")
img_path = "C:/ProjetosJupyterNotebooks/data/predicts/"
img_name = "/test.jpg"

logging.basicConfig(level=logging.INFO)
test=''

def prepare_images(file):
    img = image.load_img(img_path + file, target_size =(224,224))
    img_array = image.img_to_array(img)
    img_array_expanded_dims = np.expand_dims(img_array, axis=0)
    return tf.keras.applications.mobilenet.preprocess_input(img_array_expanded_dims)


@ app.route("/", methods=['GET'])
def hello():
    return "Ola"

@ app.route("/instaloader", methods=['POST'])
def instaloaderReq():
    return

@ app.route("/predict", methods=['POST'])
def predict():
    req = json.loads(request.get_data(as_text=True))
    data = req['data']

    img_bytes = base64.b64decode(data.encode('utf-8'))
    img = Image.open(BytesIO(img_bytes))
    img = img.save(img_path + img_name)
    # load the input image from disk
    image = cv2.imread(img_path + img_name)

    preprocessed_image=prepare_images(img_name)
    pred=model.predict(preprocessed_image)
    response = np.round(pred)
    print(response)
    response = str(response)
    if(response == '[[1. 0.]]'):
        response = 'pichacao'
    else:
        response = '!pichacao'
    return response

# Flask Start
##################################
if __name__ == "__main__":
    app.run(host='0.0.0.0')