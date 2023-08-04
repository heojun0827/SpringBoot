package com.tjoeun.test;

import java.util.Optional;

public class OptionalTest {
	
	public static void main(String[] args) {
		
		String str1 = "spring";
		
		Optional<String> opt1 = Optional.of(str1);
		Optional<String> opt2 = Optional.of("아카데미");
		
		// java.lang.NullPointerException 발생
		// Optional<String> opt3 = Optional.of(null);
		Optional<String> opt4 = Optional.ofNullable(null);
		
		// 권장하지 않음
		Optional<String> opt5 = null;
		Optional<String> opt6 = Optional.<String>empty();
		Optional<String> opt7 = Optional.empty();
						
		System.out.println("opt1 : " + opt1);
		System.out.println("opt2 : " + opt2);
		// System.out.println("opt3 : " + opt3);
		System.out.println("opt4 : " + opt4);
		System.out.println("opt5 : " + opt5);
		System.out.println("opt6 : " + opt6);
		System.out.println("opt7 : " + opt7);
		
		// 길이가 0 인 배열 <-- item 이 하나도 없는 배열
		Object[] objArr1 = new Object[0];
		
		// 권장하지 않음
		Object[] objArr2 = null;
		
		// 권장함
		String str2 = "";
	  // 권장하지 않음
		String str3 = null;
		
		Optional<String> opt8 = Optional.of("아이티");
		String str4 = opt8.get();
		
		System.out.println("str4 : " + str4);
		System.out.println("opt8 : " + opt8);
		
		String str5 = opt8.orElse("");
		System.out.println("str5 : " + str5);
		
    // Optional 에 of() 으로 null 을 저장하면 
		// java.lang.NullPointerException 예외 발생함
		// Optional 에 null 을 저장하려면 ofNullable(null) 으로 해야 함
		Optional<String> opt9 = Optional.ofNullable(null);
		
		// orElseGet()
		// opt9 에 들어있는 값이 null 이면 
		// () -> new String("테스트") 의 반환값인 "테스트"를 반환함 
		String str7 = opt9.orElseGet(() -> new String("테스트"));
		System.out.println("str7 : " + str7);
		
		/*
		String str10 = opt9.orElseThrow(() -> new NullPointerException());
		System.out.println("str10 : " + str10);
		*/
		
		// orElse()
		// String str6 = opt9.get();
		// orElse() <-- null 인 경우 argument 로 넣은 값을 반환함
		String str6 = opt9.orElse("null일 때 반환하는 문자열");
		System.out.println("str6 : " + str6);
		
		opt9 = Optional.ofNullable("스프링부트");
		// orElse() <-- null 이 아닌 경우 - 할당된 문자열을 반환함
		str6 = opt9.orElse("null일 때 반환하는 문자열");
		System.out.println("str6 : " + str6);
		
		// new String("테스트");
		str7 = opt9.orElseGet(() -> new String("테스트"));
		str7 = opt9.orElseGet(() -> new String(""));
		str7 = opt9.orElseGet(String::new);
		System.out.println("str7 : " + str7);
    
		/*		
		String str8 = "스프링";
		str8 = "스프링2";
		String str8_1 = "스프링";
		String str9 = new String("테스트");
		String str9_1 = new String("테스트2");
		
		str9 = new String("테스트3");

		int number1 = 10;
		int number2 = 20;
		*/		
		
		// orElseThrow() : null 이 있는 경우 지정한 예외를 발생시킴
		String str10 = opt9.orElseThrow(() -> new NullPointerException());
		str10 = opt9.orElseThrow(NullPointerException::new);
		System.out.println("str10 : " + str10);
		
		
		String str11 = null;		
		// Optional.ofNullable(str11) 이 null 이 아닌 경우에만 출력하기
		if(Optional.ofNullable(str11).isPresent()) {
			System.out.println("str11 : " + str11);
		}
		
		str11 = "더조은아이티";		
		// Optional.ofNullable(str11) 이 null 이 아닌 경우에만 출력하기
		if(Optional.ofNullable(str11).isPresent()) {
			System.out.println("str11 : " + str11);
		}
		// 위의 code 를 간략하게 작성함
		Optional.ofNullable(str11).ifPresent(System.out::println);
		
		System.out.println("---------------------------------------");
		
	  Optional<String>  optStr1 = Optional.of("더조은");
	  Optional<Integer> optInt1 = optStr1.map(name -> name.length());
	  Optional<Integer> optInt2 = optStr1.map(String::length);
		
	  System.out.println("optStr1 : " + optStr1);
		System.out.println("optStr1 : " + optStr1.get());
		System.out.println("optInt1 : " + optInt1.get());
		System.out.println("optInt2 : " + optInt2.get());
	  
		
		int number1 = Optional.of("1234567")
				                  .filter(strNum -> strNum.length() > 0)
				                  .map(Integer::parseInt)
				                  .get();
    		
		System.out.println("number1 : " + number1);
		
		int number2 = Optional.of("")
                  				.filter(strNum -> strNum.length() > 0)
                  				.map(Integer::parseInt)
                  				.orElse(-1);
		
		System.out.println("number2 : " + number2);
		
		Optional.of("987")
		        .map(Integer::parseInt)
		        .ifPresent(System.out::print);
		
		Optional.of("123465")
		        .map(Integer::parseInt)
		        .ifPresent(num -> System.out.printf("num : %d%n", num));
	
		/*
		java.lang.NumberFormatException: For input string: ""
		Optional.of("")
        		.map(Integer::parseInt)
        		.ifPresent(num -> System.out.printf("num : %s%n", num));
		
		*/
		
	}

}





