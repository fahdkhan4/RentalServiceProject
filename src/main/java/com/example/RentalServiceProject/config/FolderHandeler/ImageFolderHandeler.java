package com.example.RentalServiceProject.config.FolderHandeler;

import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Paths;

@Component
public class ImageFolderHandeler {

//                                           Creating image Folder
    public void creatingImageFolder(){
        try{
         final String imageFolderPath = Paths.get("src/main/resources/static").toString();
         File imageFolder = new File(imageFolderPath+"\\image");
         imageFolder.mkdir();

        }catch (Exception e){
            System.out.println("Error creating Image Folder "+e);
        }
    }
//                                            Creating userimages folder
    public void creatingUserImagesFolder(){
        try{
//                                            Image folder will be created if not exist
            creatingImageFolder();

            final String userImageFolderPath = Paths.get("src/main/resources/static/image").toString();
            File userImageFolder = new File(userImageFolderPath+"\\userimages");
            if(userImageFolder.mkdir()){
                System.out.println("Folder Has Been Created");
            }
            else{
                System.out.println("Folder Already Exist");
            }

        }catch (Exception e){
            System.out.println("Error creating User images folder "+e);
        }
    }
//                                          Creating assetimages Folder
    public void creatingAssetImagesFolder(){
        try{
//                                          image folder will be created if not exist
            creatingImageFolder();

            final String assetImageFolderPath = Paths.get("src/main/resources/static/image").toString();
            File assetImageFolder = new File(assetImageFolderPath+"\\assetimages");
            assetImageFolder.mkdir();

        }catch (Exception e){
            System.out.println("Error creating Asset images Folder "+e);
        }
    }

}
