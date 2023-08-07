package com.tjoeun.service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FileService {
  
	public String uploadFile(String uploadPath, String originalFileName, byte[] fileData) throws Exception {
		
		UUID uuid = UUID.randomUUID();
		String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
		String savedFileName = uuid.toString() + extension;
		// 실제 물리적인 server 컴퓨터 내의 경로
		String fileUploadFulUrl  = uploadPath + "/" + savedFileName;
		
		// C:/mall/item_images/43sfd23rs523r.jpg
		FileOutputStream fos = new FileOutputStream(fileUploadFulUrl);
		fos.write(fileData);
		fos.close();		
		
		// UUID 로 만든 업로드한 파일 이름을 반환함
		return savedFileName;
	}
	
	// 이미지 삭제하기
	public void deleteFile(String filePath) {
		File deleteFile = new File(filePath);
		if(deleteFile.exists()) {
			deleteFile.delete();
			log.info(">>>>>>>>>>>> 파일 삭제 완료");
		}else {
			log.info(">>>>>>>>>>>> 삭제할 파일이 없음");
		}
	}
	
	
	
}
