import cv2
import os

#open camera
video=cv2.VideoCapture(0)
#import modul deteksi 
facedetect=cv2.CascadeClassifier('haarcascade_frontalface_default.xml')

#input nama
nameID=str(input("Masukan nama: ")).lower()
path='images/'+nameID
isExist = os.path.exists(path)

#input nama baru jika sudah ada
if isExist:
	print("Nama sudah ada!")
	nameID=str(input("Masukan nama baru: "))
else:
	os.makedirs(path)

#ambil photo biasa
count1=0
while True:
	ret,frame=video.read()
	count1=count1+1
	name='./images/'+nameID+'/'+ str(count1) + '.jpg'
	print("Membuat gambar zoom in........." +name)
	cv2.imwrite(name, frame)
	#show frame
	cv2.imshow("WindowFrame", frame)
 	#ambil gambar 500x
	cv2.waitKey(1)
	if count1>499:
		break

#ambil photo zoom
count2=500
while True:
	ret,frame=video.read()
 	#scale the module
	faces=facedetect.detectMultiScale(frame,1.3, 5)
 	#boundary face box
	for x,y,w,h in faces:
		count2=count2+1
		name='./images/'+nameID+'/'+ str(count2) + '.jpg'
		print("Membuat gambar zoom in........." +name)
		cv2.imwrite(name, frame[y:y+h,x:x+w])
		cv2.rectangle(frame, (x,y), (x+w, y+h), (0,255,0), 3)
  	#show frame
	cv2.imshow("WindowFrame", frame)
 	#ambil gambar 500x
	cv2.waitKey(1)
	if count2>999:
		break
 
video.release()
cv2.destroyAllWindows()