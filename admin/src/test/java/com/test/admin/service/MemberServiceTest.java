package com.test.admin.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.test.admin.dto.MemberDTO;
import com.test.admin.entity.Member;

@SpringBootTest
class MemberServiceTest {

	@Autowired
	private MemberService service;

	@Autowired
	private BCryptPasswordEncoder encoder;

	private Random random;

	public MemberServiceTest() {
		random = new Random();
	}
	
//	@Test
	void testCreateDummyList() {
		
		service.deleteAll();
		
		List<Member> randomMembers = getRandomMembers(15);

		randomMembers.stream().forEach(service::create);

		List<MemberDTO> members = service.getList();

		System.out.println(members);

		assertNotNull(members);
	}

	private List<Member> getRandomMembers(int count) {
		
		List<Member> result = new ArrayList<Member>();
		String defaultId = "test";
		String defaultEmail = "@vitamatch.com";
		LocalDateTime now = LocalDateTime.now();
		
		for(int i=1; i<=count; i++) {
			
			Member randomMember = Member.builder()
					.username(defaultId + i)
					.email(defaultId + i + defaultEmail)
					.password(encoder.encode("qwe123!@"))
					.name(getRandomKoreanName())
					.nickname(getRandomNickName())
					.dob(getRandomDob())
					.gender(getRandomGender())
					.telephone(getRandomPhoneNumber())
					.address(getRandomAddress())
					.status(1)
					.createTime(now)
					.updateTime(now)
					.build();
			result.add(randomMember);
		}
		
		return result;
	}

	private String getRandomKoreanName() {
		String[] firstNames = { "김", "이", "박", "최", "정", "강", "조", "윤", "장", "임" };
		String[] middleNames = { "민", "지", "현", "서", "도", "예", "윤", "수", "호" };
		String[] lastNames = { "수", "훈", "우", "연", "아", "윤", "진", "윤", "빈", "호" };

		return firstNames[random.nextInt(firstNames.length)] + middleNames[random.nextInt(middleNames.length)]
				+ lastNames[random.nextInt(lastNames.length)];
	}

	private String getRandomNickName() {
		int length = random.nextInt(7) + 4;

		StringBuilder builder = new StringBuilder();

		for (int i = 0; i < length; i++) {
			builder.append((char)('a' + random.nextInt(26)));
		}

		return builder.toString();
	}
	
	private Character getRandomGender() {
		char[] genders = {'m', 'f', 'o', 'u'};
		return genders[random.nextInt(4)];
	}

	private String getRandomPhoneNumber() {
		return  String.format("010-%04d-%04d", random.nextInt(10000), random.nextInt(10000));
	}

	private LocalDate getRandomDob() {
        long startDay = LocalDate.of(1970, Month.JANUARY, 1).toEpochDay();
        long endDay = LocalDate.of(2010, Month.DECEMBER, 31).toEpochDay();
        
        long randomDay = startDay + random.nextLong(endDay - startDay + 1);
		return LocalDate.ofEpochDay(randomDay);
	}

	private String getRandomAddress() {
		String[] areas = { "서울시", "부산시", "인천시", "대구시", "광주시" };
		String[] districts = { "중구", "동구", "남구", "서구", "북구" };
		String[] roads = { "동로", "서로", "남로", "북로" };

		return String.format("%s %s %s %d %s", areas[random.nextInt(areas.length)],
				districts[random.nextInt(districts.length)], roads[random.nextInt(roads.length)],
				(random.nextInt(300) + 1), getRandomAddressExtra());
	}

	public String getRandomAddressExtra() {
		String result = "";
		String[] extras = { "빌라", "아파트", "주택", "오피스텔" };
		String extraType = extras[random.nextInt(extras.length)];

		switch (extraType) {
		case "빌라":
			result = String.format("%s %d%02d동 %d%02d호", extraType, (random.nextInt(3) + 1), (random.nextInt(5) + 1),
					(random.nextInt(3) + 1), (random.nextInt(5) + 1));
			break;
		case "아파트":
			result = String.format("%s %d%02d동 %d%02d호", extraType, (random.nextInt(30) + 1), (random.nextInt(30) + 1),
					(random.nextInt(30) + 1), (random.nextInt(30) + 1));
			break;
		case "주택":
			result = String.format("%s %d%02d호", extraType, (random.nextInt(3) + 1), (random.nextInt(5) + 1),
					(random.nextInt(5) + 1), (random.nextInt(5) + 1));
			break;
		case "오피스텔":
			result = String.format("%s %s동 %d%02d호", extraType, (random.nextBoolean() ? "A동" : "B동"),
					(random.nextInt(30) + 1), (random.nextInt(30) + 1));
			break;
		}
		return result;
	}
}
