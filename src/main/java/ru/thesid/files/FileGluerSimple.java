package ru.thesid.files;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Slf4j
@Component("fileGluerSimple")
public class FileGluerSimple implements FileGluer {

    @Value("${app.destFile}")
    private String destFile;

    @Override
    public void glue(List<String> pathes) {

        clearFile();
        for(String path: pathes) {
            List<String> lines = getLinesFromFile(path);
            writeLinesToFile(lines);
        }
    }

    private void clearFile() {
        try {
            PrintWriter pw = new PrintWriter(destFile);
            pw.close();
        } catch (Exception e) {
            log.error("Clear file error!");
        }
    }

    private List<String> getLinesFromFile(String path) {

        var res = new ArrayList<String>();
        try (Stream<String> lines = Files.lines(Paths.get(path))) {
            lines.forEachOrdered(line -> res.add(line));
        } catch (Exception e) {
            log.error("Read file error!");
        }
        return res;
    }

    private void writeLinesToFile(List<String> lines) {
        try {
            FileWriter fstream = new FileWriter(destFile, true);
            BufferedWriter out = new BufferedWriter(fstream);

            for (String line : lines) {
                out.write(line + "\n");
            }

            out.close();
            fstream.close();
        } catch (Exception e) {
            log.error("Write file error!");
        }
    }

}
