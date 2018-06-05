package main;

import com.sun.xml.internal.ws.api.pipe.ContentType;
import javafx.scene.image.Image;
import model.SSA;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.annotation.MultipartConfig;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@RestController
public class SSAController {

    @Value("${mymessage}")
    private String message;

    @GetMapping("/getMessage")
    public String getMessage(Model model) {

        model.addAttribute("message", message);

        return "show";
    }
    @Autowired
    private  SSAService ssaService;

    //nawet dziala, wrzuca info
    @RequestMapping("/gallery/pictures")
    public List<SSA> getAllSSA(){
        return ssaService.getAllSSA();
    }
    //wrzuca sciezke, ze zdjeciem
    @RequestMapping("/gallery/picture")
    public List<File> getFileList(){
        return ssaService.getImgList();
    }
    @RequestMapping("galery/upload")
    public List<ResponseEntity<?>> getUploadList(){
        return ssaService.getUploadList();
    }



    //wyswietla zdjecie
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/gallery/picture/{id}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getImage(@PathVariable("id") String id) throws IOException{
        byte[] image = ssaService.getImage(id);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image);
    }
    @ResponseBody

    //cos tam jest, usuwa z picture po id
    @RequestMapping(method = RequestMethod.DELETE, value = "/gallery/picture/{id}")
    public void deleteSSA(@PathVariable String id){
        ssaService.deleteSSA(id);
    }

    private String path="C://Users//Jakub//Desktop";
    //, headers = {"ContentType=image/jpeg"}
    //, headers = "content-type=multipart/*"

    //nie dziala
    @RequestMapping(method = RequestMethod.POST, value = "/gallery/upload")
    public String addImage(@RequestBody MultipartFile file) throws IOException{
        ssaService.addImage(file);
        return "dodano";
    }

    /*@RequestMapping(method = RequestMethod.POST, value = "/gallery/upload")
    public @ResponseBody String handleFileUpload(@RequestPart MultipartFile file){

    }*/
}
