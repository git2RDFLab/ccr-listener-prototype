package de.leipzig.htwk.gitrdf.listener.experimantation;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Disabled
public class ZipInputFileExperimentationTest {

    @Test
    @SneakyThrows
    public void testHowZipInputStreamsWork() {

        String path = "D:\\HTWK_Festplatte_D\\Sem5\\Masterprojekt\\github-repos\\kubernetes.zip";

        try (ZipInputStream zipStream = new ZipInputStream(new BufferedInputStream(new FileInputStream(new File(path))))) {

            ZipEntry zipEntry;

            int count = 0;

            while ((zipEntry = zipStream.getNextEntry()) != null) {

                if (count >= 100) {
                    break;
                } else {
                    count++;
                    System.out.println(zipEntry.getName());
                }

                zipStream.closeEntry();
            }
        }

    }

}
