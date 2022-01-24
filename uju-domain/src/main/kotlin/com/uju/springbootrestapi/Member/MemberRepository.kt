package com.uju.springbootrestapi.member

import org.springframework.stereotype.Repository
import javax.persistence.EntityManager

@Repository
class MemberRepository(
    private val em: EntityManager
) {

    fun save(member: Member) {
        em.persist(member)
    }

    fun findOne(id: Long): Member {
        return em.find(Member::class.java, id)
    }

    fun findAll(): List<Member> {
        return em.createQuery("select m from Member m", Member::class.java).resultList
    }

    fun findByName(name: String): List<Member> {
        return em.createQuery("select m from Member m where m.name = :name", Member::class.java)
            .setParameter("name", name)
            .resultList
    }
}