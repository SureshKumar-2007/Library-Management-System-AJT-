package com.example.library.service;

import com.example.library.dao.MemberDao;
import com.example.library.model.Member;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    @Autowired private MemberDao memberDao;

    public List<Member> list() { return memberDao.findAll(); }
    public Member get(int id) { return memberDao.findById(id); }
    public void add(Member m) { memberDao.insert(m); }
    public void update(Member m) { memberDao.update(m); }
    public void delete(int id) { memberDao.delete(id); }
}
