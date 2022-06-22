package ru.thesid.files.repo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.*;

@Component("glueFilesRepoImpl")
public class GlueFilesRepoImpl implements GlueFilesRepo {

    @Value("${app.srcFiles}")
    private String srcFiles;

    @Override
    public List<String> getPathes() {

        File root = new File(srcFiles);
        Queue<File> fileTree = new PriorityQueue<>();

        Collections.addAll(fileTree, root.listFiles());
        Map<String,String> filesTxt = new TreeMap<>();
        while (!fileTree.isEmpty())
        {
            File currentFile = fileTree.remove();
            if(currentFile.isDirectory()){
                Collections.addAll(fileTree, currentFile.listFiles());
            } else {
                if(currentFile.getName().toLowerCase().endsWith(".txt"))
                    filesTxt.put(currentFile.getName(), currentFile.getPath());
            }
        }

        return new ArrayList(filesTxt.values());
    }
}
