package com.mss.error;

import com.azure.storage.blob.nio.AzureFileSystem;
import com.azure.storage.common.StorageSharedKeyCredential;
import java.net.URI;
import java.nio.file.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LogTest {
    public static void main(String[] args) {
System.out.println("jar has been executed");
    }

    public void callCreateDirectory(String dirName){
        try {
            Map<String, Object> config1 = new HashMap<>();
            String stores = "logs,audits";
            StorageSharedKeyCredential credential = new StorageSharedKeyCredential("mssalesbizpoc", "ZmVNk1vvclscAxD8Dt0HaAHi/v/py8ggWsjiAfV84Yz8YdoedZ1kGLw0O9vVobiL6kDROjf3BjiXp2wYT8Ue8w==");
            config1.put(AzureFileSystem.AZURE_STORAGE_SHARED_KEY_CREDENTIAL, credential);
            config1.put(AzureFileSystem.AZURE_STORAGE_FILE_STORES, stores);
            System.out.println(Arrays.asList(config1));
            FileSystem myFs=  FileSystems.newFileSystem(new URI("azb://?endpoint=https://mssalesbizpoc.blob.core.windows.net/logs"), config1);
           /* for (FileStore store :
                    myFs.getFileStores()) {
                System.out.println(store.toString());
            }
           */
            Path path =(myFs.getPath("Audits/"+dirName));
            Files.createDirectory(path);
            for (Path p : Files.newDirectoryStream(path)) {
                System.out.println("dirs"+p.toString());
            }
            System.out.println("Directory created successfully!");
        }catch (Exception e){
            System.out.println("....."+e);


        }
    }
}
