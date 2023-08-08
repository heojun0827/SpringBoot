package com.tjoeun.service;

import java.io.IOException;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
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
	
	@Value("${itemImgLocation}")
	private String itemImgLocation;
	
  private final ItemImgRepository itemImgRepository;
  
  private final FileService fileService;
  
  // 이미지 저장하기
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
  		imgName = fileService.uploadFile(itemImgLocation, oriImgName, itemImgFile.getBytes());
      // 아래와 같이 설정하면 실제 업로드한 이름으로 접근할 수 있음
  		imgUrl = "/images/item/" + imgName;
  		
  	}
  	
    // 이미지를 변경하는 경우, 상품 이미지 정보 저장
  	itemImg.updateItemImg(oriImgName, imgName, imgUrl);
  	
  	// 실제 DB 에 저장함
  	itemImgRepository.save(itemImg);
  	
  }
  
  // 이미지 수정하기
  public void updateItemImg(Long itemImgId, MultipartFile itemImgFile) throws Exception{
  	
  	// 이미지가 있는지 확인하기
  	if(!itemImgFile.isEmpty()) {
  		
  		// DB 에서 itemImgId에 해당 하는 이미지 가져오기
  		ItemImg savedItemImg = itemImgRepository.findById(itemImgId)
  				                               .orElseThrow(EntityNotFoundException::new);
  		
      // 기존 이미지 파일 삭제 - imgName 이 있는 것들만 삭제함
      // itemImgLocation <-- C:/mall/item_images (application.properties)
  		if(!StringUtils.isEmpty(savedItemImg.getImgName())) {
  			// C:\mall\item_images
  			fileService.deleteFile(itemImgLocation + "/" + savedItemImg.getImgName());
  		}
  		
  		// 수정된 이미지 저장하기
  		// DB 에는 파일이 원래 이름(oriImgName)으로 저장됨
  		// oriImgName 은 MultipartFile itemImgFile 에 등록되어 있음
  		// MultipartFile itemImgFile.getOriginalFileName()
  		String oriImgName = itemImgFile.getOriginalFilename();
  		
      // project(server) 에 저장된 이름에는 UUID 가 적용되어 있음
  		//   ㄴ 외부에서 접근할 때 경로로 사용할 논리적 경로로 지정함
  		String imgName = fileService.uploadFile(itemImgLocation, oriImgName, itemImgFile.getBytes());
  		// 현재 project 안에서의 logical path (논리상의 경로)
  		String imgUrl = "/images/item/" + imgName;
  		
  		// DB 에 반영하기
  		savedItemImg.updateItemImg(oriImgName, imgName, imgUrl);
  		
  		
  		
  	}
  	
  }
  
}





