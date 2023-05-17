package com.sourabhsurve.SBT_File.ImageUploadAndDownload.service;

import com.sourabhsurve.SBT_File.ImageUploadAndDownload.model.ImageData;
import com.sourabhsurve.SBT_File.ImageUploadAndDownload.repository.ImageDataRepository;
import com.sourabhsurve.SBT_File.ImageUploadAndDownload.utils.ImageDataUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class ImageDataService {
    @Autowired
    private ImageDataRepository repository;

    public String uploadImage(MultipartFile file) throws IOException {

        ImageData imageData= repository.save(ImageData.builder().name(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(ImageDataUtils.compressImage(file.getBytes())).build());
        if (imageData != null) {
            return "file uploaded successfully : " + file.getOriginalFilename();
        }
        return null;
    }

    public byte[] downloadImage(String fileName){
        Optional<ImageData> dbImageData = repository.findByName(fileName);
        byte[] images=ImageDataUtils.decompressImage(dbImageData.get().getImageData());
        return images;
    }
}
