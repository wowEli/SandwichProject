package controller;

import java.util.ArrayList;

import model.SandDAO;
import model.SandVO;
import view.SandView;

public class SandController {
	private SandDAO model;
	private SandView view;
	ArrayList<SandVO> result;
	private int count; // 야채 소스 반복 횟수 저장할 변수

	public SandController(){ // 기본생성자
		model=new SandDAO();
		view=new SandView();

	}
	public SandVO addIngredient(ArrayList<SandVO> datas) { // 관리자모드 재료 추가
        view.nameView();// 이름을 입력해주세요
        String name=view.get_String(datas); // 재료 이름 입력받기
        view.stockView();// 수량을 입력해주세요
        int stock=view.get_int(); // 재고 수 입력받기
        view.priceView(); // 가격을 입력해주세요
        int price=view.get_PriceInt(); // 가격 입력받기

        SandVO advo2=new SandVO(); // 빈객체 만들어서
        advo2.setName(name);
        advo2.setPrice(price);
        advo2.setStock(stock); // 위에 받은 값들을 넣고
        return advo2; // 새로운 객체를 리턴
    }

	public void startApp() {
		while(true) {
			result = new ArrayList<SandVO>(); // 장바구니 생성
			view.startView();
			
			if(view.action==1) { // 주문하기
				if(model.soldOut()) {
					view.soldOut();
					view.endView();
					break;
				}
				
				
				view.breadChoice(); // 빵을 선택해주세요
				SandVO vo1=new SandVO(); // 빈 객체 만들기
				ArrayList<SandVO> datas1=model.breadAll(vo1);
				view.menuView(datas1); // 빵배열 받아서 인자로 넣고 메뉴 출력
				
				int num1=view.getAction(model.breadAll(vo1)); // 빵배열원소의 pk값을 num의 저장

				vo1.setNum(num1); // 빈객체에 pk를 넣어주고
				SandVO vo2=model.selectOne(vo1); // 해당 pk값 넣어서 객체 찾기

				result.add(vo2); // 사용자가 고른 객체 장바구니의 저장
				view.resultView(result); // 사용자가 고른 재료 출력
				vo2.setCnt(vo2.getCnt()+1); // 영수증에 출력하기 위한 수량

				vo2.setStock(vo2.getStock()-1); // 재고 차감
				// 빵 선택 종료 

				while(true) { // 사용자가 얼마나 다시 고를 지 모름
					view.cheeseChoice(); // 치즈를 선택해주세요
					SandVO vo3=new SandVO(); 
					ArrayList<SandVO> datas3=model.cheeseAll(vo3); // 치즈 배열 리턴 받고
					view.menuView(datas3); // 치즈메뉴 화면 출력

					int num2=view.getAction(model.cheeseAll(vo3)); // 치즈 배열 원소의 pk를 저장
					vo3.setNum(num2); // 빈 객체에 pk를 넣어주고
					SandVO vo4=model.selectOne(vo3);  // 해당 pk값을 넣어서 객체 찾기
					
					boolean flag=view.yesNo(); // 혹시 다시 선택할래?? 1.yes 2. no
					// 우리가 뒤로가기 하고싶었는데 goto는 올드해서 공부하는 의미가 커지지않아서
					// 리눅스나 파이썬 같은 다른 CUI들이 채택해서 쓰는 에스 or 노 를 사용하기로 했다
					if(flag) { // 다시 선택할래! 이러면 다시 위로가서 선택
						continue;
					}
					else { // 다시 선택 안할래! 
						result.add(vo4); // 사용자가 고른 객체 장바구니의 저장
						vo4.setStock(vo4.getStock()-1);  // 재고차감
						view.resultView(result); // 사용자가 고른 재료 출력
						vo4.setCnt(vo4.getCnt()+1); // 영수증에 출력하기 위한 수량
						break; // 정지
					}
				}
				// 치즈 선택 종료

				view.meetChoice(); // 위와 과정 동일
				SandVO vo5=new SandVO(); 
				ArrayList<SandVO> datas5=model.meetAll(vo5);
				view.menuView(datas5); 

				int num3=view.getAction(model.meetAll(vo5)); 
				vo5.setNum(num3); 
				SandVO vo6=model.selectOne(vo5); 
				result.add(vo6);
				view.resultView(result); 
				vo6.setCnt(vo6.getCnt()+1);

				vo6.setStock(vo6.getStock()-1); 
				// 고기 선택 종료
				
				while(true) { // 위와 과정 동일한데 야채는 중복 선택이 가능
					view.vegetableChoice(); 
					SandVO vo7=new SandVO(); 
					ArrayList<SandVO> datas7=model.vegetableAll(vo7);
					view.menuView(datas7); 

					int num4=view.getAction(model.vegetableAll(vo7));
					vo7.setNum(num4);
					SandVO vo8=model.selectOne(vo7); 
					
					boolean flag1=true;
					for(int i=0; i<result.size(); i++) { // 장바구니의 사용자가 고른 야채가 있니?
						if(vo8.getNum()==result.get(i).getNum()) {
							flag1=false;
						}
					}
					if(flag1) { // 장바구니의 이미 고른 야채가 없다면
						result.add(vo8); // 저장해줘
					}
					// 장바구니의 이미 같은 야채가 있다면 저장은 안하고
					vo8.setCnt(vo8.getCnt()+1); // 고른 수량 +1
					vo8.setStock(vo8.getStock()-1);  // 재고 차감
					
					this.count++; // 한 번 선택할 때마다 +1
					if(this.count==datas7.size()) { // 반복횟수가 배열에 사이즈만큼 돌면 즉시 종료(야채 1000개 선택 방지)
						view.nextMenu(); // 횟수가 초과되어 다음 메뉴로 넘어갑니다
						view.resultView(result); // 사용자가 고른 재료 출력
						this.count=0;
						break;
					} 
					
					boolean flag2=view.addMore(); // 더 추가 할래?
					if(flag2) { // 다시 위로가서 선택
						continue;
					}
					else { // 종료
						view.resultView(result); // 사용자가 고른 재료 출력
						this.count=0;
						break;
					}

				}
				// 야채 선택 종료
				
				while(true) { // 야채 과정과 동일
					view.sauceChoice(); 
					SandVO vo9=new SandVO(); 
					ArrayList<SandVO> datas9=model.sauceAll(vo9);
					view.menuView(datas9);

					int num5=view.getAction(model.sauceAll(vo9));
					vo9.setNum(num5); 
					SandVO vo10=model.selectOne(vo9); 
					boolean flag1=true;
					for(int i=0; i<result.size(); i++) {
						if(vo10.getNum()==result.get(i).getNum()) {
							flag1=false;
						}
						
					}
					if(flag1) {
						result.add(vo10);
						vo10.setCnt(vo10.getCnt()+1);
						vo10.setStock(vo10.getStock()-1); 
					}else {
						vo10.setCnt(vo10.getCnt()+1);
						vo10.setStock(vo10.getStock()-1);
					}
					
					this.count++;
					if(this.count==datas9.size()) { // 반복횟수가 배열에 사이즈만큼 돌면 즉시 종료(야채 1000개 선택 방지)
						view.resultView(result);
						this.count=0;
						break;
					}
					
					boolean flag2=view.addMore();
					if(flag2) {
						continue;
					}
					else {
						view.resultView(result); // 사용자가 고른 재료 출력
						this.count=0;
						
						break;
					}
				}
				// 소스 선택 종료
				
				// 결제 구간
				// 결제 구간
				int sum=model.sum(result);
				
				view.payPriceView(sum);	// 결제 금액 얼마입니다
				while(true) {
					view.payGetMoney(); // 사용자가 넣을 돈을 입력 받음
					int cash=model.pay(sum,view.money); // 잔돈 or 거스름 돈을 리턴
					
					if(cash<=0) { // 만약 잔돈이면
						view.receipt(result, sum); // 결제 성공으로 영수증 출력
						result.clear(); // 결제 끝나면 실행
						view.money = 0; // 초기화
						model.cntclear(); 
						break;
					}
					else {
						view.payFailView(cash); // 결제 실패 시 추가 금액 입력받기
					}
				}
				
			}
			else if(view.action==2) {
				view.endView();
				break;
				// 종료하기
			}
			else if(view.action==828253) {
				// 관리자모드
				while(true) {
					view.adminView(); // 관리자 모드입니다
					view.adminMenuView(model.admin_mu); // 관리자 메뉴들 출력
					view.menuAction(model.admin_mu); // 사용자에게 입력받기
					if(view.action==1) { // 재료추가
						view.addIngredientView(); // 재료추가 메뉴입니다
						view.adminMenuView(model.mu); // 빵 치즈 고기 야채 소스 들중
						view.insertIngredient(); // 추가할 재료를 골라주세요
						view.menuAction(model.mu); // 사용자에게 어느 배열에 저장할 지 선택받기
						SandVO vo =new SandVO();
						if(view.action==1) {
							// 빵배열에 저장
							model.breadAll(this.addIngredient(model.breadAll(vo)));
							view.successView();
						}
						else if(view.action==2) {
							// 치즈 배열에 저장
							model.cheeseAll(this.addIngredient(model.cheeseAll(vo)));
							view.successView();
						}
						else if(view.action==3) {
							// 고기 배열에 저장
							model.meetAll(this.addIngredient(model.meetAll(vo)));
							view.successView();
						}
						else if(view.action==4) {
							// 야채 배열에 저장
							model.vegetableAll(this.addIngredient(model.vegetableAll(vo)));
							view.successView();
						}
						else if(view.action==5) {
							// 소스 배열에 저장
							model.sauceAll(this.addIngredient(model.sauceAll(vo)));
							view.successView();
						}

					}
					else if(view.action==2) { // 재료 삭제
						view.deleteIngredientView(); // 재료 삭제 메뉴입니다
						view.adminMenuView(model.mu); // 어느 배열에서 삭제할래?
						view.menuAction(model.mu); // 입력받기
						SandVO advo1 = new SandVO();

						if(view.action==1) { // 빵 배열에서 삭제
							ArrayList<SandVO> bread = model.breadAll(advo1); // 빵배열을 불러와서
							view.menuAllView(bread); // 빵 배열의 원소들을 출력(품절된 메뉴도 출력됨)
							int num=view.deleteAction(bread); // 사용자에게 입력받고(품절된 메뉴도 선택됨) 인덱스 번호를 리턴
							bread.remove(num); // 인덱스 번호를 넣어 그 객체 삭제
							view.successView(); // 성공하였습니다
						}
						else if(view.action==2) { // 치즈 배열에서 삭제
							ArrayList<SandVO> cheese = model.cheeseAll(advo1);
							view.menuAllView(cheese);
							int num=view.deleteAction(cheese);
							cheese.remove(num);
							view.successView();
						}
						else if(view.action==3) { // 고기 배열에서 삭제
							ArrayList<SandVO> meet = model.meetAll(advo1);
							view.menuAllView(meet);
							int num=view.deleteAction(meet);
							meet.remove(num);
							view.successView();
						}
						else if(view.action==4) { // 야채 배열에서 삭제
							ArrayList<SandVO> vegetable = model.vegetableAll(advo1);
							view.menuAllView(vegetable);
							int num=view.deleteAction(vegetable);
							vegetable.remove(num);
							view.successView();
						}
						else if(view.action==5) { // 소스 배열에서 삭제
							ArrayList<SandVO> sauce = model.sauceAll(advo1);
							view.menuAllView(sauce);
							int num=view.deleteAction(sauce);
							sauce.remove(num);
							view.successView();
						}						
					}

					else if(view.action==3) { // 재료재고현황
						view.nowStockView(); // 재료 재고 현황입니다
						SandVO advo1 = new SandVO(); // 빈객체 만들어서
						view.stockView(model.breadAll(advo1)); // 빵배열 불러온 후 화면에 객체들을 출력
						view.stockView(model.cheeseAll(advo1)); // 치즈 배열 출력
						view.stockView(model.meetAll(advo1)); // 고기 배열 출력
						view.stockView(model.vegetableAll(advo1)); // 야채 배열 출력
						view.stockView(model.sauceAll(advo1)); // 소스 배열 출력
					}

					else if(view.action==4) { // 재료재고추가
						view.addStockView(); // 재료 재고 추가 메뉴 입니다
						view.adminMenuView(model.mu); // 무슨 배열안에 있는 재료의 재고를 추가할래?
						view.menuAction(model.mu); // 입력받기

						SandVO advo1 = new SandVO(); 
						if(view.action==1) { // 빵 배열 안에 있는 재료 재고 추가
							ArrayList<SandVO> bread = model.breadAll(advo1); // 빵 배열 리턴
							SandVO advo2 = new SandVO();
							view.stockView(bread); // 빵 배열 안에 있는 빵이름들 출력
							int num1=view.stockAction(bread); // 사용자가 고른 객체의 pk값을 리턴
							advo2.setNum(num1); // 빈 객체에 pk값을 넣기
							SandVO advo3 = model.selectOne(advo2); // 같은 pk값을 갖고있는 객체를 찾아서 리턴
							view.stockView(); // 추가할 재고의 수량을 입력해주세요
							int num2=view.get_int(); // 재고 수 입력 받기
							advo3.setStock(advo3.getStock()+num2); // 입력받은 값만큼 추가
							view.successView(); // 성공하였습니다
						}
						else if(view.action==2) { // 치즈 배열 안에 있는 재료 재고 추가
							ArrayList<SandVO>cheese = model.cheeseAll(advo1);
							SandVO advo5 = new SandVO();
							view.stockView(cheese);
							int num3=view.stockAction(cheese);
							advo5.setNum(num3);
							SandVO advo6 = model.selectOne(advo5);
							view.stockView();
							int num4=view.get_int();
							advo6.setStock(advo6.getStock()+num4);
							view.successView();
						}
						else if(view.action==3) { // 고기 배열 안에 있는 재료 재고 추가
							ArrayList<SandVO>meet = model.meetAll(advo1);
							SandVO advo8 = new SandVO();
							view.stockView(meet);
							int num5=view.stockAction(meet);
							advo8.setNum(num5);
							SandVO advo9 = model.selectOne(advo8);
							view.stockView();
							int num6=view.get_int();
							advo9.setStock(advo9.getStock()+num6);
							view.successView();
						}
						else if(view.action==4) { // 야채 배열 안에 있는 재료 재고 추가
							ArrayList<SandVO>vegetable = model.vegetableAll(advo1);
							SandVO advo11 = new SandVO();
							view.stockView(vegetable);
							int num7=view.stockAction(vegetable);
							advo11.setNum(num7);
							SandVO advo12 = model.selectOne(advo11);
							view.stockView();
							int num8=view.get_int();
							advo12.setStock(advo12.getStock()+num8);
							view.successView();
						}
						else if(view.action==5) { // 소스 배열 안에 있는 재료 재고 추가
							ArrayList<SandVO> sauce = model.sauceAll(advo1);
							SandVO advo14 = new SandVO();
							view.stockView(sauce);
							int num9=view.stockAction(sauce);
							advo14.setNum(num9);
							SandVO advo15 = model.selectOne(advo14);
							view.stockView();
							int num10=view.get_int();
							advo15.setStock(advo15.getStock()+num10);
							view.successView();
						}
					}

					else if(view.action==5) { // 종료하기
						view.endView();
						break;
					}
				}

			}

		}
	}
}
