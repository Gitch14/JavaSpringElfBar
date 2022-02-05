package com.elfbar.BenikShop.controllers;


import com.elfbar.BenikShop.controllers.repo.ukraine.odessa.Tav1Repository;
import com.elfbar.BenikShop.models.Ukraine.Odessa.Tav1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class OdessaTavriiControl {

    @Autowired
    private Tav1Repository itemRepository;


    @GetMapping("/admin-panel/od/tav1")
    public String tav1ODadminPanel(Model model){
        Iterable<Tav1> tav1Oitems = itemRepository.findAll();
        model.addAttribute("tav1Oitems", tav1Oitems);
        return "od-tav1";
    }

    @GetMapping("/admin-panel/od/tav1/add")
    public String tav1ODitemAdd(Model model){
        return "od-item-add-tav1";
    }


    @PostMapping("/admin-panel/od/tav1/add")
    public String tav1ODitemAddDB(@RequestParam String title, @RequestParam String url, @RequestParam String anons, @RequestParam String price, @RequestParam String sale, @RequestParam String cashback, @RequestParam String date,Model model){
        Tav1 tav1item = new Tav1(title,url,anons,price,sale,cashback,date);
        itemRepository.save(tav1item);
        return "redirect:/admin-panel/od/tav1";
    }


    @GetMapping("/admin-panel/od/tav1/{id}/edit")
    public String tav1ODitemEdit(@PathVariable(value = "id") long id, Model model){
        if (!itemRepository.existsById(id)){
            return "redirect:/admin-panel/od/tav1";
        }


        Optional<Tav1> tav1ODitem1 = itemRepository.findById(id);
        ArrayList<Tav1> tav1ODshopItem = new ArrayList<>();
        tav1ODitem1.ifPresent(tav1ODshopItem::add);
        model.addAttribute("tav1ODitem1", tav1ODshopItem);
        return "item-edit";
    }


    @PostMapping("/admin-panel/od/tav1/{id}/edit")
    public String tav1ODitemUpdate(@PathVariable(value = "id") long id, @RequestParam String title, @RequestParam String anons, @RequestParam String price, @RequestParam String url,  @RequestParam String sale, @RequestParam String cashback, @RequestParam String date, Model model){
        Tav1 tav1ODitem = itemRepository.findById(id).orElseThrow(null);
        tav1ODitem.setTitle(title);
        tav1ODitem.setAnons(anons);
        tav1ODitem.setPrice(price);
        tav1ODitem.setUrl(url);
        tav1ODitem.setSale(sale);
        tav1ODitem.setCashback(cashback);
        tav1ODitem.setDate(date);
        itemRepository.save(tav1ODitem);

        return "redirect:/admin-panel";
    }

    @PostMapping("/admin-panel/od/tav1/{id}/remove")
    public String tav1ODitemDelete(@PathVariable(value = "id") long id, Model model){
        Tav1 tav1ODitem = itemRepository.findById(id).orElseThrow(null);
        itemRepository.delete(tav1ODitem);

        return "redirect:/admin-panel/tav1";
    }
}
