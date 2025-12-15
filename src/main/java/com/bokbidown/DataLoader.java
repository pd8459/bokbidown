/*
package com.bokbidown;

import com.bokbidown.domain.*;
import com.bokbidown.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final AgentRepository agentRepository;
    private final PropertyRepository propertyRepository;
    private final ProposalRepository proposalRepository;

    @Override
    public void run(String... args) throws Exception {
        // 1. 집주인(User) 생성
        User user = new User();
        user.setName("집주인 김철수");
        user.setEmail("owner@test.com");
        user.setPhoneNumber("010-1234-5678");
        userRepository.save(user);

        // 2. 중개사(Agent) 생성
        Agent agent = new Agent();
        agent.setName("친절한 박사장");
        agent.setOfficeName("대박부동산");
        agent.setRegion("서울 마포구");
        agentRepository.save(agent);

        // 3. 매물(Property) 등록 (전세)
        Property property = new Property();
        property.setSeller(user); // 집주인 연결
        property.setAddress("서울 마포구 공덕동 래미안 301호");
        property.setTradeType(TradeType.JEONSE); // 전세
        property.setDeposit(500000000L); // 5억
        property.setDescription("남향이고 채광 좋아요. 입주 협의 가능.");
        propertyRepository.save(property);

        // 4. 입찰(Proposal) 하기
        Proposal proposal = new Proposal();
        proposal.setAgent(agent); // 중개사 연결
        proposal.setProperty(property); // 매물 연결
        proposal.setProposedFee(1000000L); // 수수료 100만원 제안
        proposal.setMessage("저희는 입주 청소도 무료로 해드립니다! 맡겨주세요.");
        proposalRepository.save(proposal);

        // DataLoader.java 안의 run 메소드 앞부분에 추가

// 0. 매수자(Buyer) 생성 - 추가!
        User buyer = new User();
        buyer.setName("내집마련 최씨");
        buyer.setEmail("buyer@test.com");
        buyer.setPhoneNumber("010-9999-8888");
        userRepository.save(buyer); // ID는 아마 2번이 될 겁니다 (1번은 집주인)

        System.out.println("=========================================");
        System.out.println("테스트 데이터 삽입 완료! DB를 확인해보세요.");
        System.out.println("=========================================");
    }
}
*/
