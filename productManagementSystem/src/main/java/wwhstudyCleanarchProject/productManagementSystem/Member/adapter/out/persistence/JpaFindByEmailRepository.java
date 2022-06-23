package wwhstudyCleanarchProject.productManagementSystem.Member.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import wwhstudyCleanarchProject.productManagementSystem.Member.application.port.out.FindByEmailRepository;
import wwhstudyCleanarchProject.productManagementSystem.Member.domain.Member;

import javax.persistence.EntityManager;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JpaFindByEmailRepository implements FindByEmailRepository {

    private final EntityManager em;

    @Override
    public Optional<Member> findByEmail(String email) {
        return Optional.of(em.createQuery(
                        "select m from Member m where m.email = :email",
                        Member.class)
                .setParameter("email", email)
                .getSingleResult());
    }
}
