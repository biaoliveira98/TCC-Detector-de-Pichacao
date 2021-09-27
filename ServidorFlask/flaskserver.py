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
import io
import cv2
import numpy as np
import time
from PIL import Image
import os
from model import SiameseModel

app = Flask(__name__)
app.config["DEBUG"] = True
logging.basicConfig(level=logging.INFO)
test=''

#Siamese model
IMG_WIDTH = 128
IMG_HEIGHT = 128
IMG_CHANNELS = 3

siamese_model_path = "C:/img_predict/"
#siamese_model_path = os.path.join(cur_dir, "model")

#"Normal" model
model = load_model("C:/ProjetosJupyterNotebooks/graffitiDetectionModel")
img_path = "C:/ProjetosJupyterNotebooks/data/predicts/"
img_name = "/test.jpg"


def prepare_images(file):
    img = image.load_img(img_path + file, target_size =(224,224))
    img_array = image.img_to_array(img)
    img_array_expanded_dims = np.expand_dims(img_array, axis=0)
    return tf.keras.applications.mobilenet.preprocess_input(img_array_expanded_dims)

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

@ app.route("/predict/siamese_model", methods=['POST'])
def predict_():
    body = request.json

    image = base64.b64decode(body["image"], validate=True)

    image = Image.open(io.BytesIO(image))
    image = image.resize((IMG_WIDTH, IMG_HEIGHT), Image.ADAPTIVE)
    tmp_name = "temp_{}.jpg".format(time.time())
    tmp_path = os.path.join(siamese_model_path, tmp_name)
    image.save(tmp_path)

    siamese_model = SiameseModel(siamese_model_path, (IMG_WIDTH, IMG_HEIGHT, IMG_CHANNELS))
    prediction = siamese_model.predict(tmp_path)
    os.remove(tmp_path)
    response = str(prediction)
    print(response)

    return response

# Flask Start
##################################
if __name__ == "__main__":
    app.run(host='0.0.0.0')