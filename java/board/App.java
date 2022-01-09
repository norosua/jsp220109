package board;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

//import board.member.GeneralMember;
import board.member.Member;
//import board.member.SpecialMember;
//import board.model.ArticleDBUtil;
import board.model.SqlMapper;

public class App {

	// 변수 선언
	Scanner sc = new Scanner(System.in);
	//ArrayList<Article> articles = new ArrayList<>();
	ArrayList<Member> members = new ArrayList<>();
	ArrayList<Reply> replies = new ArrayList<>();
	ArrayList<Like> likes = new ArrayList<>();

	SqlMapper mapper = new SqlMapper();
	
	ArrayList<Article> articles = mapper.getArticleList();
	
	int articleNo = 1;
	Member loginedUser = null; // 로그인한 유저
	Pagination pagination = null;
	

	// 메서드 선언
	public void run() {

		loginedUser = new Member(1, "hong123", "h1234", "홍길동", "20211128100000");
		makeTestData();
		pagination = new Pagination(articles.size());
		while (true) {
			if (loginedUser == null) {
				System.out.print("명령어를 입력해주세요 : ");
			} else {
				System.out.print("명령어를 입력해주세요 [" + loginedUser.getNickname() + "(" + loginedUser.getLoginId() + ")]: ");
			}
			String command = sc.nextLine();

			if (command.equals("exit")) {
				System.out.println("프로그램을 종료합니다.");
				break;
			} else if (command.equals("help")) {
				printHelp();

			} else if (command.equals("add")) {

				if (loginCheck()) {
					add();
				}

			} else if (command.equals("list")) {
				articles = mapper.getArticleList();
				list(articles);

			} else if (command.equals("update")) {
				update();

			} else if (command.equals("delete")) {
				delete();

			} else if (command.equals("search")) {
				search();

			} else if (command.equals("read")) {

				if (loginCheck()) {
					read();
				}

			} else if (command.equals("signup")) {
				signup();

			} else if (command.equals("login")) {
				login();

			} else if (command.equals("logout")) {
				logout();

			} else if (command.equals("page")) {
				page();
			}
		}
	}

	private void page() {

		while(true) {
			
			System.out.println("페이징 명령어를 입력해주세요 ((prev : 이전,  next : 다음,  go : 선택,  back : 뒤로가기):");
			String pageCmd = sc.nextLine();
			
			//페이징 - 현재표시되는 article의 목록을 보여주는 기능. (항상 값을 갱신하고 있기 때문에 눈에 보이지는 않지만 데이터상으로는 계속 변하는중.
			//켜면 항상 기본적인 페이지 (가장 첫번째 페이지) 를 보여주며 (가지고 오고 있으며)
			//페이지와 관련된 값을 바꿔주면, 해당하는 페이지로 넘어간 상태로 고정이 되어있음.
			//리스트명령어는 이와 관련된 값을 기반으로 가져오니때문에 페이징의 값들을 새로고침하지 않으면 반영되지 않음.
			
			if (pageCmd.equals("prev")) {
				if (pagination.getCurrentPageNo() > pagination.getFirstPageNo()) {
					pagination.setCurrentPageNo(pagination.getCurrentPageNo() - 1);
				} else {
					System.out.println("첫번째 페이지입니다.");
				}
			} else if (pageCmd.equals("next")) {
				if (pagination.getCurrentPageNo() < pagination.getLastPageNo()) {
					pagination.setCurrentPageNo(pagination.getCurrentPageNo() + 1);
				} else {
					System.out.println("마지막 페이지입니다.");
				}
			} else if (pageCmd.equals("go")) {
				
				System.out.println("몇번 페이지로 이동하시겠습니까 : ");
				int selectedPageNo = Integer.parseInt(sc.nextLine());
				
				if (selectedPageNo >= pagination.getFirstPageNo() && selectedPageNo <= pagination.getLastPageNo()) {
					pagination.setCurrentPageNo(selectedPageNo);
				} else {
					System.out.println("잘못된 페이지입니다.");
				}
			} else if(pageCmd.equals("back")) {
				break;
			}
			list(articles);
		}
	}

	private void logout() {

		loginedUser = null;
		System.out.println("로그아웃 되셨습니다.");

	}

	private void login() {
//		boolean isSuccessLogin = false;

		System.out.print("아이디 : ");
		String loginId = sc.nextLine();
		System.out.print("비밀번호 : ");
		String loginPw = sc.nextLine();
		
		Member member = mapper.getMemberLoginIdAndLoginPw(loginId, loginPw);

//		for (int i = 0; i < members.size(); i++) {
//			Member m = members.get(i);
//			if (m.getLoginId().equals(loginId)) {
//				if (m.getLoginPw().equals(loginPw)) {
//					loginedUser = m;
//					isSuccessLogin = true;
//				}
//			}
//		}

		if (member == null) {
			System.out.println("잘못된 회원정보 틀림");
		} else {
			loginedUser = member;
			System.out.println("로그인 성공." + loginedUser + " 환영합니다.");
		}

	}

	private void signup() {

//		System.out.println("1. 우수회원, 2. 일반회원");
//		int memberFlag = Integer.parseInt(sc.nextLine());

		System.out.print("아이디를 입력해주세요 : ");
		String loginId = sc.nextLine();
		System.out.print("비밀번호를 입력해주세요 : ");
		String loginPw = sc.nextLine();
		System.out.print("닉네임을 입력해주세요 : ");
		String nickname = sc.nextLine();

		Member m = null;
		//일반회원으로만 구분.

//		if (memberFlag == 1) {
//			m = new SpecialMember(loginId, loginPw, nickname, 0);
//		} else {
		m = new Member(loginId, loginPw, nickname);
//		}
		mapper.insertMember(m);

//		members.add(m);
		System.out.println("회원가입이 완료되었습니다.");
	}

	public void read() {
		System.out.println("상세보기할 게시물 선택 : ");
		
		//1. 입력된 값에 문자가 포함되어 있는지를 따지는 코드 작성
		
		//2. 예외처리
		try {
			int id = Integer.parseInt(sc.nextLine());
			//int index = getIndexByAritlceNo(no);
			
			Article article = mapper.getArticleById(id);
			
			if (article != null) {
				
				printArticleByNo(article);
				// 상세보기 기능
				readProcess(article);
				
			} else {
				System.out.println("없는 게시물입니다.");
			}			
		} catch(Exception e) {
			System.out.println("숫자만 입력 가능합니다.");
		}
		

	}

	private void printArticleByNo(Article a) {
		System.out.println("==== " + a.getNo() + "번 게시물 ====");
		System.out.println("번호 : " + a.getNo());
		System.out.println("제목 : " + a.getTitle());
		System.out.println("-------------------");
		System.out.println("내용 : " + a.getBody());
		System.out.println("-------------------");
		System.out.println("작성자 : " + a.getWriter());
		System.out.println("등록날짜: " + a.getRegDate());

		Like like = getLikeByAnoAndId(a.getNo(), loginedUser.getLoginId());

		if (like == null) {
			System.out.println("좋아요 : ♡ " + getLikeCountByAno(a.getNo()));
		} else {
			System.out.println("좋아요 : ♥ " + getLikeCountByAno(a.getNo()));
		}

		System.out.println("===================");
		System.out.println("======== 댓글 =======");
		for (int i = 0; i < replies.size(); i++) {
			Reply r = replies.get(i);

			if (r.getParentNo() == a.getNo()) {
				System.out.println("내용 : " + r.getrBody());
				System.out.println("작성자 : " + r.getWriter());
				System.out.println("작성일 : " + r.getRegDate());
				System.out.println("=======================");
			}

		}

	}

	private int getLikeCountByAno(int no) {

		int count = 0;

		for (int i = 0; i < likes.size(); i++) {
			if (likes.get(i).getArticleNo() == no) {
				count++;
			}
		}

		return count;
	}

	private void readProcess(Article a) {

		while (true) {
			System.out.print("상세보기 기능을 선택해주세요(1. 댓글 등록, 2. 좋아요, 3. 수정, 4. 삭제, 5. 목록으로) : ");
			int rcmd = Integer.parseInt(sc.nextLine());

			if (rcmd == 1) {

				System.out.print("댓글 내용을 입력해주세요 : ");
				String replyBody = sc.nextLine();
				// 작성자
				String writer = loginedUser.getNickname();
				// 작성일
				String regDate = getCurrentData();
				// 어떤 게시물의 댓글?
				int parentNo = a.getNo();

				Reply reply = new Reply(parentNo, replyBody, writer, regDate);
				replies.add(reply);

				System.out.println("댓글이 등록되었습니다.");
				printArticleByNo(a);

			} else if (rcmd == 2) {

				// 로그인한 유저가 해당 게시물에 좋아요 체크했는지 따져봄
				String loginId = loginedUser.getLoginId();
				int articleNo = a.getNo();

				Like like = getLikeByAnoAndId(articleNo, loginId);

				if (like == null) {
					// 좋아요 저장
					// 누가(회원아이디) , 어떤(게시물 아이디), 날짜(오늘날짜)
					likes.add(new Like(loginedUser.getLoginId(), a.getNo(), getCurrentData()));
					System.out.println("해당게시물을 좋아합니다.");

				} else {
					likes.remove(like);
					System.out.println("해당 게시물의 좋아요를 해제합니다.");
				}

				printArticleByNo(a);

			} else if (rcmd == 3) {
				System.out.println("[수정]");
			} else if (rcmd == 4) {
				System.out.println("[삭제]");
			} else if (rcmd == 5) {
				break;
			}
		}

	}

	public Like getLikeByAnoAndId(int ano, String loginId) {

		for (int i = 0; i < likes.size(); i++) {
			Like like = likes.get(i);

			if (like.getArticleNo() == ano && like.getUserId() == loginId) {
				return like;
			}
		}

		return null;
	}

	// 함수 -> 기능
	// 코드 재활용
	// 코드의 구조화 -> 집중
	// 코드가 깔끔 -> 가독성이 올라간다.
	// ===========================================================
	// 검색 키워드로 검색하기
	public void search() {
		System.out.println("검색 키워드 입력 : ");
		String keyword = sc.nextLine();
		
		ArrayList<Article> searchedList = mapper.getSearchedList(keyword);
		list(searchedList);

//		ArrayList<Article> searchedList = new ArrayList<>();
//		// 번호로 찾기
//		for (int i = 0; i < articles.size(); i++) {
//			Article a = articles.get(i);
//			if (a.getTitle().contains(keyword)) {
//				searchedList.add(a);
//			}
//		}

		list(searchedList);
	}

	// ===========================================================
	// 번호로 게시물 인덱스 찾는 함수
	public int getIndexByAritlceNo(int no) {

		int index = -1; // 0이 아닌 이유 : 값이 없을 경우 없다는 것을 표현하기 위함. 0은 인덱스로서 의미를 가지니까

		for (int i = 0; i < articles.size(); i++) {

			Article a = articles.get(i);

			if (no == a.getNo()) {
				index = i;
				break;
			}
		}

		return index;
	}

	// ===========================================================
	// 게시물을 삭제하는 함수
	public void delete() {
		System.out.println("삭제할 게시물 선택 : ");
		int id = Integer.parseInt(sc.nextLine());
//		int index = getIndexByAritlceNo(no);
		
		Article article = mapper.getArticleById(id);

		if (article != null) {
			mapper.deleteArticle(id);
		} else {
			System.out.println("없는 게시물입니다.");
		}
	}

	// ===========================================================
	// 게시물을 수정해주는 함수
	public void update() {
		System.out.println("수정할 게시물 선택 : ");
		int id = Integer.parseInt(sc.nextLine());
		Article article = mapper.getArticleById(id);

		//int index = getIndexByAritlceNo(no);

		//if (index != -1) {
		
		if(article != null) {
			System.out.print("새제목 : ");
			String title = sc.nextLine();
			System.out.print("새내용 : ");
			String body = sc.nextLine();

			Article a = new Article(id, title, 0, null, body, null);
			mapper.updateArticle(a);
			System.out.println("게시물이 수정되었습니다.");
		} else {
			System.out.println("없는 게시물입니다.");
		}
		
		
		//a.setTitle(title);
		//a.setBody(body);

		//articles.set(index, a);

		//} else {
		//	System.out.println("없는 게시물입니다.");
		//}
	}

	// ===========================================================
	// 게시물 목록을 보여주는 함수
	public void list(ArrayList<Article> articleList) {


		
		
		for (int i = pagination.getStartIndex(); i < pagination.getEndIndex(); i++) {
			Article a = articles.get(i);
			System.out.println("번호 : " + a.getNo());
			System.out.println("제목 : " + a.getTitle());
			System.out.println("작성자 : " + a.getWriter());
			System.out.println("작성일 : " + a.getRegDate());
			System.out.println("조회수 : " + 0);
			System.out.println("===============");
		}

		for (int i = pagination.getStartPageNoInBlock(); i <= pagination.getEndPageNoInBlock(); i++) {
			if (i == pagination.getCurrentPageNo()) {
				System.out.print("[" + i + "] ");
			} else {
				System.out.print(i + " ");
			}
		}
		System.out.println();
	}

	// ===========================================================
	// 게시물 추가하는 함수
	public void add() {

		System.out.print("제목을 입력해주세요 : ");
		String title = sc.nextLine();
		System.out.print("내용을 입력해주세요 : ");
		String body = sc.nextLine();

		// 현재 시간 구해서 등록.
		// 현재 날짜 구하기 (시스템 시계, 시스템 타임존)
		String regDate = getCurrentData();
		Article a = new Article(articleNo, title, loginedUser.getIdx(),loginedUser.getNickname(), body, regDate);
		
		
		
		mapper.insertArticle(a);		
		pagination.setTotalItemCount(articles.size());
	
		System.out.println("게시물이 등록되었습니다.");
		articleNo++;
	}

	// ===========================================================
	// 도움말 출력 함수
	public static void printHelp() {
		System.out.println("========================");
		System.out.println("help : 도움말");
		System.out.println("add : 데이터 추가");
		System.out.println("read : 데이터 조회");
		System.out.println("update : 데이터 수정");
		System.out.println("delete : 데이터 삭제");
		System.out.println("exit : 프로그램 종료");
		System.out.println("========================");
	}

	public void makeTestData() {
//		setArticleTestData("안녕하세요", "반갑습니다");
//		setArticleTestData("하이~", "냉무");
//		setArticleTestData("가입인사드립니다.", "잘부탁드립니다.");
//		
//		for(int i = 1; i <= 20; i++) {
//			setArticleTestData("제목" + i, "내용" + i);
//		}
		
		setMemberTestData("hong123", "h1234", "홍길동");
		setMemberTestData("lee123", "l1234", "이순신");

	}

	public void setMemberTestData(String loginId, String loginPw, String nick) {

		Member m1 = new Member(loginId, loginPw, nick);

		members.add(m1);
		System.out.println("테스트 회원이 등록되었습니다.");
	}

	public void setArticleTestData(String title, String body) {

		String regDate = getCurrentData();
		Article a = new Article(articleNo, title, loginedUser.getIdx() , "홍길동", body, regDate);

		articles.add(a);
		System.out.println("게시물이 등록되었습니다.");
		articleNo++;
	}

	public String getCurrentData() {
		LocalDate now = LocalDate.now();

		// 포맷 정의
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
		// 포맷 적용
		String formatedNow = now.format(formatter);

		return formatedNow;

	}

	public boolean loginCheck() {

		if (loginedUser != null) {
			return true;
		} else {
			System.out.println("로그인이 필요한 기능입니다.");
			return false;
		}
	}
}
