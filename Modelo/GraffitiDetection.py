#!/usr/bin/env python
# coding: utf-8

#Codigo não está em sua versão final

# In[3]:


import numpy as np
import tensorflow as tf
import tensorflow as keras
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Conv2D, MaxPool2D, Flatten, Dense, Dropout, Activation, BatchNormalization
from tensorflow.keras.optimizers import Adam
from tensorflow.keras.metrics import categorical_crossentropy
from keras.preprocessing.image import ImageDataGenerator
from sklearn.metrics import confusion_matrix
import itertools
import shutil
import random
import glob
import matplotlib.pyplot as plt
import warnings
import os
warnings.simplefilter(action='ignore', category=FutureWarning)
get_ipython().run_line_magic('matplotlib', 'inline')

print('done')


# In[4]:


os.chdir = 'data/dataset'
if os.path.isdir('data/dataset/train/com_pichacao') is False:
    os.makedirs('data/dataset/train/com_pichacao')
    os.makedirs('data/dataset/train/sem_pichacao')
    os.makedirs('data/dataset/valid/com_pichacao')
    os.makedirs('data/dataset/valid/sem_pichacao')
    os.makedirs('data/dataset/test/com_pichacao')
    os.makedirs('data/dataset/test/sem_pichacao')

    for c in random.sample(glob.glob('com_pichacao (*'), 807): #948 com pichacao
      shutil.move(c, 'data/dataset/train/com_pichacao')
    for c in random.sample(glob.glob('sem_pichacao (*'), 793): #933 sem pichacao
      shutil.move(c, 'data/dataset/train/sem_pichacao')
    for c in random.sample(glob.glob('com_pichacao (*'), 95): #948 com pichacao
      shutil.move(c, 'data/dataset/valid/com_pichacao')
    for c in random.sample(glob.glob('sem_pichacao (*'), 93): #933 sem pichacao
      shutil.move(c, 'data/dataset/valid/sem_pichacao')
    for c in random.sample(glob.glob('com_pichacao (*'), 46): #948 com pichacao
      shutil.move(c, 'data/dataset/test/com_pichacao')
    for c in random.sample(glob.glob('sem_pichacao (*'), 47): #933 sem pichacao
      shutil.move(c, 'data/dataset/valid/sem_pichacao')

print("done")


# In[14]:


train_path = 'data/dataset/train'
valid_path = 'data/dataset/valid'
test_path = 'data/dataset/test'

print("done")


# In[15]:


train_batches = ImageDataGenerator(preprocessing_function=tf.keras.applications.vgg16.preprocess_input)     .flow_from_directory(directory=train_path, target_size=(224,224), classes=['com_pichacao', 'sem_pichacao'], batch_size=10)        
valid_batches = ImageDataGenerator(preprocessing_function=tf.keras.applications.vgg16.preprocess_input)     .flow_from_directory(directory=valid_path, target_size=(224,224), classes=['com_pichacao', 'sem_pichacao'], batch_size=10)        
test_batches = ImageDataGenerator(preprocessing_function=tf.keras.applications.vgg16.preprocess_input)     .flow_from_directory(directory=test_path, target_size=(224,224), classes=['com_pichacao', 'sem_pichacao'], batch_size=10, shuffle=False)        

print("done")


# In[5]:


assert train_batches.n == 1582
assert valid_batches.n == 187
assert test_batches.n == 93
assert train_batches.num_classes == valid_batches.num_classes == test_batches.num_classes == 2

print("true")


# In[16]:


img, labels = next(train_batches)

print("done")


# In[17]:


def plotImages(images_arr):
    fig, axes =plt.subplots(1, 10, figsize=(20,20))
    axes = axes.flatten()
    for img, ax in zip( images_arr, axes):
        ax.imshow(img)
        ax.axis('off')
    plt.tight_layout()
    plt.show()


# In[18]:


plotImages(img)
print(labels)


# In[19]:


model = Sequential([
        Conv2D(filters=32, kernel_size=(3,3), activation='relu', padding = 'same', input_shape=(224,224,3)),
        MaxPool2D(pool_size = (2,2), strides=2),
        Conv2D(filters=64, kernel_size=(3,3), activation='relu', padding = 'same'),
        MaxPool2D(pool_size = (2,2), strides=2),
        Flatten(),
        Dense(units=2,activation='softmax'),
])


# In[20]:


model.summary()


# In[21]:


model.compile(optimizer=Adam(learning_rate=0.0001),
             loss='categorical_crossentropy',
             metrics=['accuracy'])


# In[22]:


model.fit(x=train_batches, validation_data=valid_batches, epochs=10, verbose=2)


# In[30]:


test_imgs, test_labels = next(test_batches)
plotImages(test_imgs)
print(test_labels)


# In[31]:


test_batches.classes


# In[32]:


predictions = model.predict(x=test_batches, verbose=0)


# In[33]:


np.round(predictions)


# In[35]:


cm = confusion_matrix(y_true=test_batches.classes, y_pred=np.argmax(predictions, axis=-1))


# In[36]:


def plot_confusion_matrix(cm, classes, normalize=False, title='Confusion matrix', cmap=plt.cm.Blues):
    
    plt.imshow(cm, interpolation = 'nearest', cmap=cmap)
    plt.title(title)
    plt.colorbar()
    tick_marks = np.arange(len(classes))
    plt.xticks(tick_marks,classes, rotation=45)
    plt.yticks(tick_marks,classes)
    
    if normalize:
        cm = cm.astype('float') / cm.sum(axis=1)[:, np.newaxis]
        print("Normalized confusion matrix")
    else:
        print('Confusion matrix, without normalization')
        
    print(cm)
    
    thresh = cm.max()/2.
    for i,j in itertools.product(range(cm.shape[0]), range(cm.shape[1])):
        plt.text(j, i, cm[i,j],
                 horizontalalignment="center",
                 color = "white" if cm[i,j] > thresh else "black")
        
    plt.tight_layout()
    plt.ylabel('True label')
    plt.xlabel('Predicted label')


# In[37]:


test_batches.class_indices


# In[38]:


cm_plot_labels = ['com_pichacao', 'sem_pichacao']
plot_confusion_matrix(cm =cm, classes=cm_plot_labels, title='Confusion matrix')


