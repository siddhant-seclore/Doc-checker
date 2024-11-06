package com.training.util;

import com.training.entity.Customer;

public class CustomerUtils {
	public static String getConcatanatedName(Customer customer) {
		return  String.join("_",customer.getName().split(" "));
	}
}
