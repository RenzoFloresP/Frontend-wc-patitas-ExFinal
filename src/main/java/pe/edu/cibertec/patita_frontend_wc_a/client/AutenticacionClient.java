package pe.edu.cibertec.patita_frontend_wc_a.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pe.edu.cibertec.patita_frontend_wc_a.config.FeingClientConfig;
import pe.edu.cibertec.patita_frontend_wc_a.dto.LoginResponseDTO;
import pe.edu.cibertec.patita_frontend_wc_a.dto.LoginResquestDTO;


@FeignClient(name="autenticacion", url="http://localhost:8081/autenticacion",configuration = FeingClientConfig.class)
public interface AutenticacionClient {
    @PostMapping("/login")
    LoginResponseDTO login(@RequestBody LoginResquestDTO loginResquestDTO);

}
