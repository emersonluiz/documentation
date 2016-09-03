package br.com.emersonluiz.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Zip {

    private static final Logger logger = LoggerFactory.getLogger(Zip.class);

    public ZipOutputStream generate(String addressIn, String addressOut) throws Exception {
        try {
            String internalDirectory = "";

            ZipOutputStream zipDestination = null;
            
            File file = new File(addressIn);

            if (!file.exists()) {
                throw new Exception("File was not found.");
            }
            zipDestination = new ZipOutputStream(new FileOutputStream(addressOut));

            if (file.isFile()) {
                zipFile(file, internalDirectory, zipDestination);
            } else {
                internalDirectory = file.getName();

                File[] files = file.listFiles();
                for (int i = 0; i < files.length; i++) {
                    zipFile(files[i], internalDirectory, zipDestination);
                }
            }
            zipDestination.close();

            return zipDestination;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    private void zipFile(File file, String internalDirectory, ZipOutputStream zipDestination) throws Exception {
        try {
            byte data[] = new byte[1024];

            if (file.isDirectory()) {
                File[] files = file.listFiles();
                for (int i = 0; i < files.length; i++) {
                    zipFile(files[i], internalDirectory + File.separator + file.getName(), zipDestination);
                }
                return;
            }
            FileInputStream fi = new FileInputStream(file.getAbsolutePath());
            ZipEntry entry = new ZipEntry(internalDirectory + File.separator + file.getName());
            zipDestination.putNextEntry(entry);
            int count;
            while ((count = fi.read(data)) > 0) {
                zipDestination.write(data, 0, count);
            }
            zipDestination.closeEntry();
            fi.close();
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }

    }
}
