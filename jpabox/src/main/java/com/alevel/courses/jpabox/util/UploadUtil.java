package com.alevel.courses.jpabox.util;


import java.io.File;

public class UploadUtil {

    public static String getPath(Folder folder) {
        return new File("")
                .getAbsoluteFile() + (File.separatorChar + "jpabox" + File.separatorChar + folder.getFolder());
    }


    public enum Folder {

        CSV("csv");

        Folder(String folder) {
            this.folder = folder;
        }

        private final String folder;

        public String getFolder() {
            return folder;
        }
    }
}