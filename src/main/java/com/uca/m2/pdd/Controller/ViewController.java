package com.uca.m2.pdd.Controller;

import com.uca.m2.pdd.Model.dto.AnnonceDto;
import com.uca.m2.pdd.Service.AnnonceService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
public class ViewController {

    private final AnnonceService annonceService;

    public ViewController(AnnonceService annonceService) {
        this.annonceService = annonceService;
    }
    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = (authentication != null) ? authentication.getName() : "Guest";

        model.addAttribute("username", username);
        return "dashboard";
    }

    @GetMapping("/dashboard/annonces/search")
    public String searchAnnoncesOnDashboard(
            @RequestParam(required = false) String etat,
            @RequestParam(required = false) String modeDeRemise,
            @RequestParam(required = false) String motsCles,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateDePublication,
            Model model) {

        // Split motsCles into List<String>
        List<String> motsClesList = (motsCles != null && !motsCles.isEmpty())
                ? List.of(motsCles.split(",")) : null;

        // Fetch results from service
        List<AnnonceDto> filteredAnnonces = annonceService.searchAnnonces(
                etat, modeDeRemise, motsClesList, dateDePublication);

        // Add attributes to model for rendering in Thymeleaf
        model.addAttribute("annonces", filteredAnnonces);
        model.addAttribute("queryEtat", etat);
        model.addAttribute("queryModeDeRemise", modeDeRemise);
        model.addAttribute("queryMotsCles", motsClesList);
        model.addAttribute("queryDateDePublication", dateDePublication);

        return "dashboard"; // Return the updated dashboard
    }

    @GetMapping("/notifications")
    public String showNotificationPage() {
        return "notifications";
    }

    @GetMapping("/messages")
    public String showMessageriePage() {
        return "messages";
    }
}
