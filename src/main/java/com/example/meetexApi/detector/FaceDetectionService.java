package com.example.meetexApi.detector;

import org.opencv.core.*;
import org.opencv.objdetect.CascadeClassifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.opencv.imgcodecs.Imgcodecs;

import java.io.IOException;
import java.util.List;

@Service
public class FaceDetectionService {
    private CascadeClassifier faceDetector;

    public FaceDetectionService() {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        this.faceDetector = new CascadeClassifier("haarcascade_frontalface_alt.xml");
    }

    public List<Rect> detectFaces(MultipartFile file) {
        Mat image = null;
        try {
            image = Imgcodecs.imdecode(new MatOfByte(file.getBytes()), Imgcodecs.IMREAD_UNCHANGED);
        } catch (IOException e) {
            e.printStackTrace();
        }

        MatOfRect faceDetections = new MatOfRect();
        faceDetector.detectMultiScale(image, faceDetections);

        return faceDetections.toList();
    }
}
