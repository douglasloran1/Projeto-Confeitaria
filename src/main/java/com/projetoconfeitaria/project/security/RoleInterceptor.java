package com.projetoconfeitaria.project.security;

import com.projetoconfeitaria.project.enuns.Role;
import com.projetoconfeitaria.project.model.Usuario;
import com.projetoconfeitaria.project.repository.UsuarioRepository;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.UUID;

public class RoleInterceptor implements HandlerInterceptor {

    private static final String HEADER_USER_ID = "X-User-Id";
    private final UsuarioRepository usuarioRepository;

    public RoleInterceptor(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (!(handler instanceof HandlerMethod handlerMethod)) return true;

        RequireRole requireRole = handlerMethod.getMethodAnnotation(RequireRole.class);
        if (requireRole == null) return true;

        String userIdHeader = request.getHeader(HEADER_USER_ID);
        if (userIdHeader == null || userIdHeader.isBlank())
            throw new UnauthenticatedException("Header X-User-Id é obrigatório para este recurso.");

        UUID userId;
        try { userId = UUID.fromString(userIdHeader); }
        catch (IllegalArgumentException ex) { throw new UnauthenticatedException("Header X-User-Id inválido."); }

        Usuario usuario = usuarioRepository.findById(userId)
                .orElseThrow(() -> new UnauthenticatedException("Usuário não encontrado."));

        if (!usuario.isAtivo())
            throw new AccessDeniedException("Usuário inativo não pode acessar este recurso.");

        boolean autorizado = Arrays.asList(requireRole.value()).contains(usuario.getRole());
        if (!autorizado)
            throw new AccessDeniedException("Acesso negado: papel necessário " + Arrays.toString(requireRole.value()) + ", seu papel: " + usuario.getRole());

        return true;
    }
}
