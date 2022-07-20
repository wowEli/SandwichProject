package view;

import java.util.ArrayList;
import java.util.Scanner;

import model.SandVO;

public class SandView {
	Scanner sc=new Scanner(System.in);
	public int action; // 사용자의 액션
	int count=1; // 사용자의 번호표
	public int money; // 사용자의 투입금액


	public void startView() { 
		while(true) {
			try {
				System.out.println();
				System.out.println("1. 주문하기");
				System.out.println("2. 종료하기");
				System.out.print("입력: ");
				action=sc.nextInt();
				if(action==1||action==2||action==828253) {
					break;
				}
				else {
					System.out.println("잘못된 번호입니다.");
				}
			} catch(Exception e) {
				sc.nextLine();
				System.out.println("숫자를 입력해주세요.");
			}
		}
	}

	public void menuView(ArrayList<SandVO> datas) { // 메뉴출력
		System.out.println();
		System.out.println("===재료 목록===");
		for(int i=0;i<datas.size();i++) {
			if(datas.get(i).getStock()==0) {
				System.out.println((i+1)+"번: 품절");
			}else {
				System.out.println((i+1)+"번: "+datas.get(i).getName());
			}
		}
	}

	public void menuAllView(ArrayList<SandVO> datas) { // 메뉴출력
		System.out.println();
		System.out.println("===재료 목록===");
		for(int i=0;i<datas.size();i++) {
			System.out.println((i+1)+"번: "+datas.get(i).getName());
		}
	}

	public void resultView(ArrayList<SandVO> datas) {
		System.out.print("선택하신 재료: ");
		for(int i=0;i<datas.size();i++) {
			System.out.print("["+datas.get(i).getName()+"] ");
		}
		System.out.println();

	}

	public int getAction(ArrayList<SandVO> datas) { // 사용자모드에서 사용할 입력함수
		while(true) {
			try {
				System.out.println();
				System.out.print("입력: ");
				this.action=sc.nextInt(); // 사용자의 입력 값

				if(this.action>0 && this.action<=datas.size()){ // 매번 다르게 유효성 검사 범위를 수정
					if(datas.get(this.action-1).getStock()==0) { // 만약 사용자가 고른 객체의 재고가 0 이면
						System.out.println("품절입니다."); // 품절입니다 출력
						continue; // 다시 위로가서 입력받기
					}
					return datas.get(this.action-1).getNum(); // 사용자가 고른 객체의 pk 값을 리턴
				}
				else { // 범위 외 입력 시
					System.out.println("잘못된 번호입니다.");
				}

			} catch(Exception e) { // 예외처리
				sc.nextLine();
				System.out.println("숫자를 입력해주세요.");
			}
		}
	}
	
	public int stockAction(ArrayList<SandVO> datas) { // 재고 추가에서 사용할 입력함수
		while(true) {
			try {
				System.out.println();
				System.out.print("입력: ");
				action=sc.nextInt();
				
				if(action>0 && action<=datas.size()){
					return datas.get(action-1).getNum(); // 사용자가 선택한 객체의 pk값을 리턴
				}
				else {
					System.out.println("잘못된 번호입니다.");
				}
			} catch(Exception e) {
				sc.nextLine();
				System.out.println("숫자를 입력해주세요.");
			}
		}
	}
	
	public int deleteAction(ArrayList<SandVO> datas) { // 재료 삭제에서 사용할 입력함수
		while(true) {
			try {
				System.out.println();
				System.out.print("입력: ");
				action=sc.nextInt();

				if(action>0 && action<=datas.size()){
					return action-1; // 사용자가 고른 객체의 인덱스 번호를 리턴
				}
				else {
					System.out.println("잘못된 번호입니다.");
				}
			} catch(Exception e) {
				sc.nextLine();
				System.out.println("숫자를 입력해주세요.");
			}
		}
	}
	
	public int menuAction(ArrayList<String> datas) { // 메뉴중 입력받기
		while(true) {
			try {
				System.out.print("입력: ");
				action=sc.nextInt();
				if(action>0 && action<=datas.size()){
					return action-1;
				}
				else {
					System.out.println("잘못된 번호입니다.");
				}
			} catch(Exception e) {
				sc.nextLine();
				System.out.println("숫자를 입력해주세요.");
			}
		}
	}

	public boolean addMore() { // 컨트롤러가 논리값을 받고 if continue else break
		while(true) {
			try {
				System.out.println();
				System.out.println("추가하시겠습니까?");
				
				System.out.println("1. Yes");
				System.out.println("2. No");
				System.out.print("입력: ");
				int num=sc.nextInt();
				if(num==1||num==2) {
					if(num==1) {
						return true;
					}
					return false;
				}
				else {
					System.out.println("잘못된 번호입니다.");
				}
			} catch(Exception e) {
				sc.nextLine();
				System.out.println("숫자를 입력해주세요.");

			}
		}
	}
	public boolean yesNo() { // 컨트롤러가 논리값을 받고 if continue else break
		while(true) {
			try {
				System.out.println();
				System.out.println("다시 고르시겠습니까?");
				System.out.println("1. Yes");
				System.out.println("2. No");
				System.out.print("입력: ");
				int num=sc.nextInt();
				if(num==1||num==2) {
					if(num==1) {
						return true;
					}
					return false;
				}
				else {
					System.out.println("잘못된 번호입니다.");
				}
			} catch(Exception e) {
				sc.nextLine();
				System.out.println("숫자를 입력해주세요.");

			}
		}
	}

	public void adminMenuView(ArrayList<String> datas) { // 관리자 메뉴 출력
		System.out.println();
		for(int i=0;i<datas.size();i++) {
			System.out.println((i+1)+"번 "+datas.get(i));
		}
	}
	public void adminView() {
		System.out.println();
		System.out.println("===관리자 모드===");
	}

	public void stockView(ArrayList<SandVO> datas) { // 재고 출력
		System.out.println();
		System.out.println("===재고 목록===");
		for(int i=0;i<datas.size();i++) {
			System.out.println((i+1)+"번: "+datas.get(i).getName()+" 재고: "+datas.get(i).getStock());
		}
	}

	public void countView() { // 번호표 
		System.out.println();
		System.out.println("번호표: "+this.count+"번");
		this.count++;
	}
	public int get_int() { // 단순 수량 입력받기 1이상
		while(true) {
			try {
				System.out.print("정수 입력: ");
				int num=sc.nextInt();
				if(num>0) {
					return num;
				}
				System.out.println("1 이상의 수를 입력해 주세요");
			}catch(Exception e) {
				sc.nextLine();
				System.out.println("숫자를 입력해주세요.");
			}
		}
	}
	
	public int get_PriceInt() { // 금액 입력 받기 100원 이상
		while(true) {
			try {
				System.out.print("정수 입력: ");
				int num=sc.nextInt();
				if(num>99) {
					return num;
				}
				System.out.println("100원 이상의 금액을 입력해주세요.");
			}catch(Exception e) {
				sc.nextLine();
				System.out.println("숫자를 입력해주세요.");
			}
		}
	}
	
	public String get_String(ArrayList<SandVO> datas) { //String 입력받기 > 유효성검사 필요
        String str = null;
        while(true) {
            boolean flag = true;
            System.out.print("문자열 입력: ");
            str=sc.next();
            for(SandVO vo : datas) {
                if(vo.getName().equals(str)) {
                    System.out.println("동일한 이름의 재료가 존재합니다!");
                    System.out.println();
                    System.out.println("추가하실 메뉴의 재료를 입력해주세요");
                    flag=false;
                    break;
                }
            }
            if(flag) {
                break;
            }
        }
        return str;
    }
	
	// 출력 메서드
	public void breadChoice() {
		System.out.println();
		System.out.println("빵을 선택해주세요.");
	}
	public void cheeseChoice() {
		System.out.println();
		System.out.println("치즈를 선택해주세요.");
	}
	public void meetChoice() {
		System.out.println();
		System.out.println("고기를 선택해주세요.");
	}
	public void vegetableChoice() {
		System.out.println();
		System.out.println("야채를 선택해주세요.");
	}
	public void sauceChoice() {
		System.out.println();
		System.out.println("소스를 선택해주세요.");
	}
	public void noStock() {
		System.out.println();
		System.out.println("재고가 없습니다.");
	}
	public void showResult() {
		System.out.println();
		System.out.println("샌드위치가 완성되었습니다.");
	}
	public void selectView() {
		System.out.println();
		System.out.println("선택되었습니다.");
	}
	public void insertIngredient() {
		System.out.println();
		System.out.println("어떤 재료를 추가하시겠습니까?");
	}
	public void nameView() { 
		System.out.println();
		System.out.println("추가하실 메뉴 이름을 입력해주세요 : ");
	}
	public void stockView() { 
		System.out.println();
		System.out.println("추가하실 메뉴의 재고를 입력해주세요 : ");      
	}
	public void addIngredientView() { 
		System.out.println();
		System.out.println("=== 재료 추가 메뉴 ===");
	}
	public void deleteIngredientView() {
		System.out.println();
		System.out.println("=== 재료 삭제 메뉴 ===");
	}
	public void nowStockView() { 
		System.out.println();
		System.out.println("=== 재료 재고 현황 메뉴 ===");
	}
	public void addStockView() {
		System.out.println();
		System.out.println("=== 재료 재고 추가 메뉴 ===");
	}
	public void priceView() {
		System.out.println();
		System.out.println("재료의 가격을 입력해주세요.");
	}
	public void soldOut() {
		System.out.println();
		System.out.println("재고가 모두 품절입니다.");
	}
	public void endView() { 
		System.out.println();
		System.out.println("종료합니다. ");
	}
	public void successView() { 
		System.out.println();
		System.out.println("정상적으로 처리되었습니다. ");
	}
	public void failView() {
		System.out.println();
		System.out.println("처리에 실패했습니다");
	}
	public void nextMenu() {
		System.out.println();
		System.out.println("최대 추가 횟수가 넘어 다음 메뉴로 넘어갑니다.");
	}
	
	// 결제 메서드
	public void paySuccessView(int money) { // 결제 완료 메서드
		System.out.println();
		System.out.println("결제가 완료되었습니다. 잔돈: "+(-money)+"원");
	}
	public void payFailView(int money) { // 결제 실패 메서드
		System.out.println();
		System.out.println("금액이 부족합니다. 남은 결제 금액: "+money+"원");
	}
	public void payPriceView(int sum) {
		System.out.println();
		System.out.println("결제 금액: "+sum+"원");
	}
	public void payGetMoney() {
		while(true) {
			try {
				System.out.println();
				System.out.println("돈을 넣어주세요.");
				System.out.print("입력: ");
				int num = sc.nextInt();
				if(num > 99) {
					this.money+=num;
					break;
				}
				else {
					System.out.println("100원 이상의 값을 입력해주세요.");
				}
			}catch(Exception e){
				sc.nextLine();
				System.out.println("숫자를 입력해주세요.");
			}

		}
	}
	
	public void receipt(ArrayList<SandVO> data,int sum) {
		System.out.println("====================================================");
		System.out.println("                      번호표 : "+this.count+"번        ");
		System.out.println("====================================================");
		System.out.println("품명\t\t단가\t\t수량\t\t금액");
		System.out.println("====================================================");
		for(int i=0; i<data.size(); i++) {
			System.out.printf("%-10s",data.get(i).getName());
			System.out.println();
			System.out.printf("%-10d",data.get(i).getNum());
			System.out.printf("%10d",data.get(i).getPrice());
			System.out.printf("%13d",data.get(i).getCnt());
			System.out.printf("%18d",data.get(i).getCnt()*data.get(i).getPrice());
			System.out.println();
		}
		System.out.println("====================================================");
		System.out.printf("청구금액"+"%46d", sum);
		System.out.println();
		System.out.printf("받은금액"+"%46d",this.money);
		System.out.println();
		System.out.printf("거스름돈"+"%46d",(this.money-sum));
		System.out.println();
		System.out.println("====================================================");
		System.out.println("감사합니다. 다음에 또 이용해주세요");
		
		this.count++;
		
	}

}
