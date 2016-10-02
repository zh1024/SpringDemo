package com.jimmy.spring.tx;

import java.util.List;

public interface Cashier {
	public void checkout(List<String> isbns, String username);
}
