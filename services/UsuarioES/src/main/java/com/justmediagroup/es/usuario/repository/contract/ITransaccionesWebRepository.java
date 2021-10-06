package com.justmediagroup.es.usuario.repository.contract;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.justmediagroup.es.usuario.repository.model.TransaccionesWeb;

@Repository
public interface ITransaccionesWebRepository extends JpaRepository<TransaccionesWeb, UUID>{

	@Query(nativeQuery = true,value = "select * from tb_transaccionesweb tt "
			+ "where tt.usuario_id  = :usuarioId and enlace_web = :enlace limit 1")
	public TransaccionesWeb consultarTransaccionPorUsuarioId(
			@Param("usuarioId") String usuarioId,@Param("enlace") String enlace);
	
}
