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

    @GetMapping("/login")
    public String login(Model model){
        Iterable<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "login";
    }

    @GetMapping("/logout")
    public String logOut(Model model){
        Iterable<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "LogOut";
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
    public String itemAddDB(@RequestParam String title, @RequestParam String anons, @RequestParam String price, @RequestParam String url, Model model){
        Item item = new Item(title, anons, price, url);
        itemRepository.save(item);
        return "redirect:/item";
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
    public String itemUpdate(@PathVariable(value = "id") long id, @RequestParam String title, @RequestParam String anons, @RequestParam String price, @RequestParam String url, Model model){
        Item item = itemRepository.findById(id).orElseThrow();
        item.setTitle(title);
        item.setUrl(url);
        item.setAnons(anons);
        item.setPrice(price);
        itemRepository.save(item);

        return "redirect:/admin-panel";
    }

    @PostMapping("/admin-panel/{id}/remove")
    public String itemDelete(@PathVariable(value = "id") long id, Model model){
        Item item = itemRepository.findById(id).orElseThrow();
        itemRepository.delete(item);

        return "redirect:/admin-panel";
    }
}
