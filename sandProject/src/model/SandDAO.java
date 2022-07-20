package model;

import java.util.ArrayList;

public class SandDAO {
	int pk1 = 101;	//빵
	int pk2 = 201;	//치즈
	int pk3 = 301; 	//고기
	int pk4 = 401;	//야채
	int pk5 = 501;	//소스

	ArrayList<SandVO> bread;
	ArrayList<SandVO> cheese;
	ArrayList<SandVO> vegetable;
	ArrayList<SandVO> meet;
	ArrayList<SandVO> sauce;
	public ArrayList<String> mu;
	public ArrayList<String> admin_mu;

	public SandDAO() {

		bread = new ArrayList<SandVO>();
		bread.add(new SandVO("위트", pk1++, 1000, 0));
		bread.add(new SandVO("허니오트", pk1++, 1100, 1));
		bread.add(new SandVO("화이트", pk1++, 1200, 1));
		bread.add(new SandVO("파마산 오레가노", pk1++, 1300, 1));
		bread.add(new SandVO("하티", pk1++, 1400, 1));
		bread.add(new SandVO("플랫브레드", pk1++, 1500, 1));

		cheese = new ArrayList<SandVO>();
		cheese.add(new SandVO("아메리칸 치즈", pk2++, 1000, 1));
		cheese.add(new SandVO("슈레드 치즈", pk2++, 1100, 0));
		cheese.add(new SandVO("모짜렐라", pk2++, 1200, 0));

		meet = new ArrayList<SandVO>();
		meet.add(new SandVO("페퍼로니", pk3++, 1000, 1));
		meet.add(new SandVO("햄", pk3++, 1100, 1));
		meet.add(new SandVO("미트볼", pk3++, 1200, 1));
		meet.add(new SandVO("베이컨", pk3++, 1300, 1));
		meet.add(new SandVO("참치", pk3++, 1400, 1));
		meet.add(new SandVO("살라미", pk3++, 1500, 1));

		vegetable = new ArrayList<SandVO>();
		vegetable.add(new SandVO("양상추", pk4++, 1000, 3));
		vegetable.add(new SandVO("토마토", pk4++, 1100, 3));
		vegetable.add(new SandVO("오이", pk4++, 1200, 3));
		vegetable.add(new SandVO("피망", pk4++, 1300, 3));
		vegetable.add(new SandVO("양파", pk4++, 1400, 1));
		vegetable.add(new SandVO("피클", pk4++, 1500, 1));
		vegetable.add(new SandVO("올리브", pk4++, 1600, 1));
		vegetable.add(new SandVO("할라피뇨", pk4++, 1700, 1));

		sauce = new ArrayList<SandVO>();
		sauce.add(new SandVO("랜치드레싱", pk5++, 1000, 3));
		sauce.add(new SandVO("마요네즈", pk5++, 1100, 3));
		sauce.add(new SandVO("스위트 어니언", pk5++, 1200, 3));
		sauce.add(new SandVO("허니머스타드", pk5++, 1300, 1));
		sauce.add(new SandVO("스위치 칠리", pk5++, 1400, 1));
		sauce.add(new SandVO("핫 칠리", pk5++, 1500, 1));

		mu = new ArrayList<String>();
		mu.add("빵");
		mu.add("치즈");
		mu.add("고기");
		mu.add("야채");
		mu.add("소스");

		admin_mu = new ArrayList<String>();
		admin_mu.add("재료 추가 메뉴");
		admin_mu.add("재료 삭제 메뉴");
		admin_mu.add("재고 현황");
		admin_mu.add("재고 추가");
		admin_mu.add("종료");



	}
	// sandVO selectOne(SandVO vo) // 인자와같은pk를갖고있는객체를찾고그객체를리턴
	public SandVO selectOne(SandVO vo) {
		for(int i=0; i<bread.size();i++) {
			if(bread.get(i).getNum()==vo.getNum()) {
				return bread.get(i);
			}
		}
		for(int i=0; i<cheese.size();i++) {
			if(cheese.get(i).getNum()==vo.getNum()) {
				return cheese.get(i);
			}
		}
		for(int i=0; i<vegetable.size();i++) {
			if(vegetable.get(i).getNum()==vo.getNum()) {
				return vegetable.get(i);
			}
		}
		for(int i=0; i<meet.size();i++) {
			if(meet.get(i).getNum()==vo.getNum()) {
				return meet.get(i);
			}
		}
		for(int i=0; i<sauce.size();i++) {
			if(sauce.get(i).getNum()==vo.getNum()) {
				return sauce.get(i);
			}
		}
		return null;
	}

	// 재료추가(아래함수로가능)
	//ArrayList<sandVO> breadAll(SandVO vo) // 빵배열저장(이름이있는객체는배열에저장이름이없는객체는그냥리턴)
	public ArrayList<SandVO> breadAll(SandVO vo){
		if(vo.getName()==null) {
			return bread;
		}
		else {
			vo.setNum(pk1++);
			bread.add(vo);
			return bread;
		}
	}
	public ArrayList<SandVO> cheeseAll(SandVO vo){
		if(vo.getName()==null) {
			return cheese;
		}
		else {
			vo.setNum(pk2++);
			cheese.add(vo);
			return cheese;
		}
	}
	public ArrayList<SandVO> vegetableAll(SandVO vo){
		if(vo.getName()==null) {
			return vegetable;
		}
		else {
			vo.setNum(pk3++);
			vegetable.add(vo);
			return vegetable;
		}
	}
	public ArrayList<SandVO> meetAll(SandVO vo){
		if(vo.getName()==null) {
			return meet;
		}
		else {
			vo.setNum(pk4++);
			meet.add(vo);
			return meet;
		}
	}
	public ArrayList<SandVO> sauceAll(SandVO vo){
		if(vo.getName()==null) {
			return sauce;
		}
		else {
			vo.setNum(pk5++);
			sauce.add(vo);
			return sauce;
		}
	}

	public int sum(ArrayList<SandVO> datas) { // 배열(장바구니)을 보내주면 price 모두 더해서 int값으로 리턴
		int sum=0;
		for(SandVO vo : datas) {
			sum += vo.getPrice();
		}
		return sum;
	}

	public int pay(int price, int money) { // 차액만큼 보내주는거 (양수 or 음수)
		int sum = price - money;
		return sum;
	}

	public void cntclear() {	//cnt를 0으로 초기화 메소드
		for(int i=0; i<this.bread.size(); i++) {
			this.bread.get(i).setCnt(0);
		}
		for(int i=0; i<this.cheese.size(); i++) {
			this.cheese.get(i).setCnt(0);
		}
		for(int i=0; i<this.vegetable.size(); i++) {
			this.vegetable.get(i).setCnt(0);
		}
		for(int i=0; i<this.meet.size(); i++) {
			this.meet.get(i).setCnt(0);
		}
		for(int i=0; i<this.sauce.size(); i++) {
			this.sauce.get(i).setCnt(0);
		}
	}
	public boolean soldOut() {
		int num=0;
		for(int i=0;i<this.bread.size();i++) {
			if(this.bread.get(i).getStock()==0) {
				num++;
			}
		}
		if(num==this.bread.size()) {
			return true;
		}
		
		num=0;
		for(int i=0;i<this.cheese.size();i++) {
			if(this.cheese.get(i).getStock()==0) {
				num++;
			}
		}
		if(num==this.cheese.size()) {
			return true;
		}
		
		num=0;
		for(int i=0;i<this.meet.size();i++) {
			if(this.meet.get(i).getStock()==0) {
				num++;
			}
		}
		if(num==this.meet.size()) {
			return true;
		}
		
		num=0;
		for(int i=0;i<this.vegetable.size();i++) {
			if(this.vegetable.get(i).getStock()==0) {
				num++;
			}
		}
		if(num==this.vegetable.size()) {
			return true;
		}
		
		num=0;
		for(int i=0;i<this.sauce.size();i++) {
			if(this.sauce.get(i).getStock()==0) {
				num++;
			}
		}
		if(num==this.bread.size()) {
			return true;
		}
		return false;
	}
}
