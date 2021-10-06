package com.justmediagroup.es.usuario.repository.contract;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.justmediagroup.es.usuario.repository.model.Usuario;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, UUID> {

	@Query(nativeQuery = true, value = "select * from tb_usuario tu where tu.correo = :correo and tu.password= :password")
	public Usuario consultarUsuarioPorCorreo(@Param("correo") String correo, @Param("password") String password);

}
