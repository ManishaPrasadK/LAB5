package com.greatlearning.service;

import java.util.List;


import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.greatlearning.model.Student;

public class StudentServiceImpl implements StudentService{

	private SessionFactory sessionFactory;
	
	private Session session;
	
	@Autowired
	StudentServiceImpl(SessionFactory sessionFactory){
		this.sessionFactory=sessionFactory;
		try {
			session=sessionFactory.getCurrentSession();
		}
		catch(HibernateException e) {
			session=sessionFactory.openSession();
		}
	}
	
	
	@Override
	@Transactional
	public List<Student> findAll() {
		// TODO Auto-generated method stub
		Transaction tx=session.beginTransaction();
		List<Student> students=session.createQuery("from Student").list();
		tx.commit();
		return students;
	}

	@Override
	@Transactional
	public Student findById(int id) {
		// TODO Auto-generated method stub
		Student student = new Student();
		Transaction tx=session.beginTransaction();
		student=session.get(Student.class, id);
		tx.commit();
		return student;
	}

	@Override
	@Transactional
	public void save(Student student) {
		// TODO Auto-generated method stub
		Transaction tx=session.beginTransaction();
		session.saveOrUpdate(student);
		tx.commit();
		
		
	}

	@Override
	@Transactional
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		Transaction tx=session.beginTransaction();
		Student student=session.get(Student.class,id);
		session.delete(student);
		tx.commit();
		
	}
	

}
