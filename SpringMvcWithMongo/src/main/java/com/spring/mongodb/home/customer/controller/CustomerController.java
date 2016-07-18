package com.spring.mongodb.home.customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import com.spring.mongodb.home.customer.model.Customer;
import com.spring.mongodb.home.customer.service.CustomerDAO;

@Controller
public class CustomerController {

	@Autowired
	private CustomerDAO customerDao;

	@RequestMapping(value = "/customer", method = RequestMethod.GET)
	public String getCustomerList(ModelMap model) {
		model.addAttribute("customerList", customerDao.listCustomer());
		return "output";
	}

	@RequestMapping(value = "/customer/save", method = RequestMethod.POST)
	public View createCustomer(@ModelAttribute Customer customer, ModelMap model) {
		if (StringUtils.hasText(customer.getId())) {
			customerDao.updateCustomer(customer);
		} else {
			customerDao.addCustomer(customer);
		}
		return new RedirectView("/mongodb/customer");
	}

	@RequestMapping(value = "/customer/delete", method = RequestMethod.GET)
	public View deleteCustomer(@ModelAttribute Customer customer, ModelMap model) {
		customerDao.deleteCustomer(customer);
		return new RedirectView("/mongodb/customer");
	}

}
