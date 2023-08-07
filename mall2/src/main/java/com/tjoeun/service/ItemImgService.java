package com.tjoeun.service;

import java.io.IOException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import com.tjoeun.entity.ItemImg;
import com.tjoeun.repository.ItemImgRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemImgService {
	
	@Value("${itmImgLocation}")
	private String itmImgLocation;
	
  private final ItemImgRepository itemImgRepository;
  
  private final FileService fileService;
  
  public void saveItemImg(ItemImg itemImg, MultipartFile itemImgFile) throws Exception {
  	
  	// MultipartFile itemImgFile 에 원래 파일 이름 정보가 있음
  	// 실제 이미지를 다운로드할 때는 원래 파일이름으로 다운로드함  	
  	String oriImgName = itemImgFile.getOriginalFilename();
  	String imgName = "";
  	String imgUrl  = "";
  	
    // 파일 업로드 : oriImgName 에 내용이 있는지 확인함
    // thymeleaf의 StringUtils 를 사용해서 원래 경로 값이 없는지(빈문자열인지) 확인함
  	if(!StringUtils.isEmpty(oriImgName)) {
  		
      // oriImgName이 있는 경우       ┌ UUID - 등록할 이름을 받아옴
    	// fileService.uploadFile() 을 호출해서 실제 이미지 이름을 받아옴
    	// itemImgLocation : application.properties 파일에 등록한 이미지를 저장할 실제 local 경로
    	// 이미지 파일 정보가 있는 itemImgFile 에서 이 정보를 byte 배열로 가져옴  		
  		imgName = fileService.uploadFile(itmImgLocation, oriImgName, itemImgFile.getBytes());
      // 아래와 같이 설정하면 실제 업로드한 이름으로 접근할 수 있음
  		imgUrl = "/images/item/" + imgName;
  		
  	}
  	
    // 이미지를 변경하는 경우, 상품 이미지 정보 저장
  	itemImg.updateImg(oriImgName, imgName, imgUrl);
  	
  	// 실제 DB 에 저장함
  	itemImgRepository.save(itemImg);
  	
  }
  
  
  
}





