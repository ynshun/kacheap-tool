package com.kacheap.util.path;

import java.util.Collection;
import java.util.Map;

import org.springframework.util.AntPathMatcher;

public class PathMatcherUtil {
	private static AntPathMatcher matcher = null;
	static {
		matcher = new AntPathMatcher();
		matcher.setTrimTokens(false);
		matcher.setCaseSensitive(true);
	}

	/**
	 * 实际验证路径匹配权限
	 *
	 * @param pattern 权限url
	 * @param path 访问路径
	 * @return 是否拥有权限
	 */
	public static boolean match(String pattern, String path) {
		return matcher.match(pattern, path);
	}

	/**
	 * 实际验证路径匹配权限
	 *
	 * @param patterns 权限url
	 * @param path 访问路径
	 * @return 是否拥有权限
	 */
	public static boolean matches(Collection<String> patterns, String path) {
		for (String pattern : patterns) {
			if (match(pattern, path)) {
				return true;
			}
		}
		return false;
	}

	
	/**
	 * 根据URL模板和真实URL获取url参数
	 * 
	 * @param pattern 
	 * @param path
	 * @return
	 */
	public static Map<String, String> extractUriTemplateVariables(String pattern, String path) {
		return matcher.extractUriTemplateVariables(pattern, path);
	}

	
	public static void main(String[] args) {
		String templeted = "/user/index/{id}/soga";
		String path = "/user/index/1a/soga";
		System.err.println(PathMatcherUtil.match(templeted, path));

		Map<String, String> map = PathMatcherUtil.extractUriTemplateVariables(templeted, path);
		for (String key : map.keySet()) {
			System.err.println(key + " = " + map.get(key));
		}
	}
}