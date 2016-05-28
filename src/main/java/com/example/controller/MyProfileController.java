package com.example.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.filter.USLocalDateFormatter;
import com.example.form.ProfileForm;
import com.example.profile.UserProfileSession;

@Controller
public class MyProfileController {
	/*
	 * @RequestMapping("/profile") public String displayProfile() { return
	 * "profile/profilePage"; }
	 */

	private UserProfileSession userProfileSession;

	@Autowired
	public MyProfileController(UserProfileSession userProfileSession) {
		this.userProfileSession = userProfileSession;
	}

	@ModelAttribute
	public ProfileForm getProfileForm() {
		return userProfileSession.toForm();
	}

	@RequestMapping("/profile")
	public String displayProfile(ProfileForm profileForm) {
		return "profile/profilePage";
	}

	@RequestMapping(value = "/profile", params = { "save" }, method = RequestMethod.POST)
	public String saveProfile(@Valid ProfileForm profileForm, BindingResult bindingResult) {
		System.out.println("save");
		if (bindingResult.hasErrors()) {
			return "profile/profilePage";
		}
		userProfileSession.saveForm(profileForm); 
		
		return "redirect:/search/mixed;keywords=" + String.join(",", profileForm.getTastes()); 
	}

	@ModelAttribute("dateFormat")
	public String localeFormat(Locale locale) {
		return USLocalDateFormatter.getPattern(locale);
	}

	@RequestMapping(value = "/profile", params = { "addTaste" })
	public String addRow(ProfileForm profileForm) {
		System.out.println("addTaste");
		profileForm.getTastes().add(null);
		return "profile/profilePage";
	}

	@RequestMapping(value = "/profile", params = { "removeTaste" })
	public String removeRow(ProfileForm profileForm, HttpServletRequest req) {
		System.out.println("removeTaste");
		Integer rowId = Integer.valueOf(req.getParameter("removeTaste"));
		profileForm.getTastes().remove(rowId.intValue());
		return "profile/profilePage";
	}
}