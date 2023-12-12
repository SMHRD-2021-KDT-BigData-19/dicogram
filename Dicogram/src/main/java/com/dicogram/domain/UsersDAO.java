package com.dicogram.domain;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.dicogram.database.SqlSessionManager;


public class UsersDAO {
	SqlSessionFactory sqlSessionFactory = SqlSessionManager.getSqlSession();
	SqlSession sqlSession = sqlSessionFactory.openSession();
	
	// 회원가입 기능구현
	public int JoinUser(Users joinUser) {
		int cnt = 0;
		try {
			cnt = sqlSession.insert("JoinUser", joinUser);
			if(cnt>0) {
				sqlSession.commit();
			}else {
				sqlSession.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		return cnt;
	}

	// 로그인 기능 구현
	public Users LoginUser(Users loginUser) {
		Users result = null;
		try {
			result = sqlSession.selectOne("LoginUser", loginUser);
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			//sqlSession.close();
		}
		return result;
	}
	
	// 회원정보수정 기능 구현
	public int updateUser(Users updateUser) {
		int cnt = 0;
		try {
			cnt = sqlSession.update("updateUser",updateUser);
			if(cnt>0) {
				sqlSession.commit();
			}else {
				sqlSession.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		return cnt;
	}
	
	// 회원삭제 기능 구현
	public int deleteUser(String id) {
		int cnt = 0;
			try {
				cnt = sqlSession.delete("deleteUser", id);
				if(cnt>0) {
					sqlSession.commit();
				}else {
					sqlSession.rollback();
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				sqlSession.close();
			}
		return cnt;
	}
	
	// 채팅방 생성 후 roomid가져오기
	public int createChatRoom(roomsDTO createChatRoomMem) {
		int cnt = 0;
		try {
			cnt = sqlSession.insert("createChatRoom", createChatRoomMem); 
			if(cnt>0)sqlSession.commit();
			else sqlSession.rollback();
			
		} catch(Exception e){
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		return cnt;
	}
	
	// 채팅방 메세지 DB에 저장
	public int MessageToDB(messageDTO MessageToDBMem) {
		int cnt = 0;
		try {
			cnt = sqlSession.insert("MessageToDB", MessageToDBMem); 
			if(cnt>0)sqlSession.commit();
			else sqlSession.rollback();
			
		} catch(Exception e){
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		return cnt;
	}
	
	// 방 참여자 DB에 저장
	public int userdataToDB(roomuserDTO userdataToDBMem) {
		int cnt = 0;
		try {
			cnt = sqlSession.insert("userdataToDB", userdataToDBMem); 
			if(cnt>0)sqlSession.commit();
			else sqlSession.rollback();
			
		} catch(Exception e){
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		return cnt;
	}
	
	// 방 참여자 DB에서 불러오기
	public List<roomuserDTO> loadAllUsers(String roomid) {
		List<roomuserDTO> result = null;
		try {
			result = sqlSession.selectList("loadAllUsers", roomid);			
		} catch(Exception e){
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		return result;
	}
	
	// 채팅방에 메세지 정보 모두 불러오기(시간 조건 추가)
	public List<messageDTO> loadAllMessage(roomuserDTO loadAllMessageMem) {
		List<messageDTO> result = null;	
		try {
			result = sqlSession.selectList("loadAllMessage", loadAllMessageMem);
		} catch(Exception e){
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		return result;
	}
	
	// 채팅 입장 전 확인
	public roomuserDTO checkEnterUser(roomuserDTO checkEnterUserMem) {
		roomuserDTO result = null;
		try {
			result = sqlSession.selectOne("checkEnterUser", checkEnterUserMem);
		} catch(Exception e){
			e.printStackTrace();
		} finally {
			
		}
		return result;
	}
	
	// 내가 참여했던 모든 채팅방 불러오기
	public List<roomuserDTO> loadMyChatRoom(String userid) {
		List<roomuserDTO> result = null;
		try {
			result = sqlSession.selectList("loadMyChatRoom", userid);
		} catch(Exception e){
			e.printStackTrace();
		} finally {
			
		}
		return result;
	}
	
	// 존재하는 모든 오픈채팅방 불러오기 + 내가 들어가지 않은 것
	public List<loadAllOpenChatRoomsDTO> loadOpenChatRoom(String userid) {
		List<loadAllOpenChatRoomsDTO> result = null;
		try {
			result = sqlSession.selectList("loadOpenChatRoom", userid);
		} catch(Exception e){
			e.printStackTrace();
		} finally {
			
		}
		return result;
	}
	
	// 채팅방 비밀번호 가져오기
	public String chatPWDAO (long roomid){
		String result = null;
		try {
			result = sqlSession.selectOne("chatPW", roomid);
		} catch(Exception e){
			e.printStackTrace();
		} finally {
			
		}
		return result;
	}

	
	public NickPropathDTO NickPropathDAO (String userid){
		NickPropathDTO result = null;
		try {
			result = sqlSession.selectOne("NickPropath", userid);
		} catch(Exception e){
			e.printStackTrace();
		} finally {
			
		}
		return result;
	}
	
}
