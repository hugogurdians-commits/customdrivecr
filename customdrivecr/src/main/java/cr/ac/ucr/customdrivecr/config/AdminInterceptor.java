package cr.ac.ucr.customdrivecr.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AdminInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession(false);

        if (session == null) {
            response.sendRedirect("/login");
            return false;
        }

        Object usuarioLogueado = session.getAttribute("usuarioLogueado");
        Object rolUsuario = session.getAttribute("rolUsuario");

        if (usuarioLogueado == null || rolUsuario == null) {
            response.sendRedirect("/login");
            return false;
        }

        if (!rolUsuario.toString().equalsIgnoreCase("ADMIN")) {
            response.sendRedirect("/seleccion-vehiculo");
            return false;
        }

        return true;
    }
}