package ru.thesid.files;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import ru.thesid.files.services.FileGluerService;

public class AppRunner implements CommandLineRunner {

    @Value("${app.srcFiles}")
    private String srcFiles;

    @Autowired
    private FileGluerService fileGluerServiceImpl;

    @Override
    public void run(String... args)  {
        System.out.println("Привет");

        fileGluerServiceImpl.glue(srcFiles);
    }
}
