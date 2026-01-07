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

        // 🚨 중요: 데이터가 이미 있으면 또 만들지 않기 (중복 방지)
        if (propertyRepository.count() > 0) {
            System.out.println("ℹ️ 이미 데이터가 존재하여 초기화를 건너뜁니다.");
            return;
        }

        System.out.println("🚀 테스트 데이터 생성을 시작합니다...");

        // 1. 집주인(User) 생성
        User owner = new User();
        owner.setName("집주인 김철수");
        owner.setEmail("owner@test.com");
        owner.setPhoneNumber("010-1234-5678");
        userRepository.save(owner);

        // 1-1. 매수자(Buyer) 생성
        User buyer = new User();
        buyer.setName("내집마련 최씨");
        buyer.setEmail("buyer@test.com");
        buyer.setPhoneNumber("010-9999-8888");
        userRepository.save(buyer);

        // 2. 중개사(Agent) 생성
        Agent agent = new Agent();
        agent.setName("친절한 박사장");
        agent.setOfficeName("대박부동산");
        agent.setRegion("서울 마포구");
        agentRepository.save(agent);

        // ==========================================
        // 3. 매물(Property) 등록 - 지도 테스트용 3개
        // ==========================================

        // 매물 1: 마포구 공덕동 (아까 작성하신 것)
        Property p1 = new Property();
        p1.setSeller(owner);
        p1.setAddress("서울 마포구 공덕동 래미안 301호");
        p1.setTradeType(TradeType.JEONSE);
        p1.setDeposit(500000000L); // 5억
        p1.setDescription("공덕역 5분 거리, 남향 채광 좋음");
        // 👇 좌표 추가!
        p1.setLatitude(37.544569);
        p1.setLongitude(126.950940);
        propertyRepository.save(p1);

        // 매물 2: 용산구 한남더힐 (부자 동네 테스트)
        Property p2 = new Property();
        p2.setSeller(owner);
        p2.setAddress("서울 용산구 한남동 한남더힐");
        p2.setTradeType(TradeType.SALE);
        p2.setDeposit(8000000000L); // 80억
        p2.setDescription("한강뷰, 최고급 인테리어");
        // 👇 좌표 추가!
        p2.setLatitude(37.536043);
        p2.setLongitude(127.011352);
        propertyRepository.save(p2);

        // 매물 3: 강남구 역삼동 (강남 테스트)
        Property p3 = new Property();
        p3.setSeller(owner);
        p3.setAddress("서울 강남구 역삼동 아이파크");
        p3.setTradeType(TradeType.JEONSE);
        p3.setDeposit(1200000000L); // 12억
        p3.setDescription("강남역 도보 10분, 풀옵션");
        // 👇 좌표 추가!
        p3.setLatitude(37.497952);
        p3.setLongitude(127.027619);
        propertyRepository.save(p3);


        // 4. 입찰(Proposal) 하기 (1번 매물에 대해)
        Proposal proposal = new Proposal();
        proposal.setAgent(agent);
        proposal.setProperty(p1); // 공덕동 매물에 입찰
        proposal.setProposedFee(1000000L); // 수수료 100만원 제안
        proposal.setMessage("저희는 입주 청소도 무료로 해드립니다! 맡겨주세요.");
        proposalRepository.save(proposal);

        System.out.println("=========================================");
        System.out.println("✅ 테스트 데이터(좌표 포함) 삽입 완료!");
        System.out.println("=========================================");
    }
}*/
