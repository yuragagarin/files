package ru.thesid.files.services;

import org.springframework.stereotype.Component;
import ru.thesid.files.FileGluer;
import ru.thesid.files.repo.GlueFilesRepo;

import java.util.*;

@Component("fileGluerServiceImpl")
public class FileGluerServiceImpl implements FileGluerService {

    private final GlueFilesRepo repo;
    private final FileGluer fileGluerImpl;

    public FileGluerServiceImpl(GlueFilesRepo repo, FileGluer gluer) {
        this.repo = repo;
        this.fileGluerImpl = gluer;
    }

    @Override
    public void glue(String rootDir) {

        List<String> pathes = repo.getPathes();
        fileGluerImpl.glue(pathes);
    }
}
