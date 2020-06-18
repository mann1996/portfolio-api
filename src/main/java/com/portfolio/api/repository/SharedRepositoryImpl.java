package com.portfolio.api.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.portfolio.api.entity.PostEntity;
import com.portfolio.api.entity.ProfileEntity;

@Repository
public class SharedRepositoryImpl implements SharedRepository {

	private EntityManager em;

	public SharedRepositoryImpl(EntityManager em) {
		this.em = em;
	}

	@Override
	public List<ProfileEntity> matchUser(String key) {
		Query query = em.createQuery(
				"select p from profile p where lower(p.firstName) like :key or lower(p.lastName) like :key");
		query.setParameter("key", '%' + key.toLowerCase() + '%');
		List<ProfileEntity> entities = query.getResultList();

		return entities;
	}

	@Override
	public List<PostEntity> findPublicPostsByUser(String userId) {
		Query query = em.createQuery(
				"select p from post p where p.createdBy.publicId = :userId and p.isPublic = true order by p.createdAt desc");
		query.setParameter("userId", userId);
		return query.getResultList();
	}

	@Override
	public List<PostEntity> findPublicPosts() {
		Query query = em.createQuery("select p from post p where  p.isPublic = true order by p.createdAt desc");
		return query.getResultList();
	}

	@Override
	public List<PostEntity> matchPost(String key) {
		Query query = em.createQuery("select p from post p where lower(p.title) like :key and p.isPublic = true");
		query.setParameter("key", '%' + key + '%');
		return query.getResultList();
	}

	@Override
	public List<PostEntity> findBySubscription(List<String> users) {
		Query query = em
				.createQuery("select p from post p where p.createdBy.publicId in :users order by p.createdAt desc");
		query.setParameter("users", users);
		return query.getResultList();
	}
}
