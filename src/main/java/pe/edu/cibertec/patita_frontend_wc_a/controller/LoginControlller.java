package pe.edu.cibertec.patita_frontend_wc_a.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.WebClient;
import pe.edu.cibertec.patita_frontend_wc_a.dto.LoginResponseDTO;
import pe.edu.cibertec.patita_frontend_wc_a.dto.LoginResquestDTO;
import pe.edu.cibertec.patita_frontend_wc_a.viewmodel.LoginModel;
import reactor.core.publisher.Mono;

@Controller
@RequestMapping("/login")
public class LoginControlller {

    @Autowired
    WebClient webClientAutenticacion;

    @GetMapping("/inicio")
    public String inicio(Model model) {
        LoginModel loginModel = new LoginModel("00", "", "");
        model.addAttribute("loginModel", loginModel);
        return "inicio";
    }

    @PostMapping("/autenticar")
    public String autenticar(@RequestParam("tipoDocumento") String tipoDocumento,
                             @RequestParam("numeroDocumento") String numeroDocumento,
                             @RequestParam("password") String password,
                             Model model) {

        //validar campos de entrada
        if (tipoDocumento == null || tipoDocumento.trim().length() == 0 ||
                numeroDocumento == null || numeroDocumento.trim().length() == 0 ||
                password == null || password.trim().length() == 0) {
            LoginModel loginModel = new LoginModel("01", "Ingrese sus datos correctamente", "Raul Flores");
            model.addAttribute("loginModel", loginModel);
            return "inicio";
        }

         try {


             LoginResquestDTO loginRequestDTO = new LoginResquestDTO(tipoDocumento, numeroDocumento, password);


             Mono<LoginResponseDTO> monoLoginResponseDTO = webClientAutenticacion.post()
                     .uri("/login")
                     .body(Mono.just(loginRequestDTO),LoginResquestDTO.class)
                      .retrieve()
                     .bodyToMono(LoginResponseDTO.class);

             LoginResponseDTO loginResponseDTO = monoLoginResponseDTO.block();


             if(loginResponseDTO.codigo().equals("00")){
                 LoginModel loginModel = new LoginModel("00", "", loginResponseDTO.nombreUsuario());
                 model.addAttribute("loginModel", loginModel);
                 return "principal";

             }
             else {
                 LoginModel loginModel = new LoginModel("00", "Error", "");
                 model.addAttribute("loginModel", loginModel);
                 return "inicio";
             }

         }catch (Exception e){
             LoginModel loginModel = new LoginModel("99", "Ha ocurrido un problema", "");
             model.addAttribute("loginModel", loginModel);
             System.out.println(e.getMessage());
             return "inicio";
         }

    }

    }

