package com.elfbar.BenikShop.controllers;


import com.elfbar.BenikShop.controllers.repo.ItemRepository;
import com.elfbar.BenikShop.models.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

@Controller
public class ItemControl {

    @Autowired
    private ItemRepository itemRepository;

    @GetMapping("/item")
    public String item(Model model){
        Iterable<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "shop";
    }



    @GetMapping("/admin-panel")
    public String adminPanel(Model model){
        Iterable<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "admin-panel";
    }

    @GetMapping("/admin-panel/add")
    public String itemAdd(Model model){
        return "item-add";
    }


    @PostMapping("/admin-panel/add")
    public String itemAddDB(@RequestParam String title, @RequestParam String url, @RequestParam String anons, @RequestParam String price, @RequestParam String sale, @RequestParam String cashback, @RequestParam String date,Model model){
        Item item = new Item(title,url,anons,price,sale,cashback,date) ;
        itemRepository.save(item);
        return "redirect:/admin-panel";
    }


    @GetMapping("/admin-panel/{id}")
    public String itemId(@PathVariable(value = "id") long id, Model model){
        Optional<Item> item1 = itemRepository.findById(id);
        ArrayList<Item> shopItem = new ArrayList<>();
        item1.ifPresent(shopItem::add);
        model.addAttribute("item1", shopItem);
        return "item-details";
    }

    @GetMapping("/admin-panel/{id}/edit")
    public String itemEdit(@PathVariable(value = "id") long id, Model model){
        if (!itemRepository.existsById(id)){
            return "redirect:/item";
        }


        Optional<Item> item1 = itemRepository.findById(id);
        ArrayList<Item> shopItem = new ArrayList<>();
        item1.ifPresent(shopItem::add);
        model.addAttribute("item1", shopItem);
        return "item-edit";
    }


    @PostMapping("/admin-panel/{id}/edit")
    public String itemUpdate(@PathVariable(value = "id") long id, @RequestParam String title, @RequestParam String anons, @RequestParam String price, @RequestParam String url,  @RequestParam String sale, @RequestParam String cashback, @RequestParam String date, Model model){
        Item item = itemRepository.findById(id).orElseThrow(null);
        item.setTitle(title);
        item.setAnons(anons);
        item.setPrice(price);
        item.setUrl(url);
        item.setSale(sale);
        item.setCashback(cashback);
        item.setDate(date);
        itemRepository.save(item);

        return "redirect:/admin-panel";
    }

    @PostMapping("/admin-panel/{id}/remove")
    public String itemDelete(@PathVariable(value = "id") long id, Model model){
        Item item = itemRepository.findById(id).orElseThrow(null);
        itemRepository.delete(item);

        return "redirect:/admin-panel";
    }
}
