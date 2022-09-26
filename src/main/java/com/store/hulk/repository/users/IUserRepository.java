
package com.store.hulk.repository.users;

import com.store.hulk.model.user.UserHulk;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IUserRepository extends JpaRepository<UserHulk,Long> {

    @Query("SELECT u FROM UserHulk u where " +
            "LOWER(u.userName) LIKE LOWER(:query) " +
            "OR LOWER(u.name) LIKE LOWER(:query) " +
            "OR LOWER(u.lastName) LIKE LOWER(:query) " +
            "OR LOWER(u.surName) LIKE LOWER(:query)")
    Iterable<UserHulk> typeHeadSearch(@Param("query") String query);

    @Query("SELECT u FROM UserHulk u where " +
            "LOWER(u.userName) LIKE LOWER(:query) " +
            "OR LOWER(u.name) LIKE LOWER(:query) " +
            "OR LOWER(u.lastName) LIKE LOWER(:query) " +
            "OR LOWER(u.surName) LIKE LOWER(:query)")
    Page<UserHulk> typeHeadSearchPage(@Param("query") String query, Pageable pageable);

    UserHulk findByUserName(String userName);

}
