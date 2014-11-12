package cz.PacMan.main;

import java.util.ArrayList;
import java.util.List;

public class Arraye {
	@SuppressWarnings({ "deprecation", "unchecked" })
	public static <T> List<T> asList(T... a) {
		return new ArrayList<T>(a);
	}
}