package br.com.emersonluiz.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.emersonluiz.exception.BadRequestException;

public class DocumentFile {

    private static final String server_address = (System.getProperty("os.name").equals("Linux"))?("/tmp/"):("C:/tmp/");
    private static final String server_address_upload = server_address + "upload/";
    private static final String server_address_documentation = server_address + "documentation/";
    private static final Logger logger = LoggerFactory.getLogger(DocumentFile.class);

    public DocumentFile() {
        File f1 = new File(server_address_upload);
        File f2 = new File(server_address_documentation);

        if (!f1.exists()) {
            f1.mkdirs();
        }

        if (!f2.exists()) {
            f2.mkdirs();
        }
    }
    
    public String getAddressDocumentation() {
        return server_address_documentation;
    }

    public void upload(InputStream inputStream, String folders, String fileName) throws Exception {
        try {
            if (inputStream == null) {
                logger.error("InputStream Unknown");
                throw new BadRequestException("Upload Unknown");
            }

            File dir = new File(server_address_upload+folders);
            if (dir.exists()) {
                for (File file : dir.listFiles()) {
                    file.delete();
                }
            }
            dir.mkdirs();
            File file = new File(server_address_upload+folders+"/"+fileName);
            OutputStream out = new FileOutputStream(file);

            int read = 0;
            byte[] bytes = new byte[1024];
            while ((read = inputStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }

            inputStream.close();
            out.flush();
            out.close();

            logger.debug("File created Successfully");
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }

    }

    public InputStream download(String folders, String file) throws Exception {
        try {
            InputStream inputStream = new FileInputStream(server_address_upload+folders+"/"+file);
            logger.debug("Download successfull");
            return inputStream;
        } catch (FileNotFoundException fnf) {
            logger.error("File not found.");
            throw fnf;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    public InputStream download(String file) throws Exception {
        try {
            InputStream inputStream = new FileInputStream(file);
            logger.debug("Download successfull");
            return inputStream;
        } catch (FileNotFoundException fnf) {
            logger.error("File not found.");
            throw fnf;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    public void delete(String folder, String file) {
        try {
            File fileFound = new File(server_address_upload+folder+"/"+file);
            fileFound.delete();
            fileFound = new File(server_address_upload+folder);
            fileFound.delete();
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    public void copy(String folderFrom, String folderTo, String file) throws Exception {
        try {
            String adr = folderTo + File.separator + "img";
            File dir = new File(adr);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            File from = new File(server_address_upload + folderFrom + File.separator +file);
            File to = new File(folderTo + File.separator + "img" + File.separator + file);

            if (to.exists()) {
                to.delete();
            }

            InputStream in = new FileInputStream(from);
            OutputStream out = new FileOutputStream(to);

            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            in.close();
            out.close();
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }

    }

    public String createFolderDocumentation(String folder) {
        try {
            String adr = server_address_documentation+folder;
            File dir = new File(adr);
            if (dir.exists()) {
                dir.delete();
            }
            dir.mkdirs();
            return adr;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    public void createFileDocumentation(String folder, String fileName) throws Exception {
        try {
            File file = new File(folder + File.separator + fileName);
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    public void createFolderDocumentation(String directory, String folder) {
        try {
            String adr = directory + File.separator + folder;
            File dir = new File(adr);
            if (!dir.exists()) {
                dir.mkdirs();
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

}
