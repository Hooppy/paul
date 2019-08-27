package com.paul.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.paul.model.BoardModel;
import com.paul.model.PagingCriteria;

@Repository
public class BoardDao {
	
	@Autowired
	SqlSession sqlSession;
	
	@Autowired
	BoardModel boardModel;
	
	private static String namespace = "com.paul.boardMapper";
	
	public void Insert(BoardModel boardModel) throws Exception{
		
		try {
			sqlSession.insert(namespace + ".write", boardModel);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<BoardModel> getBoardList(PagingCriteria cri){
		
		List<BoardModel> list = null;
		
		try {
			list = sqlSession.selectList(namespace + ".list", cri);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public int getTotalCnt() {
		int cnt = 0;
		try {
			cnt = sqlSession.selectOne(namespace + ".cnt");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cnt;
	}
}
