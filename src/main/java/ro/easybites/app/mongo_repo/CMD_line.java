package ro.easybites.app.mongo_repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ro.easybites.app.model.Codes;
import ro.easybites.app.model.Ingredient;
import ro.easybites.app.model.Reteta;
import ro.easybites.app.model.SimpleUser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Component
public class CMD_line implements CommandLineRunner {

    @Autowired
    private MongoRepo repo;

    @Override
    public void run(String... args) throws Exception {
//        repo.deleteAll();
        List<Reteta> lr = new ArrayList<>();
        // FELURI PRINCIPALE

//PUI

//Pui umplut cu dovlegel;

        ArrayList<String> s = new ArrayList<>();
        s.add("/img/uploads/retete/pui_umplut_cu_dovlecel_la_cuptor1.jpg");
        s.add("/img/uploads/retete/pui_umplut_cu_dovlecel_la_cuptor2.jpg");
        s.add("/img/uploads/retete/pui_umplut_cu_dovlecel_la_cuptor3.jpg");

        ArrayList<Ingredient> i = new ArrayList<>();
        i.add(new Ingredient("pui", "pui", "/img/uploads/ingrediente/piept_de_pui.jpg", 1, "buc", 300.00));
        i.add(new Ingredient("dovlecel", "dovlecel 100", "/img/uploads/ingrediente/dovlecel.jpg", 2, "buc", 200.00));
        i.add(new Ingredient("roșii cherry", "roșii cherry", "/img/uploads/ingrediente/rosii_cherry.jpg", 100, "g", 100.00));
        i.add(new Ingredient("mozzarella", "mozzarella", "/img/uploads/ingrediente/mozarella.jpg", 4, "buc", 80.00));
        i.add(new Ingredient("pudră de usturoi", "pudră de usturoi", "/img/uploads/ingrediente/usturoi_pudra.jpg", 1, "buc", 2.00));
        i.add(new Ingredient("boia dulce", "boia dulce", "/img/uploads/ingrediente/boia_dulce.jpg", 1, "buc", 2.00));
        i.add(new Ingredient("busuioc uscat", "busuioc uscact", "/img/uploads/ingrediente/busuioc_uscat.jpg", 1, "buc", 2.00));
        i.add(new Ingredient("usturoi", "usturoi", "/img/uploads/ingrediente/usturoi.jpg", 2, "buc", 20.00));

        Reteta r = new Reteta("Pui Umplut cu Dovlecel la Cuptor", "pui cu dovlecel", "main", s, "45 min", "mancare", "pui", "Toate produsele livrate de Easy Bites (OC EASYFOODS SRL) pot conține sau conțin urme de următorii alergeni: gluten, crustacee, ouă, pește, arahide și produse derivate, soia și produse derivate, lactate, fructe cu coajă, țelină, muștar, semințe de susan, sulfiți.", 0, 0.0, "0", "Pentru un prânz ușor de pregătit și mai ales sănătos.", 0.0, i);
        lr.add(r);

//Buffalo Chicken

        s = new ArrayList<>();
        s.add("/img/uploads/retete/buffalo_chicken1.jpg");
        s.add("/img/uploads/retete/buffalo_chicken2.jpg");

        i = new ArrayList<>();
        i.add(new Ingredient("pui", "pui", "/img/uploads/ingrediente/piept_de_pui.jpg", 1, "buc", 300.00));
        i.add(new Ingredient("cartofi", "cartofi", "/img/uploads/ingrediente/cartofi.jpg", 400, "g", 400.00));
        i.add(new Ingredient("suc roșii", "suc roșii 100", "/img/uploads/ingrediente/suc_de_rosii.jpg", 1, "buc", 100.00));
        i.add(new Ingredient("lapte", "lapte 40", "/img/uploads/ingrediente/lapte.jpg", 1, "buc", 40.00));
        i.add(new Ingredient("unt", "unt 10", "/img/uploads/ingrediente/unt.jpg", 2, "buc", 20.00));
        i.add(new Ingredient("usturoi", "usturoi 10", "/img/uploads/ingrediente/usturoi.jpg", 2, "buc", 20.00));
        i.add(new Ingredient("ceapă crocantă", "ceapă crocantă 10", "/img/uploads/ingrediente/ceapa_crocanta.jpg", 1, "buc", 10.00));
        i.add(new Ingredient("pătrunje verdel", "patrunjel verde 2", "/img/uploads/ingrediente/patrunjel_verde.jpg", 1, "buc", 2.00));

        r = new Reteta("Buffalo Bites", "buffalo bites", "main", s, "35 min", "mancare", "pui", "Toate produsele livrate de Easy Bites (OC EASYFOODS SRL) pot conține sau conțin urme de următorii alergeni: gluten, crustacee, ouă, pește, arahide și produse derivate, soia și produse derivate, lactate, fructe cu coajă, țelină, muștar, semințe de susan, sulfiți.", 0, 0.0, "0", "Rețetă de diner american, la fel de gustoasă servită și în sufrageria românească.", 0.0, i);
        lr.add(r);

//Chicken Caprese


        s = new ArrayList<>();
        s.add("/img/uploads/retete/chicken_caprese1.jpg");
        s.add("/img/uploads/retete/chicken_caprese2.jpg");

        i = new ArrayList<>();
        i.add(new Ingredient("pui", "pui 300", "/img/uploads/ingrediente/piept_de_pui.jpg", 1, "buc", 300.00));
        i.add(new Ingredient("roșii cherry", "roșii cherry", "/img/uploads/ingrediente/rosii_cherry.jpg", 300, "g", 300.00));
        i.add(new Ingredient("usturoi", "usturoi 10", "/img/uploads/ingrediente/usturoi.jpg", 1, "buc", 10.00));
        i.add(new Ingredient("oțet balsamic", "oțet balsamic 10", "/img/uploads/ingrediente/otet_balsamic.jpg", 1, "buc", 10.00));
        i.add(new Ingredient("busuioc uscat", "busuioc uscat 2", "/img/uploads/ingrediente/busuioc_uscat.jpg", 1, "buc", 2.00));
        i.add(new Ingredient("mozzarella", "mozzarella 80", "/img/uploads/ingrediente/mozarella.jpg", 1, "buc", 80.00));


        r = new Reteta("Italiano Vero", "chicken caprese", "main", s, "45 min", "mancare", "pui", "Toate produsele livrate de Easy Bites (OC EASYFOODS SRL) pot conține sau conțin urme de următorii alergeni: gluten, crustacee, ouă, pește, arahide și produse derivate, soia și produse derivate, lactate, fructe cu coajă, țelină, muștar, semințe de susan, sulfiți.", 0, 0.0, "0", "Rețetă de pui italian, numai bună pentru o seară specială.", 0.0, i);
        lr.add(r);

//Tigaie de pui și legume


        s = new ArrayList<>();
        s.add("/img/uploads/retete/tigaie_de_pui_si_legume1.jpg");
        s.add("/img/uploads/retete/tigaie_de_pui_si_legume2.jpg");

        i = new ArrayList<>();
        i.add(new Ingredient("pui", "pui 300", "/img/uploads/ingrediente/piept_de_pui.jpg", 1, "buc", 300.00));
        i.add(new Ingredient("dovlecel", "dovlecel 120", "/img/uploads/ingrediente/dovlecel.jpg", 1, "buc", 120.00));
        i.add(new Ingredient("broccoli", "broccoli", "/img/uploads/ingrediente/broccoli.jpg", 120, "g", 120.00));
        i.add(new Ingredient("ardei gras", "ardei gras", "/img/uploads/ingrediente/ardei_gras.jpg", 1, "buc", 120.00));
        i.add(new Ingredient("roșii cherry", "roșii cherry", "/img/uploads/ingrediente/rosii_cherry.jpg", 100, "g", 100.00));
        i.add(new Ingredient("suc rosii", "suc rosii 100", "/img/uploads/ingrediente/suc_de_rosii.jpg", 2, "buc", 200.00));
        i.add(new Ingredient("usturoi", "usturoi 10", "/img/uploads/ingrediente/usturoi.jpg", 2, "buc", 20.00));
        i.add(new Ingredient("rozmarin", "rozmarin 2", "/img/uploads/ingrediente/rozmarin_proaspat.jpg", 1, "buc", 2.00));


        r = new Reteta("Tigaie de Pui și Legume", "tigaie de pui și legume", "main", s, "45 min", "mancare", "pui", " Toate produsele livrate de Easy Bites (OC EASYFOODS SRL) pot conține sau conțin urme de următorii alergeni: gluten, crustacee, ouă, pește, arahide și produse derivate, soia și produse derivate, lactate, fructe cu coajă, țelină, muștar, semințe de susan, sulfiți.", 0, 0.0, "0", "Cine a spus că dietetic nu poate însemna și delicios? Tigaia de pui și legume este prânzul perfect de luat la birou.", 0.0, i);
        lr.add(r);

//Pui Piperat cu Piure


        s = new ArrayList<>();
        s.add("/img/uploads/retete/pui_piperat_cu_piure1.jpg");
        s.add("/img/uploads/retete/pui_piperat_cu_piure2.jpg");

        i = new ArrayList<>();
        i.add(new Ingredient("pui", "pui 300", "/img/uploads/ingrediente/piept_de_pui.jpg", 1, "buc", 300.00));
        i.add(new Ingredient("cartofi", "cartofi", "/img/uploads/ingrediente/cartofi.jpg", 400, "g", 400.00));
        i.add(new Ingredient("unt", "unt 10", "/img/uploads/ingrediente/unt.jpg", 2, "buc", 20.00));
        i.add(new Ingredient("lapte", "lapte 40", "/img/uploads/ingrediente/lapte.jpg", 1, "buc", 40.00));
        i.add(new Ingredient("piper verde", "piper verde 20", "/img/uploads/ingrediente/piper_verde.jpg", 1, "buc", 20.00));
        i.add(new Ingredient("boia ardei dulce", "boia ardei dulce 2", "/img/uploads/ingrediente/boia_dulce.jpg", 1, "buc", 2.00));
        i.add(new Ingredient("usturoi pudra", "usturoi pudra 2", "/img/uploads/ingrediente/usturoi_pudra.jpg", 1, "buc", 2.00));
        i.add(new Ingredient("cimbrișor", "cimbrișor 2", "/img/uploads/ingrediente/cimbrisor.jpg", 1, "buc", 2.00));
        i.add(new Ingredient("smântână de gătit", "smântână de gătit 80", "/img/uploads/ingrediente/smantana_pentru_gatit.jpg", 1, "buc", 80.00));


        r = new Reteta("Pui Piperat cu Piure", "pui piperat cu piure", "main", s, "35 min", "mancare", "pui", " Toate produsele livrate de Easy Bites (OC EASYFOODS SRL) pot conține sau conțin urme de următorii alergeni: gluten, crustacee, ouă, pește, arahide și produse derivate, soia și produse derivate, lactate, fructe cu coajă, țelină, muștar, semințe de susan, sulfiți.", 0, 0.0, "0", "Această rețetă clasică a fost transformată printr-un twist simplu într-o rețetă classy.", 0.0, i);
        lr.add(r);

//PASTE

//Easy Bolognese


        s = new ArrayList<>();
        s.add("/img/uploads/retete/paste_bolognese1.jpg");
        s.add("/img/uploads/retete/paste_bolognese2.jpg");

        i = new ArrayList<>();
        i.add(new Ingredient("paste", "paste", "/img/uploads/ingrediente/paste.jpg", 200, "g", 200.00));
        i.add(new Ingredient("carne de vita tocată", "vita tocata 200", "/img/uploads/ingrediente/carne_tocata_vita.jpg", 1, "buc", 200.00));
        i.add(new Ingredient("suc roșii", "suc roșii 100", "/img/uploads/ingrediente/suc_de_rosii.jpg", 2, "buc", 200.00));
        i.add(new Ingredient("roșii cherry", "roșii cherry", "/img/uploads/ingrediente/rosii_cherry.jpg", 100, "g", 100.00));
        i.add(new Ingredient("parmezan", "parmezan 20", "/img/uploads/ingrediente/parmezan.jpg", 2, "buc", 40.00));
        i.add(new Ingredient("usturoi", "usturoi 10", "/img/uploads/ingrediente/usturoi.jpg", 1, "buc", 10.00));
        i.add(new Ingredient("morcov", "morcov", "/img/uploads/ingrediente/morcov.jpg", 1, "buc", 120.00));


        r = new Reteta("Easy Bolognese", "paste bolognese", "main", s, "30 min", "mancare", "paste", " Toate produsele livrate de Easy Bites (OC EASYFOODS SRL) pot conține sau conțin urme de următorii alergeni: gluten, crustacee, ouă, pește, arahide și produse derivate, soia și produse derivate, lactate, fructe cu coajă, țelină, muștar, semințe de susan, sulfiți.", 0, 0.0, "0", "Nu putem avea Carbonara fără Bolognese, așa prevede legea.", 0.0, i);
        lr.add(r);

//Easy Carbonara

        s = new ArrayList<>();
        s.add("/img/uploads/retete/paste_carbonara1.jpg");
        s.add("/img/uploads/retete/paste_carbonara2.jpg");

        i = new ArrayList<>();
        i.add(new Ingredient("paste", "paste", "/img/uploads/ingrediente/paste.jpg", 200, "g", 200.00));
        i.add(new Ingredient("bacon afumat", "bacon afumat 250", "/img/uploads/ingrediente/bacon_afumat.jpg", 1, "buc", 250.00));
        i.add(new Ingredient("smântână de gătit", "smântână de gătit 100", "/img/uploads/ingrediente/smantana_pentru_gatit.jpg", 1, "buc", 100.00));
        i.add(new Ingredient("parmezan", "parmezan 20", "/img/uploads/ingrediente/parmezan.jpg", 1, "buc", 20.00));


        r = new Reteta("Easy Carbonara", "paste carbonara", "main", s, "20 min", "mancare", "paste", " Toate produsele livrate de Easy Bites (OC EASYFOODS SRL) pot conține sau conțin urme de următorii alergeni: gluten, crustacee, ouă, pește, arahide și produse derivate, soia și produse derivate, lactate, fructe cu coajă, țelină, muștar, semințe de susan, sulfiți.", 0, 0.0, "0", "Paste clasic italienești, garantat să vă lase gura apă doar citind rețeta.", 0.0, i);
        lr.add(r);

//Paste cu Branza


        s = new ArrayList<>();
        s.add("/img/uploads/retete/paste_cu_branza1.jpg");
        s.add("/img/uploads/retete/paste_cu_branza2.jpg");

        i = new ArrayList<>();
        i.add(new Ingredient("paste", "paste", "/img/uploads/ingrediente/paste.jpg", 200, "g", 200.00));
        i.add(new Ingredient("smântână de gătit", "smântână de gătit 100", "/img/uploads/ingrediente/smantana_pentru_gatit.jpg", 1, "buc", 100.00));
        i.add(new Ingredient("parmezan", "parmezan 20", "/img/uploads/ingrediente/parmezan.jpg", 1, "buc", 20.00));
        i.add(new Ingredient("gouda", "gouda 150", "/img/uploads/ingrediente/gouda.jpg", 1, "buc", 150.00));


        r = new Reteta("Paste say Cheese!", "paste cu branza", "main", s, "12 min", "mancare", "paste", " Toate produsele livrate de Easy Bites (OC EASYFOODS SRL) pot conține sau conțin urme de următorii alergeni: gluten, crustacee, ouă, pește, arahide și produse derivate, soia și produse derivate, lactate, fructe cu coajă, țelină, muștar, semințe de susan, sulfiți.", 0, 0.0, "0", "Paste cu brânză cu gust de copilărie.", 0.0, i);
        lr.add(r);

//Paste cu Capere si Rosii Uscate


        s = new ArrayList<>();
        s.add("/img/uploads/retete/paste_cu_capere_si_rosii_uscate1.jpg");
        s.add("/img/uploads/retete/paste_cu_capere_si_rosii_uscate2.jpg");
        s.add("/img/uploads/retete/paste_cu_capere_si_rosii_uscate3.jpg");

        i = new ArrayList<>();
        i.add(new Ingredient("paste", "paste", "/img/uploads/ingrediente/paste.jpg", 250, "g", 250.00));
        i.add(new Ingredient("capere", "capere 40", "/img/uploads/ingrediente/capere.jpg", 1, "buc", 40.00));
        i.add(new Ingredient("suc roșii", "suc roșii 100", "/img/uploads/ingrediente/suc_de_rosii.jpg", 2, "buc", 200.00));
        i.add(new Ingredient("roșii uscate", "roșii uscate 60", "/img/uploads/ingrediente/rosii_uscate.jpg", 1, "buc", 60.00));
        i.add(new Ingredient("usturoi", "usturoi 10", "/img/uploads/ingrediente/usturoi.jpg", 2, "buc", 20.00));


        r = new Reteta("Paste cu Capere și Roșii Uscate", "paste cu capere și roșii uscate", "main", s, "15 min", "mancare", "paste", " Toate produsele livrate de Easy Bites (OC EASYFOODS SRL) pot conține sau conțin urme de următorii alergeni: gluten, crustacee, ouă, pește, arahide și produse derivate, soia și produse derivate, lactate, fructe cu coajă, țelină, muștar, semințe de susan, sulfiți.", 0, 0.0, "0", "Comfort food pentru o cină leneșă.", 0.0, i);
        lr.add(r);

//Paste cu sos pesto si broccoli


        s = new ArrayList<>();
        s.add("/img/uploads/retete/paste_cu_sos_pesto_si_broccoli1.jpg");
        s.add("/img/uploads/retete/paste_cu_sos_pesto_si_broccoli2.jpg");

        i = new ArrayList<>();
        i.add(new Ingredient("paste", "paste", "/img/uploads/ingrediente/paste.jpg", 200, "g", 200.00));
        i.add(new Ingredient("pui", "pui 150", "/img/uploads/ingrediente/piept_de_pui.jpg", 1, "buc", 150.00));
        i.add(new Ingredient("broccoli", "broccoli", "/img/uploads/ingrediente/broccoli.jpg", 130, "g", 130.00));
        i.add(new Ingredient("roșii uscate", "roșii uscate 50", "/img/uploads/ingrediente/rosii_uscate.jpg", 1, "buc", 50.00));
        i.add(new Ingredient("feta", "feta 60", "/img/uploads/ingrediente/feta.jpg", 60, "g", 120.00));
        i.add(new Ingredient("sos pesto", "sos pesto 50", "/img/uploads/ingrediente/sos_pesto.jpg", 1, "buc", 50.00));
        i.add(new Ingredient("unt", "unt 10", "/img/uploads/ingrediente/unt.jpg", 2, "buc", 20.00));


        r = new Reteta("Paste cu Pui și Mult Verde", "paste cu sos pesto si broccoli", "main", s, "30 min", "mancare", "paste", " Toate produsele livrate de Easy Bites (OC EASYFOODS SRL) pot conține sau conțin urme de următorii alergeni: gluten, crustacee, ouă, pește, arahide și produse derivate, soia și produse derivate, lactate, fructe cu coajă, țelină, muștar, semințe de susan, sulfiți.", 0, 0.0, "0", "Rețeta aceasta reușește să facă până și broccoli-ul să dispară din farfurie.", 0.0, i);
        lr.add(r);

//PORC

//Classic Pork Burger


        s = new ArrayList<>();
        s.add("/img/uploads/retete/classic_pork_burger1.jpg");
        s.add("/img/uploads/retete/classic_pork_burger2.jpg");

        i = new ArrayList<>();
        i.add(new Ingredient("carne de porc tocată", "porc tocat 200", "/img/uploads/ingrediente/carne_tocata_porc.jpg", 1, "buc", 200.00));
        i.add(new Ingredient("roșii", "roșii", "/img/uploads/ingrediente/rosii.jpg", 1, "buc", 80.00));
        i.add(new Ingredient("ceapă albă", "ceapă albă", "/img/uploads/ingrediente/ceapa_alba.jpg", 1, "buc", 40.00));
        i.add(new Ingredient("baby spanac", "baby spanac 20", "/img/uploads/ingrediente/baby_spanac.jpg", 20, "g", 20.00));
        i.add(new Ingredient("sos burger", "sos burger 30", "/img/uploads/ingrediente/sos_burger.jpg", 1, "buc", 30.00));
        i.add(new Ingredient("castraveți murați", "castraveți murați 40", "/img/uploads/ingrediente/castravete_murat.jpg", 1, "buc", 40.00));
        i.add(new Ingredient("brânză chedar", "branza chedar 20", "/img/uploads/ingrediente/cheddar.jpg", 2, "buc", 40.00));
        i.add(new Ingredient("chiflă burger", "chiflă burger", "/img/uploads/ingrediente/chifla_burger.jpg", 2, "buc", 200.00));


        r = new Reteta("Burger Delicios", "classic pork burger", "main", s, "20 min", "mancare", "porc", " Toate produsele livrate de Easy Bites (OC EASYFOODS SRL) pot conține sau conțin urme de următorii alergeni: gluten, crustacee, ouă, pește, arahide și produse derivate, soia și produse derivate, lactate, fructe cu coajă, țelină, muștar, semințe de susan, sulfiți.", 0, 0.0, "0", "Un clasic cheat meal după o săptămână grea.", 0.0, i);
        lr.add(r);

//Cotlet de Porc cu Cartofi si Muraturi


        s = new ArrayList<>();
        s.add("/img/uploads/retete/cotlet_de_porc_cu_mirodenii1.jpg");
        s.add("/img/uploads/retete/cotlet_de_porc_cu_mirodenii2.jpg");


        i = new ArrayList<>();
        i.add(new Ingredient("cotlet de porc cu os", "cotlet de porc cu os 300", "/img/uploads/ingrediente/cotlet_de_porc_cu_os.jpg", 1, "buc", 300.00));
        i.add(new Ingredient("rozmarin", "rozmarin 2", "/img/uploads/ingrediente/rozmarin_proaspat.jpg", 1, "buc", 2.00));
        i.add(new Ingredient("pudră de usturoi", "pudră de usturoi", "/img/uploads/ingrediente/usturoi_pudra.jpg", 1, "buc", 2.00));
        i.add(new Ingredient("boia dulce", "boia dulce", "/img/uploads/ingrediente/boia_dulce.jpg", 1, "buc", 2.00));
        i.add(new Ingredient("castraveți murați", "castraveți murați 100", "/img/uploads/ingrediente/castravete_murat.jpg", 1, "buc", 100.00));
        i.add(new Ingredient("cotlet de porc cu os", "cotlet de porc cu os 300", "/img/uploads/ingrediente/cotlet_de_porc_cu_os.jpg", 1, "buc", 300.00));
        i.add(new Ingredient("unt", "unt 10", "/img/uploads/ingrediente/unt.jpg", 2, "buc", 20.00));
        i.add(new Ingredient("lapte", "lapte 40", "/img/uploads/ingrediente/lapte.jpg", 1, "buc", 40.00));


        r = new Reteta("Cotlet de Porc cu Cartofi și Murături", "cotlet de porc cu mirodenii", "main", s, "35 min", "mancare", "porc", " Toate produsele livrate de Easy Bites (OC EASYFOODS SRL) pot conține sau conțin urme de următorii alergeni: gluten, crustacee, ouă, pește, arahide și produse derivate, soia și produse derivate, lactate, fructe cu coajă, țelină, muștar, semințe de susan, sulfiți.", 0, 0.0, "0", "Ce comanzi de fiecare dată când ești în vacanță la munte, de data asta de acasă.", 0.0, i);
        lr.add(r);

//Cotlet de Porc cu Legume Balsamizate


        s = new ArrayList<>();
        s.add("/img/uploads/retete/cotlet_de_porc_cu_legume_balsamizate1.jpg");
        s.add("/img/uploads/retete/cotlet_de_porc_cu_legume_balsamizate2.jpg");

        i = new ArrayList<>();
        i.add(new Ingredient("cotlet de porc cu os", "cotlet de porc cu os 300", "/img/uploads/ingrediente/cotlet_de_porc_cu_os.jpg", 1, "buc", 300.00));
        i.add(new Ingredient("pătrunje verdel", "patrunjel verde 2", "/img/uploads/ingrediente/patrunjel_verde.jpg", 1, "buc", 2.00));
        i.add(new Ingredient("roșii cherry", "roșii cherry", "/img/uploads/ingrediente/rosii_cherry.jpg", 200, "g", 200.00));
        i.add(new Ingredient("ciuperci", "ciuperci 200", "/img/uploads/ingrediente/ciuperci.jpg", 200, "g", 200.00));
        i.add(new Ingredient("oțet balsamic", "oțet balsamic 20", "/img/uploads/ingrediente/otet_balsamic.jpg", 1, "buc", 20));
        i.add(new Ingredient("usturoi", "usturoi", "/img/uploads/ingrediente/usturoi.jpg", 1, "buc", 10.00));
        i.add(new Ingredient("pătrunje verdel", "patrunjel verde 2", "/img/uploads/ingrediente/patrunjel_verde.jpg", 1, "buc", 2.00));

        r = new Reteta("Friptură de Porc cu Legume Balsamizate", "cotlet de porc cu legume balsamizate", "main", s, "25 min", "mancare", "porc", " Toate produsele livrate de Easy Bites (OC EASYFOODS SRL) pot conține sau conțin urme de următorii alergeni: gluten, crustacee, ouă, pește, arahide și produse derivate, soia și produse derivate, lactate, fructe cu coajă, țelină, muștar, semințe de susan, sulfiți.", 0, 0.0, "0", "Am adăugat și roșiile balsamizate pentru a o face să pară o salată.", 0.0, i);
        lr.add(r);

///Mancare de Mazare


        s = new ArrayList<>();
        s.add("/img/uploads/retete/mancare_de_mazare1.jpg");
        s.add("/img/uploads/retete/mancare_de_mazare2.jpg");
        s.add("/img/uploads/retete/mancare_de_mazare3.jpg");

        i = new ArrayList<>();
        i.add(new Ingredient("usturoi", "usturoi", "/img/uploads/ingrediente/usturoi.jpg", 2, "buc", 20.00));
        i.add(new Ingredient("carne de porc tocată", "porc tocat 200", "/img/uploads/ingrediente/carne_tocata_porc.jpg", 1, "buc", 200.00));
        i.add(new Ingredient("morcov", "morcov", "/img/uploads/ingrediente/morcov.jpg", 1, "buc", 100.00));
        i.add(new Ingredient("ceapă albă", "ceapă albă", "/img/uploads/ingrediente/ceapa_alba.jpg", 1, "buc", 100.00));
        i.add(new Ingredient("mazăre", "mazăre 260", "/img/uploads/ingrediente/mazare.jpg", 1, "buc", 260.00));
        i.add(new Ingredient("cimbrișor", "cimbrișor 2", "/img/uploads/ingrediente/cimbrisor.jpg", 1, "buc", 2.00));
        i.add(new Ingredient("pastă tomate", "pastă tomate 40", "/img/uploads/ingrediente/pasta_tomate.jpg", 1, "buc", 40.00));
        i.add(new Ingredient("boia dulce", "boia dulce", "/img/uploads/ingrediente/boia_dulce.jpg", 1, "buc", 2.00));


        r = new Reteta("Mâncărică de mazăre", "mancare de mazare", "main", s, "20 min", "mancare", "porc", " Toate produsele livrate de Easy Bites (OC EASYFOODS SRL) pot conține sau conțin urme de următorii alergeni: gluten, crustacee, ouă, pește, arahide și produse derivate, soia și produse derivate, lactate, fructe cu coajă, țelină, muștar, semințe de susan, sulfiți.", 0, 0.0, "0", "Mâncărică de mazăre, fix cum o făcea mama.", 0.0, i);
        lr.add(r);


//Snitel cu Cartofi Wedges


        s = new ArrayList<>();
        s.add("/img/uploads/retete/snitel_cu_cartofi_wedges1.jpg");
        s.add("/img/uploads/retete/snitel_cu_cartofi_wedges2.jpg");
        s.add("/img/uploads/retete/snitel_cu_cartofi_wedges3.jpg");

        i = new ArrayList<>();
        i.add(new Ingredient("cotlet de porc cu os", "cotlet de porc cu os 300", "/img/uploads/ingrediente/cotlet_de_porc_cu_os.jpg", 1, "buc", 300.00));
        i.add(new Ingredient("rozmarin", "rozmarin 2", "/img/uploads/ingrediente/rozmarin_proaspat.jpg", 1, "buc", 2.00));
        i.add(new Ingredient("pudră de usturoi", "pudră de usturoi", "/img/uploads/ingrediente/usturoi_pudra.jpg", 1, "buc", 2.00));
        i.add(new Ingredient("cartofi", "cartofi", "/img/uploads/ingrediente/cartofi.jpg", 300, "g", 300.00));
        i.add(new Ingredient("pesmet", "pesmet 20", "/img/uploads/ingrediente/pesmet.jpg", 4, "buc", 80.00));


        r = new Reteta("Cotlet de Porc cu Cartofi și Murături", " snitel cu cartofi wedges", " main", s, "35min", " mancare", " porc", " Toate produsele livrate de Easy Bites (OC EASYFOODS SRL) pot conține sau conțin urme de următorii alergeni: gluten, crustacee, ouă, pește, arahide și produse derivate, soia și produse derivate, lactate, fructe cu coajă, țelină, muștar, semințe de susan, sulfiți.", 0, 0.0, " 0 ", " Snițel ca la mama acasă. ", 0.0, i);
        lr.add(r);


//VEGETARIENE

//Halloumi Wrap


        s = new ArrayList<>();
        s.add("/img/uploads/retete/halloumi_wrap1.jpg");
        s.add("/img/uploads/retete/halloumi_wrap2.jpg");

        i = new ArrayList<>();
        i.add(new Ingredient("halloumi", "halloumi 200", "/img/uploads/ingrediente/halloumi.jpg", 1, "buc", 200.00));
        i.add(new Ingredient("tortilla wrap", "tortilla wrap", "/img/uploads/ingrediente/trortilla_wrap.jpg", 2, "buc", 200.00));
        i.add(new Ingredient("ceapă roșie", "ceapă roșie 80", "/img/uploads/ingrediente/ceapa_rosie.jpg", 1, "buc", 80.00));
        i.add(new Ingredient("dovlecel", "dovlecel 100", "/img/uploads/ingrediente/dovlecel.jpg", 1, "buc", 100.00));
        i.add(new Ingredient("iaurt", "iaurt 100", "/img/uploads/ingrediente/iaurt.jpg", 1, "buc", 100.00));
        i.add(new Ingredient("usturoi", "usturoi", "/img/uploads/ingrediente/usturoi.jpg", 1, "buc", 10.00));
        i.add(new Ingredient("pătrunje verdel", "patrunjel verde 2", "/img/uploads/ingrediente/patrunjel_verde.jpg", 1, "buc", 2.00));
        i.add(new Ingredient("roșii cherry", "roșii cherry", "/img/uploads/ingrediente/rosii_cherry.jpg", 100, "g", 100.00));


        r = new Reteta("Halloumi Durum", "halloumi wrap", "main", s, "35 min", "mancare", "vegetariene", "Toate produsele livrate de Easy Bites (OC EASYFOODS SRL) pot conține sau conțin urme de următorii alergeni: gluten, crustacee, ouă, pește, arahide și produse derivate, soia și produse derivate, lactate, fructe cu coajă, țelină, muștar, semințe de susan, sulfiți.", 0, 0.0, " 0 ", " Rețetă clasică în orice Doner Shop din Berlin.Cu un twist sănătos.", 0.0, i);
        lr.add(r);


//Halloumi Aurit Cu Miere


        s = new ArrayList<>();
        s.add("/img/uploads/retete/halloumi_aurit_cu_miere1.jpg");
        s.add("/img/uploads/retete/halloumi_aurit_cu_miere2.jpg");


        i = new ArrayList<>();
        i.add(new Ingredient("halloumi", "halloumi 200", "/img/uploads/ingrediente/halloumi.jpg", 1, "buc", 200.00));
        i.add(new Ingredient("miere", "miere 15", "/img/uploads/ingrediente/miere.jpg", 2, "buc", 30.00));
        i.add(new Ingredient("roșii cherry", "roșii cherry", "/img/uploads/ingrediente/rosii_cherry.jpg", 100, "g", 100.00));
        i.add(new Ingredient("oțet balsamic", "oțet balsamic 10", "/img/uploads/ingrediente/otet_balsamic.jpg", 1, "buc", 20.00));
        i.add(new Ingredient("baby spanac", "baby spanac 60", "/img/uploads/ingrediente/baby_spanac.jpg", 60, "g", 60.00));


        r = new Reteta("Halloumi Aurit cu Miere", "halloumi aurit cu miere", "main", s, "30 min", "mancare", "vegetariene", " Toate produsele livrate de Easy Bites (OC EASYFOODS SRL) pot conține sau conțin urme de următorii alergeni: gluten, crustacee, ouă, pește, arahide și produse derivate, soia și produse derivate, lactate, fructe cu coajă, țelină, muștar, semințe de susan, sulfiți.", 0, 0.0, "0", "Gustul trebuie neapărat încercat, asemenea amalgam de arome nu poate fi descris.", 0.0, i);
        lr.add(r);


//Pastai cu Ciuperci


        s = new ArrayList<>();
        s.add("/img/uploads/retete/pastai_cu_ciuperci1.jpg");
        s.add("/img/uploads/retete/pastai_cu_ciuperci2.jpg");

        i = new ArrayList<>();
        i.add(new Ingredient("păstăi de fasole verde", "pastai de fasole verde 300", "/img/uploads/ingrediente/fasole_pastai_verde.jpg", 1, "buc", 300.00));
        i.add(new Ingredient("ciuperci", "ciuperci 200", "/img/uploads/ingrediente/ciuperci.jpg", 200, "g", 200.00));
        i.add(new Ingredient("ceapă albă", "ceapă albă", "/img/uploads/ingrediente/ceapa_alba.jpg", 1, "buc", 80.00));
        i.add(new Ingredient("usturoi", "usturoi", "/img/uploads/ingrediente/usturoi.jpg", 1, "buc", 10.00));
        i.add(new Ingredient("sos soia", "sos soia 20", "/img/uploads/ingrediente/sos_soia.jpg", 1, "buc", 20.00));
        i.add(new Ingredient("lapte", "lapte 60", "/img/uploads/ingrediente/lapte.jpg", 1, "buc", 60.00));
        i.add(new Ingredient("ceapă crocantă", "ceapă crocantă 10", "/img/uploads/ingrediente/ceapa_crocanta.jpg", 2, "buc", 20.00));
        i.add(new Ingredient("făina", "faina 10", "/img/uploads/ingrediente/faina.jpg", 1, "buc", 10.00));

        r = new Reteta("Caserolă de Păstăi", "pastai cu ciuperci", "main", s, "25 min", "mancare", "vegetariene", " Toate produsele livrate de Easy Bites (OC EASYFOODS SRL) pot conține sau conțin urme de următorii alergeni: gluten, crustacee, ouă, pește, arahide și produse derivate, soia și produse derivate, lactate, fructe cu coajă, țelină, muștar, semințe de susan, sulfiți.", 0, 0.0, "0", "Mâncărică făcută de seara, numai bună de încălzit la pranz.", 0.0, i);
        lr.add(r);


//Tofu cu Orez Prajit


        s = new ArrayList<>();
        s.add("/img/uploads/retete/tofu_cu_orez_prajit1.jpg");
        s.add("/img/uploads/retete/tofu_cu_orez_prajit2.jpg");
        s.add("/img/uploads/retete/tofu_cu_orez_prajit3.jpg");

        i = new ArrayList<>();
        i.add(new Ingredient("tofu", "tofu 200", "/img/uploads/ingrediente/tofu.jpg", 1, "buc", 200.00));
        i.add(new Ingredient("orez", "orez 100", "/img/uploads/ingrediente/orez.jpg", 1, "buc", 100.00));
        i.add(new Ingredient("mix de mazăre cu morcov", "mix de mazăre cu morcov 200", "/img/uploads/ingrediente/mix_morcovi_si_mazare.jpg", 1, "buc", 200.00));
        i.add(new Ingredient("usturoi", "usturoi", "/img/uploads/ingrediente/usturoi.jpg", 2, "buc", 20.00));
        i.add(new Ingredient("ulei de susan", "ulei de susan 20", "/img/uploads/ingrediente/ulei_susan.jpg", 1, "buc", 20.00));
        i.add(new Ingredient("sweet chilli", "sweet chilli 40", "/img/uploads/ingrediente/sweet_chilli.jpg", 1, "buc", 40.00));
        i.add(new Ingredient("susan", "susan 4", "/img/uploads/ingrediente/susan.jpg", 1, "buc", 4.00));
        i.add(new Ingredient("pesmet", "pesmet 20", "/img/uploads/ingrediente/pesmet.jpg", 2, "buc", 40.00));
        i.add(new Ingredient("sos soia", "sos soia 20", "/img/uploads/ingrediente/sos_soia.jpg", 1, "buc", 20.00));


        r = new Reteta("Fried Rice Tofu", "tofu cu orez prajit", "main", s, "45 min", "mancare", "vegetariene", " Toate produsele livrate de Easy Bites (OC EASYFOODS SRL) pot conține sau conțin urme de următorii alergeni: gluten, crustacee, ouă, pește, arahide și produse derivate, soia și produse derivate, lactate, fructe cu coajă, țelină, muștar, semințe de susan, sulfiți.", 0, 0.0, "0", "Ceva asiatic, vegetarian, gustos. Varianta prăjită.", 0.0, i);
        lr.add(r);


//Tofu Poke Bowl


        s = new ArrayList<>();
        s.add("/img/uploads/retete/tofu_poke_bowl1.jpg");
        s.add("/img/uploads/retete/tofu_poke_bowl2.jpg");
        s.add("/img/uploads/retete/tofu_poke_bowl3.jpg");

        i = new ArrayList<>();
        i.add(new Ingredient("tofu", "tofu 200", "/img/uploads/ingrediente/tofu.jpg", 1, "buc", 200.00));
        i.add(new Ingredient("păstăi de fasole verde", "pastai de fasole verde 200", "/img/uploads/ingrediente/fasole_pastai_verde.jpg", 1, "buc", 200.00));
        i.add(new Ingredient("orez", "orez 100", "/img/uploads/ingrediente/orez.jpg", 1, "buc", 100.00));
        i.add(new Ingredient("ulei de susan", "ulei de susan 20", "/img/uploads/ingrediente/ulei_susan.jpg", 1, "buc", 20.00));
        i.add(new Ingredient("sos soia", "sos soia 20", "/img/uploads/ingrediente/sos_soia.jpg", 1, "buc", 20.00));
        i.add(new Ingredient("susan", "susan 4", "/img/uploads/ingrediente/susan.jpg", 1, "buc", 4.00));
        i.add(new Ingredient("sweet chilli", "sweet chilli 20", "/img/uploads/ingrediente/sweet_chilli.jpg", 1, "buc", 20.00));
        i.add(new Ingredient("ceapă albă", "ceapă albă", "/img/uploads/ingrediente/ceapa_alba.jpg", 1, "buc", 40.00));
        i.add(new Ingredient("usturoi", "usturoi", "/img/uploads/ingrediente/usturoi.jpg", 2, "buc", 20.00));


        r = new Reteta("Tofu Poke Bowl", "tofu poke bowl", "main", s, "20 min", "mancare", "vegetariene", " Toate produsele livrate de Easy Bites (OC EASYFOODS SRL) pot conține sau conțin urme de următorii alergeni: gluten, crustacee, ouă, pește, arahide și produse derivate, soia și produse derivate, lactate, fructe cu coajă, țelină, muștar, semințe de susan, sulfiți.", 0, 0.0, "0", "Ceva asiatic, vegetarian, gustos. Poate chiar prea sănătos.", 0.0, i);
        lr.add(r);


//VITA


//Poke Bowl


        s = new ArrayList<>();
        s.add("/img/uploads/retete/beef_veggie_poke1.jpg");
        s.add("/img/uploads/retete/beef_veggie_poke2.jpg");
        s.add("/img/uploads/retete/beef_veggie_poke3.jpg");

        i = new ArrayList<>();
        i.add(new Ingredient("vită tocată", "vita tocata 200", "/img/uploads/ingrediente/carne_tocata_vita.jpg", 1, "buc", 200.00));
        i.add(new Ingredient("orez", "orez 120", "/img/uploads/ingrediente/orez.jpg", 1, "buc", 120.00));
        i.add(new Ingredient("zahăr brun", "zahăr brun 5", "/img/uploads/ingrediente/zahar_brun.jpg", 2, "buc", 10.00));
        i.add(new Ingredient("sos hoisin", "sos hoisin 20", "/img/uploads/ingrediente/sos_hoisin.jpg", 1, "buc", 20.00));
        i.add(new Ingredient("sos soia", "sos soia 40", "/img/uploads/ingrediente/sos_soia.jpg", 1, "buc", 40.00));
        i.add(new Ingredient("usturoi", "usturoi", "/img/uploads/ingrediente/usturoi.jpg", 1, "buc", 10.00));
        i.add(new Ingredient("ardei gras", "ardei gras", "/img/uploads/ingrediente/ardei_gras.jpg", 1, "buc", 100.00));
        i.add(new Ingredient("susan", "susan 4", "/img/uploads/ingrediente/susan.jpg", 1, "buc", 4.00));
        i.add(new Ingredient("castravete", "castravete 120", "/img/uploads/ingrediente/castravete.jpg", 1, "buc", 120.00));
        i.add(new Ingredient("oțet de orez", "oțet de orez 30", "/img/uploads/ingrediente/otet_din_orez.jpg", 1, "buc", 30.00));
        i.add(new Ingredient("zahăr alb", "zahar alb 6", "/img/uploads/ingrediente/zahar_alb.jpg", 1, "buc", 6.00));

        r = new Reteta("Poke Bowl", "beef veggie poke", "main", s, "35 min", "mancare", "vita", " Toate produsele livrate de Easy Bites (OC EASYFOODS SRL) pot conține sau conțin urme de următorii alergeni: gluten, crustacee, ouă, pește, arahide și produse derivate, soia și produse derivate, lactate, fructe cu coajă, țelină, muștar, semințe de susan, sulfiți.", 0, 0.0, "0", "Deși de origine asiatică, Poke Bowl-ul a făcut ravagii în Europa. Ți-am găsit o rețetă ușoară și gustoasă care să te scoată din monotonie.", 0.0, i);
        lr.add(r);


//Elevated Nachos


        s = new ArrayList<>();
        s.add("/img/uploads/retete/mancarica_de_vita_mexicana1.jpg");
        s.add("/img/uploads/retete/mancarica_de_vita_mexicana2.jpg");

        i = new ArrayList<>();
        i.add(new Ingredient("vită tocată", "vita tocata 200", "/img/uploads/ingrediente/carne_tocata_vita.jpg", 1, "buc", 200.00));
        i.add(new Ingredient("suc roșii", "suc roșii 100", "/img/uploads/ingrediente/suc_de_rosii.jpg", 2, "buc", 200.00));
        i.add(new Ingredient("roșii cherry", "roșii cherry", "/img/uploads/ingrediente/rosii_cherry.jpg", 60, "g", 60.00));
        i.add(new Ingredient("chimen", "chimen 2", "/img/uploads/ingrediente/chimen.jpg", 1, "buc", 2.00));
        i.add(new Ingredient("oregano", "oregano 2", "/img/uploads/ingrediente/busuioc_uscat.jpg", 1, "buc", 2.00));
        i.add(new Ingredient("usturoi", "usturoi", "/img/uploads/ingrediente/usturoi.jpg", 2, "buc", 20.00));
        i.add(new Ingredient("brânză chedar", "branza chedar 20", "/img/uploads/ingrediente/cheddar.jpg", 2, "buc", 40.00));
        i.add(new Ingredient("ardei gras", "ardei gras", "/img/uploads/ingrediente/ardei_gras.jpg", 1, "buc", 100.00));
        i.add(new Ingredient("nachos", "nachos 200", "/img/uploads/ingrediente/nachos.jpg", 1, "buc", 200.00));
        i.add(new Ingredient("ceapă albă", "ceapă albă", "/img/uploads/ingrediente/ceapa_alba.jpg", 1, "buc", 100.00));


        r = new Reteta("Elevated Nachos", "mancarica de vita mexicana", "main", s, "35 min", "mancare", "vita", " Toate produsele livrate de Easy Bites (OC EASYFOODS SRL) pot conține sau conțin urme de următorii alergeni: gluten, crustacee, ouă, pește, arahide și produse derivate, soia și produse derivate, lactate, fructe cu coajă, țelină, muștar, semințe de susan, sulfiți.", 0, 0.0, "0", "Snack-ul perfect pentru Game Night, dar și pentru mahmureala de a doua zi.", 0.0, i);
        lr.add(r);


//Messy Meatballs


        s = new ArrayList<>();
        s.add("/img/uploads/retete/messy_meatballs1.jpg");
        s.add("/img/uploads/retete/messy_meatballs2.jpg");
        s.add("/img/uploads/retete/messy_meatballs3.jpg");

        i = new ArrayList<>();
        i.add(new Ingredient("vită tocată", "vita tocata 200", "/img/uploads/ingrediente/carne_tocata_vita.jpg", 1, "buc", 200.00));
        i.add(new Ingredient("pesmet", "pesmet 15", "/img/uploads/ingrediente/pesmet.jpg", 2, "buc", 30.00));
        i.add(new Ingredient("sos pesto", "sos pesto 30", "/img/uploads/ingrediente/sos_pesto.jpg", 1, "buc", 30.00));
        i.add(new Ingredient("mozzarella", "mozzarella", "/img/uploads/ingrediente/mozarella.jpg", 4, "buc", 80.00));
        i.add(new Ingredient("suc roșii", "suc roșii 60", "/img/uploads/ingrediente/suc_de_rosii.jpg", 2, "buc", 60.00));
        i.add(new Ingredient("roșii cherry", "roșii cherry", "/img/uploads/ingrediente/rosii_cherry.jpg", 80, "g", 80.00));
        i.add(new Ingredient("chiflă burger", "chiflă burger", "/img/uploads/ingrediente/chifla_burger.jpg", 2, "buc", 200.00));


        r = new Reteta("Messy Meatballs", "messy meatballs", "main", s, "25 min", "mancare", "vita", " Toate produsele livrate de Easy Bites (OC EASYFOODS SRL) pot conține sau conțin urme de următorii alergeni: gluten, crustacee, ouă, pește, arahide și produse derivate, soia și produse derivate, lactate, fructe cu coajă, țelină, muștar, semințe de susan, sulfiți.", 0, 0.0, "0", "Chifteluțe, însă nu ca la mama acasă. Lăsați-vă surprinși!", 0.0, i);
        lr.add(r);


//Stroganoff de vita


        s = new ArrayList<>();
        s.add("/img/uploads/retete/stroganoff_de_vita1.jpg");
        s.add("/img/uploads/retete/stroganoff_de_vita2.jpg");
        s.add("/img/uploads/retete/stroganoff_de_vita3.jpg");

        i = new ArrayList<>();
        i.add(new Ingredient("carne de vita tocată", "vita tocata 200", "/img/uploads/ingrediente/carne_tocata_vita.jpg", 1, "buc", 200.00));
        i.add(new Ingredient("smântână de gătit", "smântână de gătit 100", "/img/uploads/ingrediente/smantana_pentru_gatit.jpg", 1, "buc", 100.00));
        i.add(new Ingredient("ciuperci", "ciuperci 200", "/img/uploads/ingrediente/ciuperci.jpg", 200, "g", 200.00));
        i.add(new Ingredient("unt", "unt 10", "/img/uploads/ingrediente/unt.jpg", 4, "buc", 40.00));
        i.add(new Ingredient("ceapă albă", "ceapă albă", "/img/uploads/ingrediente/ceapa_alba.jpg", 1, "buc", 100.00));
        i.add(new Ingredient("făina", "faina 10", "/img/uploads/ingrediente/faina.jpg", 1, "buc", 10.00));
        i.add(new Ingredient("sos worcester", "sos worcester 30", "/img/uploads/ingrediente/sos_worcester.jpg", 1, "buc", 30.00));
        i.add(new Ingredient("muștar dijon", "muștar dijon 20", "/img/uploads/ingrediente/mustar_dijon.jpg", 1, "buc", 20.00));
        i.add(new Ingredient("usturoi", "usturoi 10", "/img/uploads/ingrediente/usturoi.jpg", 3, "buc", 30.00));
        i.add(new Ingredient("boia ardei dulce", "boia ardei dulce 2", "/img/uploads/ingrediente/boia_dulce.jpg", 1, "buc", 2.00));

        r = new Reteta("Easy Beef Stroganoff", "stroganoff de vita", "main", s, "45 min", "mancare", "vita", " Toate produsele livrate de Easy Bites (OC EASYFOODS SRL) pot conține sau conțin urme de următorii alergeni: gluten, crustacee, ouă, pește, arahide și produse derivate, soia și produse derivate, lactate, fructe cu coajă, țelină, muștar, semințe de susan, sulfiți.", 0, 0.0, "0", "De departe cel mai delicios Stroganoff pe care l-ați mâncat până acum.", 0.0, i);
        lr.add(r);

//        repo.saveAll(lr);
    }
}
