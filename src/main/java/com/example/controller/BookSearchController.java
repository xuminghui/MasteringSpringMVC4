package com.example.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.entity.Book;
import com.example.repository.BookRepository;

@Controller
public class BookSearchController {
	@Autowired
	private BookRepository bookRepository;


	@RequestMapping("/result")
	public String hello(@RequestParam(defaultValue = "masterSpringMVC4") String search, Model model) {
		Iterable<Book> books = bookRepository.findAll();
		model.addAttribute("books", books);
		model.addAttribute("search", search);
		return "resultPage";
	}

	@RequestMapping(value = "/postSearch", method = RequestMethod.POST)
	public String postSearch(HttpServletRequest request, RedirectAttributes redirectAttributes) {
		String search = request.getParameter("search");
		if (search.toLowerCase().contains("struts")) {
			redirectAttributes.addFlashAttribute("error", "Try using spring instead!");
			return "redirect:/";
		}
		redirectAttributes.addAttribute("search", search);
		return "redirect:result";
	}
	@RequestMapping("/search/{searchType}")
	public ModelAndView search(@PathVariable String searchType, @MatrixVariable List<String> keywords) {
		Iterable<Book> books = bookRepository.findAll();
		ModelAndView modelAndView = new ModelAndView("resultPage");
		modelAndView.addObject("books", books);
		modelAndView.addObject("search", String.join(",", keywords));
		return modelAndView;
	}
}