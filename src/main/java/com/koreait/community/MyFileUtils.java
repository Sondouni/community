package com.koreait.community;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Component
public class MyFileUtils {
    //폴더 만들기
    public void makeFolders(String path) {
        File folder = new File(path);
        if (!folder.exists()) {//폴더가 있으면 그냥 지나가고 폴더가 없으면 하나 만들어준다
            folder.mkdirs();
        }
    }

    //폴더 삭제
    public void delFolderFiles(String path, boolean sureToDelete) {
        File file = new File(path);
        if (file.exists() && file.isDirectory()) {
            File[] filelist = file.listFiles();

            for (File f : filelist) {
                if (f.isDirectory()) {//isDirectory : 이 파일이 폴더인지 물어봄
                    delFolderFiles(f.getPath(), true);
                } else {
                    f.delete();
                }
            }

        }
        if (sureToDelete) {
            file.delete();
        }
    }

    //랜덤 파일명 만들기
    public String getRandomFileNm() {
        return UUID.randomUUID().toString();
    }

    //랜덤 파일명 만들기
    public String getRandomFileNm(String fileNm) {
        return getRandomFileNm()+getExt(fileNm);
    }

    //확장자 구하기
    public String getExt(String fileNm) {
        int lastIdx = fileNm.lastIndexOf(".");
        return fileNm.substring(lastIdx);
    }

    //파일 저장후, 지정된 랜덤파일명 리턴
    public String  saveFile(String path, MultipartFile mf) {
        makeFolders(path);
        String randomFileNm = getRandomFileNm(mf.getOriginalFilename());

        File targetFile = new File(path,randomFileNm);
        try {
            mf.transferTo(targetFile);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return randomFileNm;
    }

    //파일삭제
    public void delFile(String path){
        File file = new File(path);
        if(file.exists()){
            file.delete();
        }
    }
}
