package com.mu.mvc.rest;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Page {

    private String name;
    private String about;
    private String phone;
    private String website;
    
    private String awards;
    
    private String bb;
    
    private boolean can_post;
    

	public boolean isCan_post() {
		return can_post;
	}

	private Map<String, Object> cover;
    
    public Map<String, Object> getCover() {
		return cover;
	}

	public String getName() {
        return name;
    }
    
    public String getAbout() {
        return about;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public String getWebsite() {
        return website;
    }

	public String getAwards() {
		return awards;
	}

	public String getBb() {
		return bb;
	}
    
}
