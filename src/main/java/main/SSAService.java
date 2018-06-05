package main;


import model.SSA;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class SSAService {
    public SSAService() throws IOException{}
    File file=new File("C://Users//Jakub//Desktop//img1.jpg");
    BufferedImage bimg= ImageIO.read(file) ;
    String name=file.getName();
    String size=String.valueOf(file.length());
    String res=bimg.getHeight()+"x"+bimg.getWidth();
    BasicFileAttributes attr1 = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
    String created = attr1.creationTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().toString();

    File file2=new File("C://Users//Jakub//Desktop//img2.jpg");
    BufferedImage bimg2= ImageIO.read(file2) ;
    String name2=file2.getName();
    String size2=String.valueOf(file2.length());
    String res2=bimg2.getHeight()+"x"+bimg2.getWidth();
    BasicFileAttributes attr2 = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
    String created2 = attr1.creationTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().toString();

    List<SSA> ssaList=new ArrayList<SSA>(Arrays.asList(
            new SSA("1", name, res,size,created),
            new SSA("2", name2, res2,size2,created2)
    ));
    public List<SSA>getAllSSA(){
        return ssaList;
    }
    List<File> imgList=new ArrayList<File>(Arrays.asList(
            file,
            file2
    ));
    public List<File> getImgList() {
        return imgList;
    }
    List<ResponseEntity<?>> uploadList=new ArrayList<>();
    public List<ResponseEntity<?>> getUploadList(){
        return uploadList;
    }

    public void deleteSSA(String id) {
        imgList.removeIf(t->t.getName().endsWith(id+".jpg"));
        ssaList.removeIf(t->t.getName().endsWith(id+".jpg"));
    }

    public byte[] getImage(String id) throws IOException{
        Path path= Paths.get(imgList.stream().filter(t->t.getName().endsWith(id+".jpg")).findFirst().get().toString());
        byte[] data= Files.readAllBytes(path);
        return data;
    }

    public void addImage(MultipartFile image)throws IOException{
        //Path path=Paths.get("C://Users//Jakub//Desktop//img1.jpg");
        //byte[] data=Files.readAllBytes(path);
        byte[] data=image.getBytes();
        uploadList.add(ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(data));

    }


}
