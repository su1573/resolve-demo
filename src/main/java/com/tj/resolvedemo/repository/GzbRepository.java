package com.tj.resolvedemo.repository;

import com.tj.resolvedemo.domain.GzbTable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @program: resolve-demo
 * @Date: 2021/3/18 13:57
 * @Author: Mr.SU
 * @Description:
 */
public interface GzbRepository extends JpaRepository<GzbTable, Integer> {
}
