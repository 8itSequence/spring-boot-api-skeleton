package com.bitsequence.apps.spring.common.entity

import org.springframework.data.jpa.repository.JpaRepository

interface IEntityRepository<T : BaseEntity> : JpaRepository<T, Long>